// vite.config.ts
import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import path from "path";
import tailwindcss from "@tailwindcss/vite";

export default defineConfig(() => {
  return {
    resolve: {
      alias: {
        "~": path.resolve(__dirname, "src"),
      },
    },
    server: {
      open: true, // öffnet automatisch den Browser beim Starten
      port: 3000,
    },
    build: {
      outDir: "build", // Zielordner wie bei CRA
    },
    plugins: [react(), tailwindcss()],
    // Falls du process.env Probleme bekommst, kannst du diesen Block hinzufügen:
    // define: {
    //   'process.env': {},
    // },
  };
});
