import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";

const AddLocatingMethod = ({ onAdded }) => {
  const [formData, setFormData] = useState({
    locatingMethodName: "",
  });
  const [error, setError] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setError(null);
    setSuccess(false);

    try {
      const response = await fetch(
        "http://localhost:3000/api/locating_methods",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );

      if (!response.ok) {
        throw new Error("Failed to add locating method");
      }

      onAdded();
      setFormData({ locatingMethodName: "" });
      setSuccess(true);
      setTimeout(() => setSuccess(false), 3000);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="card">
      <div className="card-header bg-primary text-white">
        <h3 className="mb-0">Add New Locating Method</h3>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        {success && (
          <div className="alert alert-success">
            Locating method added successfully!
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="locatingMethodName" className="form-label">
              Locating Mehod
            </label>
            <input
              type="text"
              className="form-control"
              id="locatingMethodName"
              name="locatingMethodName"
              value={formData.locatingMethodName}
              onChange={handleChange}
              required
            />
          </div>
          <button
            type="submit"
            className="btn btn-primary"
            disabled={isSubmitting}
          >
            {isSubmitting ? (
              <>
                <span
                  className="spinner-border spinner-border-sm me-2"
                  role="status"
                  aria-hidden="true"
                ></span>
                Adding...
              </>
            ) : (
              "Add Locating Method"
            )}
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddLocatingMethod;
