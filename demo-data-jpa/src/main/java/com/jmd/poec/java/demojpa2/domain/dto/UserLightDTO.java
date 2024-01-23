package com.jmd.poec.java.demojpa2.domain.dto;

public class UserLightDTO {

    private Long id;
    private String username;


    public UserLightDTO() {
    }

    public UserLightDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
