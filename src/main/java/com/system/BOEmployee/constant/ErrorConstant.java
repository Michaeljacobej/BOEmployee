package com.system.BOEmployee.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorConstant {
    REQUEST_SUCCESS("BOE940-00-000", "Sukses", "Success", HttpStatus.OK),

    ;

    private String errorCode;
    private String errorMessageIndonesian;
    private String errorMessageEnglish;
    private HttpStatus httpStatus;

    ErrorConstant(String errorCode, String errorMessageIndonesian, String errorMessageEnglish, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessageIndonesian = errorMessageIndonesian;
        this.errorMessageEnglish = errorMessageEnglish;
        this.httpStatus = httpStatus;
    }
}
