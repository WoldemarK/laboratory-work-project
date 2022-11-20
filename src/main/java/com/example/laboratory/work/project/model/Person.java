package com.example.laboratory.work.project.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class Person {
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя  должно быть оте 2 до 100 символов длинной")
    private String fullName;

    @Min(value = 1900, message = "Год должен быть больше, чем 1900")
    private int yearOfBirth;
}
