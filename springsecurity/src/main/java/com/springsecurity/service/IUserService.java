package com.springsecurity.service;

import com.springsecurity.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserSec> findAll();
    Optional<UserSec> findById(Long id);
    UserSec save(UserSec userSec);
    void deleteById(Long id);
    void update(UserSec userSec);


}
