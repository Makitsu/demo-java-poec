package com.jmd.poec.java.demojpamongo.repository;

import com.jmd.poec.java.demojpamongo.domain.User;

import java.util.List;

public interface CustomUserRepository {

    List<User> customQuery(String username);

}
