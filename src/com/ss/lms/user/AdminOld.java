package com.ss.lms.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.PublisherDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;
import com.ss.lms.user.admin.GenreFunctions;

public class AdminOld {
	ConnectionUtil conn;
	GenreFunctions genreFunction;
	public AdminOld() throws ClassNotFoundException {
		conn = new ConnectionUtil();
		//genreFunction = new GenreFunctions(conn);
	}

	public void createBook(Book book) throws ClassNotFoundException, SQLException {
		Connection c = null;
		Integer bookId;
		try {
			c = conn.connectDatabase();
			bookId = new BookDAO(c).addBookReturnPK(book);
			AuthorDAO adao = new AuthorDAO(c);
			book.setBookId(bookId);
			
			for (Author a : book.getAuthors()) {
				adao.insertBookAuthors(a, book);
			}
			
			c.commit();
			System.out.println("Created book: " + book.getTitle());
		} catch (Exception e) {
			c.rollback();
			e.printStackTrace();
			System.err.println("Could not create Book" + book.toString());
		} finally {
			c.close();
		}


	}

	public void updateBook(Book book) throws SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new BookDAO(c).updateBook(book);
			c.commit();
		} catch (Exception e) {
			c.rollback();
			System.err.println("Could not update Book" + book.toString());
		} finally {
			c.close();
		}

	}

	public Book deleteBook(Book book) throws SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new BookDAO(c).deleteBook(book);
			c.commit();
			System.out.println("Deleted book: " + book.getTitle());
		} catch (Exception e) {
			c.rollback();
			System.err.println("Could not delete Book" + book.toString());
		} finally {
			c.close();
		}
		return book;

	}

	public List<Book> readBook() throws SQLException {

		Connection c = null;
		List<Book> listOfBooks = null;

		try {
			c = conn.connectDatabase();
			listOfBooks = new BookDAO(c).readBooks();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get books.");
		} finally {
			c.close();
		}
		return listOfBooks;
	}

	public void createAuthor(Author author) throws ClassNotFoundException, SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new AuthorDAO(c).addAuthor(author);

			c.commit();
		} catch (Exception e) {

			e.printStackTrace();
			c.rollback();
			System.out.println("Cannot add Author");
		} finally {
			c.close();
		}

	}

	public void updateAuthor(Author author) throws SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new AuthorDAO(c).updateAuthor(author);
			c.commit();
			System.out.println("Author name updated to: " + author.getAuthorName());
		} catch (Exception e) {
			c.rollback();
			System.err.println("Could not update Author" + author.toString());
		} finally {
			c.close();
		}

	}

	public Author deleteAuthor(Author author) throws SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new AuthorDAO(c).deleteAuthor(author);
			c.commit();
			System.out.println("Author Deleted: " + author.getAuthorName());
		} catch (Exception e) {
			c.rollback();
			System.err.println("Could not delete Author" + author.toString());
		} finally {
			c.close();
		}
		return author;

	}

	public List<Author> readAuthor() throws SQLException {

		Connection c = null;
		List<Author> listOfAuthors = new ArrayList<Author>();

		try {
			c = conn.connectDatabase();
			listOfAuthors = new AuthorDAO(c).readAuthors();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get Authors.");
		} finally {
			c.close();
		}
		return listOfAuthors;
	}

	public void createPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new PublisherDAO(c).addPublisher(publisher);

			c.commit();
			System.out.println("Publisher created: " + publisher.getPublisherName());
		} catch (Exception e) {

			e.printStackTrace();
			c.rollback();
			System.out.println("Cannot add Publisher");
		} finally {
			c.close();
		}

	}

	public void updatePublisher(Publisher publisher) throws SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new PublisherDAO(c).updatePublisher(publisher);
			c.commit();
			System.out.println("Updated publisher " + publisher.getPublisherName());
		} catch (Exception e) {
			c.rollback();
			System.err.println("Could not update Publishers" + publisher.toString());
		} finally {
			c.close();
		}

	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new PublisherDAO(c).deletePublisher(publisher);
			c.commit();
			System.out.println("Deleted publisher: " + publisher.getPublisherName());
		} catch (Exception e) {
			c.rollback();
			System.err.println("Could not delete publisher" + publisher.toString());
		} finally {
			c.close();
		}

	}

	public List<Publisher> readPublisher() {

		Connection c = null;
		List<Publisher> listOfPublishers = null;

		try {
			c = conn.connectDatabase();
			listOfPublishers = new PublisherDAO(c).readPublishers();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get publishers.");
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfPublishers;
	}
	
	

}
