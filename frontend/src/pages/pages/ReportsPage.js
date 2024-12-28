import React, { useEffect, useState } from "react";
import { fetchAllReports, downloadReport } from "./api";
import "./ReportsPage.css";

function ReportsPage() {
  const [reports, setReports] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const loadReports = async () => {
      setLoading(true);
      try {
        const reportData = await fetchAllReports();
        setReports(reportData);
      } catch (error) {
        console.error("Error fetching reports:", error);
        alert("Error loading reports.");
      } finally {
        setLoading(false);
      }
    };

    loadReports();
  }, []);

  const handleDownload = async (reportId) => {
    try {
      const fileData = await downloadReport(reportId);
      const blob = new Blob([fileData], { type: "application/pdf" });
      const link = document.createElement("a");
      link.href = window.URL.createObjectURL(blob);
      link.download = `Report-${reportId}.pdf`;
      link.click();
    } catch (error) {
      console.error("Error downloading report:", error);
      alert("Error downloading the report.");
    }
  };

  return (
    <div className="reports-wrapper">
      <h2>Available Reports</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div className="reports-list">
          {reports.map((report) => (
            <div key={report.id} className="report-item">
              <div className="report-info">
                <span className="report-id">Rapport : {report.id}</span>
              </div>
              <button onClick={() => handleDownload(report.id)}>DOWNLOAD</button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default ReportsPage;