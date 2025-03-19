import React from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
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
import type { MannschaftDTO } from "../../types/MannschaftDTO";

type CreateMannschaftDialogProps = {
  isOpen: boolean;
  onOpenChange: (open: boolean) => void;
  newMannschaft: Partial<MannschaftDTO>;
  setNewMannschaft: (mannschaft: Partial<MannschaftDTO>) => void;
  onCreate: () => void;
};

const CreateMannschaftDialog: React.FC<CreateMannschaftDialogProps> = ({
  isOpen,
  onOpenChange,
  newMannschaft,
  setNewMannschaft,
  onCreate,
}) => {
  return (
    <Dialog open={isOpen} onOpenChange={onOpenChange}>
      <DialogTrigger asChild>
        <Button className="bg-indigo-600 hover:bg-indigo-500 text-white border-0 shadow-lg relative overflow-hidden group">
          <div className="absolute inset-0 bg-white/20 opacity-0 group-hover:opacity-20 transition-opacity"></div>
          <Plus className="h-4 w-4 mr-2" />
          Neue Mannschaft
        </Button>
      </DialogTrigger>
      <DialogContent className="bg-slate-900/95 backdrop-blur-xl border border-slate-700/50 shadow-2xl">
        <DialogHeader>
          <DialogTitle className="text-xl font-bold flex items-center text-slate-200">
            <div className="bg-indigo-900/50 p-2 rounded-lg mr-3">
              <Plus className="h-5 w-5 text-indigo-400" />
            </div>
            Neue Mannschaft erstellen
          </DialogTitle>
          <DialogDescription className="text-slate-400">
            Füllen Sie die Felder aus, um eine neue Mannschaft zu erstellen.
          </DialogDescription>
        </DialogHeader>
        <div className="grid gap-4 py-4">
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="name" className="text-right text-slate-300">
              Name
            </Label>
            <Input
              id="name"
              value={newMannschaft.name}
              onChange={(e) =>
                setNewMannschaft({ ...newMannschaft, name: e.target.value })
              }
              className="col-span-3 border-slate-700 bg-slate-800/50 text-slate-200 placeholder:text-slate-500 focus-visible:ring-indigo-500/30"
            />
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="trainer" className="text-right text-slate-300">
              Trainer
            </Label>
            <Input
              id="trainer"
              value={newMannschaft.trainer}
              onChange={(e) =>
                setNewMannschaft({ ...newMannschaft, trainer: e.target.value })
              }
              className="col-span-3 border-slate-700 bg-slate-800/50 text-slate-200 placeholder:text-slate-500 focus-visible:ring-indigo-500/30"
            />
          </div>
          <div className="grid grid-cols-4 items-center gap-4">
            <Label htmlFor="formation" className="text-right text-slate-300">
              Formation
            </Label>
            <Select
              value={newMannschaft.formation}
              onValueChange={(value) =>
                setNewMannschaft({ ...newMannschaft, formation: value })
              }
            >
              <SelectTrigger
                id="formation"
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
            <Label htmlFor="liga" className="text-right text-slate-300">
              Liga
            </Label>
            <Select
              value={newMannschaft.liga}
              onValueChange={(value) =>
                setNewMannschaft({ ...newMannschaft, liga: value })
              }
            >
              <SelectTrigger
                id="liga"
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
            onClick={onCreate}
            className="bg-indigo-600 hover:bg-indigo-500 text-white border-0"
          >
            <CheckCircle className="h-4 w-4 mr-2" />
            Erstellen
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};

export default CreateMannschaftDialog;
