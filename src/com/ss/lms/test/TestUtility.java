package com.ss.lms.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.ss.lms.user.AdminOld;

public class TestUtility {

	@Test
	public void test() throws ClassNotFoundException {
		AdminOld a = new AdminOld();
		//List<Author> authors = new ArrayList<>();
		try {
			a.readAuthor();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}