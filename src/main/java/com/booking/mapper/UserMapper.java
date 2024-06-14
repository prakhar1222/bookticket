package com.booking.mapper;

import com.booking.dto.UserDto;
import com.booking.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User fromUserDto(UserDto userDto){
        User user = new User();
        user.setLastName(user.getLastName());
        user.setFirstName(user.getFirstName());
        user.setEmail(user.getEmail());
        return user;
    }
}
