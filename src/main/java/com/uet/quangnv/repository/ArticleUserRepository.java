package com.uet.quangnv.repository;

import com.uet.quangnv.entities.ArticleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ArticleUserRepository extends JpaRepository<ArticleUser, Long>, ArticleUserRepositoryCustom {
    @Query(value = "Select * from article_user WHERE username = ? and article_id = ?", nativeQuery = true)
    Optional<ArticleUser> findByArticleIdAndUser(String username, Long articleId);

    @Query(value = "Select * from article_user WHERE username is null and article_id = ?", nativeQuery = true)
    Optional<ArticleUser> findByArticleIdAndUserIsNull(Long articleId);

    @Query(value = "UPDATE article_user SET quantity = ? WHERE username = ? and article_id = ?", nativeQuery = true)
    @Modifying
    void addViewForArticle(Long quantity, String username, Long articleId);

    @Query(value = "UPDATE article_user SET quantity = ? WHERE username is null and article_id = ?", nativeQuery = true)
    @Modifying
    void addViewForArticleAndUserIsNull(Long quantity, Long articleId);

    @Query(value = "UPDATE article_user SET last_date_view = ? WHERE username = ? and article_id = ?", nativeQuery = true)
    @Modifying
    void updateLastDateView(Date lastDateView, String username, Long articleId);

    @Query(value = "UPDATE article_user SET article_id = ? WHERE article_id = ?", nativeQuery = true)
    @Modifying
    void updateViewForArticle(Long articleIdNew, Long articleIdOld);
}
