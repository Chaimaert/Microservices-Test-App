import React, { useState } from 'react'; 
import { useNavigate } from 'react-router-dom'; // Pour la navigation
import './TestDashboard.css'; // Importer le fichier CSS pour le style

function TestDashboard() {
  const [code, setCode] = useState('');  // State pour le code utilisateur
  const [output, setOutput] = useState(''); // State pour l'output
  const navigate = useNavigate(); // Hook pour la navigation

  // Gérer les changements dans le champ texte
  const handleCodeChange = (event) => {
    setCode(event.target.value);
  };

  // Placeholder pour la génération de rapports
  const handleGenerateReport = () => {
    setOutput('Report generated: The code has been analyzed and a report is generated.');
  };

  // Placeholder pour l'analyse des tests
  const handleAnalyzeTests = () => {
    setOutput('Tests analyzed: The code has been analyzed for potential test cases and errors.');
  };

  return (
    <div className="dashboard-wrapper">
      <div className="dashboard-header">
        <h2>Test Scenarios Dashboard</h2>
      </div>

      <div className="dashboard-content">
        {/* Partie gauche : Input de code */}
        <div className="dashboard-left">
          <h3>Enter Your Code to Test</h3>
          <textarea
            value={code}
            onChange={handleCodeChange}
            placeholder="Write or paste your source code here..."
          />
          <div className="button-container">
            <button onClick={handleAnalyzeTests}>Generate Test</button>
          </div>
        </div>

        {/* Partie droite : Output */}
        <div className="dashboard-right">
          <h3>Output</h3>
          <div className="output-container">
            <p>{output || 'The result of your code analysis will appear here.'}</p>
          </div>
          <div className="button-container">
            <button onClick={handleGenerateReport}>Analyze Code</button>
          </div>
        </div>
      </div>

      {/* Bouton Reports en bas */}
      <div className="reports-button-container">
        <button
          className="reports-button"
          onClick={() => navigate('/reports')} // Redirection vers la page des rapports
        >
          Reports
        </button>
      </div>
    </div>
  );
}

export default TestDashboard;
