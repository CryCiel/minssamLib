package com.books.common.search;

public class BookSearchTestMain {
	public static void main(String[] args) {
		BookSearch bookSearch = new BookSearch();
		System.out.println(bookSearch.search("양자역학"));
		//System.out.println(bookSearch.search("양자역학", 15));
		//System.out.println(bookSearch.search("양자역학", 20, 30));
		//System.out.println(bookSearch.search("9791186900529"));

	}
}
