package com.gestion_restaurant.gestion_restaurant.controller;

import com.gestion_restaurant.gestion_restaurant.DTO.Article_MenuDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.Article_MenuDtoResponse;
import com.gestion_restaurant.gestion_restaurant.service.ArticleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/articleMenu")
public class ArticleMenuController {
    public final ArticleMenuService articleMenuService;

    @PostMapping("/create")
    public ResponseEntity<Article_MenuDtoResponse> create(@RequestBody Article_MenuDtoRequest articleMenu){
        return articleMenuService.create(articleMenu);
    }
    @GetMapping("/")
    public ResponseEntity<List<Article_MenuDtoResponse>> getAllArticleMenu(){
        return articleMenuService.getAllArticle();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Article_MenuDtoResponse> get(@PathVariable Long id){
        return articleMenuService.getArticle(id);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Article_MenuDtoResponse> update(@PathVariable Long id, @RequestBody Article_MenuDtoRequest articleMenu){
        return articleMenuService.updateArticle(id,articleMenu);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return articleMenuService.delete(id);
    }
}
