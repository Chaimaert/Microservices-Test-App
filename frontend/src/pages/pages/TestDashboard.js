import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { generateReportFromCode } from "./api";
import "./TestDashboard.css";
import backgroundImage from '../images/AI-Background6.png'; // Importer l'image

function TestDashboard() {
  const [code, setCode] = useState(""); // Code saisi par l'utilisateur
  const [loading, setLoading] = useState(false); // État de chargement
  const navigate = useNavigate();

  const handleCodeChange = (event) => {
    setCode(event.target.value);
  };

  const handleGenerateReport = async () => {
    setLoading(true);
    try {
      await generateReportFromCode(code); // Générer le rapport
      navigate("/reports"); // Rediriger vers la page des rapports
    } catch (error) {
      alert("Erreur lors de la génération du rapport. Veuillez réessayer.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="dashboard-wrapper" style={{ backgroundImage: `url(${backgroundImage})` }}>
      <div className="dashboard-header">
        <h2>AI Test Automation</h2>
      </div>

      <div className="dashboard-content">
        <div className="dashboard-left">
          <h3>Entrez votre URL du code à tester</h3>
          <textarea
            value={code}
            onChange={handleCodeChange}
            placeholder="Écrivez ou collez votre URL du code source ici..."
            style={{ width: "100%", height: "200px" }} // Ajustez la taille ici si nécessaire
          />
        </div>
      </div>

      <div className="reports-button-container">
        <button
          className="reports-button"
          onClick={handleGenerateReport}
          disabled={loading}
        >
          Générer le Test et le Rapport
        </button>
      </div>
    </div>
  );
}

export default TestDashboard;