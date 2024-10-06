package com.offboardinghr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offboardinghr.entity.AssetReturnStatus;
import com.offboardinghr.entity.DeptClearance;
import com.offboardinghr.entity.ExitInterviewStatus;
import com.offboardinghr.entity.OffboardingStatus;
import com.offboardinghr.repository.DeptClearanceRepository;
import com.offboardinghr.repository.OffboardingStatusRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/getResignations")
public class DeptResignStatusController {
	
	@Autowired
	private DeptClearanceRepository deptClearanceRepository;
	
	@Autowired
	private OffboardingStatusRepository offboardingStatusRepository;
	
	@GetMapping("/deptwise")
	public List<Integer> getResignedEmployeeID(@RequestParam(required = false) String department){
		
		List<DeptClearance> clearance =  deptClearanceRepository.findByDepartment(department);
		List<Integer> empID = new ArrayList<Integer>();
		
		for (DeptClearance clear : clearance) {
			empID.add(clear.getEmployeeId());
		}
		
		return empID;
		
	}
	
	@GetMapping("/exitInterviewStatus")
	public List<ExitInterviewStatus> getExitInterviewStatus(){
		
		List<OffboardingStatus> clearance =  offboardingStatusRepository.findAll();
		List<ExitInterviewStatus> empID = new ArrayList<ExitInterviewStatus>();
		
		for (OffboardingStatus clear : clearance) {
			ExitInterviewStatus status = new ExitInterviewStatus();
			
			status.setEmployeeid(clear.getEmployeeId());
			status.setInterviewDone(clear.isExitInterviewDone());
			empID.add(status);
		}
		
		return empID;
		
	}
	
	@GetMapping("/assetReturnStatus")
	public List<AssetReturnStatus> getassetReturnStatus(){
		
		List<OffboardingStatus> clearance =  offboardingStatusRepository.findAll();
		List<AssetReturnStatus> empID = new ArrayList<AssetReturnStatus>();
		
		for (OffboardingStatus clear : clearance) {
			AssetReturnStatus status = new AssetReturnStatus();
			
			status.setEmployeeid(clear.getEmployeeId());
			status.setAssetReturnStatus(clear.isAssetsReturned());
			empID.add(status);
		}
		
		return empID;
		
	}
	
	
	@GetMapping("/deptwiseApproved")
	public List<Integer> getApprovedResignedEmployeeID(@RequestParam(required = false) String department, @RequestParam int clearanceApproved){
		
		boolean val = false;
		if (clearanceApproved == 0) {
			val = true;
		}
		List<DeptClearance> clearance =  deptClearanceRepository.findByDepartmentAndClearanceApproved(department, val);
		List<Integer> empID = new ArrayList<Integer>();
		
		for (DeptClearance clear : clearance) {
			empID.add(clear.getEmployeeId());
		}
		
		return empID;
		
	}

}
