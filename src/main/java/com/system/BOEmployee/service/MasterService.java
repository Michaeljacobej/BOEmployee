package com.system.BOEmployee.service;


import com.system.BOEmployee.models.dto.request.AddEmployeeRequest;
import com.system.BOEmployee.models.dto.request.LoginRequest;
import com.system.BOEmployee.models.dto.request.RegisterRequest;
import com.system.BOEmployee.models.dto.request.UpdateEmployeeRequest;
import com.system.BOEmployee.models.dto.response.ResponseOutput;

public interface MasterService {
    ResponseOutput registerUser(RegisterRequest registerRequest) throws Exception;
    ResponseOutput loginUser(LoginRequest loginRequest) throws Exception;
    ResponseOutput addEmployee(AddEmployeeRequest addEmployeeRequest) throws Exception;
    ResponseOutput updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) throws Exception;
    ResponseOutput deleteEmployee() throws Exception;
    ResponseOutput getListEmployee() throws Exception;

}
