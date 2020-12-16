package com.photoapp.api.users.service;

import org.springframework.security.core.userdetails.userDetailsService;

import com.photoapp.api.users.shared.UserDto;

public interface UsersServices extends userDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(UserDto email);
}