package com.jalbertolrz.employee_service.service;

import com.jalbertolrz.employee_service.clients.DepartmentClient;
import com.jalbertolrz.employee_service.exceptions.EmployeeNotFoundException;
import com.jalbertolrz.employee_service.model.Department;
import com.jalbertolrz.employee_service.model.Employee;
import com.jalbertolrz.employee_service.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;


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

    public Employee findEmployeeWithDepartment(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        Department department = departmentClient.getDepartment(employee.getDepartmentId());
        employee.setDepartment(department);
        return employee;
    }

    public List<Employee> findAllWithDepartment() {
        List<Employee> employees = employeeRepository.findAll();

        // Llenar el department usando Feign
        for (Employee e : employees) {
            try {
                Department dept = departmentClient.getDepartment(e.getDepartmentId());
                e.setDepartment(dept);
            } catch (Exception ex) {
                e.setDepartment(null);
            }
        }

        return employees;
    }



}
