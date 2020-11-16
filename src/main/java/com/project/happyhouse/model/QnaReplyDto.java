package com.project.happyhouse.model;

public class QnaReplyDto {
	private int replyno;
	private int articleno;
	private String replyauthor;
	private String replycontent;
	private String replyregidate;
	public QnaReplyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QnaReplyDto(int replyno, int articleno, String replyauthor, String replycontent, String replyregidate) {
		super();
		this.replyno = replyno;
		this.articleno = articleno;
		this.replyauthor = replyauthor;
		this.replycontent = replycontent;
		this.replyregidate = replyregidate;
	}
	@Override
	public String toString() {
		return "QnaReplyDto [replyno=" + replyno + ", articleno=" + articleno + ", replyauthor=" + replyauthor
				+ ", replycontent=" + replycontent + ", replyregidate=" + replyregidate + "]";
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
	public String getreplyregidate() {
		return replyregidate;
	}
	public void setreplyregidate(String replyregidate) {
		this.replyregidate = replyregidate;
	}
	
	
}
