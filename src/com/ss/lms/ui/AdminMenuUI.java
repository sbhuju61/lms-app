package com.ss.lms.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;
import com.ss.lms.user.AdminOld;
import com.ss.lms.utility.Utility;
import com.ss.lms.user.admin.Admin;
public class AdminMenuUI {
	Scanner scanner = new Scanner(System.in);
	AdminOld admin;
	Admin a;

	public AdminMenuUI() throws ClassNotFoundException {
		admin = new AdminOld();
		a = new Admin();
	}

	public void adminMenuUI() {

		String adminServiceMenu[] = { "Author Services", "Publisher Services", "Book Services","Genre Services", "Quit to Previous" };
		Utility.displayMenu(adminServiceMenu);
		int count = 0;

		try {
			while (count < 1 || count > adminServiceMenu.length) {
				System.out.print("Please enter the number" + "(" + 1 + "-" + adminServiceMenu.length + "): ");
				count = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Invalid Input: ");

		}

		if (count == 1) {
			authorServicesUI();
			adminMenuUI();

		} else if (count == 2) {
			publisherServicesUI();
			adminMenuUI();

		} else if (count == 3) {
			bookServicesUI();
			adminMenuUI();

		} else if (count == 4) {
			genreServicesUI();
			adminMenuUI();
		}
		else if (count == 5) {
			try {
				new MainMenuUI().mainMenuUI();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void authorServicesUI() {
		String authorServiceMenu[] = { "Add Author", "Delete Author", "Update Author", "Read all Author",
				"Quit to Previous" };
		Utility.displayMenu(authorServiceMenu);
		int count = 0;

		try {
			while (count < 1 || count > authorServiceMenu.length) {
				System.out.print("Please enter the number" + "(" + 1 + "-" + authorServiceMenu.length + "): ");
				count = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Invalid Input: ");

		}

		if (count == 1) {
			addAuthorMenuUI();
			authorServicesUI();

		} else if (count == 2) {
			deleteAuthorMenuUI();
			authorServicesUI();

		} else if (count == 3) {
			updateAuthorMenuUI();
			authorServicesUI();

		} else if (count == 4) {
			readAuthorMenuUI();
			authorServicesUI();
		} else if (count == 5) {
			adminMenuUI();
		}
	}

	public void readAuthorMenuUI() {
		// TODO Auto-generated method stub
		System.out.println("All the authors are: ");

		try {
			List<Author> authors = admin.readAuthor();

			for (int i = 0; i < authors.size(); i++) {
				System.out.println(i + 1 + ") " + authors.get(i).getAuthorName());
			}

			System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Count not list all authors");
		}

	}

	private void updateAuthorMenuUI() {
		// TODO Auto-generated method stub
		readAuthorMenuUI();
		System.out.println("Please type name of author to update.");
		String authorName = scanner.nextLine();
		List<Author> authors;
		try {
			authors = admin.readAuthor();
			Author authorToUpdate = pickAuthor(authors, authorName);

			if (authorToUpdate != null) {

				System.out.println("New name for author.");
				String updatedAuthorName = scanner.nextLine();
				authorToUpdate.setAuthorName(updatedAuthorName);
				admin.updateAuthor(authorToUpdate);
			} else {
				System.err.println("There is no author in database called: " + authorName);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deleteAuthorMenuUI() {
		// TODO Auto-generated method stub
		readAuthorMenuUI();
		System.out.println("Please type name of author to delete.");
		String authorName = scanner.nextLine();
		List<Author> authors;

		try {
			authors = admin.readAuthor();
			Author authorToDelete = pickAuthor(authors, authorName);

			if (authorToDelete != null) {
				admin.deleteAuthor(authorToDelete);
			} else {
				System.err.println("There is no author in database called: " + authorName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addAuthorMenuUI() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Please enter author details: ");
			System.out.println("Author Name: ");
			String authorName = scanner.nextLine();

			Author author = new Author();
			author.setAuthorName(authorName);

			admin.createAuthor(author);
		} catch (Exception e) {
			System.err.println("Invalid Input");
		}

	}

	private Author pickAuthor(List<Author> authors, String authorName) {
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getAuthorName().equals(authorName)) {
				return authors.get(i);
			}
		}
		return null;

	}

	private void publisherServicesUI() {
		// TODO Auto-generated method stub
		String publisherServiceMenu[] = { "Add Publisher", "Delete Publisher", "Update Publisher", "Read all Publisher",
				"Quit to Previous" };
		Utility.displayMenu(publisherServiceMenu);
		int count = 0;

		try {
			while (count < 1 || count > publisherServiceMenu.length) {
				System.out.print("Please enter the number" + "(" + 1 + "-" + publisherServiceMenu.length + "): ");
				count = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Invalid Input: ");

		}

		if (count == 1) {
			addPublisherMenuUI();
			publisherServicesUI();

		} else if (count == 2) {
			deletePublisherMenuUI();
			publisherServicesUI();

		} else if (count == 3) {
			updatePublisherMenuUI();
			publisherServicesUI();

		} else if (count == 4) {
			readPublisherMenuUI();
			publisherServicesUI();
		} else if (count == 5) {
			adminMenuUI();
		}

	}

	private void addPublisherMenuUI() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Please enter publisher details: ");
			System.out.println("Publisher Name: ");
			String publisherName = scanner.nextLine();

			System.out.println("Publisher Address: ");
			String publisherAddress = scanner.nextLine();

			System.out.println("Publisher Phone: ");
			String publisherPhone = scanner.nextLine();

			Publisher publisher = new Publisher();
			publisher.setPublisherName(publisherName);
			publisher.setPublisherAddress(publisherAddress);
			publisher.setPublisherPhone(publisherPhone);

			admin.createPublisher(publisher);
		} catch (Exception e) {
			System.err.println("Invalid Input");
		}

	}

	private void readPublisherMenuUI() {
		// TODO Auto-generated method stub
		System.out.println("All the publishers are: ");

		try {
			List<Publisher> publishers = admin.readPublisher();

			for (int i = 0; i < publishers.size(); i++) {
				System.out.println(i + 1 + ") " + publishers.get(i).getPublisherName());
			}

			System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Count not list all publishers");
		}
	}

	private void updatePublisherMenuUI() {
		// TODO Auto-generated method stub
		readPublisherMenuUI();
		System.out.println("Please type name of publisher to update.");
		String publisherName = scanner.nextLine();
		List<Publisher> publishers;
		try {
			publishers = admin.readPublisher();
			Publisher publisherToUpdate = pickPublisher(publishers, publisherName);

			if (publisherToUpdate != null) {
				System.out.println("Please enter updated publisher details: ");
				System.out.println("Publisher Name: ");
				String updatedPublisherName = scanner.nextLine();

				System.out.println("Publisher Address: ");
				String updatedPublisherAddress = scanner.nextLine();

				System.out.println("Publisher Phone: ");
				String updatedPublisherPhone = scanner.nextLine();

				publisherToUpdate.setPublisherName(updatedPublisherName);
				publisherToUpdate.setPublisherAddress(updatedPublisherAddress);
				publisherToUpdate.setPublisherPhone(updatedPublisherPhone);

				admin.updatePublisher(publisherToUpdate);
			} else {
				System.err.println("There is no publisher in database called: " + publisherName);
			}
		} catch (Exception e) {
			System.err.println("Invalid Input");
		}

	}

	private Publisher pickPublisher(List<Publisher> publishers, String publisherName) {
		for (int i = 0; i < publishers.size(); i++) {
			if (publishers.get(i).getPublisherName().equals(publisherName)) {
				return publishers.get(i);
			}
		}
		return null;

	}

	private void deletePublisherMenuUI() {
		// TODO Auto-generated method stub
		readPublisherMenuUI();
		System.out.println("Please type name of publisher to delete.");
		String publisherName = scanner.nextLine();
		List<Publisher> publishers;

		try {
			publishers = admin.readPublisher();
			Publisher publisherToDelete = pickPublisher(publishers, publisherName);

			if (publisherToDelete != null) {
				admin.deletePublisher(publisherToDelete);
			} else {
				System.err.println("There is no publisher in database called: " + publisherName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void bookServicesUI() {
		// TODO Auto-generated method stub
		String bookServiceMenu[] = { "Add Book", "Delete Book", "Update Book", "Read all Book", "Quit to Previous" };
		Utility.displayMenu(bookServiceMenu);
		int count = 0;

		try {
			while (count < 1 || count > bookServiceMenu.length) {
				System.out.print("Please enter the number" + "(" + 1 + "-" + bookServiceMenu.length + "): ");
				count = Integer.parseInt(scanner.nextLine());
			}

		} catch (Exception e) {
			System.err.println("Invalid Input: ");

		}

		if (count == 1) {
			addBookMenuUI();
			bookServicesUI();

		} else if (count == 2) {
			deleteBookMenuUI();
			bookServicesUI();

		} else if (count == 3) {
			updateBookMenuUI();
			bookServicesUI();

		} else if (count == 4) {
			readBookMenuUI();
			bookServicesUI();
		} else if (count == 5) {
			adminMenuUI();
		}

	}

	private void readBookMenuUI() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("All the books are: ");

		try {
			List<Book> books = admin.readBook();

			for (int i = 0; i < books.size(); i++) {
				System.out.println(i + 1 + ") " + books.get(i).getTitle());
			}

			System.out.println("");

		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("Count not list all books");
		}
	}

	private void updateBookMenuUI() {
		// TODO Auto-generated method stub
		readBookMenuUI();
		try {
			System.out.println("Please type name of book to update");
			String bookName = scanner.nextLine();

			List<Book> books = admin.readBook();

			Book book = pickBook(books, bookName);

			System.out.println("Please enter the updated Book name");
			String updatedBookName = scanner.nextLine();

			book.setTitle(updatedBookName);

			readPublisherMenuUI();
			System.out.println("Please enter the updated Publisher name");
			String updatedPublisherName = scanner.nextLine();

			List<Publisher> publishers = admin.readPublisher();
			Publisher publisher = pickPublisher(publishers, updatedPublisherName);
			
			while (publisher == null) {
				System.out.println("Please enter the updated Publisher name");
				updatedPublisherName = scanner.nextLine();

			}
			
			book.setPublisher(publisher);
			readAuthorMenuUI();
			for (Author a : book.getAuthors()) {
				System.out.println("Enter updated author name");
				String authorName = scanner.nextLine();
				a.setAuthorName(authorName);
			}

			admin.updateBook(book);

		} catch (Exception e) {

		}
	}

	private void deleteBookMenuUI() {
		// TODO Auto-generated method stub
		readBookMenuUI();
		System.out.println("Please type name of book to delete.");
		String bookName = scanner.nextLine();
		List<Book> books;

		try {
			books = admin.readBook();
			Book bookToDelete = pickBook(books, bookName);

			if (bookToDelete != null) {
				admin.deleteBook(bookToDelete);
			} else {
				System.err.println("There is no publisher in database called: " + bookName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addBookMenuUI() {
		// TODO Auto-generated method stub
		Book book = new Book();

		try {
			System.out.println("Enter book details");

			System.out.println("Enter book title: ");
			String bookName = scanner.nextLine();

			readPublisherMenuUI();
			System.out.println("Enter publisher name");
			String publisherName = scanner.nextLine();

			List<Publisher> publishers = admin.readPublisher();
			Publisher publisher = pickPublisher(publishers, publisherName);

			while (publisher == null) {
				System.out.println("Enter publisher name");
				publisherName = scanner.nextLine();
				publisher = pickPublisher(publishers, publisherName);
			}

			readAuthorMenuUI();
			List<Author> authors = admin.readAuthor();
			List<Author> addAuthorList = new ArrayList<>();

			System.out.println("How many authors for the book?");
			Integer authorCount = Integer.parseInt(scanner.nextLine());

			while (authorCount > 0) {

				System.out.println("Enter author name");
				String authorName = scanner.nextLine();
				Author author = pickAuthor(authors, authorName);

				if (author != null) {
					addAuthorList.add(author);
					authorCount--;
				}
			}

			book.setTitle(bookName);
			book.setPublisher(publisher);
			book.setAuthors(addAuthorList);
			admin.createBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Book pickBook(List<Book> books, String bookName) {
		// TODO Auto-generated method stub
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().equals(bookName)) {
				return books.get(i);
			}
		}
		return null;

	}
	public void genreServicesUI(){
		readGenreUI();
	}
	
	public void readGenreUI () {
		
		System.out.println("All the genres are: ");
		for (int i = 0; i < a.readGenre().size(); i++) {
			System.out.println(i + 1 + ") " + a.readGenre().get(i).getGenre_name());
		}
	}
	public void deleteGenreUI () {
		
		readGenreUI();
		System.out.println("Please enter number of genre to delete");
		
		
	}

	
	
}
