package com.jmd.poec.java.demojpa2.domain.dto;

public class AuthenticateDTO {

    String username;
    String password;

    public AuthenticateDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticateDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
