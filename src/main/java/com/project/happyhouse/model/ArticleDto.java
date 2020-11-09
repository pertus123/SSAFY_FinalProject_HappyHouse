package com.project.happyhouse.model;

public class ArticleDto {
	private int articleno;
	private String subject;
	private String content;
	private String regidate;
	
	public ArticleDto() {
		super();
	}

	public ArticleDto(int articleno, String subject, String content, String regidate) {
		super();
		this.articleno = articleno;
		this.subject = subject;
		this.content = content;
		this.regidate = regidate;
	}

	public int getArticleno() {
		return articleno;
	}

	public void setArticleno(int articleno) {
		this.articleno = articleno;
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

	@Override
	public String toString() {
		return "NoticeDto [articleno=" + articleno + ", subject=" + subject + ", content=" + content + ", regidate="
				+ regidate + "]";
	}
}
