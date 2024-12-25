import axios from "axios";

// Gateway URL
const API_BASE_URL = "http://localhost:9090";

const api = axios.create({
  baseURL: API_BASE_URL,
});

// Fetch generated test code
export const fetchGeneratedTest = async (data) => {
  try {
    const response = await api.post("/test/generate_code", data);
    return response.data;
  } catch (error) {
    console.error("Error generating test:", error);
    throw error;
  }
};

// Fetch test analysis result
export const fetchTestAnalysis = async (data) => {
  try {
    const response = await api.post("/analyse/code", data);
    return response.data;
  } catch (error) {
    console.error("Error analyzing test:", error);
    throw error;
  }
};

// Generate a report based on result test code
export const generateReportFromCode = async (code) => {
  try {
    const response = await api.post("/reports/generate-from-code", code, {
      headers: { "Content-Type": "text/plain" },
    });
    return response.data; 
  } catch (error) {
    console.error("Error generating report:", error);
    throw error;
  }
};

// Get all available reports
export const fetchAllReports = async () => {
  try {
    const response = await api.get("/reports");
    return response.data; 
  } catch (error) {
    console.error("Error fetching reports:", error);
    throw error;
  }
};

// Download a specific report by ID
export const downloadReport = async (reportId) => {
  try {
    const response = await api.get(`/reports/download/${reportId}`, {
      responseType: "blob", 
    });
    return response.data; 
  } catch (error) {
    console.error("Error downloading report:", error);
    throw error;
  }
};

export default api;
