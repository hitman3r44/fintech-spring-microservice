package com.wolverinesolution.securityapp.user.model;

//import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
//    @Length(min = 3, max = 6)
//    @ColumnTransformer(
//            write = "HEX(AES_ENCRYPT(?, 'password'))",
//            read = "AES_DECRYPT(UNHEX(password),'password')"
//    )
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
