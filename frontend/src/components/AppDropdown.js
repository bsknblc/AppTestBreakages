import Dropdown from "react-bootstrap/Dropdown";

const AppDropdown = ({ items, dataType, onSelect, selectedItem }) => {
  // Determine which property to display based on data type
  const getDisplayProperty = (item) => {
    if (!item) return `Select ${dataType}`;

    switch (dataType) {
      case "applications":
        return item.appName || item.id;
      case "app_releases":
        return item.releaseName || item.id;
      case "test_suites":
        return item.testSuiteName || item.id;
      case "test_cases":
        return item.testCaseName || item.id;
      case "test_case_versions":
        return item.testCaseVersionName || item.id;
      case "breakage_reasons":
        return item.reasonName || item.id;
      case "locating_methods":
        return item.locatingMethodName || item.id;
      case "breakages":
        return item.taxonomyDescription || item.id;
      case "repairs":
        return item.commitHash || item.id;
      default:
        return item.name || item.id;
    }
  };

  return (
    <Dropdown>
      <Dropdown.Toggle variant="success" id="dropdown-basic">
        {getDisplayProperty(selectedItem)}
      </Dropdown.Toggle>

      <Dropdown.Menu>
        {items.map((item) => (
          <Dropdown.Item key={item.id} onClick={() => onSelect(item)}>
            {getDisplayProperty(item)}
          </Dropdown.Item>
        ))}
      </Dropdown.Menu>
    </Dropdown>
  );
};

export default AppDropdown;
