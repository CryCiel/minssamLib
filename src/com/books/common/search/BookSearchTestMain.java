package com.books.common.search;

public class BookSearchTestMain {
	public static void main(String[] args) {
		BookSearch bookSearch = new BookSearch();
		System.out.println(bookSearch.search("���ڿ���"));
		System.out.println(bookSearch.search("���ڿ���", 15));
		System.out.println(bookSearch.search("���ڿ���", 20, 30));
		System.out.println(bookSearch.search("9791186900529"));

	}
}
