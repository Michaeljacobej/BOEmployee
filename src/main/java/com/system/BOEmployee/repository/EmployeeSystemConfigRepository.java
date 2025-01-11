package com.system.BOEmployee.repository;

import com.system.BOEmployee.models.entity.Employee;
import com.system.BOEmployee.models.entity.Employee_System_Config;
import com.system.BOEmployee.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeSystemConfigRepository extends JpaRepository<Employee_System_Config, UUID> {

    Optional<Employee_System_Config>findByUserAndEmployee(User user , Employee employee);

    List<Employee_System_Config> findAllByUser(User user );
}
