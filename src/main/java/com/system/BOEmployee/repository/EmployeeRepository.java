package com.system.BOEmployee.repository;

import com.system.BOEmployee.models.entity.Employee;
import com.system.BOEmployee.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Optional<Employee> findByFullname(String fullname) ;

    Optional<Employee>findById(UUID uuid);
}
