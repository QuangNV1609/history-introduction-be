package com.uet.quangnv.dto;

import com.uet.quangnv.entities.User;
import com.uet.quangnv.ultis.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    private String firstName;
    private String lastName;
    private Integer historicalPeriod;
    private Date createAt;
    private Date lastModifiedDate;

    private Long quantity;

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
                      String author,
                      Date createAt,
                      Date lastModifiedDate,
                      Long quantity) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.coverImage = coverImage;
        this.thumbnailImage = thumbnailImage;
        this.historyDay = Utils.beforeSendDateToClient(historyDay);
        this.postType = postType;
        this.historicalPeriod = historicalPeriod;
        this.status = status;
        this.username = username;
        this.author = author;
        this.createAt = Utils.beforeSendDateToClient(createAt);
        this.lastModifiedDate = Utils.beforeSendDateToClient(lastModifiedDate);
        this.quantity = quantity;
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
                      String author,
                      String firstName,
                      String lastName,
                      Date createAt,
                      Date lastModifiedDate,
                      Long quantity) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.coverImage = coverImage;
        this.thumbnailImage = thumbnailImage;
        this.historyDay = Utils.beforeSendDateToClient(historyDay);
        this.postType = postType;
        this.historicalPeriod = historicalPeriod;
        this.status = status;
        this.username = username;
        this.author = author;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createAt = Utils.beforeSendDateToClient(createAt);
        this.lastModifiedDate = Utils.beforeSendDateToClient(lastModifiedDate);
        this.quantity = quantity;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
