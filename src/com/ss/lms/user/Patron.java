package com.ss.lms.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.dao.BorrowerDAO;

import com.ss.lms.dao.CopiesDAO;
import com.ss.lms.dao.LoansDAO;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Copies;
import com.ss.lms.entity.Loans;

public class Patron {
	ConnectionUtil conn;

	public Patron() throws ClassNotFoundException {
		conn = new ConnectionUtil();

	}

	public List<Borrower> readBorrower() throws SQLException {

		Connection c = null;
		List<Borrower> listOfBorrowers = null;

		try {
			c = conn.connectDatabase();
			listOfBorrowers = new BorrowerDAO(c).readBorrowers();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get borrowers.");
		} finally {
			c.close();
		}
		return listOfBorrowers;
	}

	public List<Book> getAvailableBooksByBranch(Branch branch) throws SQLException {
		Connection c = null;
		List<Book> listOfBooks = new ArrayList<>();

		try {
			c = conn.connectDatabase();
			List<Copies> listOfCopies = readCopies();
			for (int i = 0; i < listOfCopies.size(); i++) {
				boolean copiesAvailable = listOfCopies.get(i).getNoOfCopies() > 0;
				boolean branchIdMatches = listOfCopies.get(i).getBranchId() == branch.getBranchId();
				if (branchIdMatches && copiesAvailable) {
					listOfBooks.add(listOfCopies.get(i).getBook());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get borrowers.");
		} finally {
			c.close();
		}
		return listOfBooks;

	}

	public void checkOutBook(Borrower borrower, Book book, Branch branch) throws SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			Copies copy = new CopiesDAO(c).readCopyByBranchIDBookID(book, branch).get(0);
			Integer noOfCopies = copy.getNoOfCopies();

			// updating tbl_book_copies
			new CopiesDAO(c).updateCopies(book, branch, noOfCopies - 1);

			LocalDate localDate = LocalDate.now();
			LocalDate dueDate = localDate.plusDays(7);

			// updating tbl_book_loans
			new LoansDAO(c).addLoans(book, branch, borrower, Date.valueOf(localDate), Date.valueOf(dueDate));
			c.commit();
			System.out.println("Checked out : " + book.getTitle() + " by " + borrower.getName() + " from "
					+ branch.getBranchName());
		} catch (Exception e) {
			c.rollback();
			e.printStackTrace();
			System.err.println("Could not checkout Book" + book.toString());
		} finally {
			c.close();
		}

	}

	public void returnBook(Borrower borrower, Book book, Branch branch) throws SQLException {
		Connection c = null;
		try {
			c = conn.connectDatabase();
			Copies copy = new CopiesDAO(c).readCopyByBranchIDBookID(book, branch).get(0);
			Integer noOfCopies = copy.getNoOfCopies();
			
			// updating tbl_book_copies
			new CopiesDAO(c).updateCopies(book, branch, noOfCopies + 1);
			LocalDate localDate = LocalDate.now();
			//updating tbl_book_loans
			new LoansDAO(c).updateDateIn(book, branch, borrower, Date.valueOf(localDate));
			
			c.commit();
			System.out.println(localDate.toString() + " Returned " + book.getTitle() + " to "+ branch.getBranchName());
		} catch (Exception e) {
			c.rollback();
			e.printStackTrace();
			System.err.println("Could not return Book" + book.toString());
			
		}
	}

	public List<Loans> readLoans() throws SQLException {
		Connection c = null;
		List<Loans> listOfLoans = null;
		// System.out.println("The size of branch " + listOfBranchs.size());
		try {
			c = conn.connectDatabase();
			listOfLoans = new LoansDAO(c).readLoans();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get branchs.");
		} finally {
			c.close();
		}

		return listOfLoans;
	}

	public List<Copies> readCopies() throws SQLException {
		Connection c = null;
		List<Copies> listOfCopies = null;
		// System.out.println("The size of branch " + listOfBranchs.size());
		try {
			c = conn.connectDatabase();
			listOfCopies = new CopiesDAO(c).readCopies();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get tbl_book_copies.");
		} finally {
			c.close();
		}

		return listOfCopies;
	}

}
