package hisc.com.spring_2810.controller;

import hisc.com.spring_2810.Entity.Category;
import hisc.com.spring_2810.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listCategories(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Category> categories = categoryService.findAll(PageRequest.of(page, 5));
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam String name, @RequestParam(defaultValue = "0") int page, Model model) {
        Page<Category> categories = categoryService.search(name, PageRequest.of(page, 5));
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "category/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Category category) {
        category.setId(id);
        categoryService.save(category);
        return "redirect:/categories";
    }
}
