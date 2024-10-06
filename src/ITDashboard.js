import React, { useEffect, useState } from 'react';
import ResignationTable from './ResignationTable';
import './App.css';
import axios from 'axios';

const ITDashboard = () => {

    const [assetReturnStatus, setAssetReturnStatus] = useState([]);


    useEffect(() => {
    // Fetch exit interview data from backend API
    const fetchAssetReturnStatus = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/getResignations/assetReturnStatus'); 
        setAssetReturnStatus(response.data); 
      } catch (error) {
        console.error("Error fetching exit interview data", error);
      }
    };

    fetchAssetReturnStatus();
    }, []);

    const handleApprove = async (id) => {
        try {
          await axios.post(`http://localhost:8080/api/doApproval/approveAssetReturn/${id}`); // Adjust the endpoint as needed
          alert('Asset Return approved successfully!');
          // Optionally, refetch the data to update the status
          // fetchExitInterviewData(); // Uncomment if you want to refresh the table after approval
        } catch (error) {
          console.error("Error approving exit interview", error);
        }
      };



    return (
        <div>
    <h2>Welcome, IT!</h2>
    <ResignationTable department="IT" />
   

<h2>Asset Tracking Status</h2>
<table  className="table">
  <thead>
    <tr>
      <th>ID</th>
      <th>Asset Tracking Status</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    {assetReturnStatus.map(interview => (
                <tr key={interview}>
                <td>{interview.employeeid}</td>
                <td>{interview.assetReturnStatus === false ? 'Pending' : 'Completed'}</td>
                <td>
                    {interview.assetReturnStatus === false && ( // Show button only if status is 0
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
  
  export default ITDashboard;