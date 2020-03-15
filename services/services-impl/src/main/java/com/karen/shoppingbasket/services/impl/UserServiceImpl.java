package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.repository.UserRepository;
import com.karen.shoppingbasket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;

/**
 * @author Karen Arakelyan
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(final Long id) {
        Assert.notNull(id, "User id must not be null");
        return userRepository.getOne(id);
    }

    @Override
    public User findByUsername(final String username) {
        Assert.hasText(username, "Username must not be empty");
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException(String.format("Not found user with username '%s'", username));
        }
        return user;
    }
}
