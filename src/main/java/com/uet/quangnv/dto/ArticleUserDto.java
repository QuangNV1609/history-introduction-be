package com.uet.quangnv.dto;

import com.uet.quangnv.ultis.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ArticleUserDto {
    private String username;
    private Long articleID;
    private String articleTitle;
    private Long coverImage;
    private Long quantity;

    private Date lastModifiedDate;

    private Date createAt;

    private Date lastDateView;

    public ArticleUserDto() {
    }

    public ArticleUserDto(Long articleID, String articleTitle, Long coverImage, Long quantity, Date lastModifiedDate, Date createAt) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.quantity = quantity;
        this.coverImage = coverImage;
        this.createAt = Utils.beforeSendDateToClient(createAt);
        this.lastModifiedDate = Utils.beforeSendDateToClient(lastModifiedDate);
    }

    public ArticleUserDto(String username, Long articleID, String articleTitle, Long coverImage, Long quantity, Date lastModifiedDate, Date createAt, Date lastDateView) {
        this.username = username;
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.quantity = quantity;
        this.coverImage = coverImage;
        this.createAt = Utils.beforeSendDateToClient(createAt);
        this.lastModifiedDate = Utils.beforeSendDateToClient(lastModifiedDate);
        this.lastDateView = Utils.beforeSendDateToClient(lastDateView);
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

    public Long getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Long coverImage) {
        this.coverImage = coverImage;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getLastDateView() {
        return lastDateView;
    }

    public void setLastDateView(Date lastDateView) {
        this.lastDateView = lastDateView;
    }
}
