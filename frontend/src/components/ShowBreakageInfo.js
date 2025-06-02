import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../MyStyle.css";
import ExplanationSelector from "./ExplanationSelector";

const ShowBreakageInfo = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { breakageId } = location.state || {};
  const [breakageData, setBreakageData] = useState(null);
  const [breakageReason, setBreakageReason] = useState(null);
  const [locatingMethod, setLocatingMethod] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedBreakageExplanations, setSelectedBreakageExplanations] =
    useState([]);
  const [repairExplanationsMap, setRepairExplanationsMap] = useState({});

  useEffect(() => {
    const fetchBreakageData = async () => {
      try {
        setLoading(true);

        // Fetch breakage data
        const breakageResponse = await fetch(
          `http://localhost:3000/api/breakages/${breakageId}`
        );
        if (!breakageResponse.ok) {
          throw new Error("Failed to fetch breakage data");
        }
        const breakageData = await breakageResponse.json();
        setBreakageData(breakageData);

        // Fetch breakage reason
        const reasonResponse = await fetch(
          `http://localhost:3000/api/breakage_reasons/${breakageData.breakageReasonId}`
        );
        if (!reasonResponse.ok) {
          throw new Error("Failed to fetch breakage reason");
        }
        const reasonData = await reasonResponse.json();
        setBreakageReason(reasonData);

        // Fetch locating method
        const methodResponse = await fetch(
          `http://localhost:3000/api/locating_methods/${breakageData.locatingMethodId}`
        );
        if (!methodResponse.ok) {
          throw new Error("Failed to fetch locating method");
        }
        const methodData = await methodResponse.json();
        setLocatingMethod(methodData);

        setSelectedBreakageExplanations(
          breakageData.breakageExplanations || []
        );

        const map = {};
        breakageData.repairs?.forEach((repair) => {
          map[repair.id] = repair.repairExplanations || [];
        });
        setRepairExplanationsMap(map);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    if (breakageId) {
      fetchBreakageData();
    }
  }, [breakageId]);

  if (loading) {
    return (
      <div className="commit-container">
        <div className="spinner-border text-primary" role="status">
          <span className="visually-hidden">Loading...</span>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="commit-container">
        <div className="alert alert-danger" role="alert">
          Error: {error}
        </div>
        <button className="btn btn-primary" onClick={() => navigate(-1)}>
          Go Back
        </button>
      </div>
    );
  }

  if (!breakageData) {
    return (
      <div className="commit-container">
        <div className="alert alert-warning" role="alert">
          No breakage data found
        </div>
      </div>
    );
  }

  const handleAddBreakageExplanation = async (explanation) => {
    try {
      const response = await fetch(
        `/api/breakages/${breakageData.id}/explanations/${explanation.id}`,
        { method: "PUT" }
      );
      if (!response.ok) throw new Error("Failed to add breakage explanation");

      setSelectedBreakageExplanations((prev) => [...prev, explanation]);
    } catch (error) {
      console.error("Error adding breakage explanation:", error);
    }
  };

  const handleRemoveBreakageExplanation = async (explanation) => {
    try {
      const response = await fetch(
        `/api/breakages/${breakageData.id}/explanations/${explanation.id}`,
        { method: "DELETE" }
      );
      if (!response.ok)
        throw new Error("Failed to remove breakage explanation");

      setSelectedBreakageExplanations((prev) =>
        prev.filter((e) => e.id !== explanation.id)
      );
    } catch (error) {
      console.error("Error removing breakage explanation:", error);
    }
  };

  const handleAddRepairExplanation = async (repairId, explanation) => {
    try {
      const response = await fetch(
        `/api/repairs/${repairId}/explanations/${explanation.id}`,
        { method: "PUT" }
      );
      if (!response.ok) throw new Error("Failed to add repair explanation");

      setRepairExplanationsMap((prev) => ({
        ...prev,
        [repairId]: [...(prev[repairId] || []), explanation],
      }));
    } catch (error) {
      console.error("Error adding repair explanation:", error);
    }
  };

  const handleRemoveRepairExplanation = async (repairId, explanation) => {
    try {
      const response = await fetch(
        `/api/repairs/${repairId}/explanations/${explanation.id}`,
        { method: "DELETE" }
      );
      if (!response.ok) throw new Error("Failed to remove repair explanation");

      setRepairExplanationsMap((prev) => ({
        ...prev,
        [repairId]: (prev[repairId] || []).filter(
          (e) => e.id !== explanation.id
        ),
      }));
    } catch (error) {
      console.error("Error removing repair explanation:", error);
    }
  };

  return (
    <div className="commit-container">
      <div className="commit-header">
        <h2>Breakage Details</h2>
        <button
          className="btn btn-outline-secondary"
          onClick={() => navigate(-1)}
        >
          Back to Breakages
        </button>
      </div>

      <div className="breakage-info">
        <div className="breakage-section">
          <h4>General Information</h4>
          <div className="info-item">
            <span className="info-label">Description:</span>
            <span className="info-value">{breakageData.description}</span>
          </div>
          <div className="info-item">
            <span className="info-label">Line:</span>
            <span className="info-value">{breakageData.line}</span>
          </div>
        </div>

        <div className="breakage-section">
          <h5>Breakage Reason</h5>
          <div className="info-item">
            <span className="info-label">Reason:</span>
            <span className="info-value">
              {breakageReason?.reasonName || "N/A"}
            </span>
          </div>
        </div>

        <div className="breakage-section">
          <h5>Locating Method</h5>
          <div className="info-item">
            <span className="info-label">Method:</span>
            <span className="info-value">
              {locatingMethod?.locatingMethodName || "N/A"}
            </span>
          </div>
        </div>

        <div className="breakage-section">
          <h5>Breakage Explanations</h5>
          <ExplanationSelector
            label="Breakage Explanations"
            apiEndpoint="/api/breakage_explanations" // Adjust this endpoint as needed
            selectedItems={selectedBreakageExplanations}
            onAdd={handleAddBreakageExplanation}
            onRemove={handleRemoveBreakageExplanation}
          />
        </div>

        <div className="breakage-section">
          <h5>Repairs</h5>
          {breakageData.repairs?.length > 0 ? (
            breakageData.repairs.map((repair) => (
              <div key={repair.id} className="repair-item">
                <div className="info-item">
                  <span className="info-label">Commit Hash:</span>
                  <span className="info-value">{repair.commitHash}</span>
                </div>
                <h6>Repair Explanations</h6>
                <ExplanationSelector
                  label="Repair Explanations"
                  apiEndpoint="/api/repair_explanations" // Adjust this endpoint as needed
                  selectedItems={repairExplanationsMap[repair.id] || []}
                  onAdd={(explanation) =>
                    handleAddRepairExplanation(repair.id, explanation)
                  }
                  onRemove={(explanation) =>
                    handleRemoveRepairExplanation(repair.id, explanation)
                  }
                />
              </div>
            ))
          ) : (
            <p>No repairs available</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default ShowBreakageInfo;
