package com.books.model.domain.book;

import java.util.List;

public class Book {
	private String title; // ����
	private String link; // ���
	private String image; // �̹��� �ּ�
	private String author; // ����
	private String publisher; // ���ǻ�
	private String pubdate; // ������
	private String isbn; // isbn
	private String description; // ����
	private List<Review> review; // ����
	private List<BookComment> bookComment; // ���

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public List<BookComment> getBookComment() {
		return bookComment;
	}

	public void setBookComment(List<BookComment> bookComment) {
		this.bookComment = bookComment;
	}

}