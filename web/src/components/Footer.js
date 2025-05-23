import React from 'react';

const Footer = () => (
    <footer className="text-center text-light py-4 mt-auto" style={{ backgroundColor: '#000' }}>
      <div className="container">
        <div className="mb-2">
          <a href="#" className="text-purple text-decoration-none mx-2">Privacy</a>
          <a href="#" className="text-purple text-decoration-none mx-2">Terms</a>
          <a href="#" className="text-purple text-decoration-none mx-2">Support</a>
        </div>
        <p className="mb-0 text-muted">&copy; {new Date().getFullYear()} MyLanding. All rights reserved.</p>
      </div>
    </footer>
  );

export default Footer;