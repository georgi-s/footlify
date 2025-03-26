// "use client";
// import React from "react";
// import {
//   Table,
//   TableBody,
//   TableCell,
//   TableHead,
//   TableHeader,
//   TableRow,
// } from "../ui/table";
// import { Badge } from "../ui/badge";
// import { Button } from "../ui/button";
// import { Edit, Trash2, Loader2, AlertTriangle, User } from "lucide-react";
// import { motion } from "framer-motion";
// // Eigene Definition für Spieler

// interface SpielerTableProps {
//   spieler: any[]; // Angepasst um FrontendSpieler zu unterstützen
//   isLoading?: boolean; // Optional gemacht
//   onEdit?: (spieler: any) => void; // Optional gemacht
//   onDelete?: (spieler: any) => void; // Optional gemacht

//   // Zusätzliche Props für MannschaftManagementPanel
//   onPlayerAction?: (player: any) => Promise<void>;
//   actionType?: string;
//   sortConfig?: { key: string; direction: string };
//   setSortConfig?: React.Dispatch<
//     React.SetStateAction<{ key: string; direction: string }>
//   >;
//   buttonText?: string;
// }

// const SpielerTable = ({
//   spieler,
//   isLoading = false,
//   onEdit,
//   onDelete,
//   onPlayerAction,
//   actionType,
//   sortConfig,
//   setSortConfig,
//   buttonText,
// }: SpielerTableProps) => {
//   // Formatiere Datum
//   const formatDate = (dateString: string) => {
//     const date = new Date(dateString);
//     return new Intl.DateTimeFormat("de-DE").format(date);
//   };

//   // Spielertyp-Badge-Farbe
//   const getTypeBadgeColor = (typ: string) => {
//     switch (typ) {
//       case "Torwart":
//         return "bg-yellow-100 text-yellow-800 dark:bg-yellow-900/30 dark:text-yellow-300";
//       case "Abwehr":
//         return "bg-blue-100 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300";
//       case "Mittelfeld":
//         return "bg-green-100 text-green-800 dark:bg-green-900/30 dark:text-green-300";
//       case "Sturm":
//         return "bg-red-100 text-red-800 dark:bg-red-900/30 dark:text-red-300";
//       default:
//         return "bg-gray-100 text-gray-800 dark:bg-gray-900/30 dark:text-gray-300";
//     }
//   };

//   if (isLoading) {
//     return (
//       <div className="flex flex-col items-center justify-center py-16">
//         <div className="relative w-16 h-16 mb-4">
//           <div className="absolute inset-0 bg-blue-500 rounded-full blur-md opacity-50 animate-pulse"></div>
//           <div className="relative flex items-center justify-center w-full h-full">
//             <Loader2 className="h-8 w-8 animate-spin text-blue-600" />
//           </div>
//         </div>
//         <p className="text-lg font-medium text-gray-700 dark:text-gray-300">
//           Lade Spielerdaten...
//         </p>
//       </div>
//     );
//   }

//   if (spieler.length === 0) {
//     return (
//       <div className="flex flex-col items-center justify-center py-16 bg-white/50 dark:bg-gray-800/50 backdrop-blur-md rounded-xl border border-white/20 dark:border-gray-700/50">
//         <div className="relative w-16 h-16 mb-4">
//           <div className="absolute inset-0 bg-gray-200 dark:bg-gray-700 rounded-full blur-md opacity-50"></div>
//           <div className="relative flex items-center justify-center w-full h-full">
//             <AlertTriangle className="h-8 w-8 text-gray-400 dark:text-gray-500" />
//           </div>
//         </div>
//         <p className="text-lg font-medium text-gray-700 dark:text-gray-300">
//           Keine Spieler gefunden
//         </p>
//         <p className="text-gray-500 dark:text-gray-400 mt-2">
//           Erstellen Sie einen neuen Spieler, um zu beginnen.
//         </p>
//       </div>
//     );
//   }

