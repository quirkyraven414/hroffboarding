package com.offboardinghr.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Department_Clearances")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DepartmentClearanceId.class)
public class DeptClearance {
	
	@Id
    private int employeeId;

    @Id
    private String department; // HR, IT, Finance, Admin, Manager

    private boolean clearanceApproved;
    private LocalDate clearanceDate;

}
