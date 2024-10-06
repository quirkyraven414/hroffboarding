package com.offboardinghr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.offboardinghr.service.ApprovalClearanceService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/doApproval")
public class ApprovalController {

	@Autowired
	private  ApprovalClearanceService approvalClearanceService;

	@PostMapping("/approve")
	public void approveClearance(@RequestParam int employeeId, @RequestParam String department) {

		approvalClearanceService.approveClearance(employeeId, department);

	}
	
	@PostMapping("/approveAssetReturn/{employeeId}")
	public void approveAssetReturn(@PathVariable int employeeId) {

		approvalClearanceService.approveAssetReturn(employeeId);

	}
	
	@PostMapping("/approveExitInterview/{employeeId}")
	public void approveExitInterview(@PathVariable int employeeId) {

		approvalClearanceService.approveExitInterview(employeeId);

	}

}
