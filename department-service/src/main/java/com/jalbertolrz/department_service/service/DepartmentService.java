package com.jalbertolrz.department_service.service;

import com.jalbertolrz.department_service.exception.DepartmentNotFoundException;
import com.jalbertolrz.department_service.model.Department;
import com.jalbertolrz.department_service.model.Employee;
import com.jalbertolrz.department_service.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;


    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findByDepartmentId(Long id){
        return departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
    }

    public List<Employee> findByEmployeeId(Long id){
        return departmentRepository.findByEmployeeId(id);
    }
    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }








}
