package org.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity // create entity using Credential class
public class Credential {

    @Id // used to denote primary key
    @Column(name = "description")
    private String description;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;


    public Credential() {

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String passwordString) {
        this.password = passwordString;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void println() {
        System.out.println("\n-----------------------------------------------------------------\n");
        System.out.println("Website/App: " + this.description);
        System.out.println("Username: " + this.username);
        System.out.println("Password: " + this.password);
    }
}

