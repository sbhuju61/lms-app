package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Genre;

public class GenreDAO extends BaseDAO {

	public GenreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
		save("insert into tbl_genre (genre_name) values (?)", new Object[] { genre.getGenre_name() });
	}
	public void updateGenre(Genre genre) throws SQLException, ClassNotFoundException {
		save("update tbl_genre set genre_name" + "=? where genreId = ?", new Object[]{genre.getGenre_name()} );
	}
	
	public List<Genre> readGenres() throws ClassNotFoundException, SQLException {
		return read("select * from tbl_genre", null);
	}
	
	public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("delete from tbl_genre where genre_id = ?", new Object[] {genre.getGenre_id()});
	}

	@Override
	List extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Genre> genres = new ArrayList<>();

		while (rs.next()) {
			Genre g = new Genre();
			g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));

			genres.add(g);
		}
		return genres;
	}

	@Override
	List extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Genre> genres = new ArrayList<>();

		while (rs.next()) {
			Genre g = new Genre();
			g.setGenre_id(rs.getInt("genre_id"));
			g.setGenre_name(rs.getString("genre_name"));

			genres.add(g);
		}
		return genres;

	}

	

}
