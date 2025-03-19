"use client";
import React from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "../ui/dialog";
import { Button } from "../ui/button";
import { AlertTriangle, Trash2 } from "lucide-react";
import type { Spiel } from "../SpielplanList";

interface SpielplanDeleteDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  spiel: Spiel;
  onConfirm: () => void;
}

const SpielplanDeleteDialog = ({
  open,
  onOpenChange,
  spiel,
  onConfirm,
}: SpielplanDeleteDialogProps) => {
  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="bg-white/90 dark:bg-gray-900/90 backdrop-blur-xl border border-white/20 dark:border-gray-800/50 shadow-2xl">
        <DialogHeader>
          <DialogTitle className="text-xl font-bold flex items-center">
            <div className="bg-red-100 dark:bg-red-900/30 p-2 rounded-lg mr-3">
              <AlertTriangle className="h-5 w-5 text-red-600 dark:text-red-400" />
            </div>
            Spiel löschen
          </DialogTitle>
          <DialogDescription>
            Sind Sie sicher, dass Sie dieses Spiel löschen möchten? Diese Aktion
            kann nicht rückgängig gemacht werden.
          </DialogDescription>
        </DialogHeader>
        <div className="py-4">
          <div className="bg-red-50 dark:bg-red-900/20 border border-red-100 dark:border-red-800/30 rounded-lg p-4">
            <p className="text-red-800 dark:text-red-300 font-medium">
              Sie sind dabei, das folgende Spiel zu löschen:
            </p>
            <div className="mt-2">
              <p className="font-bold">
                {spiel.heimMannschaft} vs. {spiel.gastMannschaft}
              </p>
              <p className="text-gray-500 dark:text-gray-400 text-sm">
                {new Date(spiel.datum).toLocaleDateString("de-DE")} um{" "}
                {spiel.uhrzeit} Uhr
              </p>
              <p className="text-gray-500 dark:text-gray-400 text-sm">
                Ort: {spiel.ort}
              </p>
            </div>
          </div>
        </div>
        <DialogFooter>
          <Button
            variant="outline"
            onClick={() => onOpenChange(false)}
            className="border-gray-200 dark:border-gray-700"
          >
            Abbrechen
          </Button>
          <Button
            onClick={onConfirm}
            variant="destructive"
            className="bg-gradient-to-r from-red-500 to-rose-600 hover:from-red-600 hover:to-rose-700 text-white border-0"
          >
            <Trash2 className="h-4 w-4 mr-2" />
            Löschen
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};

export default SpielplanDeleteDialog;
