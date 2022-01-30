package com.example.tadalist.data;

import com.example.tadalist.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
//    Optional<User> findById(Integer userId);
}
