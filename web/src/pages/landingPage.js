import React, { useEffect, useState } from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import InfoCard from '../components/RaceCard';

const LandingPage = ({ username }) => {
  const [races, setRaces] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const fetchRaces = async () => {
      try {
        const response = await fetch(
          'http://localhost:8080/f1app/api/v1/races/get/all',
          {
            headers: {
              'Authorization': 'Basic ' + btoa('admin:admin123'),
            },
          }
        );
        const data = await response.json();

        const nextRaceIndex = data.findIndex((r) => r.status !== 'Completed');
        setCurrentIndex(nextRaceIndex >= 0 ? nextRaceIndex : 0);
        setRaces(data);
      } catch (err) {
        console.error('Failed to fetch races:', err);
      }
    };

    fetchRaces();
  }, []);

  const formatTime = (date, time) => {
    return new Date(`${date.split('T')[0]}T${time}`).toLocaleString();
  };

  const handlePrev = () => {
    if (currentIndex > 0) setCurrentIndex(currentIndex - 1);
  };

  const handleNext = () => {
    if (currentIndex < races.length - 1) setCurrentIndex(currentIndex + 1);
  };

  const getRace = (offset) => {
    const index = currentIndex + offset;
    return index >= 0 && index < races.length ? races[index] : null;
  };

  return (
    <div
      className="d-flex flex-column min-vh-100"
      style={{ background: 'linear-gradient(to bottom, #000, #6f42c1)' }}
    >
      <Header />
      <main className="flex-grow-1 text-white py-5">
        <div className="container text-center">
          <h1 className="display-5 fw-bold mb-4">Welcome {username}</h1>
          <div className="d-flex justify-content-center align-items-center mb-4">
            <button
              className="btn btn-outline-light me-3"
              onClick={handlePrev}
              disabled={currentIndex <= 0}
            >
              ←
            </button>
            <div className="d-flex justify-content-center flex-wrap">
              {[getRace(-1), getRace(0), getRace(1)].map((race, i) =>
                race ? (
                  <InfoCard
                    key={race.raceId}
                    raceName={race.raceName}
                    time={formatTime(race.date, race.time)}
                  />
                ) : null
              )}
            </div>
            <button
              className="btn btn-outline-light ms-3"
              onClick={handleNext}
              disabled={currentIndex >= races.length - 1}
            >
              →
            </button>
          </div>
        </div>
      </main>
      <Footer />
    </div>
  );
};

export default LandingPage;
