import React, { useState, useEffect } from "react";
import AppDropdown from "../components/AppDropdown";

const DataProvider = () => {
  const [dataType, setDataType] = useState("applications"); // default data type
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        let endpoint = "";

        // Determine the endpoint based on selected data type
        switch (dataType) {
          case "applications":
            endpoint = "http://localhost:3000/api/applications";
            break;
          case "app_releases":
            endpoint = "http://localhost:3000/api/app_releases";
            break;
          case "test_suites":
            endpoint = "http://localhost:3000/api/test_suites";
            break;
          case "test_cases":
            endpoint = "http://localhost:3000/api/test_cases";
            break;
          case "test_case_versions":
            endpoint = "http://localhost:3000/api/test_case_versions";
            break;
          case "breakage_reasons":
            endpoint = "http://localhost:3000/api/breakage_reasons";
            break;
          case "locating_methods":
            endpoint = "http://localhost:3000/api/locating_methods";
            break;
          case "breakages":
            endpoint = "http://localhost:3000/api/breakages";
            break;
          case "repairs":
            endpoint = "http://localhost:3000/api/repairs";
            break;
          default:
            endpoint = "http://localhost:3000/api/applications";
        }

        const response = await fetch(endpoint);
        if (!response.ok) {
          throw new Error(`Failed to fetch ${dataType}`);
        }
        const result = await response.json();
        setData(result);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [dataType]);

  const handleDataTypeChange = (type) => {
    setDataType(type);
  };

  if (loading) {
    return (
      <div className="d-flex justify-content-center mt-5">
        <div className="spinner-border" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div
        className="alert alert-danger mx-auto mt-5"
        style={{ maxWidth: "600px" }}
      >
        Error: {error}
      </div>
    );
  }

  return (
    <div className="container mt-5">
      <div className="row mb-4">
        <div className="col">
          <h3>Select Data Type:</h3>
          <div className="btn-group" role="group">
            <button
              type="button"
              className={`btn ${
                dataType === "applications"
                  ? "btn-primary"
                  : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("applications")}
            >
              Applications
            </button>
            <button
              type="button"
              className={`btn ${
                dataType === "app_releases"
                  ? "btn-primary"
                  : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("app_releases")}
            >
              App Releases
            </button>
            <button
              type="button"
              className={`btn ${
                dataType === "test_suites"
                  ? "btn-primary"
                  : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("test_suites")}
            >
              Test Suites
            </button>
            <button
              type="button"
              className={`btn ${
                dataType === "test_cases"
                  ? "btn-primary"
                  : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("test_cases")}
            >
              Test Cases
            </button>
            <button
              type="button"
              className={`btn ${
                dataType === "test_case_versions"
                  ? "btn-primary"
                  : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("test_case_versions")}
            >
              Test Case Versions
            </button>
            <button
              type="button"
              className={`btn ${
                dataType === "breakage_reasons"
                  ? "btn-primary"
                  : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("breakage_reasons")}
            >
              Breakage Reasons
            </button>
            <button
              type="button"
              className={`btn ${
                dataType === "locating_methods"
                  ? "btn-primary"
                  : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("locating_methods")}
            >
              Locating Methods
            </button>
            <button
              type="button"
              className={`btn ${
                dataType === "breakages" ? "btn-primary" : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("breakages")}
            >
              Breakages
            </button>
            <button
              type="button"
              className={`btn ${
                dataType === "repairs" ? "btn-primary" : "btn-outline-primary"
              }`}
              onClick={() => handleDataTypeChange("repairs")}
            >
              Repairs
            </button>
          </div>
        </div>
      </div>

      <div className="row">
        <div className="col">
          <h4>Current Data Type: {dataType}</h4>
          <AppDropdown items={data} dataType={dataType} onSelect={() => {}} />
        </div>
      </div>
    </div>
  );
};

export default DataProvider;
