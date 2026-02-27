package com.devbraga.bgcatalog.repositories;

import com.devbraga.bgcatalog.entities.CategoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryLogRepository extends JpaRepository<CategoryLog, Long> {
}
