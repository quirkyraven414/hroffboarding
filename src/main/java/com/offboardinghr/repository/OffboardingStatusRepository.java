package com.offboardinghr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offboardinghr.entity.OffboardingStatus;

public interface OffboardingStatusRepository extends JpaRepository<OffboardingStatus, Integer> {
	
    Optional<OffboardingStatus> findByEmployeeId(Integer employeeId);
       
    List<OffboardingStatus> findAll();
    
    
}