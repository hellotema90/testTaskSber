package com.sber.testTaskSber.mapper;

import com.sber.testTaskSber.dto.RequestBuildingDto;
import com.sber.testTaskSber.model.Building;
import org.springframework.stereotype.Component;

/**
 * Класс BuildingMapper содержит преобразование сущности.
 *
 * @author Масалкин Артем
 */
@Component
public class BuildingMapper {

    /**
     * Преобразование из DTO в сущность.
     */
    public Building mapToEntity(RequestBuildingDto requestBuildingDto) {
        return Building.builder()
                .name(requestBuildingDto.getName())
                .numberOfFloors(requestBuildingDto.getNumberOfFloors())
                .build();
    }

    /**
     * Обновление полей при обновлении здания.
     */
    public Building mapToEntity(Long id, RequestBuildingDto requestBuildingDto) {
        return Building.builder()
                .id(id)
                .name(requestBuildingDto.getName())
                .numberOfFloors(requestBuildingDto.getNumberOfFloors())
                .build();
    }
}
