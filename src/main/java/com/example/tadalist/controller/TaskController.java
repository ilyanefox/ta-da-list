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
    public String displayTaskByCategoryId(@PathVariable("id") int id, Model model) {
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
//    @GetMapping("filter-priority")
//    public String displayTaskByPriority(Model model) {
//        List<Task> tasks = (List<Task>) taskRepository.findAll();
//        for (Task task : tasks) {
//            if (task.isPriority()) {
////                model.addAttribute("priority", isPriority);
//                model.addAttribute("task", task);
//                return "task/filter-priority";
//            } else {
//                return "task/list";
//            }
//        }
//        return "task/list";
//    }
//            return "task/list";

//        for (Task task : tasks) {
//            if (task.isPriority()) {
//                Task taskObj = task.get();
////                model.addAttribute("priority", isPriority);
//                model.addAttribute("task", task);
//                return "task/filter";
//            } else {
//                return "task/list";
//            }
//        model.addAttribute("categories", categoryRepository.findAll());
//        model.addAttribute("tasks", taskRepository.findByIsPriority(isPriority));
//        return "task/filter-priority";


    @GetMapping("filter-priority")
    public String displayTaskByPriority(Integer id, Boolean isPriority, Model model) {
//        Optional<Task> optTask = Optional.ofNullable(taskRepository.findByIsPriority(isPriority));
//        if (optTask.isPresent()) {
//            Task task = optTask.get();
//        Task task = taskRepository.findByIsPriority(isPriority);



             // Tried this on its own
//            model.addAttribute("tasks", taskRepository.findByIsPriority(isPriority));



        //Redundant?
        Optional<Task> optTask = taskRepository.findByIsPriority(isPriority);
        if (optTask.isPresent()) {
            Task task = optTask.get();
            if (task.isPriority()) {
                model.addAttribute("tasks", task);
                return "task/filter-priority";
            } else {
                return "task/list";
            }
        } else {
            return "task/list";
        }
    }
            //Didn't work, but at least message was from correct page
//        List<Task> tasks = (List<Task>) taskRepository.findAll();
//        for (Task task : tasks) {
//            if (task.isPriority()) {
////                Task taskObj = task.get();
////                model.addAttribute("priority", isPriority);
//                model.addAttribute("tasks", task);
//                return "task/filter";
//            } else {
//                return "task/list";
//            }
//        }
//        return "task/list";
//    }
//    }



    @GetMapping("/congrats")
    private String displayCongratsPage(Model model) {
        return "/task/congrats";
    }
}
