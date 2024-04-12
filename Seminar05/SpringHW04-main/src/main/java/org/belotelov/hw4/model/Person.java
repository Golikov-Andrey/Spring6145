package org.belotelov.hw4.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]*$", message = "Name should consist only letters")
    private String name;
    @Pattern(regexp = "\\+\\d+", message = "Telephone number consist of digits or + sign")
    private String phone;
    @Email(message = "Email is not valid")
    private String email;
    private String note;
}
