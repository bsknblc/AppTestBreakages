import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const ExplanationSearch = ({ onSearchResults }) => {
  const [searchTerm, setSearchTerm] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  const [selectedExplanations, setSelectedExplanations] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Fetch all explanation suggestions (both breakage and repair)
  useEffect(() => {
    const fetchSuggestions = async () => {
      if (searchTerm.trim().length < 2) {
        setSuggestions([]);
        return;
      }

      try {
        setLoading(true);

        // Fetch both breakage and repair explanations in parallel
        const [breakageResponse, repairResponse] = await Promise.all([
          fetch("/api/breakage_explanations"),
          fetch("/api/repair_explanations"),
        ]);

        if (!breakageResponse.ok || !repairResponse.ok) {
          throw new Error("Failed to fetch explanations");
        }

        const [breakageData, repairData] = await Promise.all([
          breakageResponse.json(),
          repairResponse.json(),
        ]);

        // Combine and filter explanations
        const allExplanations = [
          ...breakageData.map((exp) => ({ ...exp, type: "breakage" })),
          ...repairData.map((exp) => ({ ...exp, type: "repair" })),
        ];

        const filtered = allExplanations
          .filter((exp) =>
            exp.explanation.toLowerCase().includes(searchTerm.toLowerCase())
          )
          .map((exp) => ({
            text: exp.explanation,
            type: exp.type,
          }));

        setSuggestions(filtered);
        setError(null);
      } catch (err) {
        setError(err.message);
        setSuggestions([]);
      } finally {
        setLoading(false);
      }
    };

    const debounceTimer = setTimeout(fetchSuggestions, 300);
    return () => clearTimeout(debounceTimer);
  }, [searchTerm]);

  // Handle search execution across all apps
  const handleSearch = async () => {
    if (selectedExplanations.length === 0) return;

    try {
      setLoading(true);

      const breakageExplanations = selectedExplanations
        .filter((exp) => exp.type === "breakage")
        .map((exp) => exp.text);

      const repairExplanations = selectedExplanations
        .filter((exp) => exp.type === "repair")
        .map((exp) => exp.text);

      // Search for breakages with matching explanations
      const breakageResults =
        breakageExplanations.length > 0
          ? await fetch(
              `/api/breakages/search-by-explanations?explanations=${encodeURIComponent(
                breakageExplanations.join(",")
              )}`
            ).then((res) => (res.ok ? res.json() : []))
          : [];

      // Search for repairs with matching explanations, then get their breakages
      const repairResults =
        repairExplanations.length > 0
          ? await fetch(
              `/api/repairs/search-by-explanations?explanations=${encodeURIComponent(
                repairExplanations.join(",")
              )}`
            ).then((res) => (res.ok ? res.json() : []))
          : [];

      // Get unique breakage IDs from both searches
      const breakageIdsFromRepairs = [
        ...new Set(repairResults.map((repair) => repair.breakageId)),
      ];
      const allBreakageIds = [
        ...breakageResults.map((b) => b.id),
        ...breakageIdsFromRepairs,
      ].filter((id, index, self) => self.indexOf(id) === index);

      onSearchResults(allBreakageIds);
      setError(null);
    } catch (err) {
      setError(err.message);
      onSearchResults([]);
    } finally {
      setLoading(false);
    }
  };

  // Add explanation to selected list
  const handleAddExplanation = (explanation) => {
    if (
      !selectedExplanations.some(
        (e) => e.text === explanation.text && e.type === explanation.type
      )
    ) {
      setSelectedExplanations([...selectedExplanations, explanation]);
    }
    setSearchTerm("");
    setSuggestions([]);
  };

  // Remove explanation from selected list
  const handleRemoveExplanation = (explanation) => {
    setSelectedExplanations(
      selectedExplanations.filter(
        (e) => !(e.text === explanation.text && e.type === explanation.type)
      )
    );
  };

  return (
    <div className="card mb-4">
      <div className="card-header">
        <h5>Search by Explanations (All Applications)</h5>
      </div>
      <div className="card-body">
        <div className="row mb-3">
          <div className="col-md-6">
            <div className="form-group">
              <label>Search for explanations:</label>
              <input
                type="text"
                className="form-control"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                placeholder="Type to search explanations..."
              />
            </div>
          </div>
        </div>

        {/* Selected explanations */}
        {selectedExplanations.length > 0 && (
          <div className="mb-3">
            <label>Selected explanations:</label>
            <div className="d-flex flex-wrap gap-2">
              {selectedExplanations.map((exp, index) => (
                <div
                  key={index}
                  className={`badge ${
                    exp.type === "breakage" ? "bg-danger" : "bg-success"
                  }`}
                >
                  {exp.text}
                  <span className="ms-1 small">({exp.type})</span>
                  <button
                    type="button"
                    className="btn-close btn-close-white ms-2"
                    aria-label="Remove"
                    onClick={() => handleRemoveExplanation(exp)}
                    style={{ fontSize: "0.6rem" }}
                  />
                </div>
              ))}
            </div>
          </div>
        )}

        {/* Suggestions dropdown */}
        {suggestions.length > 0 && (
          <div className="mb-3">
            <label>Suggestions:</label>
            <div className="list-group">
              {suggestions.map((suggestion, index) => (
                <button
                  key={index}
                  type="button"
                  className={`list-group-item list-group-item-action ${
                    suggestion.type === "breakage"
                      ? "list-group-item-danger"
                      : "list-group-item-success"
                  }`}
                  onClick={() => handleAddExplanation(suggestion)}
                >
                  {suggestion.text}
                  <span className="float-end small">{suggestion.type}</span>
                </button>
              ))}
            </div>
          </div>
        )}

        {/* Error message */}
        {error && <div className="alert alert-danger">{error}</div>}

        {/* Search button */}
        <button
          className="btn btn-primary"
          onClick={handleSearch}
          disabled={selectedExplanations.length === 0 || loading}
        >
          {loading ? (
            <>
              <span
                className="spinner-border spinner-border-sm"
                role="status"
                aria-hidden="true"
              ></span>
              Searching...
            </>
          ) : (
            "Search Across All Applications"
          )}
        </button>
      </div>
    </div>
  );
};

export default ExplanationSearch;
