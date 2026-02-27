package com.devbraga.bgcatalog.services;

import com.devbraga.bgcatalog.dto.CategoryDTO;
import com.devbraga.bgcatalog.entities.Category;
import com.devbraga.bgcatalog.entities.CategoryLog;
import com.devbraga.bgcatalog.enuns.OperationType;
import com.devbraga.bgcatalog.repositories.CategoryLogRepository;
import com.devbraga.bgcatalog.repositories.CategoryRepository;
import com.devbraga.bgcatalog.services.exceptions.DatabaseException;
import com.devbraga.bgcatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryLogRepository categoryLogRepository;

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

    @Transactional
    public CategoryDTO insert(CategoryDTO dto){
        try{
            Category entity = new Category();
            copyDtoToEntity(dto, entity);
            entity = categoryRepository.save(entity);

            CategoryLog log = new CategoryLog();
            log.setMoment(Instant.now());
            log.setOperationType(OperationType.CREATE);
            log.setCategory(entity);
            categoryLogRepository.save(log);


            return new CategoryDTO(entity);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Não é possivel inserir duas categorais com o mesmo nome");
        }

    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto){
        try{
            Category entity = categoryRepository.getReferenceById(id);
            copyDtoToEntity(dto,entity);
            entity = categoryRepository.save(entity);

            CategoryLog log = new CategoryLog();
            log.setMoment(Instant.now());
            log.setOperationType(OperationType.UPDATE);
            log.setCategory(entity);
            categoryLogRepository.save(log);

            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
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



    public void copyDtoToEntity(CategoryDTO dto, Category entity){
        entity.setName(dto.getName());
    }

}
