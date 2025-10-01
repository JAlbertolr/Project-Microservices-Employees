package com.jalbertolrz.employee_service.service;

import com.jalbertolrz.employee_service.exceptions.EmployeeNotFoundException;
import com.jalbertolrz.employee_service.model.Employee;
import com.jalbertolrz.employee_service.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id){
     return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    public List<Employee> findByDepartmentId(Long id){
        return employeeRepository.findByDepartmentId(id);
    }
    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }


}
