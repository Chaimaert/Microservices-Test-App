import React, { useState } from 'react';
import './ReportsPage.css'; // Style optionnel

function ReportsPage() {
  // Example report data
  const reports = [
    { id: 1, name: 'Test Report 1', url: 'https://example.com/report1.pdf' },
    { id: 2, name: 'Test Report 2', url: 'https://example.com/report2.pdf' },
    { id: 3, name: 'Test Report 3', url: 'https://example.com/report3.pdf' },
    { id: 4, name: 'Test Report 4', url: 'https://example.com/report4.pdf' },
    { id: 5, name: 'Test Report 5', url: 'https://example.com/report5.pdf' },
    { id: 6, name: 'Test Report 6', url: 'https://example.com/report6.pdf' },
  ];

  const [currentPage, setCurrentPage] = useState(1);
  const reportsPerPage = 3;

  // Calculate the indexes for the current reports
  const indexOfLastReport = currentPage * reportsPerPage;
  const indexOfFirstReport = indexOfLastReport - reportsPerPage;
  const currentReports = reports.slice(indexOfFirstReport, indexOfLastReport);

  // Handle pagination
  const totalPages = Math.ceil(reports.length / reportsPerPage);

  const handleNextPage = () => {
    if (currentPage < totalPages) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handlePreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  return (
    <div className="reports-wrapper">
      <h2 className="reports-title">Test Reports</h2>
      <div className="reports-list">
        {currentReports.map((report) => (
          <div key={report.id} className="report-item">
            <span className="report-text">{report.name}</span>
            <button
              onClick={() => window.open(report.url, '_blank')}
              className="download-button"
            >
              Download
            </button>
          </div>
        ))}
      </div>
      <div className="pagination-controls">
        <button
          onClick={handlePreviousPage}
          disabled={currentPage === 1}
          className="pagination-button"
        >
          Previous
        </button>
        
        <button
          onClick={handleNextPage}
          disabled={currentPage === totalPages}
          className="pagination-button"
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default ReportsPage;
