package com.jalbertolrz.department_service.repository;

import com.jalbertolrz.department_service.model.Department;
import com.jalbertolrz.department_service.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    List<Employee> findByEmployeeId(Long id);
}
