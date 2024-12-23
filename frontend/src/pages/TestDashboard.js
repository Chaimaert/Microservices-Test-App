import React, { useState } from 'react';
import './TestDashboard.css'; // Import the CSS file for styling

function TestDashboard() {
  const [code, setCode] = useState('');  // State to hold the code input by the user
  const [output, setOutput] = useState(''); // State to hold the output of the test analysis or report

  // Function to handle the change in the textarea (code input)
  const handleCodeChange = (event) => {
    setCode(event.target.value);
  };

  // Function to handle the report generation (just a placeholder for now)
  const handleGenerateReport = () => {
    setOutput('Report generated: The code has been analyzed and a report is generated.'); // Example output
  };

  // Function to handle test analysis (just a placeholder for now)
  const handleAnalyzeTests = () => {
    setOutput('Tests analyzed: The code has been analyzed for potential test cases and errors.'); // Example output
  };

  return (
    <div className="dashboard-wrapper">
      <div className="dashboard-header">
        <h2>Test Scenarios Dashboard</h2>
      </div>

      <div className="dashboard-content">
        {/* Left side: Code input and Analyze Tests button */}
        <div className="dashboard-left">
          <h3>Enter Your Code to Test</h3>
          <textarea
            value={code}
            onChange={handleCodeChange}
            placeholder="Write or paste your source code here..."
          />
          <div className="button-container">
            <button onClick={handleAnalyzeTests}>Analyze Tests</button>
          </div>
        </div>

        {/* Right side: Output and Generate Report button */}
        <div className="dashboard-right">
          <h3>Output</h3>
          <div className="output-container">
            <p>{output || 'The result of your code analysis will appear here.'}</p>
          </div>
          <div className="button-container">
            <button onClick={handleGenerateReport}>Generate Report</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default TestDashboard;
