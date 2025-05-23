import React, { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "../MyStyle.css";

const AddApplication = ({ onAdded }) => {
  const [formData, setFormData] = useState({
    appName: "",
    url: "",
    languages: [], // Stores selected language objects
  });
  const [allLanguages, setAllLanguages] = useState([]);
  const [selectedLanguageId, setSelectedLanguageId] = useState("");
  const [error, setError] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);
  const [loadingLanguages, setLoadingLanguages] = useState(true);

  // Fetch all available languages
  useEffect(() => {
    const fetchLanguages = async () => {
      try {
        const response = await fetch("http://localhost:3000/api/languages");
        if (!response.ok) {
          throw new Error("Failed to fetch languages");
        }
        const data = await response.json();
        setAllLanguages(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoadingLanguages(false);
      }
    };

    fetchLanguages();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleAddLanguage = () => {
    if (!selectedLanguageId) return;

    const languageToAdd = allLanguages.find(
      (lang) => lang.id === parseInt(selectedLanguageId)
    );

    if (
      languageToAdd &&
      !formData.languages.some((lang) => lang.id === languageToAdd.id)
    ) {
      setFormData((prev) => ({
        ...prev,
        languages: [...prev.languages, languageToAdd],
      }));
      setSelectedLanguageId(""); // Reset selection
    }
  };

  const removeLanguage = (languageId) => {
    setFormData((prev) => ({
      ...prev,
      languages: prev.languages.filter((lang) => lang.id !== languageId),
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitting(true);
    setError(null);
    setSuccess(false);

    try {
      const response = await fetch("http://localhost:3000/api/applications", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          appName: formData.appName,
          url: formData.url,
          languages: formData.languages,
        }),
      });

      if (!response.ok) {
        throw new Error("Failed to add application");
      }

      const newApplication = await response.json();
      onAdded(newApplication);
      setFormData({ appName: "", url: "", languages: [] });
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
        <h3 className="mb-0">Add New Application</h3>
      </div>
      <div className="card-body">
        {error && <div className="alert alert-danger">{error}</div>}
        {success && (
          <div className="alert alert-success">
            Application added successfully!
          </div>
        )}

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="appName" className="form-label">
              Application Name
            </label>
            <input
              type="text"
              className="form-control"
              id="appName"
              name="appName"
              value={formData.appName}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="url" className="form-label">
              URL
            </label>
            <input
              type="url"
              className="form-control"
              id="url"
              name="url"
              value={formData.url}
              onChange={handleChange}
              required
            />
          </div>

          {/* Language Selection */}
          <div className="mb-3">
            <label className="form-label">Languages</label>
            {loadingLanguages ? (
              <div className="spinner-border spinner-border-sm" role="status">
                <span className="visually-hidden">Loading...</span>
              </div>
            ) : (
              <>
                <div className="d-flex gap-2 mb-2">
                  <select
                    className="form-select"
                    value={selectedLanguageId}
                    onChange={(e) => setSelectedLanguageId(e.target.value)}
                    onClick={handleAddLanguage}
                  >
                    <option value="">Select a language</option>
                    {allLanguages
                      .filter(
                        (lang) =>
                          !formData.languages.some((l) => l.id === lang.id)
                      )
                      .map((language) => (
                        <option key={language.id} value={language.id}>
                          {language.languageName}
                        </option>
                      ))}
                  </select>
                </div>

                {/* Selected Languages */}
                <div className="d-flex flex-wrap gap-2">
                  {formData.languages.map((language) => (
                    <div
                      key={language.id}
                      className="badge bg-primary p-2 d-flex align-items-center"
                    >
                      {language.languageName}
                      <button
                        type="button"
                        className="btn-close btn-close-white ms-2"
                        aria-label="Remove"
                        onClick={() => removeLanguage(language.id)}
                        style={{ fontSize: "0.5rem" }}
                      />
                    </div>
                  ))}
                </div>
              </>
            )}
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
              "Add Application"
            )}
          </button>
        </form>
      </div>
    </div>
  );
};

export default AddApplication;
