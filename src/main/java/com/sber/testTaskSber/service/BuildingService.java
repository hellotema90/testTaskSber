package com.sber.testTaskSber.service;

import com.sber.testTaskSber.dto.RequestBuildingDto;
import com.sber.testTaskSber.model.Building;

/**
 * Интерфейс BuildingService содержит методы действий с зданиями.
 *
 * @author Масалкин Артем
 */
public interface BuildingService {

    /**
     * Создание здания.
     */
    Building create(RequestBuildingDto requestBuildingDto);

    /**
     * Обновление здания.
     */
    Building update(Long buildingId, RequestBuildingDto requestBuildingDto);

    /**
     * Получение здания по id.
     */
    Building findById(Long buildingId);


    /**
     * Удаление здания.
     */
    void delete(Long buildingId);

    /**
     * Проверка id.
     */
    void checkExists(Long buildingId);
}
