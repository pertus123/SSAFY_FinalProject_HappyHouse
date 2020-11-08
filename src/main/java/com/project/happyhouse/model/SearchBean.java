package com.project.happyhouse.model;

public class SearchBean {

	private int dealType;
	private int searchType;
	private String keyword;

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchBean [dealType=" + dealType + ", searchType=" + searchType + ", keyword=" + keyword + "]";
	}

}
