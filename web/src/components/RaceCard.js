// src/components/RaceCard.js
import React from 'react';
import { useNavigate } from 'react-router-dom';


const RaceCard = ({ raceName, time }) => {

  const navigate = useNavigate();

  const handleclick = () => {
    navigate(`/pages/racePage/${encodeURIComponent(raceName)}`);
  }

  return (
    <div className="card bg-dark text-white m-2" style={{ width: '18rem', borderRadius: '12px', border: '1px solid #6f42c1' }} onClick={handleclick}>
      <div className="card-body">
        <h5 className="card-title">{raceName}</h5>
        <p className="card-text">{time}</p>
      </div>
    </div>
  );
};

export default RaceCard;
