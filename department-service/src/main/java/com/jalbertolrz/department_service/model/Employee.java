package com.jalbertolrz.department_service.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private Long id;
    private Long departmentId;
    private String name;
    private String email;
    private Integer birthdayYear;
    private String position;
}
