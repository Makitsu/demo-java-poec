package com.jmd.poec.java.demojpa2.controller;

import com.jmd.poec.java.demojpa2.domain.dto.AuthenticateDTO;
import com.jmd.poec.java.demojpa2.domain.dto.UserLightDTO;
import com.jmd.poec.java.demojpa2.service.AuthenticateService;
import com.jmd.poec.java.demojpa2.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticateService authenticateService;

    public UserController(UserService userService, AuthenticateService authenticateService) {
        this.userService = userService;
        this.authenticateService = authenticateService;
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthenticateDTO input){
        return authenticateService.authenticate(input);
    }

    @RequestMapping(value="",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST
    )
    public UserLightDTO create(@RequestBody AuthenticateDTO input){
        return userService.create(input);
    }

    /*
     *
     * step 1 : /authenticate -> recuperer un token
     *
     * step 2 : inserer le token dans les headers HTTP
     *
     */
}
