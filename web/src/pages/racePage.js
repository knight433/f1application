// src/pages/racePage.js
import React from 'react';
import { useParams } from 'react-router-dom';
import Header from '../components/Header';
import Footer from '../components/Footer';

const RacePage = () => {
    const { raceName } = useParams();

    return (
        <div
            className="d-flex flex-column min-vh-100"
            style={{ background: 'linear-gradient(to bottom, #000, #6f42c1)' }}
        >
            <Header />
            <main className="flex-grow-1 text-white py-5">
                <div
                    className="position-absolute top-0 end-1"
                    style={{ transform: 'translate(20px, 85px)', fontSize: '1 rem' }}
                >
                    <h1 className="text-white ">{decodeURIComponent(raceName)}</h1>
                </div>
            </main>

            <Footer />
        </div>
    );
};

export default RacePage;
