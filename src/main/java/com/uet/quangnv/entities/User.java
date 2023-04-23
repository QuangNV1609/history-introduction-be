package com.uet.quangnv.entities;

import com.uet.quangnv.dto.ArticleDto;
import com.uet.quangnv.dto.UserDto;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "User")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "UserDto",
                classes = {
                        @ConstructorResult(
                                targetClass = UserDto.class,
                                columns = {
                                        @ColumnResult(name = "username", type = String.class),
                                        @ColumnResult(name = "first_name", type = String.class),
                                        @ColumnResult(name = "last_name", type = String.class),
                                        @ColumnResult(name = "birth_day", type = Date.class),
                                        @ColumnResult(name = "imageid", type = Long.class)
                                })
                }
        )}
)
public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "isActive")
    private Boolean isActive;

    @OneToOne(mappedBy = "user")
    private UserInfo userInfo;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createAt")
    private Date createAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastModifiedDate")
    private Date lastModifiedDate;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "createBy")
    private User createBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "lastModifiedBy")
    private User lastModifiedBy;

    @ManyToMany
    @JoinTable(name = "UserRole",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles;

    public User() {
    }

    public User(String username, String password, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", createAt=" + createAt +
                ", lastModifiedDate=" + lastModifiedDate +
                ", createBy=" + createBy +
                ", lastModifiedBy=" + lastModifiedBy +
                ", roles=" + roles +
                '}';
    }
}
