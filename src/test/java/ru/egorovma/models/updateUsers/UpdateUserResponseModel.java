package ru.egorovma.models.updateUsers;

import lombok.Data;

@Data
public class UpdateUserResponseModel {
	private String name;
	private String job;
	private String updatedAt;
}