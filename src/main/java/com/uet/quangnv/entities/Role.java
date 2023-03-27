package com.uet.quangnv.entities;

import com.uet.quangnv.dto.RoleDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role")
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "Role",
                classes = {
                        @ConstructorResult(
                                targetClass = RoleDto.class,
                                columns = {
                                        @ColumnResult(name = "id", type = Integer.class),
                                        @ColumnResult(name = "role_name", type = String.class)
                                })
                }
        )}
)
public class Role {
    @Id
    private Integer id;
    @Column(name = "roleName")
    private String roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
