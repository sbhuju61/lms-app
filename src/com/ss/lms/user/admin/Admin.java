package com.ss.lms.user.admin;

import java.sql.SQLException;
import java.util.List;

import com.ss.lms.entity.Genre;
import com.ss.lms.user.ConnectionUtil;
import com.ss.lms.user.admin.GenreFunctions;

public class Admin {

	ConnectionUtil conn;
	GenreFunctions genreFunction;

	public Admin() throws ClassNotFoundException {
		conn = new ConnectionUtil();
		genreFunction = new GenreFunctions(conn);
	}

	public List<Genre> readGenre() {

		return genreFunction.readGenre();
	}

	public void deleteGenre(Genre genre) {

		try {
			genreFunction.deleteGenre(genre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
