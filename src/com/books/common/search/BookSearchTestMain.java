package com.books.common.search;

public class BookSearchTestMain {
	public static void main(String[] args) {
		BookSearch bookSearch = new BookSearch();
		System.out.println(bookSearch.searchTotal("���ڿ���"));
		System.out.println(bookSearch.searchTotal("���ڿ���", 15));
		System.out.println(bookSearch.searchTotal("���ڿ���", 20, 30));
		// System.out.println(bookSearch.searchISBN("9791186900529"));
		// System.out.println(bookSearch.searchPubl("����"));

	}
}
