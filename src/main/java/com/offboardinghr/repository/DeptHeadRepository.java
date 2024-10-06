package com.offboardinghr.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offboardinghr.entity.DeptHead;
import java.util.List;


@Repository
public interface DeptHeadRepository extends JpaRepository<DeptHead, String> {
		
	List<DeptHead> findAll();
	
	 DeptHead findByDepartment(String department);
}
