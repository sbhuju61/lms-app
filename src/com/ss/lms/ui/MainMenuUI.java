package com.ss.lms.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.ss.lms.utility.Utility;
import com.ss.lms.ui.AdminMenuUI;
import com.ss.lms.ui.BorrowerMenuUI;
import com.ss.lms.ui.LibrarianMenuUI;

public class MainMenuUI {
	Scanner scanner = new Scanner(System.in);
	
	AdminMenuUI adminMenu;
	BorrowerMenuUI borrowerMenu;
	LibrarianMenuUI librarianMenu;
	
	public MainMenuUI () throws ClassNotFoundException {
		adminMenu = new AdminMenuUI();
		borrowerMenu = new BorrowerMenuUI();
		librarianMenu = new LibrarianMenuUI();
		
	}
	public void mainMenuUI() throws ClassNotFoundException, SQLException {
		System.out.println("Welcome to the SmoothStack Library Management System.\n");

		System.out.println("Which category of a user are you?");
		String mainMenu[] = { "Librarian", "Administrator", "Borrower","Exit" };
		Utility.displayMenu(mainMenu);
		
		int input = 0;
		
		
		try {
			while (input < 1 || input > mainMenu.length) {
			System.out.print("Please enter the number" + "(" + 1 + "-" + mainMenu.length + "): ");
			input = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Error: ");
		}

		if (input == 1) {
			librarianMenu.librarianMenuUI();
		}
		if (input == 2) {
			adminMenu.adminMenuUI();
			
		}
		if (input == 3) {
			borrowerMenu.borrowerMenuUI();

		}
		if (input == 4) {
			System.out.println("Good Bye!");
			System.exit(0);
		}
	}
}
