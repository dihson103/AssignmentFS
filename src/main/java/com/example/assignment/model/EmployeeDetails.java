package com.example.assignment.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployeeDetails {

    private Employee employee;
    private Account account;

}
