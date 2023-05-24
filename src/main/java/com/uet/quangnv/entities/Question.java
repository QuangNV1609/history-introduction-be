package com.uet.quangnv.entities;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.dto.QuestionDto;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "question")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "QuestionDto",
                classes = {
                        @ConstructorResult(
                                targetClass = QuestionDto.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "content", type = String.class),
                                        @ColumnResult(name = "status", type = Integer.class),
                                        @ColumnResult(name = "article_id", type = Date.class),
                                        @ColumnResult(name = "title", type = String.class)
                                })
                }
        )}
)
public class Question extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "content")
    private String content;

    @Column(name = "articleId")
    private Long articleId;

    @Column(name = "status")
    private Integer status;

    public Question() {
    }

    public Question(Long id, String content, Long articleId) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
