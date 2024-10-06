import React from 'react';
import ResignationTable from './ResignationTable';

const FinanceDashboard = () => {
    return (
        <div>
    <h2>Welcome, Finance!</h2>
    <ResignationTable department="Finance" />
    </div>
);
  };
  
  export default FinanceDashboard;