package com.userservice.service;

import com.userservice.dto.UserRequest;
import com.userservice.dto.UserResponse;
import com.userservice.entity.User;
import com.userservice.exception.UserNotFoundException;
import com.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private UserResponse mapToResponse(User user){
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();

        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        List<User> all = userRepository.findAll();
        return all.stream().map(u->new UserResponse(u.getId(),u.getName(),u.getEmail())).toList();
    }

    public UserResponse getUserById(Long id) {

        return mapToResponse(userRepository.findById(id)
                .orElseThrow(()->
                        new UserNotFoundException("User not found with id:  "+id)
                ));

    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id)
                );

        existingUser.setName(userRequest.getName());
        existingUser.setEmail(userRequest.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return mapToResponse(updatedUser);
    }

    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id: " + id)
                );

        userRepository.delete(existingUser);
    }
}