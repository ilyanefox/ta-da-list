package com.example.tadalist.data;

import com.example.tadalist.model.Category;
import com.example.tadalist.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findByCategory(Category category);
}
