package com.news.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.news.dto.NewsDto;
import com.news.dto.NewsFormDto;
import com.news.dto.TopNewsDto;
import com.news.exception.NotFoundException;
import com.news.model.News;
import com.news.model.User;
import com.news.repository.NewsRepository;

@Service
public class NewsService {
	
	@Autowired
	NewsRepository newsRepository;
	
	@Autowired
	UserService userService;
	
	public List<TopNewsDto> getAllNews(){
		return newsRepository.findAll()
				.stream()
				.map(news->new TopNewsDto(news.getId(),news.getTitle()))
				.collect(Collectors.toList());
			
	}
	
	public List<TopNewsDto> getTopNews(){
		return newsRepository.findAllByOrderByViewsCountDesc()
				.stream()
				.map(news->new TopNewsDto(news.getId(),news.getTitle()))
				.collect(Collectors.toList());
			
	}
	
	public News getNewsById(Long news_id) throws NotFoundException {
		News news = newsRepository.findById(news_id).orElseThrow(()->new NotFoundException("News not found"));
		return news;
	}
	
	public NewsDto getNewsByIdDto(Long news_id) throws NotFoundException{
		News news = getNewsById(news_id);
		return new NewsDto(
				news.getId(),
				news.getTitle(),
				news.getText(), 
				news.getViewsCount(),
				news.getCategory()
		);
	}
	
	public void addNews(NewsFormDto newsFormDto,String username) {
		News news = new News();
		
		User user = userService.findUserByUsername(username);
		
		news.setTitle(newsFormDto.getTitle());
		news.setText(newsFormDto.getText());
		news.setCategory(newsFormDto.getCategory());
		news.setUser(user);
 		newsRepository.save(news);
	}
	
	public void editNews(Long news_id,NewsFormDto newsFormDto,String username) throws NotFoundException {
		News news = newsRepository.findById(news_id).orElseThrow(()->new NotFoundException("News not found"));
		User user = userService.findUserByUsername(username);
		news.setTitle(newsFormDto.getTitle());
		news.setText(newsFormDto.getText());
		news.setUser(user);
 		newsRepository.save(news);
	}
	
	public void deleteNewsById(Long news_id) {
		newsRepository.deleteById(news_id);
	}

	
}
