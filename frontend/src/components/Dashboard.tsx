"use client";

import React from "react";
import { motion } from "framer-motion";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "../components/ui/card";
import {
  Trophy,
  Users,
  Calendar,
  BarChart3,
  TrendingUp,
  Activity,
} from "lucide-react";
import ThemeToggle from "./theme-toggle";

const Dashboard = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-indigo-50 via-blue-50 to-sky-50 dark:from-gray-900 dark:via-indigo-950 dark:to-blue-950 p-4 md:p-8 overflow-hidden relative">
      {/* Hintergrund-Elemente */}
      <div className="absolute top-0 left-0 w-full h-full overflow-hidden z-0">
        <div className="absolute top-10 left-10 w-64 h-64 bg-purple-400/20 dark:bg-purple-600/10 rounded-full blur-3xl"></div>
        <div className="absolute bottom-10 right-10 w-80 h-80 bg-blue-400/20 dark:bg-blue-600/10 rounded-full blur-3xl"></div>
        <div className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-96 h-96 bg-sky-400/10 dark:bg-sky-600/5 rounded-full blur-3xl"></div>
      </div>

      <div className="absolute top-4 right-4 z-50">
        <ThemeToggle />
      </div>

      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="container mx-auto max-w-7xl relative z-10"
      >
        <div className="backdrop-blur-xl bg-white/70 dark:bg-gray-900/70 rounded-2xl shadow-2xl overflow-hidden border border-white/20 dark:border-gray-800/50">
          {/* Header */}
          <div className="relative overflow-hidden">
            {/* Hintergrund-Elemente */}
            <div className="absolute inset-0 bg-gradient-to-r from-blue-600 via-indigo-600 to-violet-600"></div>
            <div className="absolute inset-0 bg-[url('/placeholder.svg?height=200&width=200')] opacity-5 bg-repeat"></div>
            <div className="absolute top-0 right-0 w-96 h-96 bg-white/10 rounded-full -translate-y-1/2 translate-x-1/2 blur-3xl"></div>
            <div className="absolute bottom-0 left-0 w-64 h-64 bg-indigo-500/20 rounded-full translate-y-1/2 -translate-x-1/2 blur-3xl"></div>

            {/* Inhalt */}
            <div className="relative z-10 px-8 py-10">
              <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-8">
                <div className="flex-1">
                  <motion.div
                    initial={{ opacity: 0, y: -20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5 }}
                    className="flex items-center gap-4 mb-6"
                  >
                    <div className="relative w-12 h-12">
                      <div className="absolute inset-0 bg-white rounded-full blur-md opacity-20"></div>
                      <div className="relative flex items-center justify-center w-full h-full">
                        <Activity className="h-8 w-8 text-white" />
                      </div>
                    </div>
                    <h1 className="text-3xl font-bold tracking-tight text-white">
                      Dashboard
                    </h1>
                  </motion.div>
                  <motion.p
                    initial={{ opacity: 0, y: 20 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 0.5, delay: 0.1 }}
                    className="text-white/80 max-w-2xl"
                  >
                    Willkommen bei der Sportclub-Verwaltung. Hier finden Sie
                    eine Übersicht aller wichtigen Informationen.
                  </motion.p>
                </div>
              </div>
            </div>
          </div>

          {/* Dashboard-Inhalt */}
          <div className="p-6 md:p-8">
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
              {/* Statistik-Karten */}
              <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5, delay: 0.1 }}
              >
                <Card className="border-0 shadow-lg bg-white/80 dark:bg-gray-800/80 backdrop-blur-md">
                  <CardHeader className="pb-2">
                    <CardTitle className="text-lg font-medium flex items-center">
                      <div className="bg-blue-100 dark:bg-blue-900/30 p-2 rounded-lg mr-3">
                        <Trophy className="h-5 w-5 text-blue-600 dark:text-blue-400" />
                      </div>
                      Mannschaften
                    </CardTitle>
                    <CardDescription>Gesamtanzahl der Teams</CardDescription>
                  </CardHeader>
                  <CardContent>
                    <div className="text-3xl font-bold">12</div>
                    <div className="text-sm text-green-600 dark:text-green-400 flex items-center mt-1">
                      <TrendingUp className="h-4 w-4 mr-1" /> +2 im letzten
                      Monat
                    </div>
                  </CardContent>
                </Card>
              </motion.div>

              <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5, delay: 0.2 }}
              >
                <Card className="border-0 shadow-lg bg-white/80 dark:bg-gray-800/80 backdrop-blur-md">
                  <CardHeader className="pb-2">
                    <CardTitle className="text-lg font-medium flex items-center">
                      <div className="bg-indigo-100 dark:bg-indigo-900/30 p-2 rounded-lg mr-3">
                        <Users className="h-5 w-5 text-indigo-600 dark:text-indigo-400" />
                      </div>
                      Spieler
                    </CardTitle>
                    <CardDescription>Registrierte Spieler</CardDescription>
                  </CardHeader>
                  <CardContent>
                    <div className="text-3xl font-bold">187</div>
                    <div className="text-sm text-green-600 dark:text-green-400 flex items-center mt-1">
                      <TrendingUp className="h-4 w-4 mr-1" /> +15 im letzten
                      Monat
                    </div>
                  </CardContent>
                </Card>
              </motion.div>

              <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5, delay: 0.3 }}
              >
                <Card className="border-0 shadow-lg bg-white/80 dark:bg-gray-800/80 backdrop-blur-md">
                  <CardHeader className="pb-2">
                    <CardTitle className="text-lg font-medium flex items-center">
                      <div className="bg-green-100 dark:bg-green-900/30 p-2 rounded-lg mr-3">
                        <Calendar className="h-5 w-5 text-green-600 dark:text-green-400" />
                      </div>
                      Spiele
                    </CardTitle>
                    <CardDescription>Anstehende Spiele</CardDescription>
                  </CardHeader>
                  <CardContent>
                    <div className="text-3xl font-bold">8</div>
                    <div className="text-sm text-gray-500 dark:text-gray-400 mt-1">
                      Nächstes Spiel in 3 Tagen
                    </div>
                  </CardContent>
                </Card>
              </motion.div>

              <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5, delay: 0.4 }}
              >
                <Card className="border-0 shadow-lg bg-white/80 dark:bg-gray-800/80 backdrop-blur-md">
                  <CardHeader className="pb-2">
                    <CardTitle className="text-lg font-medium flex items-center">
                      <div className="bg-purple-100 dark:bg-purple-900/30 p-2 rounded-lg mr-3">
                        <BarChart3 className="h-5 w-5 text-purple-600 dark:text-purple-400" />
                      </div>
                      Statistiken
                    </CardTitle>
                    <CardDescription>Leistungsübersicht</CardDescription>
                  </CardHeader>
                  <CardContent>
                    <div className="text-3xl font-bold">76%</div>
                    <div className="text-sm text-green-600 dark:text-green-400 flex items-center mt-1">
                      <TrendingUp className="h-4 w-4 mr-1" /> +5% zum Vormonat
                    </div>
                  </CardContent>
                </Card>
              </motion.div>
            </div>

            {/* Weitere Dashboard-Inhalte hier */}
            <div className="mt-8 grid grid-cols-1 lg:grid-cols-3 gap-6">
              <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5, delay: 0.5 }}
                className="lg:col-span-2"
              >
                <Card className="border-0 shadow-lg bg-white/80 dark:bg-gray-800/80 backdrop-blur-md h-full">
                  <CardHeader>
                    <CardTitle>Letzte Aktivitäten</CardTitle>
                    <CardDescription>
                      Übersicht der letzten Änderungen
                    </CardDescription>
                  </CardHeader>
                  <CardContent>
                    <div className="space-y-4">
                      {[
                        {
                          action: "Neuer Spieler hinzugefügt",
                          user: "Admin",
                          time: "Vor 2 Stunden",
                        },
                        {
                          action: "Mannschaft aktualisiert",
                          user: "Trainer",
                          time: "Vor 5 Stunden",
                        },
                        {
                          action: "Spielplan geändert",
                          user: "Manager",
                          time: "Vor 1 Tag",
                        },
                        {
                          action: "Statistiken aktualisiert",
                          user: "System",
                          time: "Vor 2 Tagen",
                        },
                      ].map((activity, index) => (
                        <div
                          key={index}
                          className="flex items-center justify-between p-3 rounded-lg bg-white/50 dark:bg-gray-800/50 border border-gray-100 dark:border-gray-700"
                        >
                          <div className="flex items-center">
                            <div className="w-8 h-8 rounded-full bg-blue-100 dark:bg-blue-900/30 flex items-center justify-center mr-3">
                              <Activity className="h-4 w-4 text-blue-600 dark:text-blue-400" />
                            </div>
                            <div>
                              <div className="font-medium">
                                {activity.action}
                              </div>
                              <div className="text-xs text-gray-500 dark:text-gray-400">
                                Durch: {activity.user}
                              </div>
                            </div>
                          </div>
                          <div className="text-sm text-gray-500 dark:text-gray-400">
                            {activity.time}
                          </div>
                        </div>
                      ))}
                    </div>
                  </CardContent>
                </Card>
              </motion.div>

              <motion.div
                initial={{ opacity: 0, y: 20 }}
                animate={{ opacity: 1, y: 0 }}
                transition={{ duration: 0.5, delay: 0.6 }}
              >
                <Card className="border-0 shadow-lg bg-white/80 dark:bg-gray-800/80 backdrop-blur-md h-full">
                  <CardHeader>
                    <CardTitle>Schnellzugriff</CardTitle>
                    <CardDescription>
                      Häufig verwendete Funktionen
                    </CardDescription>
                  </CardHeader>
                  <CardContent>
                    <div className="space-y-3">
                      {[
                        {
                          icon: <Users className="h-4 w-4" />,
                          label: "Spieler hinzufügen",
                          path: "/spieler",
                        },
                        {
                          icon: <Trophy className="h-4 w-4" />,
                          label: "Mannschaft bearbeiten",
                          path: "/mannschaften",
                        },
                        {
                          icon: <Calendar className="h-4 w-4" />,
                          label: "Spielplan ansehen",
                          path: "/spielplan",
                        },
                        {
                          icon: <BarChart3 className="h-4 w-4" />,
                          label: "Statistiken",
                          path: "/statistiken",
                        },
                      ].map((item, index) => (
                        <button
                          key={index}
                          className="w-full flex items-center p-3 rounded-lg bg-white/50 dark:bg-gray-800/50 border border-gray-100 dark:border-gray-700 hover:bg-blue-50 dark:hover:bg-blue-900/20 transition-colors"
                          onClick={() => (window.location.href = item.path)}
                        >
                          <div className="w-8 h-8 rounded-full bg-blue-100 dark:bg-blue-900/30 flex items-center justify-center mr-3">
                            {item.icon}
                          </div>
                          <span className="font-medium">{item.label}</span>
                        </button>
                      ))}
                    </div>
                  </CardContent>
                </Card>
              </motion.div>
            </div>
          </div>
        </div>
      </motion.div>
    </div>
  );
};

export default Dashboard;
