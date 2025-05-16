import { useNavigate } from "react-router-dom";
import AddApplication from "../components/AddApplication";
import AddVersion from "../components/AddVersion";
import AddTestSuite from "../components/AddTestSuite";
import AddTestCase from "../components/AddTestCase";
import AddTestCaseVersion from "../components/AddTestCaseVersion";
import AddBreakage from "../components/AddBreakage";
import AddRepair from "../components/AddRepair";
import AddLocatingMethod from "../components/AddLocatingMethod";
import AddBreakageReason from "../components/AddBreakageReason";
import AddLanguage from "../components/AddLanguage";

const AddApplicationPage = () => {
  const navigate = useNavigate();

  const handleAdd = (/* newApplication */) => {
    // You can add to global state here if needed
    navigate("/admin"); // Redirect back to admin panel after adding
  };

  return (
    <div className="container mt-4">
      <button
        onClick={() => navigate("/admin")}
        className="btn btn-secondary mb-3"
      >
        ← Back to Applications
      </button>
      <AddApplication onAdded={handleAdd} />
      <AddVersion onAdded={handleAdd} />
      <AddTestSuite onAdded={handleAdd} />
      <AddTestCase onAdded={handleAdd} />
      <AddTestCaseVersion onAdded={handleAdd} />
      <AddBreakage onAdded={handleAdd} />
      <AddRepair onAdded={handleAdd} />
      <AddLocatingMethod onAdded={handleAdd} />
      <AddBreakageReason onAdded={handleAdd} />
      <AddLanguage onAdded={handleAdd} />
    </div>
  );
};

export default AddApplicationPage;
