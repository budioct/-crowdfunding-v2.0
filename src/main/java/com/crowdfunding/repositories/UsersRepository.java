package com.crowdfunding.repositories;

import com.crowdfunding.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    //@Modifying(clearAutomatically = true)
    //@Transactional
    //@Query(value = "SELECT us.name FROM users us WHERE name = :#{#users.name}", nativeQuery = true)
//    Optional<Users> findByUsername(String username);

    Optional<Users> findUsersByName(String name);


}
