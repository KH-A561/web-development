package ru.omsu.imit.web.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "APP_ROLE")
public class AppRole {
    @Id
    @Column(name="ROLE_ID", nullable = false)
    private Long roleId;
    @Column(name="ROLE_NAME", nullable = false)
    private String roleName;
}
