package com.api.restaurant.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.restaurant.exception.ResourceNotFoundException;
import com.api.restaurant.model.User;
import com.api.restaurant.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> findAll() {
        logger.info("Searching all users");
        return userRepository.findAll();
    }

    public User findById(Long id) {
        logger.info("Searching for user with ID: " + id);
        return userRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("User with ID " + id + " not found");
                return new ResourceNotFoundException("User", id);
            });
    }

    public User findByEmail(String email) {
        logger.info("Searching for user with email: " + email);
        User user = userRepository.findByEmail(email);

        if(user == null) {
            logger.error("User with email " + email + " not found");
            throw new ResourceNotFoundException("User not found with email: " + email);
        }

        return user;
    }

    public User create(User user) {
        logger.info("Creating user");
        return userRepository.save(user);
    }

    public User update(User user) {
        logger.info("Checking if user exists");
        User initialUser = userRepository.findById(user.getId())
            .orElseThrow(() -> {
                logger.error("User do not exists");
                return new ResourceNotFoundException("User", user.getId());
            });
            
        user.setOrders(initialUser.getOrders());
        user.setCart(initialUser.getCart());

        logger.info("Updating user");
        return userRepository.save(user);
    }

    public void delete(Long id) {
        logger.info("Checking if user exists");
        User userEntity = userRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("User do not exists");
                return new ResourceNotFoundException("User", id);
            });
        logger.info("Deleting user");
        userRepository.delete(userEntity);
    }
}
