package com.authservice.service;

import com.authservice.client.UserServiceClient;
import com.authservice.dto.RegisterRequest;
import com.authservice.dto.UserRequest;
import com.authservice.dto.UserResponse;
import com.authservice.entity.AuthUser;
import com.authservice.exception.EmailAlreadyExistsException;
import com.authservice.repository.AuthUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public final UserServiceClient userServiceClient;
    public final AuthUserRepository authUserRepository;
    public final PasswordEncoder passwordEncoder;


    public AuthService(UserServiceClient userServiceClient, AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.userServiceClient = userServiceClient;
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse registerUser(RegisterRequest registerRequest){
        if(authUserRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        UserRequest userRequest = new UserRequest();
        userRequest.setName(registerRequest.getName());
        userRequest.setEmail(registerRequest.getEmail());

        UserResponse userData = userServiceClient.createUser(userRequest);
        AuthUser authUser = new AuthUser();

        authUser.setEmail(registerRequest.getEmail());
        authUser.setUserId(userData.getId());
        authUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        authUserRepository.save(authUser);
        return userData;
    }




}
