package com.study.springsecurity.user.repository;

import com.study.springsecurity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);

    boolean existsById(Long id);

    void delete(User user);

}
