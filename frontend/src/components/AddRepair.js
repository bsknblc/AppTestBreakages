import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";
import AppDropdown from "./AppDropdown";
import ExplanationSelector from "./ExplanationSelector";

const AddRepair = ({ onAdded, breakage }) => {
  const [formData, setFormData] = useState({
    commitHash: "",
    breakageId: breakage || null,
    repairExplanations: [],
  });
  const [selectedBreakage, setSelectedBreakage] = useState(null);
  const [error, setError] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);
  const [breakages, setBreakages] = useState([]);
  const [loadingBreakages, setLoadingBreakages] = useState(true);

  useEffect(() => {
    if (!breakage) {
      const fetchBreakages = async () => {
        try {
          const response = await fetch("http://localhost:3000/api/breakages");
          if (!response.ok) {
            throw new Error("Failed to fetch breakages");
          }
          const data = await response.json();
          setBreakages(data);
        } catch (err) {
          setError(err.message);
        } finally {
          setLoadingBreakages(false);
        }
      };

      fetchBreakages();
    }
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleBrekageSelect = (selectedItem) => {
    setSelectedBreakage(selectedItem);
    setFormData((prev) => ({
      ...prev,
      breakageId: selectedItem.id,
    }));
  };

  const handleAddRepairExplanation = (explanation) => {
    setFormData((prev) => ({
      ...prev,
      repairExplanations: prev.repairExplanations.some(
        (e) => e.id === explanation.id
      )
        ? prev.repairExplanations
        : [...prev.repairExplanations, explanation],
    }));
  };

  const handleRemoveRepairExplanation = (explanation) => {
    setFormData((prev) => ({
      ...prev,
      repairExplanations: prev.repairExplanations.filter((e) =>
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
      const repairData = {
        ...formData,
        repairExplanationIds: formData.repairExplanations.map((e) => e.id),
      };
      const response = await fetch("http://localhost:3000/api/repairs", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(repairData),
      });

      if (!response.ok) {
        throw new Error("Failed to add repair");
      }

      onAdded();
      setFormData({ commitHash: "", breakageId: breakage || null });
      if (breakage === null) {
        setSelectedBreakage(null);
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
        <h3 className="mb-0">Add New Repair</h3>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        {success && (
          <div className="alert alert-success">Repair added successfully!</div>
        )}

        <form onSubmit={handleSubmit}>
          {!breakage && (
            <div className="mb-3">
              <label htmlFor="breakageId" className="form-label">
                Breakage
              </label>
              {loadingBreakages ? (
                <div className="d-flex justify-content-center">
                  <div className="spinner-border" role="status">
                    <span className="visually-hidden">Loading...</span>
                  </div>
                </div>
              ) : (
                <AppDropdown
                  items={breakages}
                  dataType="breakages"
                  onSelect={handleBrekageSelect}
                  selectedItem={selectedBreakage}
                />
              )}
            </div>
          )}
          <div className="mb-3">
            <label htmlFor="commitHash" className="form-label">
              Commit Hash
            </label>
            <input
              type="text"
              className="form-control"
              id="commitHash"
              name="commitHash"
              value={formData.commitHash}
              onChange={handleChange}
              required
            />
          </div>
          <ExplanationSelector
            label="Repair Explanations"
            apiEndpoint="http://localhost:3000/api/repair_explanations"
            selectedItems={formData.repairExplanations}
            onAdd={handleAddRepairExplanation}
            onRemove={handleRemoveRepairExplanation}
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
              "Add Repair"
            )}
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddRepair;
