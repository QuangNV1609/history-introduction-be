package com.uet.quangnv.repository;

import com.uet.quangnv.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Modifying
    @Query(value = "UPDATE article SET status = 0 WHERE parentid in ?1", nativeQuery = true)
    void updateStatusByListParentId(List<Long> parentIDs);

    @Query(value = "SELECT id From article WHERE id in ?1", nativeQuery = true)
    List<Long> getArticleById(List<Long> articleIds);

    @Query(value = "SELECT * From article WHERE parentid = ?1", nativeQuery = true)
    Optional<Article> findByParentId(Long parentID);
}
