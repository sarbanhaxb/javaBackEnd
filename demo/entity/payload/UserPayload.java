package com.example.demo.entity.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserPayload(
        @NotNull(message = "qwewe")
        @Size(min = 2, max = 50, message = "{errors.create.name_null}")
        String name,
        @NotNull(message = "Email is null")
        @Email(message = "Email is invalid")
        @Size(min = 5, max = 100, message = "Email must be between 5 and 50 characters")
        String email,
        @NotNull(message = "Age is null")
        @Min(value = 18, message = "Age must be greater than 17")
        int age) {
}