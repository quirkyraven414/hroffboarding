import React, { useEffect, useState } from 'react';
import ResignationTable from './ResignationTable';
import './App.css';
import axios from 'axios';

const HRDashboard = () => {

    const [exitInterviews, setExitInterviews] = useState([]);


    useEffect(() => {
    // Fetch exit interview data from backend API
    const fetchExitInterviewData = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/getResignations/exitInterviewStatus'); // Replace with your actual endpoint
        setExitInterviews(response.data); // Assuming response.data is an array of exit interviews
      } catch (error) {
        console.error("Error fetching exit interview data", error);
      }
    };

    fetchExitInterviewData();
    }, []);

    const handleApprove = async (id) => {
        try {
          await axios.post(`http://localhost:8080/api/doApproval/approveExitInterview/${id}`); // Adjust the endpoint as needed
          alert('Exit interview approved successfully!');
          // Optionally, refetch the data to update the status
          // fetchExitInterviewData(); // Uncomment if you want to refresh the table after approval
        } catch (error) {
          console.error("Error approving exit interview", error);
        }
      };


    return (
        <div>
        <h2>Welcome, HR!</h2>
        <ResignationTable department="HR" />
        <h2>Exit Interview Status</h2>
        <table  className="table">
        <thead>
            <tr>
            <th>ID</th>
            <th>Interview Status</th>
            <th>Action</th>
            </tr>
        </thead>
        <tbody>
        {exitInterviews.map(interview => (
                    <tr key={interview}>
                    <td>{interview.employeeid}</td>
                    <td>{interview.interviewDone === false ? 'Pending' : 'Completed'}</td>
                    <td>
                        {interview.interviewDone === false && ( // Show button only if status is 0
                        <button onClick={() => handleApprove(interview.employeeid)}>Approve</button>
                        )}
                    </td>
                    </tr>
                ))}
        </tbody>
        </table>
        </div>
);
};
  
  export default HRDashboard;