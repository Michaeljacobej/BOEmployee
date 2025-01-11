package com.system.BOEmployee.util;

import com.system.BOEmployee.constant.ErrorConstant;
import com.system.BOEmployee.models.dto.response.ErrorMessage;
import com.system.BOEmployee.models.dto.response.ErrorSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HelperResponseOutput {
    private static final Logger logger = LogManager.getLogger(HelperResponseOutput.class);
    public ErrorSchema errorSchema(ErrorConstant errorConstants){
        ErrorMessage errorMessage = new ErrorMessage(errorConstants.getErrorMessageEnglish(),errorConstants.getErrorMessageIndonesian());
        ErrorSchema errorSchema = new ErrorSchema(errorConstants.getErrorCode(),errorMessage);
        return errorSchema;
    }





}