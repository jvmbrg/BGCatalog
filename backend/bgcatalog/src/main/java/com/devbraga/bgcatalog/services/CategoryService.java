package com.devbraga.bgcatalog.services;

import com.devbraga.bgcatalog.dto.CategoryDTO;
import com.devbraga.bgcatalog.entities.Category;
import com.devbraga.bgcatalog.repositories.CategoryRepository;
import com.devbraga.bgcatalog.services.exceptions.DatabaseException;
import com.devbraga.bgcatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new CategoryDTO(category);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){

        if (!categoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

        try {
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }

    }

}
