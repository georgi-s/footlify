"use client";

import React from "react";

import { useState, useEffect } from "react";
import { motion } from "framer-motion";
import { cn } from "../../lib/utils";
import { useNavigate, useLocation } from "react-router-dom";
import {
  Users,
  Trophy,
  Calendar,
  BarChart3,
  Settings,
  Menu,
  X,
  Home,
} from "lucide-react";
import { Button } from "../ui/button";
import { useIsMobile } from "../../hooks/use-mobile";

interface NavItem {
  icon: React.ReactNode;
  label: string;
  path: string;
}

const AppDock = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const isMobile = useIsMobile();
  const [isExpanded, setIsExpanded] = useState(false);
  const [currentPath, setCurrentPath] = useState(location.pathname);

  useEffect(() => {
    setCurrentPath(location.pathname);
  }, [location.pathname]);

  const navItems: NavItem[] = [
    {
      icon: <Home className="h-5 w-5" />,
      label: "Dashboard",
      path: "/",
    },
    {
      icon: <Trophy className="h-5 w-5" />,
      label: "Mannschaften",
      path: "/mannschaften",
    },
    {
      icon: <Users className="h-5 w-5" />,
      label: "Spieler",
      path: "/spieler",
    },
    {
      icon: <Calendar className="h-5 w-5" />,
      label: "Spielplan",
      path: "/spielplan",
    },
    {
      icon: <BarChart3 className="h-5 w-5" />,
      label: "Statistiken",
      path: "/statistiken",
    },
    {
      icon: <Settings className="h-5 w-5" />,
      label: "Einstellungen",
      path: "/einstellungen",
    },
  ];

  const handleNavigation = (path: string) => {
    navigate(path);
    if (isMobile) {
      setIsExpanded(false);
    }
  };

  // Mobile Bottom Dock
  if (isMobile) {
    return (
      <>
        {/* Mobile Bottom Navigation */}
        <div className="fixed bottom-0 left-0 right-0 z-50 bg-white/80 dark:bg-gray-900/80 backdrop-blur-lg border-t border-gray-200 dark:border-gray-800 shadow-lg">
          <div className="flex items-center justify-around px-2 py-3">
            {navItems.slice(0, 5).map((item) => (
              <button
                key={item.path}
                onClick={() => handleNavigation(item.path)}
                className={cn(
                  "flex flex-col items-center justify-center px-3 py-2 rounded-xl transition-all",
                  currentPath === item.path
                    ? "text-blue-600 dark:text-blue-400"
                    : "text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-300"
                )}
              >
                <div
                  className={cn(
                    "relative p-1.5 rounded-full transition-all",
                    currentPath === item.path &&
                      "bg-blue-100 dark:bg-blue-900/30"
                  )}
                >
                  {item.icon}
                  {currentPath === item.path && (
                    <motion.div
                      layoutId="mobileBubble"
                      className="absolute inset-0 rounded-full bg-blue-100 dark:bg-blue-900/30 -z-10"
                      transition={{
                        type: "spring",
                        bounce: 0.2,
                        duration: 0.6,
                      }}
                    />
                  )}
                </div>
                <span className="text-xs mt-1 font-medium">{item.label}</span>
              </button>
            ))}
            <button
              onClick={() => setIsExpanded(!isExpanded)}
              className="flex flex-col items-center justify-center px-3 py-2 rounded-xl text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-300"
            >
              <div className="p-1.5 rounded-full">
                <Menu className="h-5 w-5" />
              </div>
              <span className="text-xs mt-1 font-medium">Mehr</span>
            </button>
          </div>
        </div>

        {/* Mobile Expanded Menu */}
        {isExpanded && (
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            exit={{ opacity: 0, y: 20 }}
            className="fixed inset-0 z-40 bg-white/95 dark:bg-gray-900/95 backdrop-blur-md flex flex-col"
          >
            <div className="flex justify-between items-center p-6 border-b border-gray-200 dark:border-gray-800">
              <h2 className="text-xl font-bold">Menü</h2>
              <Button
                variant="ghost"
                size="icon"
                onClick={() => setIsExpanded(false)}
                className="rounded-full"
              >
                <X className="h-5 w-5" />
              </Button>
            </div>
            <div className="flex-1 overflow-auto p-6">
              <div className="grid grid-cols-2 gap-4">
                {navItems.map((item) => (
                  <motion.button
                    key={item.path}
                    whileTap={{ scale: 0.95 }}
                    onClick={() => handleNavigation(item.path)}
                    className={cn(
                      "flex flex-col items-center justify-center p-4 rounded-xl border transition-all",
                      currentPath === item.path
                        ? "border-blue-200 bg-blue-50 dark:border-blue-800 dark:bg-blue-900/20 text-blue-600 dark:text-blue-400"
                        : "border-gray-200 dark:border-gray-800 hover:bg-gray-50 dark:hover:bg-gray-800/50"
                    )}
                  >
                    <div
                      className={cn(
                        "p-3 rounded-full mb-2",
                        currentPath === item.path
                          ? "bg-blue-100 dark:bg-blue-900/30"
                          : "bg-gray-100 dark:bg-gray-800"
                      )}
                    >
                      {item.icon}
                    </div>
                    <span className="font-medium">{item.label}</span>
                  </motion.button>
                ))}
              </div>
            </div>
          </motion.div>
        )}
      </>
    );
  }

  // Desktop Sidebar
  return (
    <motion.div
      initial={false}
      animate={{ width: isExpanded ? 240 : 80 }}
      className={cn(
        "fixed left-0 top-0 bottom-0 z-30 h-full bg-white/80 dark:bg-gray-900/80 backdrop-blur-lg border-r border-gray-200 dark:border-gray-800 shadow-lg flex flex-col",
        isExpanded ? "items-start" : "items-center"
      )}
    >
      <div className="w-full flex items-center justify-center p-4 h-16 border-b border-gray-200 dark:border-gray-800">
        <motion.div
          initial={false}
          animate={{ rotate: isExpanded ? 180 : 0 }}
          className="absolute right-0 top-4 transform translate-x-1/2 bg-white dark:bg-gray-800 rounded-full border border-gray-200 dark:border-gray-700 shadow-md cursor-pointer"
          onClick={() => setIsExpanded(!isExpanded)}
        >
          <div className="p-1.5 text-gray-500 dark:text-gray-400">
            <Menu className="h-4 w-4" />
          </div>
        </motion.div>
        {isExpanded && (
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ delay: 0.1 }}
            className="flex items-center"
          >
            <div className="w-8 h-8 bg-gradient-to-br from-blue-500 to-indigo-600 rounded-lg flex items-center justify-center text-white font-bold mr-2">
              SC
            </div>
            <span className="font-bold text-lg">SportClub</span>
          </motion.div>
        )}
        {!isExpanded && (
          <div className="w-10 h-10 bg-gradient-to-br from-blue-500 to-indigo-600 rounded-lg flex items-center justify-center text-white font-bold">
            SC
          </div>
        )}
      </div>

      <div className="flex-1 w-full py-6 overflow-y-auto">
        <ul className="space-y-2 px-3">
          {navItems.map((item) => (
            <li key={item.path}>
              <button
                onClick={() => handleNavigation(item.path)}
                className={cn(
                  "w-full flex items-center rounded-xl transition-all relative",
                  isExpanded ? "px-4 py-3" : "px-0 py-3 justify-center",
                  currentPath === item.path
                    ? "text-blue-600 dark:text-blue-400"
                    : "text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-300"
                )}
              >
                <div
                  className={cn(
                    "flex items-center justify-center rounded-lg transition-all",
                    isExpanded ? "mr-3" : ""
                  )}
                >
                  {item.icon}
                </div>
                {isExpanded && (
                  <motion.span
                    initial={{ opacity: 0 }}
                    animate={{ opacity: 1 }}
                    transition={{ delay: 0.1 }}
                    className="font-medium"
                  >
                    {item.label}
                  </motion.span>
                )}
                {currentPath === item.path && (
                  <motion.div
                    layoutId="sidebar-indicator"
                    className={cn(
                      "absolute right-0 w-1 h-6 bg-blue-600 dark:bg-blue-400 rounded-full",
                      isExpanded
                        ? "right-1"
                        : "right-0 left-0 mx-auto w-8 h-1 bottom-0"
                    )}
                    transition={{ type: "spring", bounce: 0.2, duration: 0.6 }}
                  />
                )}
              </button>
            </li>
          ))}
        </ul>
      </div>

      <div className="mt-auto p-4 w-full border-t border-gray-200 dark:border-gray-800">
        <button
          className={cn(
            "w-full flex items-center rounded-xl transition-all",
            isExpanded ? "px-4 py-3" : "px-0 py-3 justify-center",
            "text-gray-500 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-300"
          )}
        >
          <div
            className={cn(
              "flex items-center justify-center rounded-lg transition-all",
              isExpanded ? "mr-3" : ""
            )}
          >
            <Settings className="h-5 w-5" />
          </div>
          {isExpanded && (
            <motion.span
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              transition={{ delay: 0.1 }}
              className="font-medium"
            >
              Einstellungen
            </motion.span>
          )}
        </button>
      </div>
    </motion.div>
  );
};

export default AppDock;
