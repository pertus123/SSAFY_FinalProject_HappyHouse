package com.project.happyhouse.model;

public class QnaArticleDto {
	private int articleno;
	private String author;
	private String subject;
	private String content;
	private String regidate;
	
	public QnaArticleDto() {
		super();
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}

	public QnaArticleDto(int articleno, String author, String subject, String content, String regidate) {
		super();
		this.articleno = articleno;
		this.author = author;
		this.subject = subject;
		this.content = content;
		this.regidate = regidate;
	}
}
