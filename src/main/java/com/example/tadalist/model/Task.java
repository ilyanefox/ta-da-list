package com.example.tadalist.model;

import com.example.tadalist.AbstractEntity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
    public class Task extends AbstractEntity {

        @NotBlank(message = "Oops! A task name is required")
        @Size(max = 25, message = "Sorry! Task cannot exceed 25 characters")
        private String name;

        @NotNull(message = "Oops! A category is required")
        @ManyToOne
        @JoinColumn(name = "category_id", referencedColumnName = "id")
        private Category category;

        private boolean priority;


        public Task() {
        }

        public Task(String name, Category category, boolean priority) {
            this.name = name;
            this.category = category;
            this.priority = false;
        }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }


    }