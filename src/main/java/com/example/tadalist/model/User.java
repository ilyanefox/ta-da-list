package com.example.tadalist.model;

import com.example.tadalist.AbstractEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    //    private String username;
    private String email;
//    private String pwHash;

    private String firstName;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Category> categories = new ArrayList<>();


//    @OneToOne
//    private TaskList taskList;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Task> taskList = new ArrayList<>();


//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
    }


    public User(String email, String firstName, List<Category> categories, List<Task> taskList) {
        this.email = email;
//        this.pwHash = pwHash;
        this.firstName = firstName;
        this.categories = categories;
        this.taskList = taskList;
    }

    public User(String firstName, List<Category> categories) {
        this.firstName = firstName;
        this.categories = categories;
    }

//    public String getPwHash() {
//        return pwHash;
//    }
//
//    public void setPwHash(String password) {
//        this.pwHash = encoder.encode(password);
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

//    public boolean isMatchingPassword(String password) {
//        return encoder.matches(password, pwHash);
//    }

}

