import React, { useState } from 'react';
import './Login.css'; // Ensure the path is correct for the CSS file
import backgroundImage from '../images/AI-Background6.png'; // Correct the path if necessary
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faLock } from '@fortawesome/free-solid-svg-icons';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  // Static credentials for user
  const userCredentials = {
    username: 'latifa',
    password: 'latifadidialaoui123@',
  };

  const handleLogin = (event) => {
    event.preventDefault();

    // Check if the entered credentials match the static user credentials
    if (username === userCredentials.username && password === userCredentials.password) {
      navigate('/dashboard'); // Redirect to the dashboard page
    } else {
      setErrorMessage('Invalid username or password.');
    }
  };

  return (
    <div className="login-wrapper">
      <div className="login-box">
        {/* Left Side for Form */}
        <div className="left-side">
          <form onSubmit={handleLogin}>
            <h2>Login</h2>
            <div className="input-container">
              <FontAwesomeIcon icon={faUser} className="input-icon" />
              <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div className="input-container">
              <FontAwesomeIcon icon={faLock} className="input-icon" />
              <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            <button type="submit">Login</button>
          </form>

          {/* Link to Signup page */}
          <p className="signup-prompt">
            Don't have an account? <Link to="/signup">Sign up here</Link>
          </p>
        </div>

        {/* Right Side for Image */}
        <div className="right-side">
          <img src={backgroundImage} alt="Login Illustration" />
        </div>
      </div>
    </div>
  );
}

export default Login;
