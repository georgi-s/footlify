// src/components/SpielerTableRow.tsx
import React from "react";
import { SpielerDTO } from "../../types/SpielerDTO";

interface SpielerTableRowProps {
  spieler: SpielerDTO;
  onEdit: (id: number) => void;
  onDelete: (id: number) => void;
}

const SpielerTableRow: React.FC<SpielerTableRowProps> = ({
  spieler,
  onEdit,
  onDelete,
}) => {
  return (
    <tr>
      <td className="px-4 py-2 border">{spieler.id}</td>
      <td className="px-4 py-2 border">
        {spieler.vorname} {spieler.nachname}
      </td>
      <td className="px-4 py-2 border">
        {new Date(spieler.geburtsdatum).toLocaleDateString()}
      </td>
      <td className="px-4 py-2 border">{spieler.spielerType}</td>
      <td className="px-4 py-2 border">
        <button
          className="text-blue-500 mr-2"
          onClick={() => onEdit(spieler.id!)}
        >
          Bearbeiten
        </button>
        <button className="text-red-500" onClick={() => onDelete(spieler.id!)}>
          Löschen
        </button>
      </td>
    </tr>
  );
};

export default SpielerTableRow;
