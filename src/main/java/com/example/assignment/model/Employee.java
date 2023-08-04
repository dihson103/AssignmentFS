package com.example.assignment.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private String dob;
    private String phone;
    private String address;
    private String department;
    private String remark;

}
