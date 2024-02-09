package com.jmd.poec.java.demojpa2.domain.dto;

import java.util.List;

public class WilderFullDTO {

    private Long id;

    private String name;

    private String email;

    private String category;

    private List<WilderInformationDTO> informationContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<WilderInformationDTO> getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(List<WilderInformationDTO> informationContent) {
        this.informationContent = informationContent;
    }

    public WilderFullDTO(Long id, String name, String email, String category) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.category = category;
    }

    public WilderFullDTO() {
    }
}
