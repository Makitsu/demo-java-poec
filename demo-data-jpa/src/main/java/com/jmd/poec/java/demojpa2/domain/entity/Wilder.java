package com.jmd.poec.java.demojpa2.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="wilders")
public class Wilder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "wilder_email")
    private String email;

    private String category;

    @OneToOne(mappedBy = "wilder", cascade = CascadeType.ALL)
    private WilderInformation wilderInformation;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "wilders_skills",
            joinColumns = @JoinColumn(name = "wilder_id"),
            inverseJoinColumns = @JoinColumn(name= "skill_id")
    )
    private List<Skill> skills;

    public Wilder() {
    }

    public Wilder(String name, String email, String category) {
        this.name = name;
        this.email = email;
        this.category = category;
    }

    public Wilder(Long id, String name, String email, String category) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.category = category;
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

    public WilderInformation getWilderInformation() {
        return wilderInformation;
    }

    public void setWilderInformation(WilderInformation wilderInformation) {
        this.wilderInformation = wilderInformation;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
