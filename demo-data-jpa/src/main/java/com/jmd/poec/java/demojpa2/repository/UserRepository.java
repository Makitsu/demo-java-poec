package com.jmd.poec.java.demojpa2.repository;

import com.jmd.poec.java.demojpa2.domain.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findFirstByUsername(String username);

    boolean existsByUsername(String username);
}
