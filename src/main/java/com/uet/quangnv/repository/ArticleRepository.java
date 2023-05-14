package com.uet.quangnv.repository;

import com.uet.quangnv.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    @Modifying
    @Query(value = "UPDATE article SET cover_image = ?1, thumbnail_image = ?2 WHERE id = ?3", nativeQuery = true)
    void updateCoverImageAndThumbnailImage(Long coverImageID, Long thumbnailImageId, Long articleId);

    @Modifying
    @Query(value = "UPDATE article SET status = 1 WHERE id = ?1", nativeQuery = true)
    void updateStatus(Long articleId);

    @Modifying
    @Query(value = "UPDATE article SET status = 1 WHERE id in ?1", nativeQuery = true)
    void updateStatusByListId(List<Long> articleIds);

    @Query(value = "SELECT id From article WHERE id in ?1", nativeQuery = true)
    List<Long> getArticleById(List<Long> articleIds);
}
