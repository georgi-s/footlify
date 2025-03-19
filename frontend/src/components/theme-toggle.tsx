"use client";

import React, { useEffect, useState } from "react";
import { Moon, Sun } from "lucide-react";
import { motion } from "framer-motion";

// Erstelle einen globalen Theme-Context, um den Theme-Status in der gesamten App zu teilen
export const ThemeContext = React.createContext({
  theme: "light",
  setTheme: (theme: string) => {},
});

export const useTheme = () => React.useContext(ThemeContext);

export const ThemeProvider: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const [theme, setTheme] = useState<string>("light");

  useEffect(() => {
    // Beim ersten Laden den bevorzugten Modus des Benutzers oder gespeicherten Modus erkennen
    const savedTheme = localStorage.getItem("theme");

    // Wenn ein Theme gespeichert ist, dieses verwenden
    if (savedTheme) {
      setTheme(savedTheme);
      document.documentElement.classList.toggle("dark", savedTheme === "dark");
    }
    // Sonst die Systemeinstellung prüfen
    else if (
      window.matchMedia &&
      window.matchMedia("(prefers-color-scheme: dark)").matches
    ) {
      setTheme("dark");
      document.documentElement.classList.add("dark");
    }
  }, []);

  const handleThemeChange = (newTheme: string) => {
    setTheme(newTheme);
    localStorage.setItem("theme", newTheme);

    if (newTheme === "dark") {
      document.documentElement.classList.add("dark");
    } else {
      document.documentElement.classList.remove("dark");
    }
  };

  return (
    <ThemeContext.Provider value={{ theme, setTheme: handleThemeChange }}>
      {children}
    </ThemeContext.Provider>
  );
};

type ThemeToggleProps = {
  className?: string;
};

const ThemeToggle: React.FC<ThemeToggleProps> = ({ className = "" }) => {
  const { theme, setTheme } = useTheme();
  const isDarkMode = theme === "dark";

  const toggleTheme = () => {
    setTheme(isDarkMode ? "light" : "dark");
    console.log("Theme toggled to:", isDarkMode ? "light" : "dark"); // Debug-Ausgabe
  };

  return (
    <button
      className={`relative h-8 w-16 rounded-full cursor-pointer ${
        isDarkMode ? "bg-slate-700" : "bg-indigo-100"
      } p-1 transition-colors duration-300 ${className}`}
      onClick={toggleTheme}
      aria-label={isDarkMode ? "Switch to light mode" : "Switch to dark mode"}
    >
      <div className="absolute inset-0 flex items-center justify-between px-2 text-xs font-medium">
        <span
          className={`text-indigo-900 transition-opacity duration-200 ${
            isDarkMode ? "opacity-0" : "opacity-100"
          }`}
        >
          <Sun size={14} />
        </span>
        <span
          className={`text-indigo-100 transition-opacity duration-200 ${
            isDarkMode ? "opacity-100" : "opacity-0"
          }`}
        >
          <Moon size={14} />
        </span>
      </div>
      <motion.div
        className={`h-6 w-6 rounded-full shadow-md flex items-center justify-center ${
          isDarkMode ? "bg-indigo-600" : "bg-white"
        }`}
        initial={false}
        animate={{ x: isDarkMode ? 32 : 0 }}
        transition={{ type: "spring", stiffness: 500, damping: 30 }}
      >
        {isDarkMode ? (
          <Moon className="h-3.5 w-3.5 text-white" />
        ) : (
          <Sun className="h-3.5 w-3.5 text-indigo-600" />
        )}
      </motion.div>
    </button>
  );
};

export default ThemeToggle;
