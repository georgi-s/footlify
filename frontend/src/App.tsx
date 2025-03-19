// import React from "react";
// import {
//   BrowserRouter as Router,
//   Routes,
//   Route,
//   Navigate,
// } from "react-router-dom";
// import { Toaster } from "./components/ui/toaster";
// import Dashboard from "./components/Dashboard";

// // Lazy load pages
// const SpielerList = React.lazy(() => import("./components/SpielerList"));
// const MannschaftList = React.lazy(() => import("./components/MannschaftList"));
// // const LigaList = React.lazy(() => import('./pages/LigaList'));
// // const FormationList = React.lazy(() => import('./pages/FormationList'));
// // const ClubManagement = React.lazy(() => import('./pages/ClubManagement'));

// const LoadingSpinner = () => (
//   <div className="h-[200px] flex items-center justify-center">
//     <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500" />
//   </div>
// );

// function App() {
//   return (
//     <>
//       <Router>
//         {/* <Layout> */}
//         <React.Suspense fallback={<LoadingSpinner />}>
//           <Routes>
//             <Route path="/" element={<Dashboard />} />
//             <Route path="/" element={<Navigate to="/spieler" replace />} />
//             <Route path="/spieler" element={<SpielerList />} />
//             <Route path="/mannschaften" element={<MannschaftList />} />
//             {/* <Route path="/ligen" element={<LigaList />} /> */}
//             {/* <Route path="/formationen" element={<FormationList />} /> */}
//             {/* <Route path="/club-management" element={<ClubManagement />} /> */}
//           </Routes>
//         </React.Suspense>
//         {/* </Layout> */}
//       </Router>

//       {/* Toaster hinzufügen - außerhalb des Routers, aber innerhalb der App */}
//       <Toaster />
//     </>
//   );
// }

// export default App;
"use client";

import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  useNavigate,
  useLocation,
} from "react-router-dom";
import { Toaster } from "./components/ui/toaster";
import Dashboard from "./components/Dashboard";
import { Dock } from "./components/dock/Dock";
import { DockCard } from "./components/dock/DockCard";
import { DockDivider } from "./components/dock/DockDivider";
import {
  Home,
  Users,
  Trophy,
  Calendar,
  BarChart3,
  Settings,
} from "lucide-react";

// Lazy load pages
const SpielerList = React.lazy(
  () => import("./components/Spieler/SpielerList")
);
const MannschaftList = React.lazy(
  () => import("./components/Mannschaft/MannschaftList")
);
const MannschaftManagementPanel = React.lazy(
  () => import("./components/Mannschaft/MannschaftManagementPanel")
);
// const LigaList = React.lazy(() => import('./pages/LigaList'));
// const FormationList = React.lazy(() => import('./pages/FormationList'));
// const ClubManagement = React.lazy(() => import('./pages/ClubManagement'));

const LoadingSpinner = () => (
  <div className="h-[200px] flex items-center justify-center">
    <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500" />
  </div>
);

// Platzhalter-Komponenten für die anderen Seiten
const Spielplan = () => (
  <div className="p-8 text-center">Spielplan-Seite (Platzhalter)</div>
);
const Statistiken = () => (
  <div className="p-8 text-center">Statistiken-Seite (Platzhalter)</div>
);
const Einstellungen = () => (
  <div className="p-8 text-center">Einstellungen-Seite (Platzhalter)</div>
);

// Importieren Sie den neuen Hook
import { useIconSize } from "./hooks/use-icon-size";

// Erstelle eine Komponente für den Inhalt, die die Hooks verwendet
const AppContent = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [activeIndex, setActiveIndex] = React.useState<number | null>(null);
  const iconSize = useIconSize(); // Verwenden Sie den Hook

  // Setze den aktiven Index basierend auf dem aktuellen Pfad
  React.useEffect(() => {
    const pathToIndexMap: Record<string, number> = {
      "/": 0,
      "/mannschaften": 1,
      "/spieler": 2,
      "/spielplan": 3,
      "/statistiken": 4,
      "/einstellungen": 5,
    };

    setActiveIndex(pathToIndexMap[location.pathname] ?? null);
  }, [location.pathname]);

  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-blue-50 to-sky-50 dark:from-gray-900 dark:via-indigo-950 dark:to-blue-950">
      <React.Suspense fallback={<LoadingSpinner />}>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/spieler" element={<SpielerList />} />
          <Route path="/mannschaften" element={<MannschaftList />} />
          <Route path="/mannschaften/:id" element={<MannschaftManagementPanel />} />
          <Route path="/spielplan" element={<Spielplan />} />
          <Route path="/statistiken" element={<Statistiken />} />
          <Route path="/einstellungen" element={<Einstellungen />} />
        </Routes>
      </React.Suspense>

      {/* Dock am unteren Bildschirmrand, horizontal zentriert */}
      <div className="fixed bottom-4 left-0 right-0 mx-auto flex justify-center z-50">
        <Dock initialActiveIndex={activeIndex}>
          <DockCard onClick={() => navigate("/")}>
            <Home size={iconSize} />
          </DockCard>
          <DockCard onClick={() => navigate("/mannschaften")}>
            <Trophy size={iconSize} />
          </DockCard>
          <DockCard onClick={() => navigate("/spieler")}>
            <Users size={iconSize} />
          </DockCard>
          <DockDivider />
          <DockCard onClick={() => navigate("/spielplan")}>
            <Calendar size={iconSize} />
          </DockCard>
          <DockCard onClick={() => navigate("/statistiken")}>
            <BarChart3 size={iconSize} />
          </DockCard>
          <DockCard onClick={() => navigate("/einstellungen")}>
            <Settings size={iconSize} />
          </DockCard>
        </Dock>
      </div>

      {/* Toaster hinzufügen */}
      <Toaster />
    </div>
  );
}

// App-Komponente, die AppContent ohne Router enthält
function App() {
  return <AppContent />;
}

export default App;
