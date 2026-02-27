package com.devbraga.bgcatalog.dto;

import com.devbraga.bgcatalog.entities.CategoryLog;
import com.devbraga.bgcatalog.enuns.OperationType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;

public class CategoryLogDTO {

    private Long id;
    private Instant moment;
    private OperationType operationType;
    private CategoryDTO categoryDTO;

    public CategoryLogDTO() {
    }

    public CategoryLogDTO(Long id, Instant moment, OperationType operationType, CategoryDTO categoryDTO) {
        this.id = id;
        this.moment = moment;
        this.operationType = operationType;
        this.categoryDTO = categoryDTO;
    }

    public CategoryLogDTO(CategoryLog entity){
        id = entity.getId();
        moment = getMoment();
        operationType = getOperationType();
        categoryDTO = new CategoryDTO(entity.getCategory());
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }
}
