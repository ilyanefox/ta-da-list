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
import java.util.Optional;

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

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("tasks", taskRepository.findAll());
        return "/task/list";
    }

    @GetMapping("view/{id}")
    public String checkOffTask(@PathVariable("id") Integer id, Model model) {
        Optional<Task> optTask = taskRepository.findById(id);
        if (optTask.isPresent()) {
            Task task = optTask.get();
            model.addAttribute("task", task);
            taskRepository.delete(task);
            return "/task/congrats";
        } else {
            return "task/list";

        }
    }

    @GetMapping("filter/{id}")
    public String displayTaskByCategoryId(@PathVariable("id") int id, Category category, Model model) {
        Optional<Category> optCategory = categoryRepository.findById(id);
        if (optCategory.isPresent()) {
            Category categoryObj = optCategory.get();
            model.addAttribute("category", categoryObj);
            model.addAttribute("tasks", categoryObj.getTasks());

            return "task/filter";
//
            } else {
                return "task/list";
            }

        }

    @GetMapping("filter-priority")
    public String displayTaskByPriority(Model model) {
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        for (Task task : tasks) {
            if (task.isPriority()) {
//                model.addAttribute("priority", isPriority);
                model.addAttribute("task", task);
                return "task/filter";
            } else {
                return "task/list";
            }
        }
        return "task/list";
    }
//        Task task = taskRepository.findByPriority(isPriority);
//        if (optTask.isPresent()) {
//            Task task = optTask.get();
//    public String deleteUser(@PathVariable("id") Integer id, Model model) {
//        Optional<Task> optTask = taskRepository.findById(id);
//        if (optTask.isPresent()) {
//            Task task =  optTask.get();
////            taskRepository.delete(optTask);
//            taskRepository.delete(task);
//            return "/task/congrats";
//        } else {
//            return "/list";
//        task.setCompleted(true);
//    taskRepository.save(task);




    @GetMapping("/congrats")
    private String displayCongratsPage(Model model) {
        return "/task/congrats";
    }
}
