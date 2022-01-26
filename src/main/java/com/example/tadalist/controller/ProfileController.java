package com.example.tadalist.controller;

import com.example.tadalist.data.CategoryRepository;
import com.example.tadalist.data.UserRepository;
import com.example.tadalist.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("profile")
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    @GetMapping("")
    public String getCategories(Model model) {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("category", new Category());
        return "profile";
    }

    @PostMapping("")
    public String createCategory(Model model, @ModelAttribute Category newCategory) {
//        String categoryName = newCategory.getName().toLowerCase();

//        Category category = new Category();
//        List<Category> categories = (List<Category>) categoryRepository.findAll();
//        for (Category category : categories) {
//            if (!category.getName().toLowerCase().equals(newCategory.getName().toLowerCase()))
//    {

                categoryRepository.save(newCategory);


        return "redirect:/profile/";
    }


    @PostMapping("/delete")
    public String deleteCategory(Model model, @ModelAttribute String name) {

        categoryRepository.deleteByName(name);
        return "redirect:/profile/";
    }

//    @GetMapping("/delete")
//    public String getCategoriesToDelete(Model model) {
//        List<Category> categories = (List<Category>) categoryRepository.findAll();
//        model.addAttribute("categories", categories);
//        model.addAttribute("category", new Category());
//        return "/delete";
//    }
//
//    @PostMapping("/delete")
//    public String deleteCategory(Model model, @ModelAttribute Category oldCategory) {
//        List<Category> categories = (List<Category>) categoryRepository.findAll();
//        for (Category category : categories) {
//            if (category.getName().equals(oldCategory.getName())) {
////
//                categoryRepository.delete(category);
//            }
//        }
//        return "/profile";
//    }
}



