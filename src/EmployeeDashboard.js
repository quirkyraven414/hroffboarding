import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function EmployeeDashboard() {
  const [resigned, setResigned] = useState(false);
  const [approvals, setApprovals] = useState({
    HR: false,
    IT: false,
    Finance: false,
    Admin: false,
    Manager: false,
  });

  // Function to trigger resignation
  const handleResign = () => {
    axios.get('http://localhost:8080/api/offboarding/resign/4122')  // Assume userId is 1
      .then(response => {
        setResigned(true);  // Mark resigned state as true
        fetchApprovals();  // Fetch approval statuses immediately after resigning
        alert('Resignation process started.');
      })
      .catch(error => {
        console.error('Error during resignation process:', error);
      });
  };

  // Function to fetch approval statuses from the backend
  const fetchApprovals = () => {
    axios.get('http://localhost:8080/api/offboarding/approval-status/1') // Replace with correct userId
      .then(response => {
        setApprovals(response.data);  // Set the approval status for each department
      })
      .catch(error => {
        console.error('Error fetching approval statuses:', error);
      });
  };

  // Use Effect hook to fetch approval status if resigned
  useEffect(() => {
    if (resigned) {
      fetchApprovals();  // Fetch approval statuses when the component loads (if resigned)
    }
  }, [resigned]);

  return (
    <div>
      <h2>Employee Dashboard</h2>
      <h2>Welcome, Employee!</h2>
      
      {!resigned ? (
        <button onClick={handleResign}>Resign</button>
      ) : (
        <div>
          <h3>Approval Status:</h3>
          <table  className="table">
            <thead>
              <tr>
                <th>Department</th>
                <th>Approval Status</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>HR</td>
                <td>{approvals.HR ? 'Approved' : 'Pending'}</td>
              </tr>
              <tr>
                <td>IT</td>
                <td>{approvals.IT ? 'Approved' : 'Pending'}</td>
              </tr>
              <tr>
                <td>Finance</td>
                <td>{approvals.Finance ? 'Approved' : 'Pending'}</td>
              </tr>
              <tr>
                <td>Admin</td>
                <td>{approvals.Admin ? 'Approved' : 'Pending'}</td>
              </tr>
              <tr>
                <td>Manager</td>
                <td>{approvals.Manager ? 'Approved' : 'Pending'}</td>
              </tr>
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default EmployeeDashboard;
