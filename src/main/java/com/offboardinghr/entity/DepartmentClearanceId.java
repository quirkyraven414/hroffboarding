package com.offboardinghr.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class DepartmentClearanceId implements Serializable {
	
	private int employeeId;
    private String department;

}
