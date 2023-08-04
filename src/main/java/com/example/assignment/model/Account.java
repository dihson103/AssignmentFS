package com.example.assignment.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Boolean status;
    private Integer employeeId;

}
