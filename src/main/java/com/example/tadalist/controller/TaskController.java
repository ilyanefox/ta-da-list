package com.example.tadalist.controller;

import com.example.tadalist.data.CategoryRepository;
import com.example.tadalist.data.TaskRepository;
import com.example.tadalist.model.Category;
import com.example.tadalist.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("task")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/add")
    private String displayAddTaskForm(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute(new Task());
        return "/task/add";
    }

    @PostMapping("/add")
    private String processAddTask(Model model, @ModelAttribute @Valid Task newTask, Errors errors) {

        if (errors.hasErrors()) {
            return "/task/add";
        }
        taskRepository.save(newTask);
        return "redirect:/task/add";
    }

    @GetMapping("/list")
    private String displayTaskList(Model model) {

        model.addAttribute("tasks", taskRepository.findAll());
        return "/task/list";
    }
}
