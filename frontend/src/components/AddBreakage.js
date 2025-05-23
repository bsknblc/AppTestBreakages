import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";
import AppDropdown from "./AppDropdown";
import ExplanationSelector from "./ExplanationSelector";

const AddBreakage = ({ onAdded, testCaseVersion, appRelease }) => {
  const [formData, setFormData] = useState({
    description: "",
    line: "",
    breakageExplanations: [],
    testCaseVersionId: testCaseVersion || null,
    appReleaseId: appRelease || null,
    breakageReasonId: null,
    locatingMethodId: null,
  });
  const [selectedTestCaseVersion, setSelectedTestCaseVersion] = useState(null);
  const [selectedAppRelease, setSelectedAppRelease] = useState(null);
  const [selectedBreakageReason, setSelectedBreakageReason] = useState(null);
  const [selectedLocatingMethod, setSelectedLocatingMethod] = useState(null);
  const [error, setError] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);
  const [testCaseVersions, setTestCaseVersion] = useState([]);
  const [appReleases, setAppRelease] = useState([]);
  const [breakageReason, setBreakageReason] = useState([]);
  const [locatingMethod, setLocatingMethod] = useState(true);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [
          testCaseVersionsRes,
          appReleasesRes,
          breakageReasonsRes,
          locatingMethodsRes,
        ] = await Promise.all([
          fetch("http://localhost:3000/api/test_case_versions"),
          fetch("http://localhost:3000/api/app_releases"),
          fetch("http://localhost:3000/api/breakage_reasons"),
          fetch("http://localhost:3000/api/locating_methods"),
        ]);

        if (
          !testCaseVersionsRes.ok ||
          !appReleasesRes.ok ||
          !breakageReasonsRes.ok ||
          !locatingMethodsRes.ok
        ) {
          throw new Error("Failed to fetch data");
        }

        const testCaseVersions = await testCaseVersionsRes.json();
        const appReleases = await appReleasesRes.json();
        const breakageReasons = await breakageReasonsRes.json();
        const locatingMethods = await locatingMethodsRes.json();

        setTestCaseVersion(testCaseVersions);
        setAppRelease(appReleases);
        setBreakageReason(breakageReasons);
        setLocatingMethod(locatingMethods);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleDropdownSelect = (selectedItem, fieldName, setSelectedItem) => {
    setSelectedItem(selectedItem);
    setFormData((prev) => ({
      ...prev,
      [fieldName]: selectedItem.id,
    }));
  };

  const handleAddBreakageExplanation = (explanation) => {
    setFormData((prev) => ({
      ...prev,
      breakageExplanations: prev.breakageExplanations.some(
        (e) => e.id === explanation.id
      )
        ? prev.breakageExplanations
        : [...prev.breakageExplanations, explanation],
    }));
  };

  const handleRemoveBreakageExplanation = (explanation) => {
    setFormData((prev) => ({
      ...prev,
      breakageExplanations: prev.breakageExplanations.filter((e) =>
        e.id
          ? e.id !== explanation.id
          : e.explanation !== explanation.explanation
      ),
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setError(null);
    setSuccess(false);

    try {
      const breakageData = {
        ...formData,
        breakageExplanationIds: formData.breakageExplanations.map((e) => e.id),
      };
      const response = await fetch("http://localhost:3000/api/breakages", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(breakageData),
      });

      if (!response.ok) {
        throw new Error("Failed to add breakage.");
      }

      onAdded(response);
      setFormData({
        description: "",
        line: "",
        breakageExplanations: [],
        testCaseVersionId: testCaseVersion || null,
        appReleaseId: appRelease || null,
        breakageReasonId: null,
        locatingMethodId: null,
      });
      if (testCaseVersion === null) {
        setSelectedTestCaseVersion(null);
      }
      if (appRelease === null) {
        setSelectedAppRelease(null);
      }
      setSelectedBreakageReason(null);
      setSelectedLocatingMethod(null);
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
        <h3 className="mb-0">Add New Breakage</h3>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        {success && (
          <div className="alert alert-success">
            Breakage added successfully!
          </div>
        )}

        <form onSubmit={handleSubmit}>
          {!testCaseVersion && (
            <div className="mb-3">
              <label htmlFor="testCaseVersionId" className="form-label">
                Test Case Version
              </label>
              {loading ? (
                <div className="d-flex justify-content-center">
                  <div className="spinner-border" role="status">
                    <span className="visually-hidden">Loading...</span>
                  </div>
                </div>
              ) : (
                <AppDropdown
                  items={testCaseVersions}
                  dataType="test_case_versions"
                  onSelect={(item) =>
                    handleDropdownSelect(
                      item,
                      "testCaseVersionId",
                      setSelectedTestCaseVersion
                    )
                  }
                  selectedItem={selectedTestCaseVersion}
                />
              )}
            </div>
          )}
          {!appRelease && (
            <div className="mb-3">
              <label htmlFor="appReleaseId" className="form-label">
                App Release
              </label>
              {loading ? (
                <div className="d-flex justify-content-center">
                  <div className="spinner-border" role="status">
                    <span className="visually-hidden">Loading...</span>
                  </div>
                </div>
              ) : (
                <AppDropdown
                  items={appReleases}
                  dataType="app_releases"
                  onSelect={(item) =>
                    handleDropdownSelect(
                      item,
                      "appReleaseId",
                      setSelectedAppRelease
                    )
                  }
                  selectedItem={selectedAppRelease}
                />
              )}
            </div>
          )}
          <div className="mb-3">
            <label htmlFor="breakageReasonId" className="form-label">
              Breakage Reason
            </label>
            {loading ? (
              <div className="d-flex justify-content-center">
                <div className="spinner-border" role="status">
                  <span className="visually-hidden">Loading...</span>
                </div>
              </div>
            ) : (
              <AppDropdown
                items={breakageReason}
                dataType="breakage_reasons"
                onSelect={(item) =>
                  handleDropdownSelect(
                    item,
                    "breakageReasonId",
                    setSelectedBreakageReason
                  )
                }
                selectedItem={selectedBreakageReason}
              />
            )}
          </div>
          <div className="mb-3">
            <label htmlFor="locatingMethodId" className="form-label">
              Locathing Method
            </label>
            {loading ? (
              <div className="d-flex justify-content-center">
                <div className="spinner-border" role="status">
                  <span className="visually-hidden">Loading...</span>
                </div>
              </div>
            ) : (
              <AppDropdown
                items={locatingMethod}
                dataType="locating_methods"
                onSelect={(item) =>
                  handleDropdownSelect(
                    item,
                    "locatingMethodId",
                    setSelectedLocatingMethod
                  )
                }
                selectedItem={selectedLocatingMethod}
              />
            )}
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
          <div className="mb-3">
            <label htmlFor="line" className="form-label">
              Error Line
            </label>
            <input
              type="text"
              className="form-control"
              id="line"
              name="line"
              value={formData.line}
              onChange={handleChange}
              required
            />
          </div>
          <ExplanationSelector
            label="Breakage Explanations"
            apiEndpoint="http://localhost:3000/api/breakage_explanations"
            selectedItems={formData.breakageExplanations}
            onAdd={handleAddBreakageExplanation}
            onRemove={handleRemoveBreakageExplanation}
          />
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
              "Add Breakage"
            )}
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddBreakage;
