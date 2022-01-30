package com.example.tadalist.model;

import com.example.tadalist.AbstractEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull(message = "Oops! You need a first name")
    private String firstName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String pwHash;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Category> categories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Task> taskList = new ArrayList<>();


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
    }

    public User(String firstName, String email, String password) {
        this.firstName = firstName;
        this.email = email;
        this.pwHash = encoder.encode(password);
    }

    public User(String firstName, String email, String password, List<Category> categories, List<Task> taskList) {
        this.firstName = firstName;
        this.email = email;
        this.pwHash = encoder.encode(password);
        this.categories = categories;
        this.taskList = taskList;
    }

//    public User(String firstName, List<Category> categories) {
//        this.firstName = firstName;
//        this.categories = categories;
//    }

    public String getFirstName() {
        return firstName;
    }


    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}

