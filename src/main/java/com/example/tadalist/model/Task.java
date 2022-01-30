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

        @ManyToOne(cascade=CascadeType.PERSIST)
        @JoinColumn(name = "category_id", referencedColumnName = "id")
        private Category category;

        private boolean isPriority;


        public Task() {
        }

        public Task(String name, Category category, boolean isPriority) {
            this.name = name;
            this.category = category;
            this.isPriority = false;
        }

    public boolean isPriority() {
        return isPriority;
    }

    public void setPriority(boolean priority) {
        isPriority = priority;
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