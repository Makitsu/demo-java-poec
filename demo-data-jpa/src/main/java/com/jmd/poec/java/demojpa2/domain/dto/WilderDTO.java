package com.jmd.poec.java.demojpa2.domain.dto;

import java.util.List;
import java.util.Set;

public class WilderDTO {

    private Long id;

    private String name;

    private String email;

    private String category;

    private String informationContent;

    private Set<String> skillTitles;

    public WilderDTO() {
    }

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

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }

    public Set<String> getSkillTitles() {
        return skillTitles;
    }

    public void setSkillTitles(Set<String> skillTitles) {
        this.skillTitles = skillTitles;
    }
}
