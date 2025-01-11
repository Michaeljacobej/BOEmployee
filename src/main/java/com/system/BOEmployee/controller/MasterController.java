package com.system.BOEmployee.controller;

import com.system.BOEmployee.models.dto.request.AddEmployeeRequest;
import com.system.BOEmployee.models.dto.request.LoginRequest;
import com.system.BOEmployee.models.dto.request.RegisterRequest;
import com.system.BOEmployee.models.dto.request.UpdateEmployeeRequest;
import com.system.BOEmployee.models.dto.response.ResponseOutput;
import com.system.BOEmployee.service.MasterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boe/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin (@RequestBody @Valid LoginRequest loginRequest) throws  Exception{
        ResponseOutput responseOutput = masterService.loginUser(loginRequest);
        return ResponseEntity.ok().body(responseOutput);
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> userRegister (@RequestBody @Valid RegisterRequest registerRequest) throws  Exception{
        ResponseOutput responseOutput = masterService.registerUser(registerRequest);
        return ResponseEntity.ok().body(responseOutput);
    }

    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee (@RequestBody @Valid AddEmployeeRequest employeeRequest) throws  Exception{
        ResponseOutput responseOutput = masterService.addEmployee(employeeRequest);
        return ResponseEntity.ok().body(responseOutput);
    }

    @PutMapping("/employee")
    public ResponseEntity<?> updateEmployee (@RequestBody @Valid UpdateEmployeeRequest employeeRequest) throws  Exception{
        ResponseOutput responseOutput = masterService.updateEmployee(employeeRequest);
        return ResponseEntity.ok().body(responseOutput);
    }


}
