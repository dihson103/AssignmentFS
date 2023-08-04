package com.example.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ListEmployeeResponse {

    private List<Employee> employees;
    private Page page;

}
