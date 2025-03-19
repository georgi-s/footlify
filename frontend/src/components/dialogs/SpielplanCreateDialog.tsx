"use client";

import React from "react";

import { useState } from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "../ui/dialog";
import { Button } from "../ui/button";
import { Input } from "../ui/input";
import { Label } from "../ui/label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../ui/select";
import {
  CheckCircle,
  Plus,
  Calendar,
  Clock,
  MapPin,
  Trophy,
} from "lucide-react";
import type { Spiel } from "../SpielplanList";

interface SpielplanCreateDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onSubmit: (spiel: Omit<Spiel, "id">) => void;
  mannschaften: { id: number; name: string }[];
  ligen: string[];
}

const SpielplanCreateDialog = ({
  open,
  onOpenChange,
  onSubmit,
  mannschaften,
  ligen,
}: SpielplanCreateDialogProps) => {
  const [formData, setFormData] = useState<Omit<Spiel, "id">>({
    heimMannschaft: "",
    heimMannschaftId: 0,
    gastMannschaft: "",
    gastMannschaftId: 0,
    datum: "",
    uhrzeit: "",
    ort: "",
    liga: "",
    status: "Geplant",
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleHeimMannschaftChange = (value: string) => {
    const id = Number(value);
    const mannschaft = mannschaften.find((m) => m.id === id);
    if (mannschaft) {
      setFormData({
        ...formData,
        heimMannschaftId: id,
        heimMannschaft: mannschaft.name,
      });
    }
  };

  const handleGastMannschaftChange = (value: string) => {
    const id = Number(value);
    const mannschaft = mannschaften.find((m) => m.id === id);
    if (mannschaft) {
      setFormData({
        ...formData,
        gastMannschaftId: id,
        gastMannschaft: mannschaft.name,
      });
    }
  };

  const handleLigaChange = (value: string) => {
    setFormData({
      ...formData,
      liga: value,
    });
  };

  const handleStatusChange = (value: string) => {
    setFormData({
      ...formData,
      status: value as Spiel["status"],
    });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(formData);

    // Formular zurücksetzen
    setFormData({
      heimMannschaft: "",
      heimMannschaftId: 0,
      gastMannschaft: "",
      gastMannschaftId: 0,
      datum: "",
      uhrzeit: "",
      ort: "",
      liga: "",
      status: "Geplant",
    });
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="bg-white/90 dark:bg-gray-900/90 backdrop-blur-xl border border-white/20 dark:border-gray-800/50 shadow-2xl">
        <DialogHeader>
          <DialogTitle className="text-xl font-bold flex items-center">
            <div className="bg-green-100 dark:bg-green-900/30 p-2 rounded-lg mr-3">
              <Plus className="h-5 w-5 text-green-600 dark:text-green-400" />
            </div>
            Neues Spiel erstellen
          </DialogTitle>
          <DialogDescription>
            Füllen Sie die Felder aus, um ein neues Spiel zu erstellen.
          </DialogDescription>
        </DialogHeader>
        <form onSubmit={handleSubmit}>
          <div className="grid gap-6 py-4">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label>Heimmannschaft</Label>
                <Select
                  value={formData.heimMannschaftId.toString()}
                  onValueChange={handleHeimMannschaftChange}
                >
                  <SelectTrigger className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50">
                    <SelectValue placeholder="Heimmannschaft auswählen" />
                  </SelectTrigger>
                  <SelectContent>
                    {mannschaften.map((mannschaft) => (
                      <SelectItem
                        key={mannschaft.id}
                        value={mannschaft.id.toString()}
                      >
                        {mannschaft.name}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div className="space-y-2">
                <Label>Gastmannschaft</Label>
                <Select
                  value={formData.gastMannschaftId.toString()}
                  onValueChange={handleGastMannschaftChange}
                >
                  <SelectTrigger className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50">
                    <SelectValue placeholder="Gastmannschaft auswählen" />
                  </SelectTrigger>
                  <SelectContent>
                    {mannschaften.map((mannschaft) => (
                      <SelectItem
                        key={mannschaft.id}
                        value={mannschaft.id.toString()}
                      >
                        {mannschaft.name}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label className="flex items-center">
                  <Calendar className="h-4 w-4 mr-1 text-gray-400" />
                  Datum
                </Label>
                <Input
                  type="date"
                  name="datum"
                  value={formData.datum}
                  onChange={handleInputChange}
                  required
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
              <div className="space-y-2">
                <Label className="flex items-center">
                  <Clock className="h-4 w-4 mr-1 text-gray-400" />
                  Uhrzeit
                </Label>
                <Input
                  type="time"
                  name="uhrzeit"
                  value={formData.uhrzeit}
                  onChange={handleInputChange}
                  required
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label className="flex items-center">
                  <MapPin className="h-4 w-4 mr-1 text-gray-400" />
                  Spielort
                </Label>
                <Input
                  name="ort"
                  value={formData.ort}
                  onChange={handleInputChange}
                  required
                  placeholder="z.B. Allianz Arena"
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
              <div className="space-y-2">
                <Label className="flex items-center">
                  <Trophy className="h-4 w-4 mr-1 text-gray-400" />
                  Liga
                </Label>
                <Select value={formData.liga} onValueChange={handleLigaChange}>
                  <SelectTrigger className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50">
                    <SelectValue placeholder="Liga auswählen" />
                  </SelectTrigger>
                  <SelectContent>
                    {ligen.map((liga) => (
                      <SelectItem key={liga} value={liga}>
                        {liga}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
            </div>

            <div className="space-y-2">
              <Label>Status</Label>
              <Select
                value={formData.status}
                onValueChange={handleStatusChange}
              >
                <SelectTrigger className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50">
                  <SelectValue placeholder="Status auswählen" />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="Geplant">Geplant</SelectItem>
                  <SelectItem value="Live">Live</SelectItem>
                  <SelectItem value="Beendet">Beendet</SelectItem>
                  <SelectItem value="Verschoben">Verschoben</SelectItem>
                  <SelectItem value="Abgesagt">Abgesagt</SelectItem>
                </SelectContent>
              </Select>
            </div>

            {formData.status === "Beendet" && (
              <div className="grid grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label>Tore Heimmannschaft</Label>
                  <Input
                    type="number"
                    name="heimTore"
                    value={formData.heimTore || ""}
                    onChange={handleInputChange}
                    min="0"
                    className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                  />
                </div>
                <div className="space-y-2">
                  <Label>Tore Gastmannschaft</Label>
                  <Input
                    type="number"
                    name="gastTore"
                    value={formData.gastTore || ""}
                    onChange={handleInputChange}
                    min="0"
                    className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                  />
                </div>
              </div>
            )}
          </div>

          <DialogFooter>
            <Button
              type="button"
              variant="outline"
              onClick={() => onOpenChange(false)}
              className="border-gray-200 dark:border-gray-700"
            >
              Abbrechen
            </Button>
            <Button
              type="submit"
              className="bg-gradient-to-r from-green-500 to-emerald-600 hover:from-green-600 hover:to-emerald-700 text-white border-0"
            >
              <CheckCircle className="h-4 w-4 mr-2" />
              Erstellen
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default SpielplanCreateDialog;
