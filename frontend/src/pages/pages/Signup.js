import React from 'react';
import './Signup.css';
import backgroundImage from '../images/AI-Background6.png';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faEnvelope, faLock } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';  // Import Link

function Signup() {
  return (
    <div className="signup-wrapper">
      <div className="signup-box">
        <div className="left-side">
          <form>
            <h2>Signup</h2>
            <div className="input-container">
              <FontAwesomeIcon icon={faUser} className="input-icon" />
              <input type="text" placeholder="Username" />
            </div>
            <div className="input-container">
              <FontAwesomeIcon icon={faEnvelope} className="input-icon" />
              <input type="email" placeholder="Email" />
            </div>
            <div className="input-container">
              <FontAwesomeIcon icon={faLock} className="input-icon" />
              <input type="password" placeholder="Password" />
            </div>
            <button type="submit">Sign Up</button>
          </form>

          {/* Link to the Login page */}
          <p className="login-prompt">
            Already have an account? <Link to="/login">Login here</Link>
          </p>
        </div>

        <div className="right-side">
          <img src={backgroundImage} alt="Signup Illustration" />
        </div>
      </div>
    </div>
  );
}

export default Signup;
