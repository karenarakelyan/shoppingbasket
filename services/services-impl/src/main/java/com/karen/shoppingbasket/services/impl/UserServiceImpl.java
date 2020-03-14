package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.repository.UserRepository;
import com.karen.shoppingbasket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

}
