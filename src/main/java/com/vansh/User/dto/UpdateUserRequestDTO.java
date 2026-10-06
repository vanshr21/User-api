package com.vansh.User.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateUserRequestDTO {
    @NotBlank(message = "Name can't be empty")
    private String name;

    @Min(value = 18, message = "Age must be 18 or more")
    private Integer age;

    @NotBlank(message = "Country can't be empty")
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
