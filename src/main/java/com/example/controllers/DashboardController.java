package com.example.controllers;

import com.example.entities.Product;
import com.example.repositories.ProductRepository;
import com.example.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {
    final ProductService productService;

    public DashboardController(ProductService productService) {
        this.productService = productService;
    }

    String deleteStatus = "";

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("ls", productService.list());
        model.addAttribute("deleteStatus", deleteStatus);
        deleteStatus = "";
        return "dashboard";
    }

    @PostMapping("/productSave")
    public String productSave(Product product) {
        productService.save(product);
        return "redirect:/dashboard";
    }

    @GetMapping("/productDelete/{pid}")
    public String productDelete(@PathVariable int pid) {
        productService.delete(pid);
        deleteStatus = "Product deleted " + pid;
        return "redirect:/dashboard";
    }


    @GetMapping("/update/{pid}")
    public String update(@PathVariable(value = "pid") int pid, Model model) {
        Product product = productService.getProductById(pid);
        model.addAttribute("product", product);
        return "update";
    }

}
