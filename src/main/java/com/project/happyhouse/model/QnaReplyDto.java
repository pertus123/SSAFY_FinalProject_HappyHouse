package com.project.happyhouse.model;

public class QnaReplyDto {
	private int replyno;
	private int articleno;
	private String replyauthor;
	private String replycontent;
	private String regidate;
	public QnaReplyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaReplyDto(int replyno, int articleno, String replyauthor, String replycontent, String regidate) {
		super();
		this.replyno = replyno;
		this.articleno = articleno;
		this.replyauthor = replyauthor;
		this.replycontent = replycontent;
		this.regidate = regidate;
	}
	@Override
	public String toString() {
		return "QnaReplyDto [replyno=" + replyno + ", articleno=" + articleno + ", replyauthor=" + replyauthor
				+ ", replycontent=" + replycontent + ", regidate=" + regidate + "]";
	}
	public int getReplyno() {
		return replyno;
	}
	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getReplyauthor() {
		return replyauthor;
	}
	public void setReplyauthor(String replyauthor) {
		this.replyauthor = replyauthor;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public String getRegidate() {
		return regidate;
	}
	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}
	
	
}
