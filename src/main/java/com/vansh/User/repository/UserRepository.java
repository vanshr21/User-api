package com.vansh.User.repository;

import com.vansh.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    Optional<User> findUserByIdAndIsDeletedFalse(Integer id);
    Optional<User> findUserById(Integer id);
    List<User> findUsersByIsDeletedFalse();
    boolean existsUserById(Integer id);

}
