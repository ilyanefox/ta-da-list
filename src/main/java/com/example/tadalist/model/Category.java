package com.example.tadalist.model;

import com.example.tadalist.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
    public class Category extends AbstractEntity {

        @Size(max = 25, message = "Sorry! Category cannot exceed 25 characters")
        private String name;

        @OneToMany(mappedBy= "category")
        private List<Task> tasks = new ArrayList<>();

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        public Category() {
        }

        public Category(String name, List<Task> tasks) {
            this.name = name;
            this.tasks = tasks;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
