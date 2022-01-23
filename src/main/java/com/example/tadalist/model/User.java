package com.example.tadalist.model;

import com.example.tadalist.AbstractEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

    @Entity
    public class User extends AbstractEntity {

        //    private String username;
        private String email;
        private String pwHash;

        private String firstName;

    @OneToMany
    private List<Category> categories = new ArrayList<Category>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> toDoList = new ArrayList<Task>();


        private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        public User() {
        }


        public User(String email, String pwHash, String firstName, List<Category> categories, List<Task> toDoList) {
            this.email = email;
            this.pwHash = pwHash;
            this.firstName = firstName;
        this.categories = categories;
        this.toDoList = toDoList;
        }

        public User(String firstName, List<Category> categories, List<Task> toDoList) {
            this.firstName = firstName;
        this.categories = categories;
        this.toDoList = toDoList;
        }

        public String getPwHash() {
            return pwHash;
        }

        public void setPwHash(String password) {
            this.pwHash = encoder.encode(password);
        }

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

    public List<Task> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<Task> toDoList) {
        this.toDoList = toDoList;
    }

    public boolean isMatchingPassword(String password) {
            return encoder.matches(password, pwHash);
        }

    }

