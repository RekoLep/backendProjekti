import { useEffect, useState } from "react";

function App() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/message")
      .then((response) => response.text())
      .then((data) => setMessage(data))
      .catch((error) => console.error("Virhe haettaessa dataa:", error));
  }, []);

  return (
    <div>
      <h1>Tervetuloa ylläpitäjä!</h1>
      <p>{message}</p>
    </div>
  );
}

function ListaAsiakkaista() {
  // Tila asiakaslistalle
  const [asiakkaat, setAsiakkaat] = useState([]);

  // Efekti, joka lataa asiakkaat backendistä
  useEffect(() => {
    fetch('/asiakkaat') // Oletetaan, että tämä reitti palauttaa asiakkaat
      .then((response) => response.json()) // Muutetaan vastaus JSON-muotoon
      .then((data) => setAsiakkaat(data)) // Tallennetaan asiakkaat tilaan
      .catch((error) => console.error('Virhe asiakkaiden latauksessa:', error)); // Virheiden käsittely
  }, []); // Tyhjä riippuvuuslista tarkoittaa, että efekti suoritetaan vain komponentin latautumisen yhteydessä

  return (
    <div>
      <h1>Asiakastieto</h1>
      {asiakkaat.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Etunimi</th>
              <th>Sukunimi</th>
              <th>Puhelinnumero</th>
              <th>Sähköposti</th>
            </tr>
          </thead>
          <tbody>
            {asiakkaat.map((asiakas) => (
              <tr key={asiakas.id}>
                <td>{asiakas.etunimi}</td>
                <td>{asiakas.sukunimi}</td>
                <td>{asiakas.puhelinnumero}</td>
                <td>{asiakas.sahkoposti}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Asiakkaita ei löytynyt.</p>
      )}
    </div>
  );
}

export default App;
