import React, { useState, useEffect } from "react";
import AppDropdown from "../components/AppDropdown";
import AddBreakage from "../components/AddBreakage";
import AddApplication from "../components/AddApplication";
import AddVersion from "../components/AddVersion";
import AddTestSuite from "../components/AddTestSuite";
import AddTestCase from "../components/AddTestCase";
import AddTestCaseVersion from "../components/AddTestCaseVersion";
import AddRepair from "../components/AddRepair";
import AddLanguage from "../components/AddLanguage";
import AddBreakageReason from "../components/AddBreakageReason";
import AddLocatingMethod from "../components/AddLocatingMethod";

const DataProvider = () => {
  const [dataType, setDataType] = useState("applications");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [selectedApp, setSelectedApp] = useState(null);
  const [applicationsData, setApplicationsData] = useState([]);
  const [addApp, setAddApp] = useState(false);

  const [addLanguage, setAddLanguage] = useState(null);
  const [addBreakageReason, setAddBreakageReason] = useState(null);
  const [addLocatingMethod, setAddLocatingMethod] = useState(null);

  const [selectedRelease, setSelectedRelease] = useState(null);
  const [releasesData, setReleasesData] = useState([]);
  const [addRelease, setAddRelease] = useState(false);
  const [showReleasesDropdown, setShowReleasesDropdown] = useState(false);

  const [selectedTestSuite, setSelectedTestSuite] = useState(null);
  const [testSuitesData, setTestSuitesData] = useState([]);
  const [addTestSuite, setAddTestSuite] = useState(false);
  const [showTestSuiteDropdown, setShowTestSuiteDropdown] = useState(false);

  const [selectedTestCase, setSelectedTestCase] = useState(null);
  const [testCasesData, setTestCasesData] = useState([]);
  const [addTestCase, setAddTestCase] = useState(false);
  const [showTestCaseDropdown, setShowTestCaseDropdown] = useState(false);

  const [selectedTestCaseVersion, setSelectedTestCaseVersion] = useState(null);
  const [testCaseVersionsData, setTestCaseVersionsData] = useState([]);
  const [addTestCaseVersion, setAddTestCaseVersion] = useState(false);
  const [showTestCaseVersionDropdown, setShowTestCaseVersionDropdown] =
    useState(false);

  const [selectedBreakage, setSelectedBreakage] = useState(null);
  const [breakagesData, setBreakagesData] = useState([]);
  const [addBreakage, setAddBreakage] = useState(false);
  const [showBreakageDropdown, setShowBreakageDropdown] = useState(false);

  const [selectedRepair, setSelectedRepair] = useState(null);
  const [repairData, setRepairData] = useState([]);
  const [addRepair, setAddRepair] = useState(false);
  const [showRepairAdd, setShowRepairAdd] = useState(false);

  const fetchData = async () => {
    try {
      setLoading(true);
      let endpoint = "";

      switch (dataType) {
        case "applications":
          endpoint = "http://localhost:3000/api/applications";
          break;
        case "app_releases":
          endpoint = `http://localhost:3000/api/applications/${selectedApp?.id}/app_releases`;
          break;
        case "test_suites":
          endpoint = `http://localhost:3000/api/applications/${selectedApp?.id}/test_suites`;
          break;
        case "test_cases":
          endpoint = `http://localhost:3000/api/test_suites/${selectedTestSuite?.id}/test_cases`;
          break;
        case "test_case_versions":
          endpoint = `http://localhost:3000/api/test_cases/${selectedTestCase?.id}/test_case_versions`;
          break;
        case "breakages":
          endpoint = `http://localhost:3000/api/test_case_versions/${selectedTestCaseVersion?.id}/breakages`;
          break;
        case "repairs":
          endpoint = `http://localhost:3000/api/breakages/${selectedBreakage?.id}/repairs`;
          break;
        default:
          endpoint = "http://localhost:3000/api/applications";
      }

      const response = await fetch(endpoint);
      if (!response.ok) {
        throw new Error(`Failed to fetch ${dataType}`);
      }
      const result = await response.json();

      switch (dataType) {
        case "applications":
          setApplicationsData(result);
          break;
        case "app_releases":
          setReleasesData(result);
          break;
        case "test_suites":
          setTestSuitesData(result);
          break;
        case "test_cases":
          setTestCasesData(result);
          break;
        case "test_case_versions":
          setTestCaseVersionsData(result);
          break;
        case "breakages":
          setBreakagesData(result);
          break;
        case "repairs":
          setRepairData(result);
          break;
        default:
          setApplicationsData(result);
          break;
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, [
    dataType,
    selectedApp?.id,
    selectedRelease?.id,
    selectedTestSuite?.id,
    selectedTestCase?.id,
    selectedTestCaseVersion?.id,
    selectedBreakage?.id,
    selectedRepair?.id,
  ]); // Add dependencies

  const handleAppSelect = (item) => {
    setSelectedApp(item);
    setDataType("app_releases");
    setShowReleasesDropdown(true);
    setSelectedRelease(null);
    setSelectedTestSuite(null);
    setSelectedTestCase(null);
    setSelectedTestCaseVersion(null);
    setSelectedBreakage(null);
    setShowTestSuiteDropdown(false);
    setShowTestCaseDropdown(false);
    setShowTestCaseVersionDropdown(false);
    setShowBreakageDropdown(false);
    setShowRepairAdd(false);
  };

  const handleReleaseSelect = (item) => {
    setSelectedRelease(item);
    setDataType("test_suites");
    setShowTestSuiteDropdown(true);
    // Reset downstream selections
    setSelectedTestSuite(null);
    setSelectedTestCase(null);
    setSelectedTestCaseVersion(null);
    setSelectedBreakage(null);
    setSelectedRepair(null);
    setShowTestCaseDropdown(false);
    setShowTestCaseVersionDropdown(false);
    setShowBreakageDropdown(false);
    setShowRepairAdd(false);
  };

  const handleTestSuiteSelect = (item) => {
    setSelectedTestSuite(item);
    setDataType("test_cases");
    setShowTestCaseDropdown(true);
    // Reset downstream selections
    setSelectedTestCase(null);
    setSelectedTestCaseVersion(null);
    setSelectedBreakage(null);
    setSelectedRepair(null);
    setShowTestCaseVersionDropdown(false);
    setShowBreakageDropdown(false);
    setShowRepairAdd(false);
  };

  const handleTestCaseSelect = (item) => {
    setSelectedTestCase(item);
    setDataType("test_case_versions");
    setShowTestCaseVersionDropdown(true);
    // TODO: add tc version açıkken test case değişince ad tcv deki id sanırım değişmiyor
    setSelectedTestCaseVersion(null);
    setSelectedBreakage(null);
    setSelectedRepair(null);
    setShowBreakageDropdown(false);
    setShowRepairAdd(false);
  };

  const handleTestCaseVersionSelect = (item) => {
    setSelectedTestCaseVersion(item);
    setDataType("breakages");
    setShowBreakageDropdown(true);
    setSelectedBreakage(null);
    setSelectedRepair(null);
    setShowRepairAdd(false);
  };

  const handleBreakageSelect = (item) => {
    setSelectedBreakage(item);
    setDataType("repairs");
    setShowRepairAdd(true);
  };

  const handleRepairSelect = (item) => {
    setSelectedRepair(item);
    setDataType("repairs");
    setShowRepairAdd(true);
  };

  const refreshData = (type = "applications") => {
    setDataType(type);
    fetchData();
  };

  return (
    <div className="container mt-5">
      <div className="row mb-4">{/* ... */}</div>

      <div className="row">
        <div className="col">
          <div className="d-flex align-items-center mb-3">
            <h4 className="mb-0 me-3">Applications</h4>
          </div>
          <AppDropdown
            items={applicationsData}
            dataType="applications"
            onSelect={handleAppSelect}
            selectedItem={selectedApp} // Pass the selected app to show in dropdown
          />
          <button
            className="btn btn-success btn-sm"
            onClick={() => setAddApp((prev) => !prev)}
          >
            + Add Application
          </button>
          <button
            className="btn btn-success btn-sm"
            onClick={() => setAddLanguage((prev) => !prev)}
          >
            + Add Language
          </button>
          {addApp && (
            <div className="mt-3">
              <AddApplication
                onAdded={() => {
                  setAddApp(false);
                  refreshData("applications");
                }}
                onCancel={() => setAddApp(false)}
              />
            </div>
          )}
          {addLanguage && (
            <div className="mt-3">
              <AddLanguage
                onAdded={() => {
                  setAddLanguage(false);
                }}
                onCancel={() => setAddLanguage(false)}
              />
            </div>
          )}

          {showReleasesDropdown && (
            <>
              <div className="d-flex align-items-center mb-3">
                <h4 className="mt-4">
                  App Releases for {selectedApp?.appName}
                </h4>
              </div>
              <AppDropdown
                items={releasesData}
                dataType="app_releases"
                onSelect={handleReleaseSelect}
                selectedItem={selectedRelease}
              />
              <button
                className="btn btn-success btn-sm"
                onClick={() => setAddRelease((prev) => !prev)}
              >
                + Add Version
              </button>
              {addRelease && (
                <div className="mt-3">
                  <AddVersion
                    onAdded={() => {
                      setAddRelease(false);
                      refreshData("app_releases");
                    }}
                    application={selectedApp?.id}
                    onCancel={() => setAddRelease(false)}
                  />
                </div>
              )}
            </>
          )}

          {showTestSuiteDropdown && (
            <>
              <div className="d-flex align-items-center mb-3">
                <h4 className="mt-4">Test Suite for {selectedApp?.appName}</h4>
              </div>
              <AppDropdown
                items={testSuitesData}
                dataType="test_suites"
                onSelect={handleTestSuiteSelect}
                selectedItem={selectedTestSuite}
              />
              <button
                className="btn btn-success btn-sm"
                onClick={() => setAddTestSuite((prev) => !prev)}
              >
                + Add Test Suite
              </button>
              {addTestSuite && (
                <div className="mt-3">
                  <AddTestSuite
                    onAdded={() => {
                      setAddTestSuite(false);
                      refreshData("test_suites");
                    }}
                    application={selectedApp?.id}
                    onCancel={() => setAddTestSuite(false)}
                  />
                </div>
              )}
            </>
          )}

          {showTestCaseDropdown && (
            <>
              <div className="d-flex align-items-center mb-3">
                <h4 className="mt-4">
                  Test Case for {selectedTestSuite?.testSuiteName}
                </h4>
              </div>
              <AppDropdown
                items={testCasesData}
                dataType="test_cases"
                onSelect={handleTestCaseSelect}
                selectedItem={selectedTestCase}
              />
              <button
                className="btn btn-success btn-sm"
                onClick={() => setAddTestCase((prev) => !prev)}
              >
                + Add Test Case
              </button>
              {addTestCase && (
                <div className="mt-3">
                  <AddTestCase
                    onAdded={() => {
                      setAddTestCase(false);
                      refreshData("test_cases");
                    }}
                    testSuite={selectedTestSuite?.id}
                    onCancel={() => setAddTestCase(false)}
                  />
                </div>
              )}
            </>
          )}

          {showTestCaseVersionDropdown && (
            <>
              <div className="d-flex align-items-center mb-3">
                <h4 className="mt-4">
                  Test Case Version for {selectedTestCase?.testCaseName}
                </h4>
              </div>
              <AppDropdown
                items={testCaseVersionsData}
                dataType="test_case_versions"
                onSelect={handleTestCaseVersionSelect}
                selectedItem={selectedTestCaseVersion}
              />
              <button
                className="btn btn-success btn-sm"
                onClick={() => setAddTestCaseVersion((prev) => !prev)}
              >
                + Add Test Case Version
              </button>
              {addTestCaseVersion && (
                <div className="mt-3">
                  <AddTestCaseVersion
                    onAdded={() => {
                      setAddTestCaseVersion(false);
                      refreshData("test_case_versions");
                    }}
                    testCase={selectedTestCase?.id}
                    onCancel={() => setAddTestCaseVersion(false)}
                  />
                </div>
              )}
            </>
          )}

          {showBreakageDropdown && (
            <>
              <div className="d-flex align-items-center mb-3">
                <h4 className="mt-4">
                  Breakage for {selectedTestCaseVersion?.testCaseVersionName}
                </h4>
              </div>
              <AppDropdown
                items={breakagesData}
                dataType="breakages"
                onSelect={handleBreakageSelect}
                selectedItem={selectedBreakage}
              />
              <button
                className="btn btn-success btn-sm"
                onClick={() => setAddBreakage((prev) => !prev)}
              >
                + Add Breakage
              </button>
              <button
                className="btn btn-success btn-sm"
                onClick={() => setAddBreakageReason((prev) => !prev)}
              >
                + Add Breakage Reason
              </button>
              <button
                className="btn btn-success btn-sm"
                onClick={() => setAddLocatingMethod((prev) => !prev)}
              >
                + Add Locathing Method
              </button>
              {addBreakage && (
                <div className="mt-3">
                  <AddBreakage
                    onAdded={() => {
                      setAddBreakage(false);
                      refreshData("breakages");
                    }}
                    testCaseVersion={selectedTestCaseVersion?.id}
                    appRelease={selectedRelease?.id}
                    onCancel={() => setAddTestCaseVersion(false)}
                  />
                </div>
              )}
              {addBreakageReason && (
                <div className="mt-3">
                  <AddBreakageReason
                    onAdded={() => {
                      setAddBreakageReason(false);
                    }}
                    onCancel={() => setAddBreakageReason(false)}
                  />
                </div>
              )}
              {addLocatingMethod && (
                <div className="mt-3">
                  <AddLocatingMethod
                    onAdded={() => {
                      setAddLocatingMethod(false);
                    }}
                    onCancel={() => setAddLocatingMethod(false)}
                  />
                </div>
              )}
            </>
          )}

          {showRepairAdd && (
            <>
              <div className="d-flex align-items-center mb-3">
                <h4 className="mt-4">
                  Repair for {selectedBreakage?.description}
                </h4>
              </div>
              <AppDropdown
                items={repairData}
                dataType="repairs"
                onSelect={handleRepairSelect}
                selectedItem={selectedRepair}
              />
              <button
                className="btn btn-success btn-sm"
                onClick={() => setAddRepair((prev) => !prev)}
              >
                + Add Repair
              </button>
              {addRepair && (
                <div className="mt-3">
                  <AddRepair
                    onAdded={() => {
                      setAddRepair(false);
                      refreshData("repairs");
                    }}
                    breakage={selectedBreakage?.id}
                    onCancel={() => setAddRepair(false)}
                  />
                </div>
              )}
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default DataProvider;
