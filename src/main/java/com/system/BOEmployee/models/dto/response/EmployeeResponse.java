package com.system.BOEmployee.models.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.system.BOEmployee.models.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EmployeeResponse {
    private Integer id;
    private String fullname;

    private String description;

    private String imgurl;

    private Timestamp dateOfBirth;

    private List<Integer> department;

    private Long salary;
}
