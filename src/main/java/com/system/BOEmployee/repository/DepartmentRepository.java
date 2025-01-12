package com.system.BOEmployee.repository;

import com.system.BOEmployee.models.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Override
    Optional<Department> findById(Integer uuid);
}
