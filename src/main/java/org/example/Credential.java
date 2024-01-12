package org.example;

public class Credential {

    private String description;
    private String username;
    private String password;


    public Credential(){

    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String passwordString) {
        this.password = passwordString;
    }

    public String getDescription(){
        return this.description;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void println(){
        System.out.println("\n-----------------------------------------------------------------\n");
        System.out.println("Website/App: " + this.description);
        System.out.println("Username: " + this.username);
        System.out.println("Password: " + this.password);
    }
}

