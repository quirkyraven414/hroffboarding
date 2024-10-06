package com.offboardinghr.entity;

public class LoginResponse {
	
	private String department;

    public LoginResponse(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

}
