package com.jmd.poec.java.demojpamongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@Builder
public class User {

    // ObjectId, bson...

    @Id
    private String id;

    private String username;

    private String password;

    private List<Skill> skills;


}
