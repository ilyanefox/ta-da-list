package com.example.tadalist.controller;

import com.example.tadalist.data.CategoryRepository;
import com.example.tadalist.data.UserRepository;
import com.example.tadalist.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
////        Category category = new Category();
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        for (Category category : categories) {
            if (!category.getName().toLowerCase().equals(newCategory.getName().toLowerCase())) {
                categoryRepository.save(newCategory);
            }
        }
        return "redirect:/profile/";
    }
}
