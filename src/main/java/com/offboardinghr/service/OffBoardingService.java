package com.offboardinghr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.offboardinghr.entity.DeptClearance;
import com.offboardinghr.entity.DeptHead;
import com.offboardinghr.entity.OffboardingStatus;
import com.offboardinghr.repository.DeptClearanceRepository;
import com.offboardinghr.repository.DeptHeadRepository;
import com.offboardinghr.repository.OffboardingStatusRepository;

@Service
public class OffBoardingService {
	
	@Autowired
	private final DeptClearanceRepository departmentClearanceRepository;
	
	@Autowired
    private final DeptHeadRepository departmentHeadRepository;
	
	@Autowired
	private final OffboardingStatusRepository offboardingStatusRepository;
	
	private final JavaMailSender mailSender;
	
	public OffBoardingService(DeptClearanceRepository departmentClearanceRepository, 
            DeptHeadRepository departmentHeadRepository, JavaMailSender mailSender, OffboardingStatusRepository offboardingStatusRepository) {
		
		this.departmentClearanceRepository = departmentClearanceRepository;
		this.departmentHeadRepository = departmentHeadRepository;
		this.mailSender = mailSender;
		this.offboardingStatusRepository = offboardingStatusRepository;
	}
    
	public void assignApprovalTasks(int employeeId) {
		
		List<DeptHead> deptHeads = departmentHeadRepository.findAll();
		
//		System.out.println(deptHeads);
		
		for (DeptHead head : deptHeads) {
			
            DeptClearance clearance = new DeptClearance(
                employeeId,
                head.getDepartment(),
                false, // Not yet approved
                null   // No clearance date yet
            );
            
            // Save the clearance task
            departmentClearanceRepository.save(clearance);

            // Send notification email to the department head
            sendApprovalEmail(head.getHeadEmail(), employeeId, head.getDepartment());
        }
		
		
		OffboardingStatus status = new OffboardingStatus(
               employeeId, false, false, false, false, false, false);  
		
		offboardingStatusRepository.save(status);
		
	}
	
	private void sendApprovalEmail(String email, int employeeId, String department) {
		
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Approval Task Assigned: " + department);
        message.setText("Please approve the offboarding clearance for employee ID: " + employeeId);
        
        
        mailSender.send(message);
    }
	
    

}
