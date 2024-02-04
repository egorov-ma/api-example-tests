package ru.egorovma.models.createUsers;

import lombok.Data;

@Data
public class CreateUsersResponseModel {
	private String createdAt;
	private String name;
	private String id;
	private String job;
}