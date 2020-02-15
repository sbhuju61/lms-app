package com.ss.lms.user.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.dao.GenreDAO;
import com.ss.lms.entity.Genre;
import com.ss.lms.user.ConnectionUtil;

public final class GenreFunctions {
	ConnectionUtil conn;

	protected GenreFunctions(ConnectionUtil conn) {
		this.conn = conn;

	}

	protected List<Genre> readGenre() {

		Connection c = null;
		List<Genre> listOfGenres = null;

		try {
			c = conn.connectDatabase();
			listOfGenres = new GenreDAO(c).readGenres();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get genres.");
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfGenres;
	}

	public void deleteGenre(Genre genre) throws SQLException {
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new GenreDAO(c).deleteGenre(genre);
			c.commit();
			System.out.println("Deleted genre: " + genre.getGenre_name());
		} catch (Exception e) {
			c.rollback();
			System.err.println("Could not delete Book" + genre.toString());
		} finally {
			c.close();
		}

	}
}
