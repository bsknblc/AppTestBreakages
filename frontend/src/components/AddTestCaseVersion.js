import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";
import AppDropdown from "./AppDropdown";

const AddTestCaseVersion = ({ onAdded, testCase }) => {
  const [formData, setFormData] = useState({
    testCaseVersionName: "",
    testCaseId: testCase || null,
  });
  const [selectedTestCase, setSelectedTestCase] = useState(null);
  const [error, setError] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);
  const [testCases, setTestCases] = useState([]);
  const [loadingTestCases, setLoadingTestCases] = useState(true);

  useEffect(() => {
    if (testCase === null) {
      const fetchTestCases = async () => {
        try {
          const response = await fetch("http://localhost:3000/api/test_cases");
          if (!response.ok) {
            throw new Error("Failed to fetch test_cases");
          }
          const data = await response.json();
          setTestCases(data);
        } catch (err) {
          setError(err.message);
        } finally {
          setLoadingTestCases(false);
        }
      };

      fetchTestCases();
    }
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleTestCaseSelect = (selectedItem) => {
    setSelectedTestCase(selectedItem);
    setFormData((prev) => ({
      ...prev,
      testCaseId: selectedItem.id,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setError(null);
    setSuccess(false);

    try {
      const response = await fetch(
        "http://localhost:3000/api/test_case_versions",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );

      if (!response.ok) {
        throw new Error("Failed to add test case version");
      }

      onAdded();
      setFormData({ testCaseVersionName: "", testCaseId: testCase || null });
      if (testCase === null) {
        setSelectedTestCase(null);
      }
      setSuccess(true);
      setTimeout(() => setSuccess(false), 3000);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="card">
      <div className="card-header bg-primary text-white">
        <h3 className="mb-0">Add New Test Case Version</h3>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        {success && (
          <div className="alert alert-success">
            Test Case Version added successfully!
          </div>
        )}

        <form onSubmit={handleSubmit}>
          {!testCase && (
            <div className="mb-3">
              <label htmlFor="testCaseId" className="form-label">
                Test Case
              </label>
              {loadingTestCases ? (
                <div className="d-flex justify-content-center">
                  <div className="spinner-border" role="status">
                    <span className="visually-hidden">Loading...</span>
                  </div>
                </div>
              ) : (
                <AppDropdown
                  items={testCases}
                  dataType="test_cases"
                  onSelect={handleTestCaseSelect}
                  selectedItem={selectedTestCase}
                />
              )}
            </div>
          )}
          <div className="mb-3">
            <label htmlFor="testCaseVersionName" className="form-label">
              Test Case Version Name
            </label>
            <input
              type="text"
              className="form-control"
              id="testCaseVersionName"
              name="testCaseVersionName"
              value={formData.testCaseVersionName}
              onChange={handleChange}
              required
            />
          </div>
          <button
            type="submit"
            className="btn btn-primary"
            disabled={isSubmitting}
          >
            {isSubmitting ? (
              <>
                <span
                  className="spinner-border spinner-border-sm me-2"
                  role="status"
                  aria-hidden="true"
                ></span>
                Adding...
              </>
            ) : (
              "Add Test Case Version"
            )}
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddTestCaseVersion;
