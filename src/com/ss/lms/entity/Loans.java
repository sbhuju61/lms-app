package com.ss.lms.entity;

import java.time.LocalDate;

public class Loans {
	private Branch libraryBranch;
	private Book book;
	private LocalDate dateOut;
	private LocalDate dueDate;
	private LocalDate dateIn;

	public Branch getLibraryBranch() {
		return libraryBranch;
	}

	public void setLibraryBranch(Branch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getDateIn() {
		return dateIn;
	}

	public void setDateIn(LocalDate dateIn) {
		this.dateIn = dateIn;
	}

}
