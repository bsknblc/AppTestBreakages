import React, { useState, useEffect } from "react";
import AppDropdown from "../components/AppDropdown";
import AddBreakageLite from "../components/AddBreakageLite";

const DataProvider = () => {
  const [dataType, setDataType] = useState("applications");
  const [data, setData] = useState([]);

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [selectedAppId, setSelectedAppId] = useState(null);
  const [selectedReleaseId, setSelectedReleaseId] = useState(null);
  const [showReleasesDropdown, setShowReleasesDropdown] = useState(false);

  const [selectedTestSuiteId, setSelectedTestSuiteId] = useState(null);
  const [showTestSuiteDropdown, setShowTestSuiteDropdown] = useState(false);

  const [selectedTestCaseId, setSelectedTestCaseId] = useState(null);
  const [showTestCaseDropdown, setShowTestCaseDropdown] = useState(false);

  const [selectedTestCaseVersionId, setSelectedTestCaseVersionId] =
    useState(null);
  const [showTestCaseVersionDropdown, setShowTestCaseVersionDropdown] =
    useState(false);

  const [selectedBreakageId, setSelectedBreakageId] = useState(null);
  const [showBreakageAdd, setShowBreakageAdd] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        let endpoint = "";

        switch (dataType) {
          case "applications":
            endpoint = "http://localhost:3000/api/applications";
            break;
          case "app_releases":
            endpoint =
              "http://localhost:3000/api/applications/" +
              selectedAppId +
              "/app_releases";
            break;
          case "test_suites":
            endpoint =
              "http://localhost:3000/api/applications/" +
              selectedAppId +
              "/test_suites";
            break;
          case "test_cases":
            endpoint =
              "http://localhost:3000/api/test_suites/" +
              selectedTestSuiteId +
              "/test_cases";
            break;
          case "test_case_versions":
            endpoint =
              "http://localhost:3000/api/test_cases/" +
              selectedTestCaseId +
              "/test_case_versions";
            break;
          default:
            endpoint = "http://localhost:3000/api/applications";
        }

        const response = await fetch(`${endpoint}`);
        if (!response.ok) {
          throw new Error(`Failed to fetch ${dataType}`);
        }
        const result = await response.json();
        setData(result);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [dataType, selectedAppId]); // Add selectedAppId to dependencies

  const handleAppSelect = (item) => {
    setSelectedAppId(item.id);
    setDataType("app_releases");
    setShowReleasesDropdown(true);
  };

  const handleReleaseSelect = (item) => {
    setSelectedReleaseId(item.id);
    setDataType("test_suites");
    setShowTestSuiteDropdown(true);
  };

  const handleTestSuiteSelect = (item) => {
    setSelectedTestSuiteId(item.id);
    setDataType("test_cases");
    setShowTestCaseDropdown(true);
  };

  const handleTestCaseSelect = (item) => {
    setSelectedTestCaseId(item.id);
    setDataType("test_case_versions");
    setShowTestCaseVersionDropdown(true);
  };

  const handleTestCaseVersionSelect = (item) => {
    setSelectedTestCaseVersionId(item.id);
    setDataType("breakages");
    setShowBreakageAdd(true);
  };

  return (
    <div className="container mt-5">
      {/* Your button group for data type selection remains the same */}
      <div className="row mb-4">{/* ... */}</div>

      <div className="row">
        <div className="col">
          <h4>Applications</h4>
          <AppDropdown
            items={dataType === "applications" ? data : []}
            dataType="applications"
            onSelect={handleAppSelect}
          />

          {showReleasesDropdown && (
            <>
              <h4 className="mt-4">App Releases for Selected Application</h4>
              <AppDropdown
                items={dataType === "app_releases" ? data : []}
                dataType="app_releases"
                onSelect={handleReleaseSelect}
              />
            </>
          )}
          {showTestSuiteDropdown && (
            <>
              <h4 className="mt-4">Test Suites for Selected Application</h4>
              <AppDropdown
                items={dataType === "test_suites" ? data : []}
                dataType="test_suites"
                onSelect={handleTestSuiteSelect}
              />
            </>
          )}
          {showTestCaseDropdown && (
            <>
              <h4 className="mt-4">Test Cases for Selected Test Suite</h4>
              <AppDropdown
                items={dataType === "test_cases" ? data : []}
                dataType="test_cases"
                onSelect={handleTestCaseSelect}
              />
            </>
          )}
          {showTestCaseVersionDropdown && (
            <>
              <h4 className="mt-4">
                Test Cases Version for Selected Test Case
              </h4>
              <AppDropdown
                items={dataType === "test_case_versions" ? data : []}
                dataType="test_case_versions"
                onSelect={handleTestCaseVersionSelect}
              />
            </>
          )}
          {showBreakageAdd && (
            <>
              <h4 className="mt-4">Add Breakage</h4>
              <AddBreakageLite
                onAdded={() => {}}
                testCaseVersion={selectedTestCaseVersionId}
                appRelease={selectedReleaseId}
              />
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default DataProvider;
