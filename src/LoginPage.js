import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('employee'); // default role is employee
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    if (role === 'employee' && username === 'employee' && password === 'password') {
      navigate('/employee-dashboard');
    } else if (role === 'manager' ) {
        
        axios.post('http://localhost:8080/api/login', { username, password })
        .then(response => {
            
          const { department } = response.data;
  
          // Redirect based on department
          if (department === 'HR') {
            navigate('/hr-dashboard');
          } else if (department === 'Admin') {
            navigate('/admin-dashboard');
          } else if (department === 'Finance') {
            navigate('/finance-dashboard');
          } else if (department === 'IT') {
            navigate('/it-dashboard');
          } else {
            setError('Invalid department');
          }
        })
        .catch(error => {
          setError('Invalid credentials');
        });
    } else {
      setError('Invalid username, password, or role');
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        {/* Role Selection */}
        <div>
          <label>Login as:</label>
          <select value={role} onChange={(e) => setRole(e.target.value)}>
            <option value="employee">Employee</option>
            <option value="manager">Dept. Head</option>
          </select>
        </div>

        {error && <p style={{ color: 'red' }}>{error}</p>}
        
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
