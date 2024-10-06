package com.offboardinghr.service;

import java.time.LocalDate;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.offboardinghr.entity.DeptClearance;
import com.offboardinghr.entity.OffboardingStatus;
import com.offboardinghr.repository.DeptClearanceRepository;
import com.offboardinghr.repository.EmployeeRepository;
import com.offboardinghr.repository.OffboardingStatusRepository;

@Service
public class ApprovalClearanceService {

	private final DeptClearanceRepository departmentClearanceRepository;

	private final OffboardingStatusRepository offboardingStatusRepository;
	
	private final EmployeeRepository employeeRepository;

	private final JavaMailSender mailSender;

	public ApprovalClearanceService(DeptClearanceRepository departmentClearanceRepository,
			OffboardingStatusRepository offboardingStatusRepository, JavaMailSender mailSender, EmployeeRepository employeeRepository) {
		this.departmentClearanceRepository = departmentClearanceRepository;
		this.offboardingStatusRepository = offboardingStatusRepository;
		this.mailSender = mailSender;
		this.employeeRepository = employeeRepository;
	}

	public void approveExitInterview(@RequestParam int employeeId) {
		// Find the clearance task
		
		OffboardingStatus status = offboardingStatusRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee resignation status not found"));

		status.setExitInterviewDone(true);

		offboardingStatusRepository.save(status);
		
		String employeeMail = employeeRepository.findByEmployeeId(employeeId).getEmail();
				
		sendOtherApprovalDoneEmail(employeeMail, employeeId, "Exit Interview");
	}
	
	public void approveAssetReturn(@RequestParam int employeeId) {
		// Find the clearance task
		
		OffboardingStatus status = offboardingStatusRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee resignation status not found"));

		status.setAssetsReturned(true);

		offboardingStatusRepository.save(status);
		
		String employeeMail = employeeRepository.findByEmployeeId(employeeId).getEmail();
				
		sendOtherApprovalDoneEmail(employeeMail, employeeId, "Asset Return");
	}
	
	
	public void approveClearance(@RequestParam int employeeId, @RequestParam String department) {
		// Find the clearance task
		DeptClearance clearance = departmentClearanceRepository.findByEmployeeIdAndDepartment(employeeId, department);

		if (clearance != null) {
			clearance.setClearanceApproved(true);
			clearance.setClearanceDate(LocalDate.now());

			// Update the clearance status
			departmentClearanceRepository.save(clearance);
		}

		OffboardingStatus status = offboardingStatusRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee resignation status not found"));

		if (department.equalsIgnoreCase("HR")) {
			status.setHrApproved(true);
		} else if (department.equalsIgnoreCase("IT")) {
			status.setItApproved(true);
		} else if (department.equalsIgnoreCase("Finance")) {
			status.setFinanceApproved(true);
		} else if (department.equalsIgnoreCase("Admin")) {
			status.setAdminApproved(true);
		}

		offboardingStatusRepository.save(status);
		
		String employeeMail = employeeRepository.findByEmployeeId(employeeId).getEmail();
				
		sendApprovalDoneEmail(employeeMail, employeeId, department);
	}

	private void sendApprovalDoneEmail(String email, int employeeId, String department) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Request Approved department: " + department);
		message.setText("Your resignation request for employeeid: " + employeeId + "has been approved by "+ department);

		mailSender.send(message);
	}
	
	private void sendOtherApprovalDoneEmail(String email, int employeeId, String department) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Request Approved: " + department);
		message.setText("Your request for employeeid: " + employeeId + " has been approved for "+ department);

		mailSender.send(message);
	}

}
