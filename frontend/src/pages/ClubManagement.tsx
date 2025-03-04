import React from 'react';
import ClubPanel from '../components/ClubPanel';

const ClubManagement: React.FC = () => {
  return (
    <div className="p-4">
      <h1 className="text-3xl font-bold mb-6">Mannschaftsverwaltung</h1>
      <ClubPanel />
    </div>
  );
};

export default ClubManagement;
