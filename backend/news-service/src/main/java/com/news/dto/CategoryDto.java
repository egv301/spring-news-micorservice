package com.news.dto;

public class CategoryDto {
	public Integer id;
	public String title;
	
	public CategoryDto() {}
	
	public CategoryDto(Integer id, String title) {
		this.id = id;
		this.title=title;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
