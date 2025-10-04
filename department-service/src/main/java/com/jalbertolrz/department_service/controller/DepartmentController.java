package com.jalbertolrz.department_service.controller;

import com.jalbertolrz.department_service.clients.CityClient;
import com.jalbertolrz.department_service.clients.EmployeeClient;
import com.jalbertolrz.department_service.model.City;
import com.jalbertolrz.department_service.model.Department;
import com.jalbertolrz.department_service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.net.URI;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final EmployeeClient employeeClient;
    private final CityClient cityClient;

    @GetMapping("/details")
    public ResponseEntity<List<Department>> findAllWithDetails(){
        List<Department> departments = departmentService.findAll();
        departments.forEach(department -> {
            // Llenar empleados
            department.setEmployees(employeeClient.findAllByDepartmentId(department.getId()));
            // Llenar ciudad
            if(department.getCityId() != null) {
                department.setCity(cityClient.getCity(department.getCityId()));
            }
        });
        return ResponseEntity.ok().body(departments);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDepartmentById(@PathVariable Long id){
        return ResponseEntity.ok().body(departmentService.findByDepartmentId(id));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> findDepartmentWithDetailsById(@PathVariable Long id){
       Department department=departmentService.findByDepartmentId(id);
        department.setEmployees(employeeClient.findAllByDepartmentId(department.getId()));
        City city= cityClient.getCity(department.getId());
        department.setCity(city);

        return ResponseEntity.ok().body(department);
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        Department dept=departmentService.createDepartment(department);
        URI location=URI.create("/api/v1/departments/"+dept.getId());
        return ResponseEntity.created(location).body(dept);
    }





}
