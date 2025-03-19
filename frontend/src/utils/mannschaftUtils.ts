export function getFormationName(formation: string): string {
  const formationMap: Record<string, string> = {
    f442: "4-4-2",
    f433: "4-3-3",
    f352: "3-5-2",
    f532: "5-3-2",
    f343: "3-4-3",
    f541: "5-4-1",
    f451: "4-5-1",
    f4231: "4-2-3-1",
    f4321: "4-3-2-1",
    f3421: "3-4-2-1",
    f4141: "4-1-4-1",
    f4222: "4-2-2-2",
  };

  return formationMap[formation] || formation;
}

export function getLigaName(liga: string): string {
  const ligaMap: Record<string, string> = {
    Bundesliga1: "Bundesliga",
    Bundesliga2: "2. Bundesliga",
    Bundesliga3: "3. Liga",
    Regionalliga: "Regionalliga",
    Oberliga: "Oberliga",
    Verbandsliga: "Verbandsliga",
    Landesliga: "Landesliga",
    Bezirksliga: "Bezirksliga",
    Kreisliga: "Kreisliga",
    Kreisklasse: "Kreisklasse",
  };

  return ligaMap[liga] || liga;
}

export function getLigaBadgeColor(liga: string): string {
  // Wir geben hier nur die Klasse zurück, die in der Badge-Komponente verwendet wird

  // Für Light und Dark Mode angepasste Farben
  switch (liga) {
    case "Bundesliga1":
      return "bg-rose-50 text-rose-700 border-rose-200 dark:bg-rose-900/30 dark:text-rose-300 dark:border-rose-800/50";
    case "Bundesliga2":
      return "bg-blue-50 text-blue-700 border-blue-200 dark:bg-blue-900/30 dark:text-blue-300 dark:border-blue-800/50";
    case "Bundesliga3":
      return "bg-emerald-50 text-emerald-700 border-emerald-200 dark:bg-emerald-900/30 dark:text-emerald-300 dark:border-emerald-800/50";
    case "Regionalliga":
      return "bg-amber-50 text-amber-700 border-amber-200 dark:bg-amber-900/30 dark:text-amber-300 dark:border-amber-800/50";
    case "Oberliga":
      return "bg-purple-50 text-purple-700 border-purple-200 dark:bg-purple-900/30 dark:text-purple-300 dark:border-purple-800/50";
    case "Verbandsliga":
      return "bg-cyan-50 text-cyan-700 border-cyan-200 dark:bg-cyan-900/30 dark:text-cyan-300 dark:border-cyan-800/50";
    case "Landesliga":
      return "bg-pink-50 text-pink-700 border-pink-200 dark:bg-pink-900/30 dark:text-pink-300 dark:border-pink-800/50";
    case "Bezirksliga":
      return "bg-lime-50 text-lime-700 border-lime-200 dark:bg-lime-900/30 dark:text-lime-300 dark:border-lime-800/50";
    case "Kreisliga":
      return "bg-orange-50 text-orange-700 border-orange-200 dark:bg-orange-900/30 dark:text-orange-300 dark:border-orange-800/50";
    case "Kreisklasse":
      return "bg-teal-50 text-teal-700 border-teal-200 dark:bg-teal-900/30 dark:text-teal-300 dark:border-teal-800/50";
    default:
      return "bg-gray-50 text-gray-700 border-gray-200 dark:bg-gray-900/30 dark:text-gray-300 dark:border-gray-800/50";
  }
}
