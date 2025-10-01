package com.jalbertolrz.department_service.controller;

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

    @GetMapping
    public ResponseEntity<List<Department>> findAll(){
        return ResponseEntity.ok().body(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDepartmentById(@PathVariable Long id){
        return ResponseEntity.ok().body(departmentService.findByDepartmentId(id));
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        Department dept=departmentService.createDepartment(department);
        URI location=URI.create("/api/v1/departments/"+dept.getId());
        return ResponseEntity.created(location).body(dept);
    }





}
