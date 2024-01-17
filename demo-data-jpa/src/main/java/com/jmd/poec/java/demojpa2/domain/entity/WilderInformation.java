package com.jmd.poec.java.demojpa2.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wilder_informations")
public class WilderInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @OneToOne
    @JoinColumn(name="wilder_id",referencedColumnName = "id")
    private Wilder wilder;

    public WilderInformation() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Wilder getWilder() {
        return wilder;
    }

    public void setWilder(Wilder wilder) {
        this.wilder = wilder;
    }
}
