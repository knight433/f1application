import React, { useState } from 'react';

function LoginForm({ onLoginSuccess }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const response = await fetch('http://localhost:8080/f1buzz/api/v1/user/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Basic ' + btoa('admin:admin123'),
        },
        body: JSON.stringify({ username, password }),
      });

      if (response.ok) {
        onLoginSuccess(username);
      } else {
        alert('Login failed. Check credentials.');
      }
    } catch (error) {
      console.error('Error during login:', error);
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center min-vh-100" style={{ background: 'linear-gradient(to bottom, #000, #6f42c1)' }}>
      <div className="card bg-dark text-white p-4 shadow" style={{ width: '300px' }}>
        <h4 className="mb-3 text-center">Login</h4>
        <div className="mb-3">
          <input
            type="text"
            className="form-control bg-secondary text-white border-0"
            placeholder="Username"
            value={username}
            onChange={e => setUsername(e.target.value)}
          />
        </div>
        <div className="mb-3">
          <input
            type="password"
            className="form-control bg-secondary text-white border-0"
            placeholder="Password"
            value={password}
            onChange={e => setPassword(e.target.value)}
          />
        </div>
        <button className="btn btn-purple w-100" onClick={handleLogin} style={{ backgroundColor: '#6f42c1', border: 'none' }}>
          Login
        </button>
      </div>
    </div>
  );
}

export default LoginForm;
