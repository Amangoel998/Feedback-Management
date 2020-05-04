package com.cg.feedback.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="login_credentials")
@NamedNativeQuery(
    name = "LoginCredentials.validateUser",
    query = "SELECT * FROM login_credentials WHERE user_id=:id AND password=:pass",
    resultClass = LoginCredentials.class
)
public class LoginCredentials{
    @Id
    @Column(name="user_id")
    private String id;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    public LoginCredentials() {
        super();
    }
    public LoginCredentials(String id, String password, String role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "LoginCredentials [password=" + password + ", role=" + role + ", id=" + id + "]";
    }


}