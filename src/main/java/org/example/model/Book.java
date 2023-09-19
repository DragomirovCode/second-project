package org.example.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "это поле не должно быть пустым")
    @Size(min = 1, max = 30, message = "это поле должно иметь минимум 1 и максимум 30 символов")
    private String title;

    @NotEmpty(message = "это поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "это поле должно иметь минимум 2 и максимум 30 символов")
    private String author;

    @Min(value = 1, message = "это поле должно быть больше 0")
    private int year;

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}