package com.booking.mapper;

import com.booking.dto.UserDto;
import com.booking.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromUserDto(UserDto userDto){
        User user = new User();
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmailId());
        return user;
    }
}