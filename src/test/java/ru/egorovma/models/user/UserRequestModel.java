package ru.egorovma.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequestModel {
    private String name;
    private String job;
}