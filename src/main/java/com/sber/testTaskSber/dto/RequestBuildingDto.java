package com.sber.testTaskSber.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;

/**
 * Класс RequestBuildingDto для передачи тела запроса на сервер с данными для создания и обновления здания.
 *
 * @author Масалкин Артем
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestBuildingDto {

    /**
     * Название здания.
     */
    @NotBlank(message = "Название не должно быть пустым")
    @Size(min = 1, max = 100, message = "Допустимая длина названия здания от 1 до 100 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯЁё0-9\\s.,:;?!*+%\\-<>@\\[\\]{}/_$#]+$",
            message = "В названии критерия оценки разрешены русские, английские символы, цифры, пробел и спецсимволы " +
                    ",:;?!*+%-<>@[]/_{}$#")
    private String name;

    /**
     * Количество этажей.
     */
    @Range(min = 1, max = 200, message = "Допустимый диапазон количества этажей от 1 до 200")
    private Integer numberOfFloors;
}
