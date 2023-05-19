package com.uet.quangnv.dto;

public class ArticleUserDto {
    private String username;
    private Long articleID;
    private String articleTitle;
    private Long quantity;

    public ArticleUserDto() {
    }

    public ArticleUserDto(Long articleID, String articleTitle, Long quantity) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.quantity = quantity;
    }

    public ArticleUserDto(String username, Long articleID, String articleTitle, Long quantity) {
        this.username = username;
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
