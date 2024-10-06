import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';

const ResignationTable = ({ department }) => {
  const [resignations, setResignations] = useState([]);

  useEffect(() => {
    // Fetch resignations by department, only retrieving the IDs
    axios.get(`http://localhost:8080/api/getResignations/deptwiseApproved?department=${department}&clearanceApproved=1`)
      .then(response => setResignations(response.data))
      .catch(error => console.error('Error fetching resignations:', error));
  }, [department]);

  const handleApprove = (id) => {
    // Send both id and department in the request params
    
    axios.post(`http://localhost:8080/api/doApproval/approve`, null, {
      params: {
        employeeId: resignations[0],
        department: department,
      }
    })
    .then(response => {
      console.log('Resignation approved:', response.data);
      // Update the resignation list by setting the approved flag to true
      setResignations(resignations.map(resignation => 
        resignation.employee_id === resignation ? { ...resignation, approved: true } : resignation
      ));
    })
    .catch(error => console.error('Error approving resignation:', error));
  };

  return (
    <div>
      <h2>{department} - List of Resignation IDs to be Approved</h2>
      <table className="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
        {resignations.map(resignation => (
            <tr key={`${department}-${resignation.id}`}>
              <td>{resignation}</td>
              <td>{resignation.approved ? "Approved" : "Pending"}</td>
              <td>
                {resignation.approved ? (
                  "Already Approved"
                ) : (
                  <button onClick={() => handleApprove(resignation.id)}>Approve</button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ResignationTable;
