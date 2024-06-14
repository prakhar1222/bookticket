package com.booking.controller;

import com.booking.dto.Response;
import com.booking.dto.UserDto;
import com.booking.entity.User;
import com.booking.mapper.UserMapper;
import com.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody UserDto userInput) {
        User user  = userMapper.fromUserDto(userInput);
        userRepository.save(user);
        return ResponseEntity.ok().body(generateResponse("Successfully USer added!!",
                HttpStatus.OK.name(), null));
    }

    private Response generateResponse(String message, String status, Object responseData) {
        Response response = new Response();
        response.setMessage(message);
        response.setStatus(status);
        response.setResponseData(responseData);

        return response;
    }
}
