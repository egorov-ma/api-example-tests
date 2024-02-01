package ru.egorovma.models.singleUser;

import lombok.Data;

@Data
public class SingleUserResponse {
	private SingleUserResponseDataModel data;
	private SingleUserResponseSupportModel support;

	public SingleUserResponse(SingleUserResponseDataModel data, SingleUserResponseSupportModel support) {
		this.data = data;
		this.support = support;
	}
}