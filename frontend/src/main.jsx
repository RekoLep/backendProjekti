import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import App from "./App";
import ListaAsiakkaista from "./ListaAsiakkaista.jsx";
import ListaAsunnoista from "./ListaAsunnoista.jsx";
import VuokrasopimusPage from "./Vuokrasopimusfunktiot.jsx"; 

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/index" element={<App />} />
        <Route path="/asiakkaat" element={<ListaAsiakkaista />} />
        <Route path="/asunnot" element={<ListaAsunnoista />} />
        <Route path="/vuokrasopimukset" element={<VuokrasopimusPage />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);