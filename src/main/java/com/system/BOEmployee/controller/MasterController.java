package com.system.BOEmployee.controller;

import com.system.BOEmployee.models.dto.request.*;
import com.system.BOEmployee.models.dto.response.ResponseOutput;
import com.system.BOEmployee.service.MasterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<?> addEmployee (@RequestBody @Valid UserEmployeeRequest userEmployeeRequest) throws  Exception{
        ResponseOutput responseOutput = masterService.addEmployee(userEmployeeRequest);
        return ResponseEntity.ok().body(responseOutput);
    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<?> updateEmployee (@RequestBody @Valid UserEmployeeRequest userEmployeeRequest,@PathVariable UUID employeeId) throws  Exception{
        ResponseOutput responseOutput = masterService.updateEmployee(userEmployeeRequest,employeeId);
        return ResponseEntity.ok().body(responseOutput);
    }

    @GetMapping("/employees")
    public ResponseEntity<?> gettAllEmployee ( @RequestHeader ("email")  String email) throws  Exception{
        ResponseOutput responseOutput = masterService.getListEmployee(email);
        return ResponseEntity.ok().body(responseOutput);
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<?> deleteEmployee (@RequestHeader ("email")  String email,@PathVariable UUID employeeId) throws  Exception{
        ResponseOutput responseOutput = masterService.deleteEmployee(email,employeeId);
        return ResponseEntity.ok().body(responseOutput);
    }



}
