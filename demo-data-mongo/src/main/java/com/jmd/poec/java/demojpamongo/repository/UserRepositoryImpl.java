package com.jmd.poec.java.demojpamongo.repository;

import com.jmd.poec.java.demojpamongo.domain.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> customQuery(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("password").is("TEST"));
        return mongoTemplate.find(query, User.class);
    }
}
