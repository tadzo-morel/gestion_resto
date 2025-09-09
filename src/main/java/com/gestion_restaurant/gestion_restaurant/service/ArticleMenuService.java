package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.Article_MenuDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.Article_MenuDtoResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticleMenuService {
    public ResponseEntity<Article_MenuDtoResponse> create(Article_MenuDtoRequest articleMenuDtoRequest);
    public ResponseEntity <Article_MenuDtoResponse> getArticle(Long id);
    public ResponseEntity<List<Article_MenuDtoResponse>> getAllArticle();
    public ResponseEntity <Article_MenuDtoResponse> updateArticle(Long id,Article_MenuDtoRequest articleMenuDtoRequest);
    public String delete(Long id);


}
