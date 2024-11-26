package com.sber.testTaskSber.service.impl;

import com.sber.testTaskSber.mapper.BuildingMapper;
import com.sber.testTaskSber.model.Building;
import jakarta.persistence.EntityNotFoundException;
import com.sber.testTaskSber.dto.RequestBuildingDto;
import com.sber.testTaskSber.repository.BuildingRepository;
import com.sber.testTaskSber.service.BuildingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Класс BuildingServiceImpl содержит методы действий со зданиями.
 *
 * @author Масалкин Артем
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    /**
     * Создание здания.
     */
    @Override
    public Building create(RequestBuildingDto requestBuildingDto) {
        log.debug("Создание здания.");
        return buildingRepository.save(buildingMapper.mapToEntity(requestBuildingDto));
    }

    /**
     * Проверка id на существование.
     */
    @Override
    public void checkExists(Long buildingId) {
        log.debug("Проверка id на существование.");
        if (!buildingRepository.existsById(buildingId))
            throw new EntityNotFoundException(String.format("Здание с id %d не найдено", buildingId));
    }

    /**
     * Обновление здания.
     */
    @Override
    public Building update(Long buildingId, RequestBuildingDto requestBuildingDto) {
        log.debug("Обновление здания.");
        checkExists(buildingId);
        return buildingRepository.save(buildingMapper.mapToEntity(buildingId, requestBuildingDto));
    }

    /**
     * Получение здания по id.
     */
    @Override
    public Building findById(Long buildingId) {
        log.debug("Получение здания по id.");
        checkExists(buildingId);
        return buildingRepository.findById(buildingId).get();
    }

    /**
     * Удаление здания.
     */
    @Override
    public void delete(Long buildingId) {
        log.debug("Удаление здания.");
        checkExists(buildingId);
        buildingRepository.deleteById(buildingId);
    }
}

