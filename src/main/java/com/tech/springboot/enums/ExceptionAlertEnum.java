package com.tech.springboot.enums;

import com.tech.springboot.exception.dto.IAlertMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionAlertEnum implements IAlertMessage {
    USER_EXIST_DB("51001", "user.db.exist"),
    USER_NOT_FOUND("51002", "user.not.found"),
    USER_NON_ACTIVE("51003", "user.non.active"),
    ROLE_NOT_FOUND("51004", "role.not.found"),
    PERMISSION_EXIST_DB("51005", "permission.db.exist"),
    ROLE_EXIST_DB("51006", "role.db.exist"),
    ROLE_NON_ACTIVE("51007", "role.non.active"),
    PERMISSION_NON_ACTIVE("51008", "permission.non.active");
    private String code;
    private String label;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
