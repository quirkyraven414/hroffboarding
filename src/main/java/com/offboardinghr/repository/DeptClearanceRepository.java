package com.offboardinghr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.offboardinghr.entity.DepartmentClearanceId;
import com.offboardinghr.entity.DeptClearance;

@Repository
public interface DeptClearanceRepository extends JpaRepository<DeptClearance, DepartmentClearanceId>{

	List<DeptClearance> findByEmployeeId(int employeeId);
	
	List<DeptClearance> findByDepartment(String department);
	
	List<DeptClearance> findByDepartmentAndClearanceApproved(String department, boolean clearanceApproved);
	
	DeptClearance findByEmployeeIdAndDepartment(int employeeId, String department);
   
    List<DeptClearance> findAllByClearanceApprovedFalse();
}
