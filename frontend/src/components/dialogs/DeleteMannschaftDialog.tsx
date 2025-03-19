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
import { AlertTriangle, Trash2, Shield } from "lucide-react";
import type { MannschaftDTO } from "../../types/MannschaftDTO";

type DeleteMannschaftDialogProps = {
  isOpen: boolean;
  onOpenChange: (open: boolean) => void;
  currentMannschaft: MannschaftDTO;
  onDelete: () => void;
};

const DeleteMannschaftDialog: React.FC<DeleteMannschaftDialogProps> = ({
  isOpen,
  onOpenChange,
  currentMannschaft,
  onDelete,
}) => {
  return (
    <Dialog open={isOpen} onOpenChange={onOpenChange}>
      <DialogContent className="bg-slate-900/95 backdrop-blur-xl border border-slate-700/50 shadow-2xl">
        <DialogHeader>
          <DialogTitle className="text-xl font-bold flex items-center text-slate-200">
            <div className="bg-rose-900/50 p-2 rounded-lg mr-3">
              <AlertTriangle className="h-5 w-5 text-rose-400" />
            </div>
            Mannschaft löschen
          </DialogTitle>
          <DialogDescription className="text-slate-400">
            Sind Sie sicher, dass Sie diese Mannschaft löschen möchten? Diese
            Aktion kann nicht rückgängig gemacht werden.
          </DialogDescription>
        </DialogHeader>
        <div className="py-4">
          <div className="bg-slate-800/70 border border-slate-700/50 rounded-lg p-4 text-slate-300">
            <p className="font-medium text-lg text-slate-200">
              {currentMannschaft.name}
            </p>
            <p className="text-sm text-slate-400 mt-1">
              Trainer: {currentMannschaft.trainer}
            </p>
          </div>
        </div>
        <DialogFooter>
          <Button
            variant="outline"
            onClick={() => onOpenChange(false)}
            className="border-slate-700 text-slate-300 hover:bg-slate-800 hover:text-slate-200"
          >
            Abbrechen
          </Button>
          <Button
            onClick={onDelete}
            variant="destructive"
            className="bg-rose-600 hover:bg-rose-500 text-white border-0"
          >
            <Trash2 className="h-4 w-4 mr-2" />
            Löschen
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};

export default DeleteMannschaftDialog;
