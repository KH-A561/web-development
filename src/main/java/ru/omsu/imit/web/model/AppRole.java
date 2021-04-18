package ru.omsu.imit.web.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "APP_ROLE")
public class AppRole {
    @Id
    @GeneratedValue
    @Column(name="ROLE_ID", nullable = false)
    private Long roleId;

    @Column(name="ROLE_NAME", nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "appRoles")
    private List<AppUser> users;
}
