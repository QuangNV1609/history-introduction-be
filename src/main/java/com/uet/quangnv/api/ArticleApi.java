package com.uet.quangnv.api;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.entities.Article;
import com.uet.quangnv.exception.domain.ResoureNotFoundException;
import com.uet.quangnv.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/article")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class ArticleApi {
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/find-by-id/{articleID}")
    public ResponseEntity<ArticleDto> getArticleByID(@PathVariable("articleID") Long articleID) throws ResoureNotFoundException {
        log.info("Request to get article by id: " + articleID);
        ArticleDto articleDto = articleService.findArticleByID(articleID);
        return new ResponseEntity<>(articleDto, HttpStatus.OK);
    }

    @GetMapping(value = "/find-all-by-username")
    @PreAuthorize("hasRole('ROLE_ADMIN_2') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ArticleDto>> getAllArticleByUsername() {
        log.info("Request to get all article by username: ");
        List<ArticleDto> articleDtoList = articleService.findAllArticleByUsername();
        return new ResponseEntity<>(articleDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/find-all-by-censorship")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_2')")
    public ResponseEntity<List<ArticleDto>> getAllArticleByUsername(@RequestParam Boolean isCensorship) {
        log.info("Request to get all article by censorship: ");
        List<ArticleDto> articleDtoList = articleService.findArticleIsCensorship(isCensorship);
        return new ResponseEntity<>(articleDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/search-article")
    public ResponseEntity<List<ArticleDto>> searchArticle(
            @RequestParam(value = "historicalPeriod", required = false) Integer historicalPeriod,
            @RequestParam(value = "historyDay", required = false) String historyDay,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "postType", required = false) Integer postType,
            @RequestParam(value = "content", required = false) Integer content) {
        log.info("Request to get all article by censorship: ");
        List<ArticleDto> articleDtoList = articleService.searchArticle(historicalPeriod, historyDay, status, postType, content);
        return new ResponseEntity<>(articleDtoList, HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN_2') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Article> save(
            @RequestParam(name = "coverImage", required = false) MultipartFile coverImage,
            @RequestParam(name = "thumbnailImage", required = false) MultipartFile thumbnailImage,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam(name = "historyDay", required = false) String historyDay,
            @RequestParam("postType") Integer postType,
            @RequestParam(name = "historicalPeriod", required = false) Integer historicalPeriod,
            @RequestParam(value = "parentID", required = false) Long parentID
    ) throws ResoureNotFoundException {
        log.info("Request to save article");
        Article article = articleService.saveArticle(title, content, coverImage, thumbnailImage, historyDay, postType, historicalPeriod, parentID);
        article.setCreateBy(null);
        article.setLastModifiedBy(null);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PutMapping(value = "/censorship")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void censorship(@RequestBody Long articleId) throws ResoureNotFoundException {
        log.info("Request to censorship article");
        articleService.censorship(articleId);
    }

    @PutMapping(value = "/censorship-list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void censorship(@RequestBody List<Long> articleIds) {
        log.info("Request to censorship article");
        articleService.censorshipList(articleIds);
    }

    @DeleteMapping(value = "/delete-list-by-id")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_2')")
    public void deleteArticleByIDs(@RequestBody Long articleId) {
        log.info("Request to censorship article");
        articleService.deleteArticleByIDs(List.of(articleId));
    }

    @DeleteMapping(value = "/delete-list-by-ids")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_2')")
    public void deleteArticleByIDs(@RequestBody List<Long> articleIds) {
        log.info("Request to censorship article");
        articleService.deleteArticleByIDs(articleIds);
    }

}
