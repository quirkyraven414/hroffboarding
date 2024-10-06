package com.offboardinghr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Offboarding_Status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OffboardingStatus {
	
	@Id
    private int employeeId;
    private boolean exitInterviewDone;
    private boolean assetsReturned;
    
    private boolean hrApproved;
    private boolean itApproved;
    private boolean financeApproved;
    private boolean adminApproved;
    

}
