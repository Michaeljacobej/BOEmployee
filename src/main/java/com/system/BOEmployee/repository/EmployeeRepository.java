package com.system.BOEmployee.repository;

import com.system.BOEmployee.models.entity.Department;
import com.system.BOEmployee.models.entity.Employee;
import com.system.BOEmployee.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByFullname(String fullname) ;

    List<Employee>findByDepartment(Department department);
    Optional<Employee>findById(Integer uuid);


}
