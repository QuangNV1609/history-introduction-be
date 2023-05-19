package com.uet.quangnv.api;

import com.uet.quangnv.dto.ArticleUserDto;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;
import com.uet.quangnv.service.ArticleUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/article-user")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class ArticleUserApi {
    @Autowired
    private ArticleUserService articleUserService;

    @GetMapping(value = "/get-article-by-user")
    public ResponseEntity<List<ArticleUserDto>> getListArticleByUser() {
        List<ArticleUserDto> articleUserDtos = articleUserService.getListArticleByUser();
        return new ResponseEntity<>(articleUserDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/get-article-many-view")
    public ResponseEntity<List<ArticleUserDto>> getListArticleManyView() {
        List<ArticleUserDto> articleUserDtos = articleUserService.getListArticleByUser();
        return new ResponseEntity<>(articleUserDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/add-view-article")
    public void getArticleByID(@RequestBody Long articleID) throws ResoureNotFoundException {
        log.info("Request to add view article: ");
        articleUserService.addViewArticle(articleID);
    }
}
