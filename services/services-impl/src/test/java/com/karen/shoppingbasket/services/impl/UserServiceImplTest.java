package com.karen.shoppingbasket.services.impl;

import com.karen.shoppingbasket.dto.user.UserDto;
import com.karen.shoppingbasket.entity.user.Role;
import com.karen.shoppingbasket.entity.user.User;
import com.karen.shoppingbasket.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Karen Arakelyan
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Test
    public void shouldCreateUser() {
        final UserDto userDto = createUserDto("username", "someStorngPass", "name", "surname", LocalDateTime.now(), Role.CUSTOMER);
        when(userRepository.save(isA(User.class))).thenAnswer((Answer<User>) invocationOnMock -> {
            final User user = invocationOnMock.getArgument(0);
            user.setId(123L);
            return user;
        });
        userService.create(userDto);
        verify(userRepository).save(userArgumentCaptor.capture());
        final User user = userArgumentCaptor.getValue();
        assertNotNull(user);
        assertEquals(Long.valueOf(123L), user.getId());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getName(), user.getName());
        assertEquals(userDto.getSurname(), user.getSurname());
        assertEquals(userDto.getBirthday(), user.getBirthday());
        assertEquals(userDto.getRole(), user.getRole());
    }

    @Test
    public void shouldThrowExceptionWhenUserCreationParametersAreIncorrect() {
        try {
            userService.create(null);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final UserDto userDto = createUserDto("", "someStorngPass", "name", "surname", LocalDateTime.now(), Role.CUSTOMER);
            userService.create(userDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final UserDto userDto = createUserDto("username", "", "name", "surname", LocalDateTime.now(), Role.CUSTOMER);
            userService.create(userDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final UserDto userDto = createUserDto("username", "someStorngPass", "", "surname", LocalDateTime.now(), Role.CUSTOMER);
            userService.create(userDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final UserDto userDto = createUserDto("username", "someStorngPass", "name", "", LocalDateTime.now(), Role.CUSTOMER);
            userService.create(userDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final UserDto userDto = createUserDto("username", "someStorngPass", "name", "surname", null, Role.CUSTOMER);
            userService.create(userDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
        try {
            final UserDto userDto = createUserDto("username", "someStorngPass", "name", "surname", LocalDateTime.now(), null);
            userService.create(userDto);
            fail();
        } catch (final IllegalArgumentException exp) {
            //Exception
        }
    }

    @Test
    public void shouldReturnUser() {
        final Long id = 123L;
        final User user = new User();
        when(userRepository.getOne(id)).thenReturn(user);
        final User result = userService.findById(id);
        verify(userRepository).getOne(id);
        assertEquals(user, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNull() {
        userService.findById(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowExceptionWhenNoUserFound() {
        when(userRepository.getOne(123L)).thenThrow(new EntityNotFoundException());
        userService.findById(123L);
    }

    @Test
    public void shouldReturnUserByUsername() {
        final String username = "username";
        final User user = new User();
        when(userRepository.findByUsername(username)).thenReturn(user);
        final User result = userService.findByUsername(username);
        verify(userRepository).findByUsername(username);
        assertEquals(user, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenUsernameIsNull() {
        userService.findByUsername(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowExceptionWhenNoUserFoundWithUsername() {
        when(userRepository.findByUsername("kuku")).thenReturn(null);
        userService.findByUsername("kuku");
    }


    private UserDto createUserDto(final String username, final String password, final String name, final String surname, final LocalDateTime birthday, final Role role) {
        final UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setName(name);
        userDto.setSurname(surname);
        userDto.setBirthday(birthday);
        userDto.setRole(role);
        return userDto;
    }

}
