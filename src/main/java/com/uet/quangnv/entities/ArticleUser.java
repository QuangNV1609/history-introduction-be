package com.uet.quangnv.entities;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.dto.ArticleUserDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "articleUser")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ArticleUserDto",
                classes = {
                        @ConstructorResult(
                                targetClass = ArticleUserDto.class,
                                columns = {
                                        @ColumnResult(name = "username", type = String.class),
                                        @ColumnResult(name = "article_id", type = Long.class),
                                        @ColumnResult(name = "title", type = String.class),
                                        @ColumnResult(name = "quantity", type = Long.class)

                                })
                }
        )},
        @SqlResultSetMapping(
                name = "ArticleUserDtoView",
                classes = {
                        @ConstructorResult(
                                targetClass = ArticleUserDto.class,
                                columns = {
                                        @ColumnResult(name = "article_id", type = Long.class),
                                        @ColumnResult(name = "title", type = String.class),
                                        @ColumnResult(name = "quantity", type = Long.class)

                                })
                }
        )}
)
public class ArticleUser {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "articleId")
    private Long articleId;
    @Column(name = "quantity")
    private Long quantity;

    public ArticleUser() {
    }

    public ArticleUser(String username, Long articleId, Long quantity) {
        this.username = username;
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
