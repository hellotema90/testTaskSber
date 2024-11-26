package com.sber.testTaskSber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sber.testTaskSber.controller.BuildingController;
import com.sber.testTaskSber.dto.RequestBuildingDto;

import static org.mockito.ArgumentMatchers.any;

import com.sber.testTaskSber.model.Building;
import com.sber.testTaskSber.service.BuildingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Класс BuildingMVCTest содержит методы MockMVC тестов.
 *
 * @author Масалкин Артем
 */
@ExtendWith(MockitoExtension.class)
public class BuildingMVCTest {

    private static final long ID_1 = 1L;
    private static final String name = "high";
    private static final int numberOfFloors = 50;

    @Mock
    private BuildingService buildingService;
    @InjectMocks
    private BuildingController buildingController;
    private final ObjectMapper mapper = new ObjectMapper();
    private MockMvc mvc;
    private Building building;
    private RequestBuildingDto requestBuildingDto;

    /**
     * Заполнение полей.
     */
    @BeforeEach
    public void unit() {
        mvc = MockMvcBuilders
                .standaloneSetup(buildingController)
                .build();
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
     * Создание нового здания с вызовом сервиса.
     */
    @Test
    @DisplayName("Создание нового здания с вызовом сервиса.")
    void shouldCreateWhenCallService() throws Exception {
        when(buildingService.create(any())).thenReturn(building);
        mvc.perform(post("/buildings")
                        .content(mapper.writeValueAsString(requestBuildingDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID_1), Long.class))
                .andExpect(jsonPath("$.name", is(requestBuildingDto.getName())))
                .andExpect(jsonPath("$.numberOfFloors", is(requestBuildingDto.getNumberOfFloors())));
    }

    /**
     * Обновление здания с вызовом сервиса.
     */
    @Test
    @DisplayName("Обновление здания с вызовом сервиса.")
    void shouldUpdateWhenCallService() throws Exception {
        when(buildingService.update(any(), any())).thenReturn(building);
        mvc.perform(patch("/buildings/{id}", ID_1)
                        .content(mapper.writeValueAsString(requestBuildingDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID_1), Long.class))
                .andExpect(jsonPath("$.name", is(requestBuildingDto.getName())))
                .andExpect(jsonPath("$.numberOfFloors", is(requestBuildingDto.getNumberOfFloors())));

    }

    /**
     * Получение здания по id с вызовом сервиса.
     */
    @Test
    @DisplayName("Получение здания по id с вызовом сервиса.")
    void shouldFindByIdWhenCallService() throws Exception {
        when(buildingService.findById(any())).thenReturn(building);
        mvc.perform(get("/buildings/{id}", ID_1)
                        .content(mapper.writeValueAsString(requestBuildingDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID_1), Long.class))
                .andExpect(jsonPath("$.name", is(requestBuildingDto.getName())))
                .andExpect(jsonPath("$.numberOfFloors", is(requestBuildingDto.getNumberOfFloors())));
    }

    /**
     * Удаление здания с вызовом сервиса.
     */
    @Test
    @DisplayName("Удаление здания с вызовом сервиса.")
    void shouldDeleteWhenCallService() throws Exception {
        buildingService.delete(ID_1);
        mvc.perform(delete("/buildings/{id}", ID_1)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
