This is the backend for the HROffboarding prohject.
For simplicity it uses Spring Data JPA for db purposes.

-- List of APIs

All the APIs start with http://localhost:8080/api/ for simplicity purposes


Approvals:

1. /doApproval/Approve - For a department to approve the resignation request of an employee
2. /doApproval/approveAssetReturn/{empID} - For IT to approve the Asset return status
3. /doApproval/approveExitInterview/{empID} - For HR to approve the Exit Interview status

GetResignationList to show in Frontend:

1. /getResignations/deptWise - To get the resignation list deptwise
2. /getResignations/exitInterviewStatus - To get the exitInterviewStatus of resigned employees
3. /getResignations/assetReturnStatus - To get the assetReturnstatus of resigned employees

Login Controller to simply login for different roles (Employee and Dept heads)

OffBoarding - To allow an employee to resign

1. /offboarding/resign/{empid}
2. /offboardingstatus/approval-status/{empid}

Using these APIs, the following functionalities are added in the HROffBoardingAPP:

1. Allowing an employee to resign
2. Allowing the deptheads to directly get list of all employees resgined
3. Allowing the dept heads to approve or reject the resignation request
4. Allowing the IT and HR heads to change the exit interview status and asset return status

In addition to these following functionalities are added:

1. When resigned, all the dept heads will get a mail
2. When not approved, every 10:30AM the dept heads will be getting remainder mails
3. Real time mails to the employee whenever the resignation is approved by any of the heads