//   return (
//     <div className="bg-white/80 dark:bg-gray-800/80 backdrop-blur-md rounded-xl border border-white/50 dark:border-gray-700/50 shadow-xl overflow-hidden">
//       <Table>
//         <TableHeader className="bg-white/30 dark:bg-gray-800/30 backdrop-blur-md">
//           <TableRow className="hover:bg-transparent border-b border-white/20 dark:border-gray-700/50">
//             <TableHead className="w-[50px] font-semibold">ID</TableHead>
//             <TableHead className="font-semibold">Name</TableHead>
//             <TableHead className="font-semibold">Geburtsdatum</TableHead>
//             <TableHead className="font-semibold">Typ</TableHead>
//             <TableHead className="text-right font-semibold">Aktionen</TableHead>
//           </TableRow>
//         </TableHeader>
//         <TableBody>
//           {spieler.map((spieler, index) => (
//             <motion.tr
//               key={spieler.id}
//               initial={{ opacity: 0, y: 10 }}
//               animate={{ opacity: 1, y: 0 }}
//               transition={{ duration: 0.3, delay: index * 0.05 }}
//               className="hover:bg-white/40 dark:hover:bg-gray-800/40 transition-colors border-b border-white/10 dark:border-gray-700/30"
//             >
//               <TableCell className="font-medium">
//                 <div className="relative w-10 h-10">
//                   <div className="absolute inset-0 bg-blue-500/10 dark:bg-blue-500/20 rounded-full blur-sm"></div>
//                   <div className="relative flex items-center justify-center w-full h-full bg-white/30 dark:bg-gray-800/30 backdrop-blur-sm rounded-full border border-white/30 dark:border-gray-700/30">
//                     {spieler.id}
//                   </div>
//                 </div>
//               </TableCell>
//               <TableCell>
//                 <div className="flex items-center gap-3">
//                   <div className="bg-indigo-100 dark:bg-indigo-900/30 w-8 h-8 rounded-full flex items-center justify-center">
//                     <User className="h-4 w-4 text-indigo-600 dark:text-indigo-400" />
//                   </div>
//                   <div>
//                     <div className="font-semibold">
//                       {spieler.vorname} {spieler.nachname}
//                     </div>
//                     <div className="text-xs text-gray-500 dark:text-gray-400">
//                       {spieler.spieleGespielt} Spiele
//                     </div>
//                   </div>
//                 </div>
//               </TableCell>
//               <TableCell>{formatDate(spieler.geburtsdatum)}</TableCell>
//               <TableCell>
//                 <Badge
//                   variant="outline"
//                   className={getTypeBadgeColor(spieler.typ)}
//                 >
//                   {spieler.typ}
//                 </Badge>
//               </TableCell>
//               <TableCell className="text-right">
//                 <div className="flex items-center justify-end gap-2">
//                   {onEdit && (
//                     <Button
//                       variant="outline"
//                       size="sm"
//                       onClick={() => onEdit(spieler)}
//                       className="bg-blue-100/50 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300 border-blue-200 dark:border-blue-800/30 hover:bg-blue-200/50 dark:hover:bg-blue-800/40"
//                     >
//                       <Edit className="h-4 w-4" />
//                     </Button>
//                   )}
//                   {onDelete && (
//                     <Button
//                       variant="outline"
//                       size="sm"
//                       onClick={() => onDelete(spieler)}
//                       className="bg-red-100/50 text-red-800 dark:bg-red-900/30 dark:text-red-300 border-red-200 dark:border-red-800/30 hover:bg-red-200/50 dark:hover:bg-red-800/40"
//                     >
//                       <Trash2 className="h-4 w-4" />
//                     </Button>
//                   )}
//                   {onPlayerAction && buttonText && (
//                     <Button
//                       variant="outline"
//                       size="sm"
//                       onClick={() => onPlayerAction(spieler)}
//                       className={
//                         actionType === "add"
//                           ? "bg-green-100/50 text-green-800 dark:bg-green-900/30 dark:text-green-300 border-green-200 dark:border-green-800/30 hover:bg-green-200/50 dark:hover:bg-green-800/40"
//                           : "bg-red-100/50 text-red-800 dark:bg-red-900/30 dark:text-red-300 border-red-200 dark:border-red-800/30 hover:bg-red-200/50 dark:hover:bg-red-800/40"
//                       }
//                     >
//                       {buttonText}
//                     </Button>
//                   )}
//                 </div>
//               </TableCell>
//             </motion.tr>
//           ))}
//         </TableBody>
//       </Table>
//     </div>
//   );
// };

