"use client";

import React from "react";
import { motion } from "framer-motion";
import { Trophy, RefreshCw } from "lucide-react";
import { Button } from "../ui/button";

type MannschaftHeaderProps = {
  onRefresh: () => void;
};

const MannschaftHeader: React.FC<MannschaftHeaderProps> = ({ onRefresh }) => {
  return (
    <div className="relative overflow-hidden">
      <div className="absolute inset-0 bg-gradient-to-r from-indigo-600 via-indigo-500 to-violet-600"></div>
      <div className="absolute inset-0 bg-[url('/placeholder.svg?height=200&width=200')] opacity-5 bg-repeat"></div>
      <div className="absolute top-0 right-0 w-96 h-96 bg-indigo-400/10 rounded-full -translate-y-1/2 translate-x-1/2 blur-3xl"></div>
      <div className="absolute bottom-0 left-0 w-64 h-64 bg-violet-500/10 rounded-full translate-y-1/2 -translate-x-1/2 blur-3xl"></div>

      {/* Animated particles */}
      <div className="absolute inset-0 overflow-hidden">
        {[...Array(6)].map((_, i) => (
          <motion.div
            key={i}
            className="absolute w-2 h-2 bg-white/20 rounded-full"
            initial={{
              x: Math.random() * 100,
              y: Math.random() * 100,
              opacity: 0.2 + Math.random() * 0.3,
            }}
            animate={{
              x: Math.random() * 100 + 50,
              y: Math.random() * 100 + 50,
              opacity: 0.1 + Math.random() * 0.2,
            }}
            transition={{
              duration: 5 + Math.random() * 10,
              repeat: Number.POSITIVE_INFINITY,
              repeatType: "reverse",
              ease: "easeInOut",
            }}
            style={{
              top: `${Math.random() * 100}%`,
              left: `${Math.random() * 100}%`,
            }}
          />
        ))}
      </div>

      <div className="relative z-10 px-8 py-12">
        <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-8">
          <div className="flex-1">
            <motion.div
              initial={{ opacity: 0, y: -20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5 }}
              className="flex items-center gap-4 mb-6"
            >
              <div className="relative w-14 h-14">
                <div className="absolute inset-0 bg-white/20 rounded-full blur-md"></div>
                <div className="relative flex items-center justify-center w-full h-full bg-white/10 backdrop-blur-sm rounded-full border border-white/20">
                  <Trophy className="h-8 w-8 text-white" />
                </div>
              </div>
              <h1 className="text-3xl font-bold tracking-tight text-white">
                Mannschaftsverwaltung
              </h1>
            </motion.div>
            <motion.p
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.5, delay: 0.1 }}
              className="text-indigo-100/80 max-w-2xl text-lg"
            >
              Verwalten Sie Ihre Mannschaften, erstellen Sie neue Teams und
              bearbeiten Sie bestehende Einträge.
            </motion.p>
          </div>
          <motion.div
            initial={{ opacity: 0, scale: 0.9 }}
            animate={{ opacity: 1, scale: 1 }}
            transition={{ duration: 0.5 }}
            className="flex items-center gap-3"
          >
            <Button
              onClick={onRefresh}
              variant="outline"
              className="bg-white/10 border-white/20 text-white hover:bg-white/20 hover:text-white transition-colors group"
            >
              <RefreshCw className="h-4 w-4 mr-2 group-hover:rotate-180 transition-transform duration-500" />
              Refresh
            </Button>
          </motion.div>
        </div>
      </div>
    </div>
  );
};

export default MannschaftHeader;
