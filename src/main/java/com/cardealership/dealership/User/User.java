package com.cardealership.dealership.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.util.DigestUtils;

import javax.persistence.*;

import javax.persistence.GeneratedValue;


@Entity
@Data
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @JsonIgnore
    public String getPassword(){
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password){
        this.password = DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public void updatePassword(String password){
        this.password = password;
    }

}
