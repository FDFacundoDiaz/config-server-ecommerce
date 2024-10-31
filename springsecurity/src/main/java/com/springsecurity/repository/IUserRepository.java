package com.springsecurity.repository;

import com.springsecurity.model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserSec, Long> {

    //Tmb se puede hacer mediante Query pero en este caso no es necesario
    Optional<UserSec> findUserEntityByUsername(String username);


}
