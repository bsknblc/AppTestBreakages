import React, { useState, useEffect } from "react";
import { Bar } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const ExplanationVisualization = ({ onFilter }) => {
  const [breakageExplanations, setBreakageExplanations] = useState([]);
  const [repairExplanations, setRepairExplanations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [activeTab, setActiveTab] = useState("distal"); // 'distal', 'proximal', or 'repair'

  useEffect(() => {
    const fetchExplanationCounts = async () => {
      try {
        setLoading(true);
        setError(null);

        const [breakageResponse, repairResponse] = await Promise.all([
          fetch("/api/breakages/explanation-stats"),
          fetch("/api/repairs/explanation-stats"),
        ]);

        if (!breakageResponse.ok || !repairResponse.ok) {
          throw new Error("Failed to fetch explanation counts");
        }

        const [breakageData, repairData] = await Promise.all([
          breakageResponse.json(),
          repairResponse.json(),
        ]);

        // Sort by count descending
        const sortedBreakageData = [...breakageData].sort(
          (a, b) => b.count - a.count
        );
        const sortedRepairData = [...repairData].sort(
          (a, b) => b.count - a.count
        );

        setBreakageExplanations(sortedBreakageData);
        setRepairExplanations(sortedRepairData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchExplanationCounts();
  }, []);

  const handleExplanationClick = (explanation) => {
    const type = activeTab === "repair" ? "repair" : "breakage";
    onFilter([{ text: explanation.explanation, type }]);
  };

  const createChartOptions = (title) => ({
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        callbacks: {
          label: (context) => `${context.parsed.y} occurrences`,
          afterLabel: () => "Click to filter by this explanation",
        },
      },
      title: {
        display: true,
        text: title,
        font: { size: 16 },
      },
    },
    scales: {
      y: {
        beginAtZero: true,
        title: { display: true, text: "Count" },
      },
      x: {
        title: { display: true, text: "Explanation" },
        ticks: {
          autoSkip: false,
          maxRotation: 45,
          minRotation: 45,
          callback: function (value) {
            const label = this.getLabelForValue(value);
            return label.length > 15 ? label.substring(0, 15) + "..." : label;
          },
        },
      },
    },
    onClick: (event, elements) => {
      if (elements.length > 0) {
        const index = elements[0].index;
        const explanation = getCurrentData()[index];
        handleExplanationClick(explanation);
      }
    },
  });

  const getCurrentData = () => {
    switch (activeTab) {
      case "distal":
        return breakageExplanations.filter(
          (exp) => exp.causeType === "Distal Cause"
        );
      case "proximal":
        return breakageExplanations.filter(
          (exp) => exp.causeType === "Proximal Cause"
        );
      case "repair":
        return repairExplanations;
      default:
        return [];
    }
  };

  const getChartColor = () => {
    switch (activeTab) {
      case "distal":
        return "rgba(220, 53, 69, 0.7)"; // Dark red
      case "proximal":
        return "rgba(255, 159, 64, 0.7)"; // Orange
      case "repair":
        return "rgba(40, 167, 69, 0.7)"; // Green
      default:
        return "rgba(100, 100, 100, 0.7)"; // Gray
    }
  };

  const createChartData = () => {
    const currentData = getCurrentData();
    const color = getChartColor();

    return {
      labels: currentData.map((exp) => exp.explanation),
      datasets: [
        {
          label: "Count",
          data: currentData.map((exp) => exp.count),
          backgroundColor: color,
          borderColor: color.replace("0.7", "1"),
          borderWidth: 1,
        },
      ],
    };
  };

  if (loading) return <div className="text-center py-5">Loading...</div>;
  if (error) return <div className="alert alert-danger">{error}</div>;

  return (
    <div className="card mb-4">
      <div className="card-header">
        <div className="d-flex justify-content-between align-items-center">
          <h5 className="mb-0">Explanation Statistics</h5>
          <div className="btn-group">
            <button
              className={`btn btn-sm ${
                activeTab === "distal" ? "btn-danger" : "btn-outline-danger"
              }`}
              onClick={() => setActiveTab("distal")}
            >
              Distal Causes
            </button>
            <button
              className={`btn btn-sm ${
                activeTab === "proximal" ? "btn-warning" : "btn-outline-warning"
              }`}
              onClick={() => setActiveTab("proximal")}
            >
              Proximal Causes
            </button>
            <button
              className={`btn btn-sm ${
                activeTab === "repair" ? "btn-success" : "btn-outline-success"
              }`}
              onClick={() => setActiveTab("repair")}
            >
              Repairs
            </button>
          </div>
        </div>
      </div>
      <div className="card-body p-0">
        <div className="p-3">
          <div style={{ height: "500px" }}>
            <Bar
              data={createChartData()}
              options={createChartOptions(
                activeTab === "distal"
                  ? "Distal Causes"
                  : activeTab === "proximal"
                  ? "Proximal Causes"
                  : "Repair Explanations"
              )}
            />
          </div>
        </div>
        <div className="px-3 pb-3">
          <div className="alert alert-info mb-0">
            <i className="bi bi-info-circle me-2"></i>
            {activeTab === "distal"
              ? "Distal causes are root causes of breakages. Click bars to filter."
              : activeTab === "proximal"
              ? "Proximal causes are immediate causes of breakages. Click bars to filter."
              : "Repair explanations show how issues were fixed. Click bars to filter."}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExplanationVisualization;
