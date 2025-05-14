import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AdminPanel from "./pages/AdminPanel";
import AddApplicationPage from "./pages/AddApplicationPage";
import "bootstrap/dist/css/bootstrap.min.css";
import DataProvider from "./pages/DataProvider";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/admin" element={<AdminPanel />} />
        <Route path="/add-application" element={<AddApplicationPage />} />
        <Route path="/add-breakage" element={<DataProvider />} />
        <Route path="/" element={<AdminPanel />} />
      </Routes>
    </Router>
  );
}

export default App;
