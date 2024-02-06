package ru.egorovma.models.singleusers;

import lombok.Data;

@Data
public class SingleUserResponseModel {
    SingleUserResponseDataModel data;
    SingleUserResponseSupportModel support;
}