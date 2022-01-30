package com.example.tadalist.model;

import com.example.tadalist.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
    public class Category extends AbstractEntity {

//        @Column(unique=true)
        private String name;

        public Category() {
        }

        public Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
