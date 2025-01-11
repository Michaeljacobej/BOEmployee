package com.system.BOEmployee;

import com.bca.bomt940.exception.custom.BadRequestException;
import com.bca.bomt940.exception.custom.InternalServerException;
import com.bca.bomt940.models.dto.response.ErrorMessage;
import com.bca.bomt940.models.dto.response.ErrorSchema;
import com.bca.bomt940.models.dto.response.ResponseOutput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<?> internalServerException(InternalServerException e){
        ResponseOutput responseOutput = new ResponseOutput(e.getErrorSchema(), e.getOutputSchema());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseOutput);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException e){
        ResponseOutput responseOutput = new ResponseOutput(e.getErrorSchema(), e.getOutputSchema());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseOutput);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
