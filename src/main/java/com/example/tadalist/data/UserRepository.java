package com.example.tadalist.data;

import com.example.tadalist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasAuthenticationCallbackHandler;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    User findByFirstName(String firstName);
    Optional<User> findById(Integer userId);
}
