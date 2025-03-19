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
import { AlertTriangle, Shield, Trash2 } from "lucide-react";
import type { Spieler } from "../SpielerList";

interface SpielerDeleteDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  spieler: Spieler;
  onConfirm: () => void;
}

const SpielerDeleteDialog = ({
  open,
  onOpenChange,
  spieler,
  onConfirm,
}: SpielerDeleteDialogProps) => {
  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="bg-white/90 dark:bg-gray-900/90 backdrop-blur-xl border border-white/20 dark:border-gray-800/50 shadow-2xl">
        <DialogHeader>
          <DialogTitle className="text-xl font-bold flex items-center">
            <div className="bg-red-100 dark:bg-red-900/30 p-2 rounded-lg mr-3">
              <AlertTriangle className="h-5 w-5 text-red-600 dark:text-red-400" />
            </div>
            Spieler löschen
          </DialogTitle>
          <DialogDescription>
            Sind Sie sicher, dass Sie diesen Spieler löschen möchten? Diese
            Aktion kann nicht rückgängig gemacht werden.
          </DialogDescription>
        </DialogHeader>
        <div className="py-4">
          <div className="bg-red-50 dark:bg-red-900/20 border border-red-100 dark:border-red-800/30 rounded-lg p-4">
            <p className="text-red-800 dark:text-red-300 font-medium">
              Sie sind dabei, den folgenden Spieler zu löschen:
            </p>
            <div className="mt-2 flex items-center gap-3">
              <Shield className="h-5 w-5 text-red-600 dark:text-red-400" />
              <span className="font-bold">
                {spieler.vorname} {spieler.nachname}
              </span>
              <span className="text-gray-500 dark:text-gray-400">
                (Typ: {spieler.typ})
              </span>
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

export default SpielerDeleteDialog;
