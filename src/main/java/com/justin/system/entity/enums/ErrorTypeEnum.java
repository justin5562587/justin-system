package com.justin.system.entity.enums;

public enum ErrorTypeEnum {

    // get failure
    CAN_NOT_FOUND("Can Not Found"),

    CREATE_FAILURE("Create Failure"),
    UPDATE_FAILURE("Update Failure"),
    DELETE_FAILURE("Delete Failure");

    private String description;

    ErrorTypeEnum(String description) {
        this.description = description;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

    public String getDescription() {
        return this.description;
    }

}
