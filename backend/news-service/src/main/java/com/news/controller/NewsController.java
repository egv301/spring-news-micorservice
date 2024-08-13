package com.news.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.news.dto.NewsDto;
import com.news.dto.NewsFormDto;
import com.news.dto.TopNewsDto;
import com.news.exception.NotFoundException;
import com.news.service.NewsService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/news")
public class NewsController {
	
	@Autowired
	NewsService newsService;
	
	@GetMapping("/all")
	public ResponseEntity<List<TopNewsDto>> getAllNews(){
		return ResponseEntity.ok(newsService.getAllNews());	
	}
	
	@GetMapping("/top-news")
	public ResponseEntity<List<TopNewsDto>> getTopNews(){
		return ResponseEntity.ok(newsService.getTopNews());	
	}
	
	@GetMapping("/{news_id}")
	public ResponseEntity<NewsDto> showNewsItem(@PathVariable("news_id") Long news_id) throws NotFoundException{
		return ResponseEntity.ok(newsService.getNewsByIdDto(news_id));	
	}
	
//	@PostMapping("/add-news")
//	public ResponseEntity<?> addNews(
//			@RequestBody NewsFormDto newsFormDto,
//			@RequestHeader("username") String username,
//			@RequestHeader("role") String role) {
//		System.out.println("adding news");
//		if(role.equals("ROLE_ADMIN")) {
//			newsService.addNews(newsFormDto,username);
//		} else {
//			throw new RuntimeException("Unauhorized ");
//		}
//		
//		return new ResponseEntity<>(HttpStatus.OK);
//	}
	
	
	@PostMapping("/add-news")
	public void addNews(
			@RequestBody NewsFormDto newsFormDto) {
		System.out.println("adding news 2");
		
	}
	
//	@PostMapping("/add-news")
//	public void addNews() {
//		System.out.println("adding news");
//		
//	}
	
	@PutMapping("/edit-news/{news_id}")
	public ResponseEntity<?> addNews(
			@PathVariable Long news_id,
 			@RequestBody NewsFormDto newsFormDto,
			@RequestHeader("username") String username,
			@RequestHeader("role") String role) throws NotFoundException {
		if(role.equals("ROLE_ADMIN")) {
			newsService.editNews(news_id, newsFormDto, username);
		} else {
			throw new RuntimeException("Unauhorized ");
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-news/{news_id}")
	public ResponseEntity<?> deleteNews(@PathVariable Long news_id){
		newsService.deleteNewsById(news_id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
