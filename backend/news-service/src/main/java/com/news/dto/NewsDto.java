package com.news.dto;

public class NewsDto {
	
	private Long id;
	private String title;
	private String text;
	private Integer viewsCount;
	private String category;
	
	public NewsDto() {}
	
	public NewsDto(Long id, String title, String text, Integer viewsCount, String category) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.viewsCount = viewsCount;
		this.category = category;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Integer getViewsCount() {
		return viewsCount;
	}

	public void setViewsCount(Integer viewsCount) {
		this.viewsCount = viewsCount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	
}
