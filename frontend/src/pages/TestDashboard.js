import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  fetchGeneratedTest,
  fetchTestAnalysis,
  generateReportFromCode,
} from "./api";
import "./TestDashboard.css";

function TestDashboard() {
  const [code, setCode] = useState(""); // Code saisi par l'utilisateur
  const [output, setOutput] = useState(""); // Sortie pour les tests/analyse
  const [loading, setLoading] = useState(false); // État de chargement
  const navigate = useNavigate();

  const handleCodeChange = (event) => {
    setCode(event.target.value);
  };

  const handleGenerateTest = async () => {
    setLoading(true);
    setOutput(""); // Effacer la sortie précédente
    try {
      const result = await fetchGeneratedTest({ code });
      // Extraire le testCode du résultat
      const testCode = result[0]?.testCode || "Aucun code de test généré."; // Gérer le cas où le résultat est vide
      setOutput(testCode); // Afficher uniquement le testCode
    } catch (error) {
      setOutput("Erreur lors de la génération du test. Veuillez réessayer.");
    } finally {
      setLoading(false);
    }
  };

  const handleAnalyzeCode = async () => {
    setLoading(true);
    try {
        const analysisResult = await fetchTestAnalysis({ code });

        // Vérifiez que analysisResult est un objet
        if (analysisResult && typeof analysisResult === 'object') {
            // Extraire uniquement le message d'analyse
            const analysisMessage = analysisResult.analysis || "Aucune analyse disponible.";
            setOutput((prevOutput) => prevOutput + "\nAnalyse : " + analysisMessage); // Ajouter à la sortie existante
        } else {
            setOutput("Aucune analyse disponible.");
        }
    } catch (error) {
        setOutput("Erreur lors de l'analyse du code. Veuillez réessayer.");
    } finally {
        setLoading(false);
    }
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
    <div className="dashboard-wrapper">
      <div className="dashboard-header">
        <h2>AI Test Automation</h2>
      </div>

      <div className="dashboard-content">
        <div className="dashboard-left">
          <h3>Entrez votre code à tester</h3>
          <textarea
            value={code}
            onChange={handleCodeChange}
            placeholder="Écrivez ou collez votre code source ici..."
          />
          <div className="button-container">
            <button onClick={handleGenerateTest} disabled={loading}>
              Générer le Test
            </button>
            <button onClick={handleAnalyzeCode} disabled={loading}>
              Analyser le Code
            </button>
          </div>
        </div>

        <div className="dashboard-right">
          <h3>Sortie</h3>
          <div className="output-container">
            <pre>{output || "Le résultat de votre analyse apparaîtra ici."}</pre>
          </div>
        </div>
      </div>

      <div className="reports-button-container">
        <button
          className="reports-button"
          onClick={handleGenerateReport}
          disabled={loading}
        >
          Générer le Rapport
        </button>
      </div>
    </div>
  );
}

export default TestDashboard;