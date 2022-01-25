package com.example.tadalist.model;

import com.example.tadalist.AbstractEntity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
    public class Task extends AbstractEntity {

        @NotBlank(message = "Oops! A task name is required")
        @Size(max = 25, message = "Sorry! Task cannot exceed 25 characters")
        private String name;

        @ManyToOne(cascade = CascadeType.ALL)
//        @JoinColumn(name = "category_id", referencedColumnName = "id")
        private Category category;

        public Task() {
        }

        public Task(String name, Category category) {
            this.name = name;
            this.category = category;
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
