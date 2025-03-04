import React from 'react';
import { Toaster } from 'sonner';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Layout from './components/Layout';

// Lazy load pages
const SpielerList = React.lazy(() => import('./pages/SpielerList'));
const MannschaftList = React.lazy(() => import('./pages/MannschaftList'));
const LigaList = React.lazy(() => import('./pages/LigaList'));
const FormationList = React.lazy(() => import('./pages/FormationList'));
const ClubManagement = React.lazy(() => import('./pages/ClubManagement'));

const LoadingSpinner = () => (
  <div className="h-[200px] flex items-center justify-center">
    <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500" />
  </div>
);

function App() {
  return (
    <>
      <Toaster richColors position="top-right" />
      <Router>
        <Layout>
          <React.Suspense fallback={<LoadingSpinner />}>
            <Routes>
              <Route path="/" element={<Navigate to="/spieler" replace />} />
              <Route path="/spieler" element={<SpielerList />} />
              <Route path="/mannschaften" element={<MannschaftList />} />
              <Route path="/ligen" element={<LigaList />} />
              <Route path="/formationen" element={<FormationList />} />
              <Route path="/club-management" element={<ClubManagement />} />
            </Routes>
          </React.Suspense>
        </Layout>
      </Router>
    </>
  );
}

export default App;
