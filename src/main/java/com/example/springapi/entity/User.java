package com.example.springapi.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    public int getID(){
        return id;
    }

    public int setID(int id){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String admin) {
        this.username = admin;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getRoleAuthorityName(){
        return role.getAuthorityName();
    }

    public enum Role {
        ADMIN("ROLE_ADMIN"),
        CUSTOMER("ROLE_CUSTOMER");

       Role(String authorityName) {
           this.authorityName = authorityName;
       }

       private String authorityName;

       public String getAuthorityName() {return authorityName;}
    }
}