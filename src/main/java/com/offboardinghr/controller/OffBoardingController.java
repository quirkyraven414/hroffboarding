package com.offboardinghr.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offboardinghr.entity.OffboardingStatus;
import com.offboardinghr.service.OffBoardingService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/offboarding")
public class OffBoardingController {
	
	private final OffBoardingService offboardingService;

    public OffBoardingController(OffBoardingService offBoardingService) {
        this.offboardingService = offBoardingService;
    }

    @GetMapping("/resign/{employeeId}")
    public OffboardingStatus getApprovalStatus(@PathVariable Integer employeeId) {
        
    	offboardingService.assignApprovalTasks(employeeId);
    	
		return null;
    }

}
