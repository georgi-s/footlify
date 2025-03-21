import React from "react";
import ReactDOM from "react-dom/client";
import "./globals.css"; // Hier "./globals.css" statt "globals.css"
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { ThemeProvider } from "./components/theme-toggle";
import { BrowserRouter as Router } from "react-router-dom";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <React.StrictMode>
    <Router>
      <ThemeProvider>
        <App />
      </ThemeProvider>
    </Router>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
