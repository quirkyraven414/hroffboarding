package com.offboardinghr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offboardinghr.entity.OffboardingStatus;
import com.offboardinghr.service.OffboardingStatusService;

@RestController	
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/offboardingstatus")
public class OffBoardingStatusController {
	
	private final OffboardingStatusService offboardingStatusService;

    public OffBoardingStatusController(OffboardingStatusService offboardingStatusService) {
        this.offboardingStatusService = offboardingStatusService;
    }

    @GetMapping("/approval-status/{employeeId}")
    public OffboardingStatus getApprovalStatus(@PathVariable int employeeId) {
        return offboardingStatusService.getApprovalStatus(employeeId);
    }

}
