import React from 'react';
import './Login.css';  // Ensure the path is correct for the CSS file
import backgroundImage from '../images/AI-Background6.png';  // Correct the path if necessary
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faLock } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

function Login() {
  return (
    <div className="login-wrapper">
      <div className="login-box">
        {/* Left Side for Form */}
        <div className="left-side">
          <form>
            <h2>Login</h2>
            <div className="input-container">
              <FontAwesomeIcon icon={faUser} className="input-icon" />
              <input type="text" placeholder="Username" />
            </div>
            <div className="input-container">
              <FontAwesomeIcon icon={faLock} className="input-icon" />
              <input type="password" placeholder="Password" />
            </div>
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
