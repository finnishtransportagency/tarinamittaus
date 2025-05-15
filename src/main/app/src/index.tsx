import React from "react";
import { createRoot } from "react-dom/client";
import App from "./App";

//Importing the bootstrap CSS
import "bootstrap/dist/css/bootstrap.min.css";
import "./styles/styles.css";

const domNode = document.getElementById("root")!;
const root = createRoot(domNode);
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
