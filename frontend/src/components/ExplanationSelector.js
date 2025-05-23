import React, { useState } from "react";

const ExplanationSelector = ({
  label,
  apiEndpoint,
  selectedItems,
  onAdd,
  onRemove,
  propertyName = "explanation",
}) => {
  const [filter, setFilter] = useState("");
  const [newItem, setNewItem] = useState("");
  const [showAddOption, setShowAddOption] = useState(false);
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchItems = async () => {
    setLoading(true);
    try {
      const response = await fetch(apiEndpoint);
      if (!response.ok) throw new Error("Failed to fetch explanations");
      const data = await response.json();
      setItems(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleFilterChange = (e) => {
    const value = e.target.value;
    setFilter(value);

    if (value && !items.length) {
      fetchItems();
    }

    // Show "add new" option if no matches found
    const hasMatch = items.some((item) =>
      item[propertyName].toLowerCase().includes(value.toLowerCase())
    );
    setShowAddOption(value && !hasMatch);
  };

  const handleAddNew = async () => {
    if (newItem.trim()) {
      try {
        const response = await fetch(apiEndpoint, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ [propertyName]: newItem.trim() }),
        });

        if (!response.ok) throw new Error("Failed to add explanation");
        const newExplanation = await response.json();

        setItems((prev) => [...prev, newExplanation]);
        onAdd(newExplanation);
        setFilter("");
        setNewItem("");
        setShowAddOption(false);
      } catch (err) {
        setError(err.message);
      }
    }
  };

  return (
    <div className="mb-3">
      <label className="form-label">{label}</label>
      {error && <div className="alert alert-danger">{error}</div>}

      <div className="input-group mb-2">
        <input
          type="text"
          className="form-control"
          placeholder={`Search or add new ${label.toLowerCase()}`}
          value={filter}
          onChange={handleFilterChange}
        />
      </div>

      {filter && loading ? (
        <div className="d-flex justify-content-center">
          <div className="spinner-border" role="status">
            <span className="visually-hidden">Loading...</span>
          </div>
        </div>
      ) : (
        filter && (
          <div className="list-group mb-2">
            {items
              .filter((item) =>
                item[propertyName].toLowerCase().includes(filter.toLowerCase())
              )
              .map((item) => (
                <button
                  type="button"
                  className="list-group-item list-group-item-action"
                  key={item.id}
                  onClick={() => {
                    onAdd(item);
                    setFilter("");
                  }}
                >
                  {item[propertyName]}
                </button>
              ))}

            {showAddOption && (
              <div className="input-group">
                <input
                  type="text"
                  className="form-control"
                  value={newItem}
                  onChange={(e) => setNewItem(e.target.value)}
                  placeholder={`Enter new ${label.toLowerCase()}`}
                />
                <button
                  className="btn btn-outline-secondary"
                  type="button"
                  onClick={handleAddNew}
                >
                  Add
                </button>
              </div>
            )}
          </div>
        )
      )}

      <div className="d-flex flex-wrap gap-2">
        {selectedItems.map((item) => (
          <span
            key={item.id || item[propertyName]}
            className="badge bg-primary d-flex align-items-center"
          >
            {item[propertyName]}
            <button
              type="button"
              className="btn-close btn-close-white ms-2"
              style={{ fontSize: "0.5rem" }}
              onClick={() => onRemove(item)}
              aria-label="Remove"
            ></button>
          </span>
        ))}
      </div>
    </div>
  );
};

export default ExplanationSelector;
