package com.buddywindow.core.service.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfile {

	private String id;
    private String username;
    private String email;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;

}