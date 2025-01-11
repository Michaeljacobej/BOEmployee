package com.system.BOEmployee.exception.custom;


import com.system.BOEmployee.models.dto.response.ErrorMessage;
import com.system.BOEmployee.models.dto.response.ErrorSchema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternalServerException extends  RuntimeException{

    private ErrorSchema errorSchema;

    private Object outputSchema;

    public InternalServerException(ErrorSchema errorSchema, Object outputSchema) {
        this.errorSchema = errorSchema;
        this.outputSchema = outputSchema;
    }


}
