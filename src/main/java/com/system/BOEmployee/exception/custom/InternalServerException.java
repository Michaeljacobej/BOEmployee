package com.system.BOEmployee.exception.custom;


import com.system.BOEmployee.models.dto.response.ErrorMessage;
import com.system.BOEmployee.models.dto.response.ErrorSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalServerException extends  RuntimeException{

    private ErrorSchema errorSchema;

    private Object outputSchema;




}
