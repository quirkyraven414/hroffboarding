package com.offboardinghr.service;

import org.springframework.stereotype.Service;

import com.offboardinghr.entity.OffboardingStatus;
import com.offboardinghr.repository.OffboardingStatusRepository;

@Service
public class OffboardingStatusService {

    private final OffboardingStatusRepository offboardingStatusRepository;

    public OffboardingStatusService(OffboardingStatusRepository offboardingStatusRepository) {
        this.offboardingStatusRepository = offboardingStatusRepository;
    }

    public OffboardingStatus getApprovalStatus(int employeeId) {
        return offboardingStatusRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("No offboarding status found for employee ID: " + employeeId));
    }
}