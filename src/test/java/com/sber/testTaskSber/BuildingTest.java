package com.sber.testTaskSber;

import com.sber.testTaskSber.mapper.BuildingMapper;
import jakarta.persistence.EntityNotFoundException;
import com.sber.testTaskSber.dto.RequestBuildingDto;
import com.sber.testTaskSber.model.Building;
import com.sber.testTaskSber.repository.BuildingRepository;
import com.sber.testTaskSber.service.impl.BuildingServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Класс BuildingTest содержит методы Unit тестов.
 *
 * @author Масалкин Артем
 */
@ExtendWith(MockitoExtension.class)
@Transactional
public class BuildingTest {

    private static final long ID_1 = 1L;
    private static final String name = "high";
    private static final Integer numberOfFloors = 50;

    @Mock
    private BuildingRepository buildingRepository;
    @Mock
    private BuildingMapper buildingMapper;
    @InjectMocks
    private BuildingServiceImpl buildingService;
    private Building building;
    private RequestBuildingDto requestBuildingDto;

    /**
     * Заполнение полей.
     */
    @BeforeEach
    public void unit() {
        building = Building.builder()
                .id(ID_1)
                .name(name)
                .numberOfFloors(numberOfFloors)
                .build();
        requestBuildingDto = RequestBuildingDto.builder()
                .name(name)
                .numberOfFloors(numberOfFloors)
                .build();
    }

    /**
     * Создание нового здания с вызовом репозитория.
     */
    @Test
    @DisplayName("Создание нового здания с вызовом репозитория.")
    void shouldCreateWhenCallRepository() {
        when(buildingMapper.mapToEntity(requestBuildingDto)).thenReturn(building);
        when(buildingRepository.save(building)).thenReturn(building);
        int expectedId = 1;
        Building buildingResult = buildingService.create(requestBuildingDto);
        assertNotNull(buildingResult);
        assertEquals(expectedId, buildingResult.getId());
        verify(buildingRepository, times(1)).save(buildingResult);
    }

    /**
     * Проверка id на существование с вызовом репозитория.
     */
    @Test
    @DisplayName("Проверка id на существование с вызовом репозитория.")
    void shouldCheckExistsWhenCallRepository() {
        when(buildingRepository.existsById(building.getId())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> buildingService.checkExists(ID_1));
    }

    /**
     * Обновление здания с вызовом репозитория.
     */
    @Test
    @DisplayName("Обновление здания с вызовом репозитория.")
    void shouldUpdateWhenCallRepository() {
        when(buildingRepository.existsById(building.getId())).thenReturn(true);
        when(buildingMapper.mapToEntity(building.getId(), requestBuildingDto)).thenReturn(building);
        when(buildingRepository.save(building)).thenReturn(building);
        int expectedId = 1;
        Building buildingResult = buildingService.update(building.getId(), requestBuildingDto);
        assertNotNull(buildingResult);
        assertEquals(expectedId, buildingResult.getId());
        verify(buildingRepository, times(1)).save(buildingResult);
    }

    /**
     * Получение здания по id с вызовом репозитория.
     */
    @Test
    @DisplayName("Получение здания по id с вызовом репозитория.")
    void shouldFindByIdWhenCallRepository() {
        when(buildingRepository.existsById(building.getId())).thenReturn(true);
        when(buildingRepository.findById(building.getId())).thenReturn(Optional.ofNullable(building));
        Building buildingResult = buildingService.findById(this.building.getId());
        int expectedId = 1;
        assertEquals(expectedId, buildingResult.getId());
        verify(buildingRepository, times(1)).findById(buildingResult.getId());
    }

    /**
     * Удаление здания с вызовом репозитория.
     */
    @Test
    @DisplayName("Удаление здания с вызовом репозитория.")
    void shouldDeleteWhenCallRepository() {
        when(buildingRepository.existsById(building.getId())).thenReturn(true);
        buildingService.delete(ID_1);
        verify(buildingRepository, times(1)).existsById(ID_1);
        verify(buildingRepository, times(1)).deleteById(ID_1);
    }
}
