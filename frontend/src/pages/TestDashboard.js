import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  fetchGeneratedTest,
  fetchTestAnalysis,
  generateReportFromCode,
} from "./api";
import "./TestDashboard.css";

function TestDashboard() {
  const [code, setCode] = useState(""); // User's input code
  const [output, setOutput] = useState(""); // Output for tests/analysis
  const [loading, setLoading] = useState(false); // Loading state
  const navigate = useNavigate();

  const handleCodeChange = (event) => {
    setCode(event.target.value);
  };

  const handleGenerateTest = async () => {
    setLoading(true);
    try {
      const result = await fetchGeneratedTest({ code });
      setOutput(result); // Display test output
    } catch (error) {
      setOutput("Error generating test. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const handleAnalyzeCode = async () => {
    setLoading(true);
    try {
      const analysisResult = await fetchTestAnalysis({ code });
      setOutput(analysisResult); // Display analysis output
    } catch (error) {
      setOutput("Error analyzing code. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const handleGenerateReport = async () => {
    setLoading(true);
    try {
      await generateReportFromCode(code); // Generate the report
      navigate("/reports"); // Redirect to the reports page
    } catch (error) {
      alert("Error generating report. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="dashboard-wrapper">
      <div className="dashboard-header">
        <h2>AI Test Automation</h2>
      </div>

      <div className="dashboard-content">
        <div className="dashboard-left">
          <h3>Enter Your Code to Test</h3>
          <textarea
            value={code}
            onChange={handleCodeChange}
            placeholder="Write or paste your source code here..."
          />
          <div className="button-container">
            <button onClick={handleGenerateTest} disabled={loading}>
              Generate Test
            </button>
          </div>
        </div>

        <div className="dashboard-right">
          <h3>Output</h3>
          <div className="output-container">
            <p>{output || "The result of your code analysis will appear here."}</p>
          </div>
          <div className="button-container">
            <button onClick={handleAnalyzeCode} disabled={loading}>
              Analyze Code
            </button>
          </div>
        </div>
      </div>

      <div className="reports-button-container">
        <button
          className="reports-button"
          onClick={handleGenerateReport}
          disabled={loading}
        >
          Generate Report
        </button>
      </div>
    </div>
  );
}

export default TestDashboard;
