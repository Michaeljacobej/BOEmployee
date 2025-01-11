package com.system.BOEmployee.service;


import com.system.BOEmployee.models.dto.request.*;
import com.system.BOEmployee.models.dto.response.ResponseOutput;
import com.system.BOEmployee.models.entity.User;

import java.util.UUID;

public interface MasterService {
    ResponseOutput registerUser(RegisterRequest registerRequest) throws Exception;
    ResponseOutput loginUser(LoginRequest loginRequest) throws Exception;
    ResponseOutput addEmployee(UserEmployeeRequest userEmployeeRequest) throws Exception;
    ResponseOutput updateEmployee(UserEmployeeRequest userEmployeeRequest, UUID id) throws Exception;
    ResponseOutput deleteEmployee() throws Exception;
    ResponseOutput getListEmployee() throws Exception;

}
