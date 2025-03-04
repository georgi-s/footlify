"use client";

import { Team } from "../../src/types/team";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Separator } from "@/components/ui/separator";
import { Button } from "@/components/ui/button";
import { motion } from "framer-motion";
import { useState } from "react";

interface TeamDetailsProps {
  team: Team;
  onUpdateTeam: (team: Team) => void;
}

export default function TeamDetails({ team, onUpdateTeam }: TeamDetailsProps) {
  const [formData, setFormData] = useState({
    name: team.name,
    trainer: team.trainer,
    formation: team.formation,
    liga: team.liga,
  });

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onUpdateTeam({
      ...team,
      ...formData,
    });
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      transition={{ duration: 0.5 }}
    >
      <Card className="bg-white/80 dark:bg-gray-800/80 backdrop-blur-md border-white/50 dark:border-gray-700/50">
        <CardHeader>
          <CardTitle>Team Details</CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div className="space-y-2">
                <Label htmlFor="name">Team Name</Label>
                <Input
                  id="name"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                  className="bg-white/50 dark:bg-gray-900/50"
                />
              </div>
              <div className="space-y-2">
                <Label htmlFor="trainer">Trainer</Label>
                <Input
                  id="trainer"
                  name="trainer"
                  value={formData.trainer}
                  onChange={handleChange}
                  className="bg-white/50 dark:bg-gray-900/50"
                />
              </div>
            </div>

            <Separator />

            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div className="space-y-2">
                <Label htmlFor="formation">Formation</Label>
                <Input
                  id="formation"
                  name="formation"
                  value={formData.formation}
                  onChange={handleChange}
                  className="bg-white/50 dark:bg-gray-900/50"
                />
              </div>
              <div className="space-y-2">
                <Label htmlFor="liga">League</Label>
                <Input
                  id="liga"
                  name="liga"
                  value={formData.liga}
                  onChange={handleChange}
                  className="bg-white/50 dark:bg-gray-900/50"
                />
              </div>
            </div>

            <div className="flex justify-end">
              <Button
                type="submit"
                className="bg-gradient-to-r from-blue-500 to-indigo-600 text-white hover:from-blue-600 hover:to-indigo-700"
              >
                Save Changes
              </Button>
            </div>
          </form>
        </CardContent>
      </Card>
    </motion.div>
  );
}
