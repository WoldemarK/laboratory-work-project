package com.example.laboratory.work.project.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class Book {

    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть оте 2 до 100 символов длинной")
    private String title;

    @NotEmpty(message = "Автор не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно автора быть оте 2 до 100 символов длинной")
    private String author;

    @Min(value = 1500, message = "Год должен быть больше, чем 1500")
    private int year;
}
