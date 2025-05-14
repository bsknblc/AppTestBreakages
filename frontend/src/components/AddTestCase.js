import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";
import AppDropdown from "./AppDropdown";

const AddTestCase = ({ onAdded }) => {
  const [formData, setFormData] = useState({
    testCaseName: "",
    description: "",
    testSuiteId: null,
  });
  const [selectedTestSuite, setSelectedTestSuite] = useState(null);
  const [error, setError] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);
  const [testSuites, setTestSuites] = useState([]);
  const [loadingSuites, setLoadingSuites] = useState(true);

  useEffect(() => {
    const fetchTestSuites = async () => {
      try {
        const response = await fetch("http://localhost:3000/api/test_suites");
        if (!response.ok) {
          throw new Error("Failed to fetch test suites");
        }
        const data = await response.json();
        setTestSuites(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoadingSuites(false);
      }
    };

    fetchTestSuites();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleTestSuiteSelect = (selectedItem) => {
    setSelectedTestSuite(selectedItem);
    setFormData((prev) => ({
      ...prev,
      testSuiteId: selectedItem.id,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setError(null);
    setSuccess(false);

    try {
      const response = await fetch("http://localhost:3000/api/test_cases", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Failed to add test case.");
      }

      onAdded();
      setFormData({ testCaseName: "", description: "", testSuiteId: null });
      setSelectedTestSuite(null);
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
        <h3 className="mb-0">Add New Test Case</h3>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        {success && (
          <div className="alert alert-success">
            Test case added successfully!
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="testSuiteId" className="form-label">
              Test Suite
            </label>
            {loadingSuites ? (
              <div className="d-flex justify-content-center">
                <div className="spinner-border" role="status">
                  <span className="visually-hidden">Loading...</span>
                </div>
              </div>
            ) : (
              <AppDropdown
                items={testSuites}
                dataType="test_suites"
                onSelect={handleTestSuiteSelect}
                selectedItem={selectedTestSuite}
              />
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="testCaseName" className="form-label">
              Test Case Name
            </label>
            <input
              type="text"
              className="form-control"
              id="testCaseName"
              name="testCaseName"
              value={formData.testCaseName}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="description" className="form-label">
              Description
            </label>
            <input
              type="text"
              className="form-control"
              id="description"
              name="description"
              value={formData.description}
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
              "Add Test Case"
            )}
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddTestCase;
