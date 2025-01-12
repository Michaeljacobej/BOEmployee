package com.system.BOEmployee.service;

import com.system.BOEmployee.constant.ErrorConstant;
import com.system.BOEmployee.models.dto.request.*;
import com.system.BOEmployee.models.dto.response.EmployeeResponse;
import com.system.BOEmployee.models.dto.response.ErrorSchema;
import com.system.BOEmployee.models.dto.response.ListEmployeeResponse;
import com.system.BOEmployee.models.dto.response.ResponseOutput;
import com.system.BOEmployee.models.entity.Department;
import com.system.BOEmployee.models.entity.Employee;
import com.system.BOEmployee.models.entity.Employee_System_Config;
import com.system.BOEmployee.models.entity.User;
import com.system.BOEmployee.repository.DepartmentRepository;
import com.system.BOEmployee.repository.EmployeeRepository;
import com.system.BOEmployee.repository.EmployeeSystemConfigRepository;
import com.system.BOEmployee.repository.UserRepository;
import com.system.BOEmployee.util.HelperResponseOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MasterServiceImpl implements MasterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

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
    }

    @Override
    public ResponseOutput loginUser(LoginRequest loginRequest) throws Exception {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmailAddress());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            Optional<User> userWithPassword = userRepository.findByEmailAndPassword(loginRequest.getEmailAddress(), loginRequest.getPassword());

            if (userWithPassword.isPresent()) {
                return new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), user
                );
            } else {

                return new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.USER_PASSWORD_NOT_MATCHED), ""
                );
            }
        }
        else {

            return new ResponseOutput(
                    responseOutput.errorSchema(ErrorConstant.USER_NEED_TO_BE_REGISTERED), null
            );
        }
    }


    @Override
    public ResponseOutput getListEmployee(String email) throws Exception {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            List<Employee_System_Config> employeeSystemConfigs = employeeSystemConfigRepository.findAllByUser(userOpt.get());
            ListEmployeeResponse listEmployeeResponse = new ListEmployeeResponse();
            listEmployeeResponse.setEmail(email);
            if(!employeeSystemConfigs.isEmpty()){
                List<EmployeeResponse>employeeResponses = new ArrayList<>();
                for(Employee_System_Config employeeSystemConfig:employeeSystemConfigs){
                    EmployeeResponse employeeResponse = new EmployeeResponse();
                    employeeResponse.setId(employeeSystemConfig.getEmployee().getId());
                    employeeResponse.setFullname(employeeSystemConfig.getEmployee().getFullname());
                    employeeResponse.setDateOfBirth(employeeSystemConfig.getEmployee().getDob());
                    List<Employee>employeeList = employeeRepository.findByDepartment(employeeSystemConfig.getEmployee().getDepartment());
                    List<Integer> idDepartment = new ArrayList<>();
                    if(!employeeList.isEmpty()){
                   for(Employee employee:employeeList){
                       idDepartment.add(employee.getDepartment().getId());
                   }
                   employeeResponse.setDepartment(idDepartment);
                    }
                    employeeResponse.setSalary(employeeSystemConfig.getEmployee().getSalary());
                    employeeResponse.setImgurl(employeeSystemConfig.getEmployee().getImgurl());
                    employeeResponse.setDescription(employeeSystemConfig.getEmployee().getDescription());
                    employeeResponses.add(employeeResponse);
                }
                listEmployeeResponse.setListEmployee(employeeResponses);

                return new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), listEmployeeResponse
                );

            }

            return new ResponseOutput(
                    responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), ""
            );


        }
        else {

            return new ResponseOutput(
                    responseOutput.errorSchema(ErrorConstant.USER_NEED_TO_BE_REGISTERED), null
            );
        }
    }

    @Override
    public ResponseOutput addEmployee(UserEmployeeRequest userEmployeeRequest) throws Exception {

        Optional<User> userOpt = userRepository.findByEmail(userEmployeeRequest.getEmailAddress());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            Optional<Employee>optionalEmployee = employeeRepository.findByFullname(userEmployeeRequest.getEmployee().getFullname());

            if(optionalEmployee.isPresent()){
                Optional<Employee_System_Config>optionalEmployeeSystemConfig = employeeSystemConfigRepository.findByUserAndEmployee(user,optionalEmployee.get());
                if(optionalEmployeeSystemConfig.isPresent()){
                    return new ResponseOutput(
                            responseOutput.errorSchema(ErrorConstant.EMPLOYEE_HAVE_BEEN_REGISTERED), null
                    );
                }
                else{

                    Employee_System_Config employeeSystemConfig = new Employee_System_Config();
                    employeeSystemConfig.setEmployee(optionalEmployee.get());
                    employeeSystemConfig.setUser(user);
                    employeeSystemConfigRepository.save(employeeSystemConfig);

                    Employee employeeGet = optionalEmployee.get();

                    List<Integer>integerList = new ArrayList<>();
                    for(Integer departementId:userEmployeeRequest.getEmployee().getDepartment()){
                        Optional<Department>department = departmentRepository.findById(departementId);
                        if(department.isPresent()){
                            integerList.add(department.get().getId());
                            employeeGet.setDepartment(department.get());
                        }
                    }
                    employeeRepository.save(employeeGet);
                    EmployeeResponse employeeResponse = new EmployeeResponse();
                    employeeResponse.setId(employeeSystemConfig.getEmployee().getId());
                    employeeResponse.setFullname(employeeSystemConfig.getEmployee().getFullname());
                    employeeResponse.setDateOfBirth(employeeSystemConfig.getEmployee().getDob());

                    List<Employee>employeeList = employeeRepository.findByDepartment(employeeSystemConfig.getEmployee().getDepartment());

                    List<Integer> idDepartment = new ArrayList<>();

                    if(!employeeList.isEmpty()){
                        for(Employee employees:employeeList){
                            idDepartment.add(employees.getDepartment().getId());
                        }
                        employeeResponse.setDepartment(idDepartment);
                    }
                    employeeResponse.setSalary(employeeSystemConfig.getEmployee().getSalary());
                    employeeResponse.setImgurl(employeeSystemConfig.getEmployee().getImgurl());
                    employeeResponse.setDescription(employeeSystemConfig.getEmployee().getDescription());

                    return new ResponseOutput(
                            responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), employeeResponse
                    );
                }

            }

            else{
                Employee employee = new Employee();
                employee.setFullname(userEmployeeRequest.getEmployee().getFullname());
                employee.setImgurl(userEmployeeRequest.getEmployee().getImgurl());
                List<Integer>integerList = new ArrayList<>();
                for(Integer departementId:userEmployeeRequest.getEmployee().getDepartment()){
                    Optional<Department>department = departmentRepository.findById(departementId);
                    if(department.isPresent()){
                        integerList.add(department.get().getId());
                        employee.setDepartment(department.get());
                    }
                }
                employee.setDob(userEmployeeRequest.getEmployee().getDateOfBirth());
                employee.setSalary(userEmployeeRequest.getEmployee().getSalary());
                employee.setDescription(userEmployeeRequest.getEmployee().getDescription());
                employeeRepository.save(employee);

                Employee_System_Config employeeSystemConfig = new Employee_System_Config();
                employeeSystemConfig.setEmployee(employee);
                employeeSystemConfig.setUser(user);
                employeeSystemConfigRepository.save(employeeSystemConfig);

                EmployeeResponse employeeResponse = new EmployeeResponse();
                employeeResponse.setId(employeeSystemConfig.getEmployee().getId());
                employeeResponse.setFullname(employeeSystemConfig.getEmployee().getFullname());
                employeeResponse.setDateOfBirth(employeeSystemConfig.getEmployee().getDob());

                List<Employee>employeeList = employeeRepository.findByDepartment(employeeSystemConfig.getEmployee().getDepartment());

                List<Integer> idDepartment = new ArrayList<>();

                if(!employeeList.isEmpty()){
                    for(Employee employees:employeeList){
                        idDepartment.add(employees.getDepartment().getId());
                    }
                    employeeResponse.setDepartment(idDepartment);
                }
                employeeResponse.setSalary(employeeSystemConfig.getEmployee().getSalary());
                employeeResponse.setImgurl(employeeSystemConfig.getEmployee().getImgurl());
                employeeResponse.setDescription(employeeSystemConfig.getEmployee().getDescription());

                return new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), employeeResponse
                );
            }

        }
        else {

            return new ResponseOutput(
                    responseOutput.errorSchema(ErrorConstant.USER_NEED_TO_BE_REGISTERED), null
            );
        }
    }

    @Override
    public ResponseOutput updateEmployee(UserEmployeeRequest userEmployeeRequest, Integer id) throws Exception {

        Optional<User> userOpt = userRepository.findByEmail(userEmployeeRequest.getEmailAddress());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            Optional<Employee>optionalEmployee = employeeRepository.findById(id);

            if(optionalEmployee.isPresent()){
                Optional<Employee_System_Config>optionalEmployeeSystemConfig = employeeSystemConfigRepository.findByUserAndEmployee(user,optionalEmployee.get());
                if(optionalEmployeeSystemConfig.isPresent()){
                    Employee employee = optionalEmployee.get();
                    employee.setFullname(userEmployeeRequest.getEmployee().getFullname());
                    for (Integer idDpartment:userEmployeeRequest.getEmployee().getDepartment()){
                        Optional<Department>department= departmentRepository.findById(id);
                        if(department.isPresent()){
                            employee.setDepartment(department.get());
                        }
                    }
                    employee.setImgurl(userEmployeeRequest.getEmployee().getImgurl());
                    employee.setDob(userEmployeeRequest.getEmployee().getDateOfBirth());
                    employee.setSalary(userEmployeeRequest.getEmployee().getSalary());
                    employee.setDescription(userEmployeeRequest.getEmployee().getDescription());
                    employeeRepository.save(employee);

                    Employee_System_Config employeeSystemConfig = optionalEmployeeSystemConfig.get();
                    employeeSystemConfig.setEmployee(employee);
                    employeeSystemConfig.setUser(user);
                    employeeSystemConfigRepository.save(employeeSystemConfig);

                    return new ResponseOutput(
                            responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), employeeSystemConfig
                    );
                }
                else{
                    return new ResponseOutput(
                            responseOutput.errorSchema(ErrorConstant.EMPLOYEE_NEED_TO_BE_REGISTERED), ""
                    );
                }

            }

            else{
                return new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.EMPLOYEE_NEED_TO_BE_REGISTERED), ""
                );
            }

        }
        else {

            return new ResponseOutput(
                    responseOutput.errorSchema(ErrorConstant.USER_NEED_TO_BE_REGISTERED), null
            );
        }
    }

    @Override
    public ResponseOutput deleteEmployee(String email, Integer id) throws Exception {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            Optional<Employee>optionalEmployee = employeeRepository.findById(id);

            if(optionalEmployee.isPresent()){
                Optional<Employee_System_Config>optionalEmployeeSystemConfig = employeeSystemConfigRepository.findByUserAndEmployee(user,optionalEmployee.get());
                if(optionalEmployeeSystemConfig.isPresent()){
                    Employee_System_Config employeeSystemConfig = optionalEmployeeSystemConfig.get();
                    employeeSystemConfigRepository.delete(employeeSystemConfig);

                    return new ResponseOutput(
                            responseOutput.errorSchema(ErrorConstant.REQUEST_SUCCESS), "Berhasil Hapus"
                    );
                }
                else{
                    return new ResponseOutput(
                            responseOutput.errorSchema(ErrorConstant.EMPLOYEE_NEED_TO_BE_REGISTERED), ""
                    );
                }

            }

            else{
                return new ResponseOutput(
                        responseOutput.errorSchema(ErrorConstant.EMPLOYEE_NEED_TO_BE_REGISTERED), ""
                );
            }

        }
        else {

            return new ResponseOutput(
                    responseOutput.errorSchema(ErrorConstant.USER_NEED_TO_BE_REGISTERED), null
            );
        }
    }
}
