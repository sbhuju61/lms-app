package com.ss.lms.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


import com.ss.lms.entity.Branch;

import com.ss.lms.user.Librarian;
import com.ss.lms.utility.Utility;

public class LibrarianMenuUI {
	Scanner scanner = new Scanner(System.in);
	Librarian librarian;
	
	public LibrarianMenuUI() throws ClassNotFoundException {
		librarian = new Librarian();
	}
	
	public void librarianMenuUI () {
		String librarianServiceMenu[] = { "List all Library Branch","Update Library Branch", "Quit to Previous" };
		Utility.displayMenu(librarianServiceMenu);
		int count = 0;

		try {
			while (count < 1 || count > librarianServiceMenu.length) {
				System.out.print("Please enter the number" + "(" + 1 + "-" + librarianServiceMenu.length + "): ");
				count = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Invalid Input: ");

		}

		if (count == 1) {
			readBranchMenuUI();
			librarianMenuUI();

		}
		else if (count == 2) {
			updateLibrayBranchServicesUI();
			librarianMenuUI();

		}
		else if (count == 3) {
			try {
				new MainMenuUI().mainMenuUI();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 
			
		}

	private void updateLibrayBranchServicesUI() {
		// TODO Auto-generated method stub
		readBranchMenuUI();
		System.out.println("Name of library branch to update");
		String branchName = scanner.nextLine();
		List<Branch> libraryBranch;
		try {
			libraryBranch = librarian.readBranch();
			Branch branchToUpdate = pickBranch(libraryBranch, branchName);

			if (branchToUpdate != null) {
				System.out.println("Please enter updated library branch details: ");
				System.out.println("Branch Name: ");
				String updatedBranchName = scanner.nextLine();

				System.out.println("Branch Address: ");
				String updatedBranchAddress = scanner.nextLine();

				

				branchToUpdate.setBranchName(updatedBranchName);
				branchToUpdate.setBranchAddress(updatedBranchAddress);
				

				librarian.updateBranch(branchToUpdate);
			} else {
				System.err.println("There is no library branch in database called: " + branchName);
			}
		} catch (Exception e) {
			System.err.println("Invalid Input");
		}

	}
	
	
	protected void readBranchMenuUI() {
		// TODO Auto-generated method stub
		System.out.println("All the library branches are: ");

		try {
			List<Branch> branchs = librarian.readBranch();

			for (int i = 0; i < branchs.size(); i++) {
				System.out.println(i + 1 + ") " + branchs.get(i).getBranchName());
				System.out.println("Address: " + branchs.get(i).getBranchAddress());
				System.out.println("");
			}

			System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Count not list all branches");
		}
	}
	
	protected Branch pickBranch(List<Branch > branches, String branchName) {
		for (int i = 0; i < branches.size(); i++) {
			if (branches.get(i).getBranchName().equals(branchName)) {
				return branches.get(i);
			}
		}
		return null;

	}
	
	
	
}
