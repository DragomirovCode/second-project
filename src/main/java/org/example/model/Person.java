package org.example.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int id;

    @NotEmpty(message = "это поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "это поле должно иметь минимум 2 и максимум 30 символов")
    private String name;

    @Min(value = 1, message = "это поле должно быть больше 0")
    private int age;

    public Person(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}