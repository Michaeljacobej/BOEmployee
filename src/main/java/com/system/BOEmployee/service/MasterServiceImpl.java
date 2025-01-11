package com.system.BOEmployee.service;

import com.system.BOEmployee.constant.ErrorConstant;
import com.system.BOEmployee.models.dto.request.AddEmployeeRequest;
import com.system.BOEmployee.models.dto.request.LoginRequest;
import com.system.BOEmployee.models.dto.request.RegisterRequest;
import com.system.BOEmployee.models.dto.request.UpdateEmployeeRequest;
import com.system.BOEmployee.models.dto.response.ErrorSchema;
import com.system.BOEmployee.models.dto.response.ResponseOutput;
import com.system.BOEmployee.models.entity.User;
import com.system.BOEmployee.repository.EmployeeRepository;
import com.system.BOEmployee.repository.EmployeeSystemConfigRepository;
import com.system.BOEmployee.repository.UserRepository;
import com.system.BOEmployee.util.HelperResponseOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MasterServiceImpl implements MasterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeSystemConfigRepository employeeSystemConfigRepository;

    @Autowired
    private HelperResponseOutput responseOutput;

    @Override
    public ResponseOutput registerUser(RegisterRequest registerRequest) throws Exception {
        return userRepository.findByEmail(registerRequest.getEmailAddress())
                .map(user -> new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.USER_REGISTERED_FAILED), "")
                )
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setFullname(registerRequest.getFullname());
                    newUser.setUsername(registerRequest.getUsername());
                    newUser.setEmail(registerRequest.getEmailAddress());
                    newUser.setPassword(registerRequest.getPassword()); // Ensure password is hashed if necessary
                    userRepository.save(newUser);
                    return new ResponseOutput(
                            responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), newUser
                    );
                });
//        return new ResponseOutput(
//                responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "Success"
//        );
    }

    @Override
    public ResponseOutput loginUser(LoginRequest loginRequest) throws Exception {

        return null;
    }

    @Override
    public ResponseOutput getListEmployee() throws Exception {

        return null;
    }

    @Override
    public ResponseOutput addEmployee(AddEmployeeRequest addEmployeeRequest) throws Exception {

        return null;
    }

    @Override
    public ResponseOutput updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) throws Exception {

        return null;
    }

    @Override
    public ResponseOutput deleteEmployee() throws Exception {

        return null;
    }
}