// export default SpielerTable;
"use client";
import React from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "../ui/table";
import { Badge } from "../ui/badge";
import { Button } from "../ui/button";
import { Edit, Trash2, Loader2, AlertTriangle, User } from "lucide-react";
import { motion } from "framer-motion";
// Eigene Definition für Spieler

interface SpielerTableProps {
  spieler: any[]; // Angepasst um FrontendSpieler zu unterstützen
  isLoading?: boolean; // Optional gemacht
  onEdit?: (spieler: any) => void; // Optional gemacht
  onDelete?: (spieler: any) => void; // Optional gemacht

  // Zusätzliche Props für MannschaftManagementPanel
  onPlayerAction?: (player: any) => Promise<void>;
  actionType?: string;
  sortConfig?: { key: string; direction: string };
  setSortConfig?: React.Dispatch<
    React.SetStateAction<{ key: string; direction: string }>
  >;
  buttonText?: string;
}

const SpielerTable = ({
  spieler,
  isLoading = false,
  onEdit,
  onDelete,
  onPlayerAction,
  actionType,
  sortConfig,
  setSortConfig,
  buttonText,
}: SpielerTableProps) => {
  // Formatiere Datum
  const formatDate = (dateString: string | undefined | null) => {
    if (!dateString) return "N/A";

    try {
      const date = new Date(dateString);

      // Prüfen, ob das Datum gültig ist
      if (isNaN(date.getTime())) {
        return "Ungültiges Datum";
      }

      return new Intl.DateTimeFormat("de-DE").format(date);
    } catch (error) {
      console.error("Fehler beim Formatieren des Datums:", error);
      return "Fehler";
    }
  };

  // Spielertyp-Badge-Farbe
  const getTypeBadgeColor = (typ: string) => {
    switch (typ) {
      case "Torwart":
        return "bg-yellow-100 text-yellow-800 dark:bg-yellow-900/30 dark:text-yellow-300";
      case "Abwehr":
        return "bg-blue-100 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300";
      case "Mittelfeld":
        return "bg-green-100 text-green-800 dark:bg-green-900/30 dark:text-green-300";
      case "Sturm":
        return "bg-red-100 text-red-800 dark:bg-red-900/30 dark:text-red-300";
      default:
        return "bg-gray-100 text-gray-800 dark:bg-gray-900/30 dark:text-gray-300";
    }
  };

  if (isLoading) {
    return (
      <div className="flex flex-col items-center justify-center py-16">
        <div className="relative w-16 h-16 mb-4">
          <div className="absolute inset-0 bg-blue-500 rounded-full blur-md opacity-50 animate-pulse"></div>
          <div className="relative flex items-center justify-center w-full h-full">
            <Loader2 className="h-8 w-8 animate-spin text-blue-600" />
          </div>
        </div>
        <p className="text-lg font-medium text-gray-700 dark:text-gray-300">
          Lade Spielerdaten...
        </p>
      </div>
    );
  }

  if (spieler.length === 0) {
    return (
      <div className="flex flex-col items-center justify-center py-16 bg-white/50 dark:bg-gray-800/50 backdrop-blur-md rounded-xl border border-white/20 dark:border-gray-700/50">
        <div className="relative w-16 h-16 mb-4">
          <div className="absolute inset-0 bg-gray-200 dark:bg-gray-700 rounded-full blur-md opacity-50"></div>
          <div className="relative flex items-center justify-center w-full h-full">
            <AlertTriangle className="h-8 w-8 text-gray-400 dark:text-gray-500" />
          </div>
        </div>
        <p className="text-lg font-medium text-gray-700 dark:text-gray-300">
          Keine Spieler gefunden
        </p>
        <p className="text-gray-500 dark:text-gray-400 mt-2">
          Erstellen Sie einen neuen Spieler, um zu beginnen.
        </p>
      </div>
    );
  }

  return (
    <div className="bg-white/80 dark:bg-gray-800/80 backdrop-blur-md rounded-xl border border-white/50 dark:border-gray-700/50 shadow-xl overflow-hidden">
      <Table>
        <TableHeader className="bg-white/30 dark:bg-gray-800/30 backdrop-blur-md">
          <TableRow className="hover:bg-transparent border-b border-white/20 dark:border-gray-700/50">
            <TableHead className="w-[50px] font-semibold">ID</TableHead>
            <TableHead className="font-semibold">Name</TableHead>
            <TableHead className="font-semibold">Geburtsdatum</TableHead>
            <TableHead className="font-semibold">Typ</TableHead>
            <TableHead className="text-right font-semibold">Aktionen</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {spieler.map((spieler, index) => (
            <motion.tr
              key={spieler.id}
              initial={{ opacity: 0, y: 10 }}
              animate={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.3, delay: index * 0.05 }}
              className="hover:bg-white/40 dark:hover:bg-gray-800/40 transition-colors border-b border-white/10 dark:border-gray-700/30"
            >
              <TableCell className="font-medium">
                <div className="relative w-10 h-10">
                  <div className="absolute inset-0 bg-blue-500/10 dark:bg-blue-500/20 rounded-full blur-sm"></div>
                  <div className="relative flex items-center justify-center w-full h-full bg-white/30 dark:bg-gray-800/30 backdrop-blur-sm rounded-full border border-white/30 dark:border-gray-700/30">
                    {spieler.id}
                  </div>
                </div>
              </TableCell>
              <TableCell>
                <div className="flex items-center gap-3">
                  <div className="bg-indigo-100 dark:bg-indigo-900/30 w-8 h-8 rounded-full flex items-center justify-center">
                    <User className="h-4 w-4 text-indigo-600 dark:text-indigo-400" />
                  </div>
                  <div>
                    <div className="font-semibold">
                      {spieler.vorname} {spieler.nachname}
                    </div>
                    <div className="text-xs text-gray-500 dark:text-gray-400">
                      {spieler.spieleGespielt} Spiele
                    </div>
                  </div>
                </div>
              </TableCell>
              <TableCell>
                {spieler.geburtsdatum
                  ? formatDate(spieler.geburtsdatum)
                  : "N/A"}
              </TableCell>
              <TableCell>
                <Badge
                  variant="outline"
                  className={getTypeBadgeColor(spieler.typ)}
                >
                  {spieler.typ}
                </Badge>
              </TableCell>
              <TableCell className="text-right">
                <div className="flex items-center justify-end gap-2">
                  {onEdit && (
                    <Button
                      variant="outline"
                      size="sm"
                      onClick={() => onEdit(spieler)}
                      className="bg-blue-100/50 text-blue-800 dark:bg-blue-900/30 dark:text-blue-300 border-blue-200 dark:border-blue-800/30 hover:bg-blue-200/50 dark:hover:bg-blue-800/40"
                    >
                      <Edit className="h-4 w-4" />
                    </Button>
                  )}
                  {onDelete && (
                    <Button
                      variant="outline"
                      size="sm"
                      onClick={() => onDelete(spieler)}
                      className="bg-red-100/50 text-red-800 dark:bg-red-900/30 dark:text-red-300 border-red-200 dark:border-red-800/30 hover:bg-red-200/50 dark:hover:bg-red-800/40"
                    >
                      <Trash2 className="h-4 w-4" />
                    </Button>
                  )}
                  {onPlayerAction && buttonText && (
                    <Button
                      variant="outline"
                      size="sm"
                      onClick={() => onPlayerAction(spieler)}
                      className={
                        actionType === "add"
                          ? "bg-green-100/50 text-green-800 dark:bg-green-900/30 dark:text-green-300 border-green-200 dark:border-green-800/30 hover:bg-green-200/50 dark:hover:bg-green-800/40"
                          : "bg-red-100/50 text-red-800 dark:bg-red-900/30 dark:text-red-300 border-red-200 dark:border-red-800/30 hover:bg-red-200/50 dark:hover:bg-red-800/40"
                      }
                    >
                      {buttonText}
                    </Button>
                  )}
                </div>
              </TableCell>
            </motion.tr>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default SpielerTable;
