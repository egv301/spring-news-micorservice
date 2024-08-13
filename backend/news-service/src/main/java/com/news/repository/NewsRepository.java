package com.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.news.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsRepository  extends JpaRepository<News,Long> {
    List<News> findAllByOrderByViewsCountDesc();
    
    
}
