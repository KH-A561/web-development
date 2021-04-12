package ru.omsu.imit.web.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "APP_USER")
public class AppUser {
    @Id
    @Column(name="USER_ID", nullable = false)
    private Long userId;
    @Column(name="USER_NAME", nullable = false)
    private String userName;
    @Column(name="ENCRYPTED_PASSWORD", nullable = false)
    private String encryptedPassword;

    @Override
    public String toString() {
        return this.userName + "/" + this.encryptedPassword;
    }
}
