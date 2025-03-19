"use client";
import React from "react";
import { motion } from "framer-motion";
import { Button } from "../ui/button";
import { Plus, Calendar } from "lucide-react";

interface SpielplanHeaderProps {
  onAddSpiel: () => void;
}

const SpielplanHeader = ({ onAddSpiel }: SpielplanHeaderProps) => {
  return (
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
                  <Calendar className="h-8 w-8 text-white" />
                </div>
              </div>
              <h1 className="text-3xl font-bold tracking-tight text-white">
                Spielplan
              </h1>
            </motion.div>
            <motion.p
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5, delay: 0.1 }}
              className="text-white/80 max-w-2xl"
            >
              Verwalten Sie den Spielplan, planen Sie neue Spiele und behalten
              Sie den Überblick über alle Begegnungen.
            </motion.p>
          </div>

          <motion.div
            initial={{ opacity: 0, scale: 0.9 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.5 }}
          >
            <Button
              onClick={onAddSpiel}
              className="bg-gradient-to-r from-green-500 to-emerald-600 hover:from-green-600 hover:to-emerald-700 text-white border-0 shadow-lg relative overflow-hidden group"
            >
              <div className="absolute inset-0 bg-white/20 opacity-0 group-hover:opacity-20 transition-opacity"></div>
              <Plus className="h-4 w-4 mr-2" />
              Neues Spiel
            </Button>
          </motion.div>
        </div>
      </div>
    </div>
  );
};

export default SpielplanHeader;
