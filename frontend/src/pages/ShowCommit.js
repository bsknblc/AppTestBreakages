import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./ShowCommit.css"; // We'll create this for styling
import ShowBreakageInfo from "../components/ShowBreakageInfo";

const ShowCommit = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { breakageId } = location.state || {};
  const [commitData, setCommitData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCommitData = async () => {
      try {
        const response = await fetch(
          `http://localhost:3000/api/breakages/${breakageId}/commit`
        );
        if (!response.ok) {
          throw new Error("Failed to fetch commit data");
        }
        const data = await response.json();
        setCommitData(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    if (breakageId) {
      fetchCommitData();
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

  if (!commitData) {
    return (
      <div className="commit-container">
        <div className="alert alert-warning" role="alert">
          No commit data found
        </div>
      </div>
    );
  }

  return (
    <>
      <ShowBreakageInfo />
      <div className="commit-container">
        <div className="commit-header">
          <h2>Commit Details</h2>
          <button
            className="btn btn-outline-secondary"
            onClick={() => navigate(-1)}
          >
            Back to Breakages
          </button>
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
          <h3>File Changes ({commitData.files.length})</h3>
          {commitData.files.map((file, index) => (
            <div key={index} className="file-change-card">
              <div className="file-header">
                <span className={`file-status ${file.status}`}>
                  {file.status.toUpperCase()}
                </span>
                <span className="file-name">{file.filename}</span>
                <span className="file-stats">
                  <span className="additions">+{file.additions}</span>
                  <span className="deletions">-{file.deletions}</span>
                </span>
              </div>
              {file.patch && (
                <pre className="diff-container">
                  {file.patch.split("\n").map((line, i) => {
                    let className = "";
                    if (line.startsWith("+")) className = "added";
                    else if (line.startsWith("-")) className = "deleted";
                    else if (line.startsWith("@@")) className = "diff-info";
                    return (
                      <div key={i} className={`diff-line ${className}`}>
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
    </>
  );
};

export default ShowCommit;
