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
  const [activeTab, setActiveTab] = useState("breakage"); // 'breakage' or 'repair'

  useEffect(() => {
    const fetchExplanationCounts = async () => {
      try {
        setLoading(true);

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

        setBreakageExplanations(breakageData);
        setRepairExplanations(repairData);
        setError(null);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchExplanationCounts();
  }, []);

  const handleExplanationClick = (explanation, type) => {
    onFilter([{ text: explanation.explanation, type }]);
  };

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
      tooltip: {
        callbacks: {
          afterLabel: () => "Click to filter by this explanation",
        },
      },
      title: {
        display: true,
        text:
          activeTab === "breakage"
            ? "Breakage Explanations Frequency"
            : "Repair Explanations Frequency",
        font: {
          size: 16,
        },
      },
    },
    scales: {
      y: {
        beginAtZero: true,
        ticks: {
          font: {
            size: 12,
          },
        },
      },
      x: {
        ticks: {
          font: {
            size: 12,
          },
          callback: function (value) {
            const label = this.getLabelForValue(value);
            return label.length > 20 ? label.substring(0, 20) + "..." : label;
          },
        },
      },
    },
    onClick: (event, elements) => {
      if (elements.length > 0) {
        const chart = event.chart;
        const elementIndex = elements[0].index;
        const explanation = chart.data.labels[elementIndex];
        const type = activeTab;

        const selectedExplanation =
          type === "breakage"
            ? breakageExplanations.find(
                (exp) => exp.explanation === explanation
              )
            : repairExplanations.find((exp) => exp.explanation === explanation);

        if (selectedExplanation) {
          handleExplanationClick(selectedExplanation, type);
        }
      }
    },
  };

  const currentChartData =
    activeTab === "breakage"
      ? {
          labels: breakageExplanations.map((exp) => exp.explanation),
          datasets: [
            {
              label: "Count",
              data: breakageExplanations.map((exp) => exp.count),
              backgroundColor: "rgba(220, 53, 69, 0.7)",
              borderColor: "rgba(220, 53, 69, 1)",
              borderWidth: 1,
            },
          ],
        }
      : {
          labels: repairExplanations.map((exp) => exp.explanation),
          datasets: [
            {
              label: "Count",
              data: repairExplanations.map((exp) => exp.count),
              backgroundColor: "rgba(40, 167, 69, 0.7)",
              borderColor: "rgba(40, 167, 69, 1)",
              borderWidth: 1,
            },
          ],
        };

  if (loading)
    return (
      <div className="text-center py-5">Loading explanation statistics...</div>
    );
  if (error) return <div className="alert alert-danger">Error: {error}</div>;

  return (
    <div className="card mb-4">
      <div className="card-header d-flex justify-content-between align-items-center">
        <h5 className="mb-0">Explanation Statistics</h5>
        <div className="btn-group" role="group">
          <button
            type="button"
            className={`btn btn-sm ${
              activeTab === "breakage" ? "btn-danger" : "btn-outline-danger"
            }`}
            onClick={() => setActiveTab("breakage")}
          >
            Breakages
          </button>
          <button
            type="button"
            className={`btn btn-sm ${
              activeTab === "repair" ? "btn-success" : "btn-outline-success"
            }`}
            onClick={() => setActiveTab("repair")}
          >
            Repairs
          </button>
        </div>
      </div>
      <div className="card-body p-0">
        <div style={{ height: "500px", padding: "20px" }}>
          <Bar data={currentChartData} options={chartOptions} height={400} />
        </div>
        <div className="px-3 pb-3">
          <div className="alert alert-info mb-0">
            <i className="bi bi-info-circle me-2"></i>
            Click on any bar to filter breakages by that explanation.
            {activeTab === "breakage"
              ? " Breakage explanations show why tests failed."
              : " Repair explanations show how issues were fixed."}
          </div>
        </div>
      </div>
    </div>
  );
};

export default ExplanationVisualization;
