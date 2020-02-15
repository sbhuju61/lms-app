package com.ss.lms.ui;

import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Copies;
import com.ss.lms.entity.Loans;
import com.ss.lms.user.AdminOld;
import com.ss.lms.user.Librarian;
import com.ss.lms.user.Patron;
import com.ss.lms.utility.Utility;

public class BorrowerMenuUI {
	Scanner scanner = new Scanner(System.in);
	Patron patron;

	public BorrowerMenuUI() throws ClassNotFoundException {
		patron = new Patron();

	}

	public void borrowerMenuUI() {
		String borrowerServiceMenu[] = { "Check out a book", "Return a book", "Quit to Previous" };
		Utility.displayMenu(borrowerServiceMenu);
		int count = 0;

		try {
			while (count < 1 || count > borrowerServiceMenu.length) {
				System.out.print("Please enter the number" + "(" + 1 + "-" + borrowerServiceMenu.length + "): ");
				count = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Invalid Input: ");

		}

		if (count == 1) {
			checkOutBookMenuUI();
			borrowerMenuUI();

		} else if (count == 2) {
			returnBookMenuUI();
			borrowerMenuUI();

		} else if (count == 3) {
			try {
				new MainMenuUI().mainMenuUI();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void checkOutBookMenuUI() {
		// TODO Auto-generated method stub
		try {
			List<Borrower> borrowers = patron.readBorrower();

			System.out.println("Enter your card number");
			Integer cardNo = Integer.parseInt(scanner.nextLine());

			Borrower validatedBorrower = pickBorrower(borrowers, cardNo);
			while (validatedBorrower == null) {
				System.out.println("Invalid card number");
				System.out.println("Enter your card number");
				cardNo = Integer.parseInt(scanner.nextLine());

				validatedBorrower = pickBorrower(borrowers, cardNo);

			}
			System.out.println("Borrower Info: ");
			System.out.println(validatedBorrower.getName() + " " + validatedBorrower.getAddress());

			new LibrarianMenuUI().readBranchMenuUI();
			System.out.println("Which library would to like to check out book from?");
			String branchName = scanner.nextLine();
			List<Branch> branchs = new Librarian().readBranch();

			Branch validatedBranch = new LibrarianMenuUI().pickBranch(branchs, branchName);
			while (validatedBranch == null) {
				System.out.println("Invalid branch name");
				System.out.println("Enter branch Name");
				branchName = scanner.nextLine();

				validatedBranch = new LibrarianMenuUI().pickBranch(branchs, branchName);

			}
			System.out.println("Branch Info: ");
			System.out.println(validatedBranch.getBranchName() + " " + validatedBranch.getBranchAddress());
			List<Book> availableBooks = patron.getAvailableBooksByBranch(validatedBranch);

			System.out.println("List of Available Books");
			for (Book b : availableBooks) {
				System.out.println(b.getTitle());
			}

			System.out.println("Please type name of book you would like to check out: ");
			String bookName = scanner.nextLine();

			Book validatedBook = pickBook(availableBooks, bookName);

			while (validatedBook == null) {
				System.out.println("Please type name of book you would like to check out: ");
				bookName = scanner.nextLine();

				validatedBook = pickBook(availableBooks, bookName);
			}
			patron.checkOutBook (validatedBorrower, validatedBook, validatedBranch);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void returnBookMenuUI() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Please enter your Card No.");
			Integer cardNo = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Please enter name of book to return: ");
			String bookName = scanner.nextLine();
			
			System.out.println("Please enter branch name: ");
			String branchName = scanner.nextLine();
			
			List<Borrower> borrowers = patron.readBorrower();
			List<Book> books = new AdminOld().readBook();
			List<Branch> branches = new Librarian().readBranch();
			
			Borrower borrower = pickBorrower(borrowers,cardNo);
			Book book = pickBook(books,bookName);
			Branch branch = new LibrarianMenuUI().pickBranch(branches, branchName);
			
			if (borrower != null && book != null && branch != null) {
				
				patron.returnBook(borrower, book, branch);
			}
			
			else {
				System.err.println("Could not return book. ");
			}
		}
		catch (Exception e) {
			System.err.println("Could not return book. ");
		}
	

	}

	private void readCopiesMenuUI() {
		// TODO Auto-generated method stub
		System.out.println("All the copies are: ");

		try {
			List<Copies> loans = patron.readCopies();

			for (int i = 0; i < loans.size(); i++) {
				System.out.print(i + 1 + ") ");
				System.out.println("Book id " + loans.get(i).getBookId());
				System.out.println("Branch id " + loans.get(i).getBranchId());
				System.out.println("Copies " + loans.get(i).getNoOfCopies());
				System.out.println("Book Title " + loans.get(i).getBook().getTitle());

				System.out.println("");
			}

			System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Count not list all borrower");
		}

	}

	private void readBorrowerMenuUI() {
		// TODO Auto-generated method stub
		System.out.println("All the library borrower are: ");

		try {
			List<Borrower> borrowers = patron.readBorrower();

			for (int i = 0; i < borrowers.size(); i++) {
				System.out.println(i + 1 + ") " + borrowers.get(i).getName());
				System.out.println("Address: " + borrowers.get(i).getAddress());
				System.out.println("");
			}

			System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Count not list all borrower");
		}
	}

	private Borrower pickBorrower(List<Borrower> borrowers, Integer cardNo) {
		for (int i = 0; i < borrowers.size(); i++) {
			if (borrowers.get(i).getCardNo().equals(cardNo)) {
				return borrowers.get(i);
			}
		}
		return null;

	}

	private void readLoansMenuUI() {
		// TODO Auto-generated method stub
		System.out.println("All the library branches are: ");

		try {
			List<Loans> loans = patron.readLoans();

			for (int i = 0; i < loans.size(); i++) {
				System.out.print(i + 1 + ") ");
				System.out.println("Book id " + loans.get(i).getBookId());
				System.out.println("Branch id " + loans.get(i).getBranchId());
				System.out.println("cardNo " + loans.get(i).getCardNo());
				System.out.println("dateOut " + loans.get(i).getDateOut());
				System.out.println("dueDate " + loans.get(i).getDueDate());
				System.out.println("dateIn " + loans.get(i).getDateIn());
				System.out.println("");
			}

			System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Count not list all borrower");
		}
	}

	protected Book pickBook(List<Book> books, String bookName) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(bookName)) {
				return books.get(i);
			}
		}
		return null;

	}
}
