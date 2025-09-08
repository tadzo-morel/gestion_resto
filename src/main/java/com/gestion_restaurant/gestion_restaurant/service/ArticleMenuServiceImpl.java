package com.gestion_restaurant.gestion_restaurant.service;

import com.gestion_restaurant.gestion_restaurant.DTO.Article_MenuDtoRequest;
import com.gestion_restaurant.gestion_restaurant.DTO.Article_MenuDtoResponse;
import com.gestion_restaurant.gestion_restaurant.entity.ArticleMenu;
import com.gestion_restaurant.gestion_restaurant.repository.ArticleMenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ArticleMenuServiceImpl implements ArticleMenuService{
    private final ArticleMenuRepository articleMenuRepository;
    @Override
    public ResponseEntity<Article_MenuDtoResponse> create(Article_MenuDtoRequest articleMenuDtoRequest) {
        ArticleMenu articleMenu=new ArticleMenu();
        articleMenu.setNomPlat(articleMenuDtoRequest.nom_plat());
        articleMenu.setDescription(articleMenuDtoRequest.description());
        articleMenu.setPrix(articleMenuDtoRequest.prix());
        ArticleMenu newArticleMenu=articleMenuRepository.save(articleMenu);
        Article_MenuDtoResponse articleMenuDtoResponse=new Article_MenuDtoResponse(
                newArticleMenu.getId(),
                newArticleMenu.getNomPlat(),
                newArticleMenu.getDescription(),
                newArticleMenu.getPrix()
        );
        return new ResponseEntity<>(articleMenuDtoResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Article_MenuDtoResponse> getArticle(Long id) {
        Optional<ArticleMenu> articleMenu=articleMenuRepository.findById(id);
        if (articleMenu.isPresent()){
            ArticleMenu articleMenu1=articleMenu.get();
            Article_MenuDtoResponse articleMenuDtoResponse=new Article_MenuDtoResponse(
                    articleMenu1.getId(),
            articleMenu1.getNomPlat(),
            articleMenu1.getDescription(),
            articleMenu1.getPrix()
            );
           return new ResponseEntity<>(articleMenuDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Article_MenuDtoResponse>> getAllArticle() {
        List<ArticleMenu> articleMenus=articleMenuRepository.findAll();
        List<Article_MenuDtoResponse> articleMenuDtoResponses=new ArrayList<>();
        for (ArticleMenu articleMenu:articleMenus){
            articleMenuDtoResponses.add(new Article_MenuDtoResponse(
                    articleMenu.getId(),
                    articleMenu.getNomPlat(),
                    articleMenu.getDescription(),
                    articleMenu.getPrix()
            ));
        }
        return new ResponseEntity<>(articleMenuDtoResponses,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Article_MenuDtoResponse> updateArticle(Long id, Article_MenuDtoRequest articleMenuDtoRequest) {
        Optional<ArticleMenu>articleMenu=articleMenuRepository.findById(id);
        if (articleMenu.isPresent()){
            ArticleMenu setArticleMenu=articleMenu.get();
            setArticleMenu.setNomPlat(articleMenuDtoRequest.nom_plat());
            setArticleMenu.setDescription(articleMenuDtoRequest.description());
            setArticleMenu.setPrix(articleMenuDtoRequest.prix());
            ArticleMenu newArticleMenu=articleMenuRepository.save(setArticleMenu);
            Article_MenuDtoResponse articleMenuDtoResponse=new Article_MenuDtoResponse(
                    newArticleMenu.getId(),
                    newArticleMenu.getNomPlat(),
                    newArticleMenu.getDescription(),
                    newArticleMenu.getPrix()
            );
            return new ResponseEntity<>(articleMenuDtoResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public String delete(Long id) {
        articleMenuRepository.deleteById(id);
        return "Article de Menu supprimer";
    }
}
