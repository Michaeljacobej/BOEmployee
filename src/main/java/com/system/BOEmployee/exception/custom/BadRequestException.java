package com.system.BOEmployee.exception.custom;

import com.system.BOEmployee.models.dto.response.ErrorSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestException extends  RuntimeException{
    private ErrorSchema errorSchema;

    private Object outputSchema;


}
