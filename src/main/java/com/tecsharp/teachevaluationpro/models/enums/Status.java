package com.tecsharp.teachevaluationpro.models.enums;

import lombok.Getter;

@Getter
public enum Status {

    NO_REGISTERED(0),
    REGISTERED(1);

    private final int status;

    Status(int status) {
        this.status = status;
    }

}
