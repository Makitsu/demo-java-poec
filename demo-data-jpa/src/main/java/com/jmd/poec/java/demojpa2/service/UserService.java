package com.jmd.poec.java.demojpa2.service;

import com.jmd.poec.java.demojpa2.domain.dto.AuthenticateDTO;
import com.jmd.poec.java.demojpa2.domain.dto.UserLightDTO;
import com.jmd.poec.java.demojpa2.domain.entity.User;
import com.jmd.poec.java.demojpa2.domain.exception.UserException;
import com.jmd.poec.java.demojpa2.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found from username "+username));
    }

    public UserLightDTO create(AuthenticateDTO input) {

        if(Strings.isBlank(input.getPassword()) || Strings.isBlank(input.getUsername())){
            throw new UserException(HttpStatus.INTERNAL_SERVER_ERROR,"Request not valid !");
        }

        if(userRepository.existsByUsername(input.getUsername())){
            throw new UserException(HttpStatus.CONFLICT,"User already exists !");
        }

        User newUser = new User();
        newUser.setUsername(input.getUsername());
        newUser.setPassword(passwordEncoder.encode(input.getPassword()));

        User savedUser = userRepository.save(newUser);

        return modelMapper.map(savedUser,UserLightDTO.class);
    }
}
