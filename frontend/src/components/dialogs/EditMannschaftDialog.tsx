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
import { Input } from "../ui/input";
import { Label } from "../ui/label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../ui/select";
import { CheckCircle, Edit } from "lucide-react";
import type { MannschaftDTO } from "../../types/MannschaftDTO";

type EditMannschaftDialogProps = {
  isOpen: boolean;
  onOpenChange: (open: boolean) => void;
  currentMannschaft: MannschaftDTO;
  setCurrentMannschaft: (mannschaft: MannschaftDTO) => void;
  onUpdate: () => void;
};

const EditMannschaftDialog: React.FC<EditMannschaftDialogProps> = ({
  isOpen,
  onOpenChange,
  currentMannschaft,
  setCurrentMannschaft,
  onUpdate,
}) => {
  return (
    <Dialog open={isOpen} onOpenChange={onOpenChange}>
      <DialogContent className="bg-slate-900/95 backdrop-blur-xl border border-slate-700/50 shadow-2xl">
        <DialogHeader>
          <DialogTitle className="text-xl font-bold flex items-center text-slate-200">
            <div className="bg-indigo-900/50 p-2 rounded-lg mr-3">
              <Edit className="h-5 w-5 text-indigo-400" />
            </div>
            Mannschaft bearbeiten
          </DialogTitle>
          <DialogDescription className="text-slate-400">
            Bearbeiten Sie die Informationen der Mannschaft.
          </DialogDescription>
        </DialogHeader>
        <div className="grid gap-4 py-4">
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="edit-name" className="text-right text-slate-300">
              Name
            </Label>
            <Input
              id="edit-name"
              value={currentMannschaft.name}
              onChange={(e) =>
                setCurrentMannschaft({
                  ...currentMannschaft,
                  name: e.target.value,
                })
              }
              className="col-span-3 border-slate-700 bg-slate-800/50 text-slate-200 placeholder:text-slate-500 focus-visible:ring-indigo-500/30"
            />
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="edit-trainer" className="text-right text-slate-300">
              Trainer
            </Label>
            <Input
              id="edit-trainer"
              value={currentMannschaft.trainer}
              onChange={(e) =>
                setCurrentMannschaft({
                  ...currentMannschaft,
                  trainer: e.target.value,
                })
              }
              className="col-span-3 border-slate-700 bg-slate-800/50 text-slate-200 placeholder:text-slate-500 focus-visible:ring-indigo-500/30"
            />
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label
              htmlFor="edit-formation"
              className="text-right text-slate-300"
            >
              Formation
            </Label>
            <Select
              value={currentMannschaft.formation}
              onValueChange={(value) =>
                setCurrentMannschaft({ ...currentMannschaft, formation: value })
              }
            >
              <SelectTrigger
                id="edit-formation"
                className="col-span-3 border-slate-700 bg-slate-800/50 text-slate-200"
              >
                <SelectValue placeholder="Formation auswählen" />
              </SelectTrigger>
              <SelectContent className="bg-slate-800/95 backdrop-blur-md border-slate-700 text-slate-200">
                <SelectItem value="f442">4-4-2</SelectItem>
                <SelectItem value="f433">4-3-3</SelectItem>
                <SelectItem value="f352">3-5-2</SelectItem>
                <SelectItem value="f343">3-4-3</SelectItem>
                <SelectItem value="f532">5-3-2</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="edit-liga" className="text-right text-slate-300">
              Liga
            </Label>
            <Select
              value={currentMannschaft.liga}
              onValueChange={(value) =>
                setCurrentMannschaft({ ...currentMannschaft, liga: value })
              }
            >
              <SelectTrigger
                id="edit-liga"
                className="col-span-3 border-slate-700 bg-slate-800/50 text-slate-200"
              >
                <SelectValue placeholder="Liga auswählen" />
              </SelectTrigger>
              <SelectContent className="bg-slate-800/95 backdrop-blur-md border-slate-700 text-slate-200">
                <SelectItem value="Bundesliga1">Bundesliga</SelectItem>
                <SelectItem value="Bundesliga2">2. Bundesliga</SelectItem>
                <SelectItem value="Bundesliga3">3. Liga</SelectItem>
              </SelectContent>
            </Select>
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
            onClick={onUpdate}
            className="bg-indigo-600 hover:bg-indigo-500 text-white border-0"
          >
            <CheckCircle className="h-4 w-4 mr-2" />
            Aktualisieren
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};

export default EditMannschaftDialog;
