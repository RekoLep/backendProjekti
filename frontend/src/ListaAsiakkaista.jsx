import React, { useState, useEffect } from 'react';

const Asiakkaat = () => {
  const [asiakkaat, setAsiakkaat] = useState([]);

  useEffect(() => {
    // Hae asiakkaita API:sta
    fetch('http://localhost:8080/api/asiakkaat')
      .then(response => response.json())
      .then(data => setAsiakkaat(data))
      .catch(error => console.error('Virhe asiakkaita haettaessa:', error));
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
          {asiakkaat.map(asiakas => (
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
    </div>
  );
};

export default Asiakkaat;