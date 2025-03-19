"use client";
import React from "react";
import type { ReactNode } from "react";
import AppDock from "./DockApp";
// Korrigieren Sie den Import von useMobile zu useIsMobile
import { useIsMobile } from "../../hooks/use-mobile";
import { cn } from "../../lib/utils";

interface AppLayoutProps {
  children: ReactNode;
}

const AppLayout = ({ children }: AppLayoutProps) => {
  // Und aktualisieren Sie die Verwendung
  const isMobile = useIsMobile();

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-950">
      <AppDock />
      <main
        className={cn(
          "transition-all duration-300",
          isMobile
            ? "pb-20" // Platz für die mobile Navigation unten
            : "ml-20" // Platz für die eingeklappte Seitenleiste
        )}
      >
        {children}
      </main>
    </div>
  );
};

export default AppLayout;
