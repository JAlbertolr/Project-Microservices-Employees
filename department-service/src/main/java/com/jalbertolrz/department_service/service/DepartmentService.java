package com.jalbertolrz.department_service.service;

import com.jalbertolrz.department_service.clients.CityClient;
import com.jalbertolrz.department_service.exception.DepartmentNotFoundException;
import com.jalbertolrz.department_service.model.City;
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
    private final CityClient cityClient;


    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findByDepartmentId(Long id){
        return departmentRepository.findById(id).orElseThrow(()->new DepartmentNotFoundException(id));
    }

    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    public Department findDepartmentWithCity(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        City city = cityClient.getCity(dept.getCityId());
        dept.setCity(city);

        return dept;
    }







}
