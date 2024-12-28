import React from 'react';
import './App.css'; // Import the CSS file for styling
import { Route, Routes } from 'react-router-dom'; // Import React Router components
import Login from './pages/Login'; // Correct path to Login.js
import Signup from './pages/Signup'; // Correct path to Signup.js
import TestDashboard from './pages/TestDashboard'; // Import the TestDashboard page
import ReportsPage from './pages/ReportsPage'; // Import the ReportsPage

function App() {
  return (
    <div className="app">
      {/* Header Section */}
      <header className="app-header">
        <h1>AI Test Automation</h1>
      </header>

      {/* Main Content Area */}
      <div className="app-content">
        <Routes>
          {/* Define the routes for Login, Signup, Dashboard, and Reports */}
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/dashboard" element={<TestDashboard />} />
          <Route path="/reports" element={<ReportsPage />} />
        </Routes>
      </div>
      
      {/* Footer Section */}
      <footer className="app-footer">
        <p>&copy; 2024 All rights reserved</p>
      </footer>
    </div>
  );
}

export default App;
