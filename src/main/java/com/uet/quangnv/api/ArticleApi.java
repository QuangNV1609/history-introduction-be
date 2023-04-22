package com.uet.quangnv.api;

import com.uet.quangnv.entities.Article;
import com.uet.quangnv.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/article")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class ArticleApi {
    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN_2')")
    public ResponseEntity<Article> save(
            @RequestParam("coverImage") MultipartFile coverImage,
            @RequestParam("thumbnailImage") MultipartFile thumbnailImage,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("historyDay") String historyDay,
            @RequestParam("postType") Integer postType,
            @RequestParam(value = "parentID", required = false) Long parentID
    ) {
        log.info("Request to save article");
        Article article = articleService.save(title, content, coverImage, thumbnailImage, historyDay, postType, parentID);
        article.setCreateBy(null);
        article.setLastModifiedBy(null);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
}
