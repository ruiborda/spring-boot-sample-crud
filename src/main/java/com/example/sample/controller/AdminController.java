package com.example.sample.controller;

import com.example.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("products", productService.findAll().getProducts());
        model.addAttribute("currentPage", "dashboard");
        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String productList(Model model) {
        model.addAttribute("products", productService.findAll().getProducts());
        model.addAttribute("currentPage", "products");
        return "admin/products/list";
    }

    @GetMapping("/products/create")
    public String createProductForm(Model model) {
        model.addAttribute("currentPage", "products");
        return "admin/products/create";
    }

    @GetMapping("/products/{id}/edit")
    public String editProductForm(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("currentPage", "products");
        return "admin/products/edit";
    }
}