package com.devbraga.bgcatalog.resources;

import com.devbraga.bgcatalog.dto.CategoryDTO;
import com.devbraga.bgcatalog.entities.Category;
import com.devbraga.bgcatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity <List<CategoryDTO>> findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }
}
