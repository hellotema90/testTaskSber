package com.sber.testTaskSber.controller;

import com.sber.testTaskSber.dto.RequestBuildingDto;
import com.sber.testTaskSber.model.Building;
import com.sber.testTaskSber.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Класс BuildingController содержит эндпойнты, относящиеся к зданиям.
 *
 * @author Масалкин Артем
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/buildings")
@Validated
public class BuildingController {

    private final BuildingService buildingService;

    /**
     * Создание здания.
     */
    @PostMapping
    public Building create(@Valid @RequestBody RequestBuildingDto requestBuildingDto) {
        return buildingService.create(requestBuildingDto);
    }

    /**
     * Обновление здания.
     */
    @PatchMapping("/{id}")
    public Building update(@PathVariable("id") Long buildingId, @Valid @RequestBody RequestBuildingDto requestBuildingDto) {
        return buildingService.update(buildingId, requestBuildingDto);
    }

    /**
     * Получение здания по id.
     */
    @GetMapping("/{id}")
    public Building findById(@PathVariable("id") Long buildingId) {
        return buildingService.findById(buildingId);
    }

    /**
     * Удаление здания.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long buildingId) {
        buildingService.delete(buildingId);
    }
}
