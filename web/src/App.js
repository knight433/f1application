// src/App.js
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginForm from './pages/LoginForm';
import LandingPage from './pages/landingPage';

function App() {
  const [username, setUsername] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={
            isLoggedIn ? (
              <Navigate to="/landing" replace />
            ) : (
              <LoginForm onLoginSuccess={(name) => {
                setUsername(name);
                setIsLoggedIn(true);
              }} />
            )
          }
        />
        <Route
          path="/landing"
          element={
            isLoggedIn ? (
              <LandingPage username={username} />
            ) : (
              <Navigate to="/" replace />
            )
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
