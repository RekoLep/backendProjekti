import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const ListaAsunnoista = () => {
  const [asunnot, setAsunnot] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/asunnot")
      .then((response) => response.json())
      .then((data) => setAsunnot(data))
      .catch((error) => console.error("Virhe asunotjen hakemisessa:", error));
  }, []);

  return (
    <div>
      <h1>Asunnot</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Osoite</th>
            <th>Kaupunki</th>
            <th>Kaupunginosa</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {asunnot.map((asunto) => (
            <tr key={asunto.asuntoId}>
              <td>{asunto.asuntoId}</td>
              <td>{asunto.osoite}</td>
              <td>{asunto.kaupunki}</td>
              <td>{asunto.kaupunginOsa}</td>
              <td>{asunto.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/index">Takaisin etusivulle</Link>
    </div>
  );
};

export default ListaAsunnoista;