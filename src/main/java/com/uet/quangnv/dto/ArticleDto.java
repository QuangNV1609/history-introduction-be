package com.uet.quangnv.dto;

import javax.persistence.*;
import java.util.Date;

public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String link;
    //Loại link: 0-hình ảnh, 1-video, 2-âm thanh.
    private Integer typeLink;
    private Long coverImage;
    private Long thumbnailImage;
    private Date historyDay;
    private Integer postType;
    private Integer status;
    private Long parentID;
    private String author;
    private String username;
    private Integer historicalPeriod;

    public ArticleDto() {
    }

    public ArticleDto(Long id,
                      String title,
                      String content,
                      Date historyDay,
                      Integer status,
                      Integer postType,
                      Integer historicalPeriod,
                      Long thumbnailImage,
                      Long coverImage,
                      String username,
                      String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.coverImage = coverImage;
        this.thumbnailImage = thumbnailImage;
        this.historyDay = historyDay;
        this.postType = postType;
        this.historicalPeriod = historicalPeriod;
        this.status = status;
        this.username = username;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getTypeLink() {
        return typeLink;
    }

    public void setTypeLink(Integer typeLink) {
        this.typeLink = typeLink;
    }

    public Long getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Long coverImage) {
        this.coverImage = coverImage;
    }

    public Long getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(Long thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public Date getHistoryDay() {
        return historyDay;
    }

    public void setHistoryDay(Date historyDay) {
        this.historyDay = historyDay;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getParentID() {
        return parentID;
    }

    public void setParentID(Long parentID) {
        this.parentID = parentID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHistoricalPeriod() {
        return historicalPeriod;
    }

    public void setHistoricalPeriod(Integer historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }
}
