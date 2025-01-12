package com.system.BOEmployee.models.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmployeeRequest {

    private String fullname;

    private Timestamp dateOfBirth;

    private List<Integer> department;

    private String imgurl;

    private String description;

    private Long salary;
}
