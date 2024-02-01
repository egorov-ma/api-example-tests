package ru.egorovma.models.singleUser;

import lombok.Data;

@Data
public class SingleUserResponseDataModel {
	private String lastName;
	private int id;
	private String avatar;
	private String firstName;
	private String email;
}