package com.example.tadalist.data;

import com.example.tadalist.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByFirstName(String firstName);
    Optional<User> findById(Integer userId);
}
