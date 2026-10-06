package com.vansh.User.dto;

import jakarta.validation.constraints.*;

public class CreateUserRequestDTO {
    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "valid email is required")
    private String email;

    @NotNull(message = "age is required")
    @Min(value = 18, message = "user must be 18 or older")
    private Integer age;

    @NotBlank(message = "county is required")
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
