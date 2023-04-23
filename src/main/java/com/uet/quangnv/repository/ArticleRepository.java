package com.uet.quangnv.repository;

import com.uet.quangnv.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryCustom {
    @Query(value = "UPDATE article SET cover_image = ?1, thumbnail_image = ?2 WHERE id = ?3", nativeQuery = true)
    @Modifying
    void updateCoverImageAndThumbnailImage(Long coverImageID, Long thumbnailImageId, Long articleId);
}
