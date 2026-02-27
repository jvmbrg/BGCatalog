package com.devbraga.bgcatalog.entities;

import com.devbraga.bgcatalog.enuns.OperationType;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_category_log")
public class CategoryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryLog(){}
    public CategoryLog(Long id, Instant moment, OperationType operationType, Category category) {
        this.id = id;
        this.moment = moment;
        this.operationType = operationType;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
