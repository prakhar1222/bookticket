package com.booking.controller;

import com.booking.dto.Response;
import com.booking.dto.UserDto;
import com.booking.entity.User;
import com.booking.mapper.UserMapper;
import com.booking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;



    @Mock
    UserMapper userMapper;

    @Mock
    UserRepository userRepository;

    @Test
    public void testCreateUser() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        UserDto userInput = new UserDto();
        userInput.setFirstName("Test User");
        User userToBeAdded  = new User();
        userToBeAdded.setFirstName("Test User");
        when(userMapper.fromUserDto(any(UserDto.class))).thenReturn(userToBeAdded);
        ResponseEntity<Response> result = userController.createUser(userInput);
        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }


}
