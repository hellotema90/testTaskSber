package com.sber.testTaskSber.repository;

import com.sber.testTaskSber.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Интерфейс BuildingRepository определяет стандартные методы CRUD операций.
 *
 * @author Масалкин Артем
 */
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
