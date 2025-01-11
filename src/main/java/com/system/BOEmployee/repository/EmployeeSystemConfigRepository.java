package com.system.BOEmployee.repository;

import com.system.BOEmployee.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeSystemConfigRepository extends JpaRepository<EmployeeSystemConfigRepository, UUID> {
}
