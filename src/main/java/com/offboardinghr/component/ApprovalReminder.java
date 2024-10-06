package com.offboardinghr.component;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.offboardinghr.entity.DeptClearance;
import com.offboardinghr.entity.DeptHead;
import com.offboardinghr.repository.DeptClearanceRepository;
import com.offboardinghr.repository.DeptHeadRepository;

@Component
public class ApprovalReminder {
	
	private final DeptClearanceRepository departmentClearanceRepository;
	private final DeptHeadRepository deptHeadRepository;
    private final JavaMailSender mailSender;

    public ApprovalReminder(DeptClearanceRepository departmentClearanceRepository, 
                               JavaMailSender mailSender, DeptHeadRepository deptHeadRepository) {
        this.departmentClearanceRepository = departmentClearanceRepository;
        this.mailSender = mailSender;
        this.deptHeadRepository = deptHeadRepository;
    }

    @Scheduled(cron = "0 30 10 * * ?") // Every day at 10:30 AM
    public void sendApprovalReminders() {
        
    	System.out.println("Scheduled task");
        List<DeptClearance> pendingClearances = departmentClearanceRepository
            .findAllByClearanceApprovedFalse();

        for (DeptClearance clearance : pendingClearances) {
            // Get the department head email
            DeptHead head = deptHeadRepository
                .findByDepartment(clearance.getDepartment());

            // Send reminder email
            sendReminderEmail(head.getHeadEmail(), clearance.getEmployeeId(), clearance.getDepartment());
        }
    }

    private void sendReminderEmail(String email, int employeeId, String department) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reminder: Approval Pending for " + department);
        message.setText("Please approve the offboarding clearance for employee ID: " + employeeId);

        mailSender.send(message);
    }

}
