import React, { useState } from 'react';

function Login({ onLogin }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = (userType) => {
    // Add your authentication logic here
    if (username === 'user' && password === 'password') {
      onLogin('user');
    } else if (username === 'manager' && password === 'password') {
      onLogin('manager');
    } else {
      alert('Invalid credentials');
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <button onClick={() => handleLogin('user')}>User Login</button>
      <button onClick={() => handleLogin('manager')}>Manager Login</button>
    </div>
  );
}

export default Login;
