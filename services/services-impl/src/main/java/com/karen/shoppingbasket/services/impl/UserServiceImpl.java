package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.dto.user.UserDto;
import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.repository.UserRepository;
import com.karen.shoppingbasket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Long create(final UserDto userDto) {
        assertUserDto(userDto);
        final User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setBirthday(userDto.getBirthday());
        user.setRole(userDto.getRole());
        final User savedUser = userRepository.save(user);
        return savedUser.getId();
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

    private void assertUserDto(final UserDto userDto) {
        Assert.notNull(userDto, "User dto must not be null");
        Assert.hasText(userDto.getUsername(), "Username must not be null");
        Assert.hasText(userDto.getPassword(), "Password must not be null");
        Assert.hasText(userDto.getName(), "Name must not be null");
        Assert.hasText(userDto.getSurname(), "Surname must not be null");
        Assert.notNull(userDto.getBirthday(), "Birthday must not be null");
        Assert.notNull(userDto.getRole(), "Role must not be null");
    }

}
