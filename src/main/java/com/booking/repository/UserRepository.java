package com.booking.repository;

import com.booking.dto.UserDto;
import com.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(UserDto userInput);
}