import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";
import AppDropdown from "./AppDropdown";

const AddBreakage = ({ onAdded, testCaseVersion, appRelease }) => {
  const [formData, setFormData] = useState({
    taxonomyDescription: "",
    testCaseVersionId: testCaseVersion,
    appReleaseId: appRelease,
    breakageReasonId: null,
    locatingMethodId: null,
  });
  const [error, setError] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);
  const [loading, setLoading] = useState(true);
  const [selectedBreakageReason, setSelectedBreakageReason] = useState(null);
  const [selectedLocatingMethod, setSelectedLocatingMethod] = useState(null);
  const [breakageReason, setBreakageReason] = useState([]);
  const [locatingMethod, setLocatingMethod] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [breakageReasonsRes, locatingMethodsRes] = await Promise.all([
          fetch("http://localhost:3000/api/breakage_reasons"),
          fetch("http://localhost:3000/api/locating_methods"),
        ]);

        if (!breakageReasonsRes.ok || !locatingMethodsRes.ok) {
          throw new Error("Failed to fetch data");
        }

        const breakageReasons = await breakageReasonsRes.json();
        const locatingMethods = await locatingMethodsRes.json();

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

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setError(null);
    setSuccess(false);
    console.log(formData);

    try {
      const response = await fetch("http://localhost:3000/api/breakages", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error("Failed to add breakage.");
      }

      onAdded();
      setFormData({
        taxonomyDescription: "",
        testCaseVersionId: testCaseVersion,
        appReleaseId: appRelease,
        breakageReasonId: null,
        locatingMethodId: null,
      });
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
            <label htmlFor="taxonomyDescription" className="form-label">
              Taxonomy Description
            </label>
            <input
              type="text"
              className="form-control"
              id="taxonomyDescription"
              name="taxonomyDescription"
              value={formData.taxonomyDescription}
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
              "Add Breakage"
            )}
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddBreakage;
