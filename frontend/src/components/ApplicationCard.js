import React, { useEffect, useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";

function ApplicationCard({ app }) {
  const [expanded, setExpanded] = useState(false);

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
                                            <small className="text-muted">
                                              {testCase.description}
                                            </small>
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
                                                                {version.breakages.map(
                                                                  (
                                                                    breakage
                                                                  ) => (
                                                                    <li
                                                                      key={
                                                                        breakage.id
                                                                      }
                                                                    >
                                                                      <small>
                                                                        {
                                                                          breakage.description
                                                                        }
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
    </div>
  );
}

export default ApplicationCard;
