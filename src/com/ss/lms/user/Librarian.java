package com.ss.lms.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.lms.dao.BranchDAO;

import com.ss.lms.entity.Branch;

public class Librarian {
	ConnectionUtil conn;

	public Librarian() throws ClassNotFoundException {
		conn = new ConnectionUtil();

	}
	public List<Branch> readBranch() throws SQLException {
		
		Connection c = null;
		List<Branch> listOfBranchs = null;
		//System.out.println("The size of branch " + listOfBranchs.size());
		try {
			c = conn.connectDatabase();
			listOfBranchs = new BranchDAO(c).readBranchs();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not get branchs.");
		} finally {
			c.close();
		}
		
		return listOfBranchs;
	}
	public void updateBranch(Branch branchToUpdate) throws SQLException {
		// TODO Auto-generated method stub
		Connection c = null;

		try {
			c = conn.connectDatabase();
			new BranchDAO(c).updateBranch(branchToUpdate);
			c.commit();
			System.out.println("Updated branch " + branchToUpdate.getBranchName());
		} catch (Exception e) {
			e.printStackTrace();
			c.rollback();
			System.err.println("Could not update Branch" + branchToUpdate.toString());
		} finally {
			c.close();
		}
		
	}
	
	
}
