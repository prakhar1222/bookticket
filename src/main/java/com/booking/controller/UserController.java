package com.booking.controller;

import com.booking.dto.Response;
import com.booking.dto.UserDto;
import com.booking.entity.User;
import com.booking.mapper.UserMapper;
import com.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Response> getAllUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(generateResponse("Successfully USer fetched!!",
                HttpStatus.OK.name(), userRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody UserDto userInput) {
        User user  = userMapper.fromUserDto(userInput);
        userRepository.save(user);
        return ResponseEntity.ok().body(generateResponse("Successfully USer added!!",
                HttpStatus.OK.name(), userRepository.findAll()));
    }

    private Response generateResponse(String message, String status, Object responseData) {
        Response response = new Response();
        response.setMessage(message);
        response.setStatus(status);
        response.setResponseData(responseData);

        return response;
    }

    @PutMapping
    public  ResponseEntity<Response> updateUserSeat(@RequestBody UserDto userDto) {
        Long id = userDto.getId();
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        User userU  = user.get();
        userU.setFirstName(userDto.getFirstName());
        userU.setLastName(userDto.getLastName());
        userU.setEmail(userDto.getEmailId());
        userRepository.save(userU);
        return ResponseEntity.ok().body(generateResponse("Successfully USer updated!!",
                HttpStatus.OK.name(), null));
    }

    @DeleteMapping
    public  ResponseEntity<Response> deleteUserFromTrain(@RequestBody UserDto userInput) {
        User user  = userMapper.fromUserDto(userInput);
        userRepository.delete(user);
        return ResponseEntity.ok().body(generateResponse("Successfully USer deleted!!",
                HttpStatus.OK.name(), null));
    }
}