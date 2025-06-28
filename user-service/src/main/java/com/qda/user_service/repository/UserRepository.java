package com.qda.user_service.repository;

import com.qda.user_service.entitty.User;
import com.qda.user_service.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email) throws UserNotFoundException;
}
