package com.sxis.model;



public class BorrowDetailBean{
	private Integer id;
	private String readerId;
	private String readerName;
	private String bookId;
	private String bookName;
	private String borrowTime;
	private String backTime;
	private Integer ifback;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	public Integer getIfback() {
		return ifback;
	}
	public void setIfback(Integer ifback) {
		this.ifback = ifback;
	}
	
	
	
  
}
