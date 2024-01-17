package com.jmd.poec.java.demojpa2.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="wilder_skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "skills",cascade = CascadeType.ALL)
    private List<Wilder> wilder;

    public Skill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Wilder> getWilder() {
        return wilder;
    }

    public void setWilder(List<Wilder> wilder) {
        this.wilder = wilder;
    }

    public Skill(String title) {
        this.title = title;
    }
}
