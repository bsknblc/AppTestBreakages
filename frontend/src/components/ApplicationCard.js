import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";
import ExplanationSelector from "./ExplanationSelector";

function ApplicationCard({ app, filteredBreakageIds }) {
  const [expanded, setExpanded] = useState(false);
  const [selectedBreakage, setSelectedBreakage] = useState(null);
  const [commitData, setCommitData] = useState(null);
  const [breakageData, setBreakageData] = useState(null);
  const [breakageReason, setBreakageReason] = useState(null);
  const [locatingMethod, setLocatingMethod] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [selectedBreakageExplanations, setSelectedBreakageExplanations] =
    useState([]);
  const [repairExplanationsMap, setRepairExplanationsMap] = useState({});

  const fetchBreakageDetails = async (breakageId) => {
    try {
      setLoading(true);
      setError(null);

      // Fetch commit data
      const commitResponse = await fetch(
        `http://localhost:3000/api/breakages/${breakageId}/commit`
      );
      if (!commitResponse.ok) {
        throw new Error("Failed to fetch commit data");
      }
      const commitData = await commitResponse.json();
      setCommitData(commitData);

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

      setSelectedBreakageExplanations(breakageData.breakageExplanations || []);

      const map = {};
      breakageData.repairs?.forEach((repair) => {
        map[repair.id] = repair.repairExplanations || [];
      });
      setRepairExplanationsMap(map);

      setSelectedBreakage(breakageId);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const closeBreakageDetails = () => {
    setSelectedBreakage(null);
    setCommitData(null);
    setBreakageData(null);
    setBreakageReason(null);
    setLocatingMethod(null);
    setError(null);
  };

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

  const shouldHighlightBreakage = (breakageId) => {
    return filteredBreakageIds && filteredBreakageIds.includes(breakageId);
  };

  return (
    <div className="card mb-3">
      <div className="card-header d-flex justify-content-between align-items-center">
        <h5 className="mb-0">
          {app.appName}
          <small className="text-muted ms-2">({app.url})</small>
        </h5>
        <button
          className="btn btn-sm btn-outline-primary"
          onClick={() => setExpanded(!expanded)}
        >
          {expanded ? "Collapse" : "Expand"}
        </button>
      </div>

      {expanded && (
        <div className="card-body">
          <div className="mb-4">
            <h6>Releases</h6>
            {app.releases && app.releases.length > 0 ? (
              <div className="list-group">
                {app.releases.map((release) => (
                  <div key={release.id} className="list-group-item">
                    <strong>{release.releaseName}</strong>
                    <strong> Breakage Count: {release.breakages.length}</strong>
                    {release.breakages && release.breakages.length > 0 && (
                      <div>
                        <h6>Test Suites</h6>
                        {app.testSuites && app.testSuites.length > 0 ? (
                          <div className="list-group">
                            {app.testSuites.map((suite) => (
                              <div key={suite.id} className="list-group-item">
                                <strong>{suite.testSuiteName}</strong>
                                {suite.testCases &&
                                  suite.testCases.length > 0 && (
                                    <div className="mt-2">
                                      <small className="text-muted">
                                        Test Cases:
                                      </small>
                                      <ul className="list-unstyled ms-3">
                                        {suite.testCases.map((testCase) => (
                                          <li
                                            key={testCase.id}
                                            className="mb-2"
                                          >
                                            <div>{testCase.testCaseName}</div>
                                            {testCase.testCaseVersions &&
                                              testCase.testCaseVersions.length >
                                                0 && (
                                                <div className="mt-1">
                                                  <small className="text-muted">
                                                    Versions:
                                                  </small>
                                                  <ul className="list-unstyled ms-3">
                                                    {testCase.testCaseVersions.map(
                                                      (version) => (
                                                        <li key={version.id}>
                                                          <small>
                                                            {
                                                              version.testCaseVersionName
                                                            }
                                                            <div className="mt-2">
                                                              <small className="text-muted">
                                                                Breakages:
                                                              </small>
                                                              <ul className="list-unstyled ms-3">
                                                                {version.breakages
                                                                  .filter(
                                                                    (
                                                                      breakage
                                                                    ) =>
                                                                      !filteredBreakageIds ||
                                                                      filteredBreakageIds.includes(
                                                                        breakage.id
                                                                      )
                                                                  )
                                                                  .map(
                                                                    (
                                                                      breakage
                                                                    ) => (
                                                                      <li
                                                                        key={
                                                                          breakage.id
                                                                        }
                                                                        className={
                                                                          shouldHighlightBreakage(
                                                                            breakage.id
                                                                          )
                                                                            ? "highlighted-breakage"
                                                                            : ""
                                                                        }
                                                                      >
                                                                        <small>
                                                                          <button
                                                                            onClick={() =>
                                                                              fetchBreakageDetails(
                                                                                breakage.id
                                                                              )
                                                                            }
                                                                            className={`btn btn-link p-0 ms-1 ${
                                                                              shouldHighlightBreakage(
                                                                                breakage.id
                                                                              )
                                                                                ? "text-danger fw-bold"
                                                                                : ""
                                                                            }`}
                                                                          >
                                                                            {
                                                                              breakage.description
                                                                            }
                                                                          </button>
                                                                          {/* Add breakage explanation tags */}
                                                                          {breakage
                                                                            .breakageExplanations
                                                                            ?.length >
                                                                            0 && (
                                                                            <div className="mt-1">
                                                                              {breakage.breakageExplanations.map(
                                                                                (
                                                                                  explanation
                                                                                ) => (
                                                                                  <span
                                                                                    key={
                                                                                      explanation.id
                                                                                    }
                                                                                    className="explanation-tag breakage-explanation-tag"
                                                                                  >
                                                                                    {
                                                                                      explanation.explanation
                                                                                    }
                                                                                  </span>
                                                                                )
                                                                              )}
                                                                            </div>
                                                                          )}
                                                                          {/* Add repair explanation tags */}
                                                                          {breakage
                                                                            .repairs
                                                                            ?.length >
                                                                            0 &&
                                                                            breakage
                                                                              .repairs[0]
                                                                              .repairExplanations
                                                                              ?.length >
                                                                              0 && (
                                                                              <div className="mt-1">
                                                                                {breakage.repairs[0].repairExplanations.map(
                                                                                  (
                                                                                    explanation
                                                                                  ) => (
                                                                                    <span
                                                                                      key={
                                                                                        explanation.id
                                                                                      }
                                                                                      className="explanation-tag repair-explanation-tag"
                                                                                    >
                                                                                      {
                                                                                        explanation.explanation
                                                                                      }
                                                                                    </span>
                                                                                  )
                                                                                )}
                                                                              </div>
                                                                            )}
                                                                        </small>
                                                                      </li>
                                                                    )
                                                                  )}
                                                              </ul>
                                                            </div>
                                                          </small>
                                                        </li>
                                                      )
                                                    )}
                                                  </ul>
                                                </div>
                                              )}
                                          </li>
                                        ))}
                                      </ul>
                                    </div>
                                  )}
                              </div>
                            ))}
                          </div>
                        ) : (
                          <p className="text-muted">No test suites found</p>
                        )}
                      </div>
                    )}
                  </div>
                ))}
              </div>
            ) : (
              <p className="text-muted">No releases found</p>
            )}
          </div>
        </div>
      )}

      {/* Breakage Details Modal */}
      {(selectedBreakage || loading) && (
        <div className="modal-backdrop">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title">Breakage Details</h5>
              <button
                type="button"
                className="btn-close"
                onClick={closeBreakageDetails}
                aria-label="Close"
              ></button>
            </div>
            <div className="modal-body">
              {loading ? (
                <div className="d-flex justify-content-center">
                  <div className="spinner-border" role="status">
                    <span className="visually-hidden">Loading...</span>
                  </div>
                </div>
              ) : error ? (
                <div className="alert alert-danger">{error}</div>
              ) : (
                <>
                  {/* Breakage Info Section */}
                  <div className="breakage-info mb-4">
                    <div className="breakage-section mb-3">
                      <h4>General Information</h4>
                      <div className="info-item">
                        <span className="info-label">Description:</span>
                        <span className="info-value">
                          {breakageData.description}
                        </span>
                      </div>
                      <div className="info-item">
                        <span className="info-label">Line:</span>
                        <span className="info-value">{breakageData.line}</span>
                      </div>
                    </div>

                    <div className="breakage-section mb-3">
                      <h5>Breakage Reason</h5>
                      <div className="info-item">
                        <span className="info-label">Reason:</span>
                        <span className="info-value">
                          {breakageReason?.reasonName || "N/A"}
                        </span>
                      </div>
                    </div>

                    <div className="breakage-section mb-3">
                      <h5>Locating Method</h5>
                      <div className="info-item">
                        <span className="info-label">Method:</span>
                        <span className="info-value">
                          {locatingMethod?.locatingMethodName || "N/A"}
                        </span>
                      </div>
                    </div>

                    <div className="breakage-section mb-3">
                      <h5>Breakage Explanations</h5>
                      <ExplanationSelector
                        label="Breakage Explanations"
                        apiEndpoint="/api/breakage_explanations"
                        selectedItems={selectedBreakageExplanations}
                        onAdd={handleAddBreakageExplanation}
                        onRemove={handleRemoveBreakageExplanation}
                      />
                    </div>

                    <div className="breakage-section">
                      <h5>Repairs</h5>
                      {breakageData.repairs?.length > 0 ? (
                        breakageData.repairs.map((repair) => (
                          <div key={repair.id} className="repair-item mb-3">
                            <div className="info-item">
                              <span className="info-label">Commit Hash:</span>
                              <span className="info-value">
                                {repair.commitHash}
                              </span>
                            </div>
                            <h6>Repair Explanations</h6>
                            <ExplanationSelector
                              label="Repair Explanations"
                              apiEndpoint="/api/repair_explanations"
                              selectedItems={
                                repairExplanationsMap[repair.id] || []
                              }
                              onAdd={(explanation) =>
                                handleAddRepairExplanation(
                                  repair.id,
                                  explanation
                                )
                              }
                              onRemove={(explanation) =>
                                handleRemoveRepairExplanation(
                                  repair.id,
                                  explanation
                                )
                              }
                            />
                          </div>
                        ))
                      ) : (
                        <p>No repairs available</p>
                      )}
                    </div>
                  </div>

                  {/* Commit Details Section */}
                  {commitData && (
                    <div className="commit-container mt-4">
                      <div className="commit-header">
                        <h4>Commit Details</h4>
                      </div>

                      <div className="commit-meta">
                        <div className="commit-hash">
                          <span className="meta-label">Commit Hash:</span>
                          <code>{commitData.sha}</code>
                        </div>
                        <div className="commit-author">
                          <span className="meta-label">Author:</span>
                          {commitData.commit.author.name}
                        </div>
                        <div className="commit-message">
                          <span className="meta-label">Message:</span>
                          {commitData.commit.message}
                        </div>
                      </div>

                      <div className="file-changes">
                        <h5>File Changes ({commitData.files.length})</h5>
                        {commitData.files.map((file, index) => (
                          <div key={index} className="file-change-card">
                            <div className="file-header">
                              <span className={`file-status ${file.status}`}>
                                {file.status.toUpperCase()}
                              </span>
                              <span className="file-name">{file.filename}</span>
                              <span className="file-stats">
                                <span className="additions">
                                  +{file.additions}
                                </span>
                                <span className="deletions">
                                  -{file.deletions}
                                </span>
                              </span>
                            </div>
                            {file.patch && (
                              <pre className="diff-container">
                                {file.patch.split("\n").map((line, i) => {
                                  let className = "";
                                  if (line.startsWith("+")) className = "added";
                                  else if (line.startsWith("-"))
                                    className = "deleted";
                                  else if (line.startsWith("@@"))
                                    className = "diff-info";
                                  return (
                                    <div
                                      key={i}
                                      className={`diff-line ${className}`}
                                    >
                                      {line}
                                    </div>
                                  );
                                })}
                              </pre>
                            )}
                          </div>
                        ))}
                      </div>
                    </div>
                  )}
                </>
              )}
            </div>
            <div className="modal-footer">
              <button
                className="btn btn-secondary"
                onClick={closeBreakageDetails}
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default ApplicationCard;
