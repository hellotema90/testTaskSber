package com.sber.testTaskSber.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Класс Building содержит информацию о названии здания и количество этажей.
 *
 * @author Масалкин Артем
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BUILDINGS")
public class Building {

    /**
     * Идентификатор здания.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название здания.
     */
    @Column(name = "name")
    private String name;

    /**
     * Количество этажей.
     */
    @Column(name = "number_of_floors")
    private Integer numberOfFloors;
}
