package com.devbraga.bgcatalog.services;

import com.devbraga.bgcatalog.dto.CategoryDTO;
import com.devbraga.bgcatalog.entities.Category;
import com.devbraga.bgcatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> result = new ArrayList<>();
        result = categoryRepository.findAll();

        return result.stream().map(x -> new CategoryDTO(x)).toList();
    }

}
