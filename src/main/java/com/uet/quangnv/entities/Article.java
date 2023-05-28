package com.uet.quangnv.entities;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.dto.RoleDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "article")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ArticleDto",
                classes = {
                        @ConstructorResult(
                                targetClass = ArticleDto.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "title", type = String.class),
                                        @ColumnResult(name = "content", type = String.class),
                                        @ColumnResult(name = "history_day", type = Date.class),
                                        @ColumnResult(name = "status", type = Integer.class),
                                        @ColumnResult(name = "post_type", type = Integer.class),
                                        @ColumnResult(name = "historical_period", type = Integer.class),
                                        @ColumnResult(name = "thumbnail_image", type = Long.class),
                                        @ColumnResult(name = "cover_image", type = Long.class),
                                        @ColumnResult(name = "username", type = String.class),
                                        @ColumnResult(name = "author", type = String.class),
                                        @ColumnResult(name = "create_at", type = Date.class),
                                        @ColumnResult(name = "last_modified_date", type = Date.class),
                                        @ColumnResult(name = "quantity", type = Long.class)
                                })
                }
        ),
        @SqlResultSetMapping(
                name = "ArticleDtoDetails",
                classes = {
                        @ConstructorResult(
                                targetClass = ArticleDto.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Long.class),
                                        @ColumnResult(name = "title", type = String.class),
                                        @ColumnResult(name = "content", type = String.class),
                                        @ColumnResult(name = "history_day", type = Date.class),
                                        @ColumnResult(name = "status", type = Integer.class),
                                        @ColumnResult(name = "post_type", type = Integer.class),
                                        @ColumnResult(name = "historical_period", type = Integer.class),
                                        @ColumnResult(name = "thumbnail_image", type = Long.class),
                                        @ColumnResult(name = "cover_image", type = Long.class),
                                        @ColumnResult(name = "username", type = String.class),
                                        @ColumnResult(name = "author", type = String.class),
                                        @ColumnResult(name = "first_name", type = String.class),
                                        @ColumnResult(name = "last_name", type = String.class),
                                        @ColumnResult(name = "author", type = String.class),
                                        @ColumnResult(name = "create_at", type = Date.class),
                                        @ColumnResult(name = "last_modified_date", type = Date.class),
                                        @ColumnResult(name = "quantity", type = Long.class)
                                })
                }
        )}
)
public class Article extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "link")
    private String link;

    //Loại link: 0-hình ảnh, 1-video, 2-âm thanh.
    @Column
    private Integer typeLink;

    @Column(name = "coverImage")
    private Long coverImage;

    @Column(name = "thumbnailImage")
    private Long thumbnailImage;

    @Temporal(TemporalType.DATE)
    @Column(name = "historyDay")
    private Date historyDay;

    @Column(name = "postType")
    private Integer postType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "parentID")
    private Long parentID;
    @Column(name = "historicalPeriod")
    private Integer historicalPeriod;

    @Column(name = "version")
    private Integer version;

    public Article() {
    }

    public Article(String title, String content, Date historyDay, Integer postType, Integer historicalPeriod, Long parentID) {
        this.title = title;
        this.content = content;
        this.historyDay = historyDay;
        this.postType = postType;
        this.parentID = parentID;
        this.historicalPeriod = historicalPeriod;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getHistoricalPeriod() {
        return historicalPeriod;
    }

    public void setHistoricalPeriod(Integer historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
