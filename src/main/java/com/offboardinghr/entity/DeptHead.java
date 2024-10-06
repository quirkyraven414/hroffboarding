package com.offboardinghr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Department_Heads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptHead {
	
	@Id
    private String department;
    private int headId;
    private String headEmail;

}
