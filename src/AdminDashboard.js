import React from 'react';
import ResignationTable from './ResignationTable';

const AdminDashboard = () => {
    return (
        <div>
    <h2>Welcome, Admin!</h2>
    <ResignationTable department="Admin" />
    </div>
);
  };
  
  export default AdminDashboard;