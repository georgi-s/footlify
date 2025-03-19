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
import { CheckCircle, Plus } from "lucide-react";
import type { Spieler, SpielerTyp } from "../SpielerList";

interface SpielerCreateDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  onSubmit: (spieler: Omit<Spieler, "id">) => void;
}

const SpielerCreateDialog = ({
  open,
  onOpenChange,
  onSubmit,
}: SpielerCreateDialogProps) => {
  const [formData, setFormData] = useState({
    vorname: "",
    nachname: "",
    geburtsdatum: "",
    typ: "Mittelfeld" as SpielerTyp,
    spieleGespielt: 0,
    gelbeKarten: 0,
    roteKarten: 0,
    // Spezifische Felder
    gehalteneSchuesse: 0,
    zuNullSpiele: 0,
    tacklingQuote: 0,
    abgefangenePassse: 0,
    passquote: 0,
    vorlagen: 0,
    tore: 0,
    schuesse: 0,
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value, type } = e.target;
    setFormData({
      ...formData,
      [name]: type === "number" ? Number.parseInt(value) || 0 : value,
    });
  };

  const handleSelectChange = (value: string) => {
    setFormData({
      ...formData,
      typ: value as SpielerTyp,
    });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    // Erstelle Spieler basierend auf Typ
    let spieler: Omit<Spieler, "id">;

    switch (formData.typ) {
      case "Torwart":
        spieler = {
          vorname: formData.vorname,
          nachname: formData.nachname,
          geburtsdatum: formData.geburtsdatum,
          typ: "Torwart",
          spieleGespielt: formData.spieleGespielt,
          gelbeKarten: formData.gelbeKarten,
          roteKarten: formData.roteKarten,
          gehalteneSchuesse: formData.gehalteneSchuesse,
          zuNullSpiele: formData.zuNullSpiele,
        };
        break;
      case "Abwehr":
        spieler = {
          vorname: formData.vorname,
          nachname: formData.nachname,
          geburtsdatum: formData.geburtsdatum,
          typ: "Abwehr",
          spieleGespielt: formData.spieleGespielt,
          gelbeKarten: formData.gelbeKarten,
          roteKarten: formData.roteKarten,
          tacklingQuote: formData.tacklingQuote,
          abgefangenePassse: formData.abgefangenePassse,
        };
        break;
      case "Mittelfeld":
        spieler = {
          vorname: formData.vorname,
          nachname: formData.nachname,
          geburtsdatum: formData.geburtsdatum,
          typ: "Mittelfeld",
          spieleGespielt: formData.spieleGespielt,
          gelbeKarten: formData.gelbeKarten,
          roteKarten: formData.roteKarten,
          passquote: formData.passquote,
          vorlagen: formData.vorlagen,
        };
        break;
      case "Sturm":
        spieler = {
          vorname: formData.vorname,
          nachname: formData.nachname,
          geburtsdatum: formData.geburtsdatum,
          typ: "Sturm",
          spieleGespielt: formData.spieleGespielt,
          gelbeKarten: formData.gelbeKarten,
          roteKarten: formData.roteKarten,
          tore: formData.tore,
          schuesse: formData.schuesse,
        };
        break;
      default:
        return;
    }

    onSubmit(spieler);

    // Formular zurücksetzen
    setFormData({
      vorname: "",
      nachname: "",
      geburtsdatum: "",
      typ: "Mittelfeld",
      spieleGespielt: 0,
      gelbeKarten: 0,
      roteKarten: 0,
      gehalteneSchuesse: 0,
      zuNullSpiele: 0,
      tacklingQuote: 0,
      abgefangenePassse: 0,
      passquote: 0,
      vorlagen: 0,
      tore: 0,
      schuesse: 0,
    });
  };

  // Rendere spezifische Felder basierend auf Spielertyp
  const renderSpecificFields = () => {
    switch (formData.typ) {
      case "Torwart":
        return (
          <>
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label>Gehaltene Schüsse</Label>
                <Input
                  type="number"
                  name="gehalteneSchuesse"
                  value={formData.gehalteneSchuesse}
                  onChange={handleInputChange}
                  min="0"
                />
              </div>
              <div className="space-y-2">
                <Label>Zu-Null-Spiele</Label>
                <Input
                  type="number"
                  name="zuNullSpiele"
                  value={formData.zuNullSpiele}
                  onChange={handleInputChange}
                  min="0"
                />
              </div>
            </div>
          </>
        );
      case "Abwehr":
        return (
          <>
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label>Tackling-Quote (%)</Label>
                <Input
                  type="number"
                  name="tacklingQuote"
                  value={formData.tacklingQuote}
                  onChange={handleInputChange}
                  min="0"
                  max="100"
                  step="0.1"
                />
              </div>
              <div className="space-y-2">
                <Label>Abgefangene Pässe</Label>
                <Input
                  type="number"
                  name="abgefangenePassse"
                  value={formData.abgefangenePassse}
                  onChange={handleInputChange}
                  min="0"
                />
              </div>
            </div>
          </>
        );
      case "Mittelfeld":
        return (
          <>
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label>Passquote (%)</Label>
                <Input
                  type="number"
                  name="passquote"
                  value={formData.passquote}
                  onChange={handleInputChange}
                  min="0"
                  max="100"
                  step="0.1"
                />
              </div>
              <div className="space-y-2">
                <Label>Vorlagen</Label>
                <Input
                  type="number"
                  name="vorlagen"
                  value={formData.vorlagen}
                  onChange={handleInputChange}
                  min="0"
                />
              </div>
            </div>
          </>
        );
      case "Sturm":
        return (
          <>
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label>Tore</Label>
                <Input
                  type="number"
                  name="tore"
                  value={formData.tore}
                  onChange={handleInputChange}
                  min="0"
                />
              </div>
              <div className="space-y-2">
                <Label>Schüsse</Label>
                <Input
                  type="number"
                  name="schuesse"
                  value={formData.schuesse}
                  onChange={handleInputChange}
                  min="0"
                />
              </div>
            </div>
          </>
        );
      default:
        return null;
    }
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="bg-white/90 dark:bg-gray-900/90 backdrop-blur-xl border border-white/20 dark:border-gray-800/50 shadow-2xl">
        <DialogHeader>
          <DialogTitle className="text-xl font-bold flex items-center">
            <div className="bg-green-100 dark:bg-green-900/30 p-2 rounded-lg mr-3">
              <Plus className="h-5 w-5 text-green-600 dark:text-green-400" />
            </div>
            Neuen Spieler erstellen
          </DialogTitle>
          <DialogDescription>
            Füllen Sie die Felder aus, um einen neuen Spieler zu erstellen.
          </DialogDescription>
        </DialogHeader>
        <form onSubmit={handleSubmit}>
          <div className="grid gap-6 py-4">
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label>Vorname</Label>
                <Input
                  name="vorname"
                  value={formData.vorname}
                  onChange={handleInputChange}
                  required
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
              <div className="space-y-2">
                <Label>Nachname</Label>
                <Input
                  name="nachname"
                  value={formData.nachname}
                  onChange={handleInputChange}
                  required
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label>Geburtsdatum</Label>
                <Input
                  type="date"
                  name="geburtsdatum"
                  value={formData.geburtsdatum}
                  onChange={handleInputChange}
                  required
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
              <div className="space-y-2">
                <Label>Spielertyp</Label>
                <Select value={formData.typ} onValueChange={handleSelectChange}>
                  <SelectTrigger className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50">
                    <SelectValue placeholder="Spielertyp auswählen" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="Torwart">Torwart</SelectItem>
                    <SelectItem value="Abwehr">Abwehr</SelectItem>
                    <SelectItem value="Mittelfeld">Mittelfeld</SelectItem>
                    <SelectItem value="Sturm">Sturm</SelectItem>
                  </SelectContent>
                </Select>
              </div>
            </div>

            <div className="grid grid-cols-3 gap-4">
              <div className="space-y-2">
                <Label>Spiele gespielt</Label>
                <Input
                  type="number"
                  name="spieleGespielt"
                  value={formData.spieleGespielt}
                  onChange={handleInputChange}
                  min="0"
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
              <div className="space-y-2">
                <Label>Gelbe Karten</Label>
                <Input
                  type="number"
                  name="gelbeKarten"
                  value={formData.gelbeKarten}
                  onChange={handleInputChange}
                  min="0"
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
              <div className="space-y-2">
                <Label>Rote Karten</Label>
                <Input
                  type="number"
                  name="roteKarten"
                  value={formData.roteKarten}
                  onChange={handleInputChange}
                  min="0"
                  className="border-white/20 dark:border-gray-700/50 bg-white/50 dark:bg-gray-900/50"
                />
              </div>
            </div>

            {/* Spezifische Felder basierend auf Spielertyp */}
            {renderSpecificFields()}
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

export default SpielerCreateDialog;
