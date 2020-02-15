package com.ss.lms.entity;

import java.io.Serializable;

public class Copies implements Serializable {

	private static final long serialVersionUID = -8525882036755155408L;
	private Integer noOfCopies;
	private Integer bookId;
	private Integer branchId;
	private Book book;
	private Branch libraryBranch;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Branch getLibraryBranch() {
		return libraryBranch;
	}

	public void setLibraryBranch(Branch branch) {
		this.libraryBranch = branch;
	}

	public Integer getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(Integer noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
}
