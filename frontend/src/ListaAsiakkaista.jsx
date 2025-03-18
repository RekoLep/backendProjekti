import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const ListaAsiakkaista = () => {
  const [asiakkaat, setAsiakkaat] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/asiakkaat")
      .then((response) => response.json())
      .then((data) => setAsiakkaat(data))
      .catch((error) => console.error("Virhe asiakkaiden hakemisessa:", error));
  }, []);

  return (
    <div>
      <h1>Asiakkaat</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Etunimi</th>
            <th>Sukunimi</th>
            <th>Puhelinnumero</th>
            <th>Sähköposti</th>
          </tr>
        </thead>
        <tbody>
          {asiakkaat.map((asiakas) => (
            <tr key={asiakas.asiakasId}>
              <td>{asiakas.asiakasId}</td>
              <td>{asiakas.etunimi}</td>
              <td>{asiakas.sukunimi}</td>
              <td>{asiakas.puhelinnumero}</td>
              <td>{asiakas.sahkoposti}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/index">Takaisin etusivulle</Link>
    </div>
  );
};

export default ListaAsiakkaista;