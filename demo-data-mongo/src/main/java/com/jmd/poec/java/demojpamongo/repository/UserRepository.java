package com.jmd.poec.java.demojpamongo.repository;

import com.jmd.poec.java.demojpamongo.domain.Skill;
import com.jmd.poec.java.demojpamongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends MongoRepository<User,String>,CustomUserRepository {

    Optional<User> findFirstByUsernameIgnoreCase(String username);

//    @Query("{ skills : { $eq :  ?0 } }")
//    List<User> customQuery(Skill skill);

}
