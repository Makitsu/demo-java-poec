package com.jmd.poec.java.demojpamongo.service;

import com.jmd.poec.java.demojpamongo.domain.Skill;
import com.jmd.poec.java.demojpamongo.domain.User;
import com.jmd.poec.java.demojpamongo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void populateDatabase(){


        Skill angular = Skill.builder().title("ANGULAR").build();
        Skill java = Skill.builder().title("JAVA").build();

        User user = new User(null,"TOTO","TOTO", List.of(java));

        userRepository.save(user);

//        Optional<User> oUser = userRepository.findFirstByUsernameIgnoreCase("test");
//
//        oUser.ifPresent(us -> log.info("User : "+us));

//        List<User> users = userRepository.customQuery(angular);
//
//        users.forEach(us -> log.info("User found ! :"+us));


        // mongoTemplate

        List<User> foundUsers = userRepository.customQuery("TEST");

        foundUsers.forEach(us -> log.info("User found ! :"+us));
    }


}
