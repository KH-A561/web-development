package ru.omsu.imit.web.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "APP_USER")
public class AppUser {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "ENCRYPTED_PASSWORD", nullable = false)
    private String encryptedPassword;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(
                    name = "USER_ID", referencedColumnName = "USER_ID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "ROLE_ID"
            ),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"USER_ID", "ROLE_ID"}
            )
    )
    private List<AppRole> appRoles;

    @Override
    public String toString() {
        return this.userName + "/" + this.encryptedPassword;
    }
}
