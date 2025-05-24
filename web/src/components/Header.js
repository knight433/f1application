import React from 'react';

const Header = () => (
  <nav className="navbar navbar-expand-lg" style={{ background: 'linear-gradient(to right, #6f42c1, #000)' }}>
    <div className="container-fluid text-white">
      <a className="navbar-brand text-white fw-bold" href="#">MyLanding</a>
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav ms-auto">
          <li className="nav-item">
            <a className="nav-link text-white" href="#">Home</a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">Features</a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">Contact</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
);

export default Header;