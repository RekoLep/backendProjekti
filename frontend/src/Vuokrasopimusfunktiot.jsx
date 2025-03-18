import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const VuokrasopimusPage = () => {
  const [vuokrasopimukset, setVuokrasopimukset] = useState([]);
  const [uusivuokrasopimus, setUusivuokrasopimus] = useState({
    asiakasId: "",
    asuntoId: "",
    aloitusPvm: "",
    loppuPvm: "",
  });
  const [editVuokrasopimus, setEditVuokrasopimus] = useState(null);
  const [deleteId, setDeleteId] = useState("");

  // Hakee kaikki vuokrasopimukset alussa
  useEffect(() => {
    fetch("http://localhost:8080/api/vuokrasopimukset")
      .then((response) => response.json())
      .then((data) => setVuokrasopimukset(data))
      .catch((error) => console.error("Virhe vuokrasopimusten hakemisessa:", error));
  }, []);

  // Luo uuden vuokrasopimuksen
  const handleCreate = () => {
    const uusiSopimusData = {
      asiakas: { asiakasId: Number(uusivuokrasopimus.asiakasId) },
      asunto: { asuntoId: Number(uusivuokrasopimus.asuntoId) },
      alkamisaika: uusivuokrasopimus.aloitusPvm,
      paattymisaika: uusivuokrasopimus.loppuPvm || null,
    };

    fetch("http://localhost:8080/api/vuokrasopimukset", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(uusiSopimusData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Virhe palvelimella: " + response.status);
        }
        return response.json();
      })
      .then((data) => {
        setVuokrasopimukset([...vuokrasopimukset, data]);
        setUusivuokrasopimus({
          asiakasId: "",
          asuntoId: "",
          aloitusPvm: "",
          loppuPvm: "",
        });
      })
      .catch((error) => console.error("Virhe vuokrasopimuksen luomisessa:", error));
  };

  // Päivittää vuokrasopimuksen
  const handleUpdate = () => {
    if (!editVuokrasopimus) return;

    const updatedVuokrasopimus = {
      id: editVuokrasopimus.id,
      asiakas: { asiakasId: Number(editVuokrasopimus.asiakas.asiakasId) },
      asunto: { asuntoId: Number(editVuokrasopimus.asunto.asuntoId) },
      alkamisaika: editVuokrasopimus.alkamisaika,
      paattymisaika: editVuokrasopimus.paattymisaika || null,
    };

    fetch(`http://localhost:8080/api/vuokrasopimukset/${editVuokrasopimus.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedVuokrasopimus),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Virhe palvelimella: " + response.status);
        }
        return response.json();
      })
      .then((data) => {
        setVuokrasopimukset(
          vuokrasopimukset.map((v) => (v.id === data.id ? data : v))
        );
        setEditVuokrasopimus(null);
      })
      .catch((error) =>
        console.error("Virhe vuokrasopimuksen päivittämisessä:", error)
      );
  };

  // Poistaa vuokrasopimuksen
  const handleDelete = () => {
    fetch(`http://localhost:8080/api/vuokrasopimukset/${deleteId}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Virhe palvelimella: " + response.status);
        }
        return response.text();
      })
      .then(() => {
        setVuokrasopimukset(vuokrasopimukset.filter((v) => v.id !== deleteId));
        setDeleteId("");
      })
      .catch((error) =>
        console.error("Virhe vuokrasopimuksen poistamisessa:", error)
      );
  };

  return (
    <div>
      <h1>Vuokrasopimukset</h1>

      {/* Vuokrasopimusten listaaminen */}
      <table>
        <thead>
          <tr>
            <th>VuokrasopimusID</th>
            <th>ID Asiakas</th>
            <th>ID Asunto</th>
            <th>Aloitus Pvm</th>
            <th>Loppu Pvm</th>
            <th>Toiminnot</th>
          </tr>
        </thead>
        <tbody>
          {vuokrasopimukset.map((vuokrasopimus) => (
            <tr key={vuokrasopimus.id}>
              <td>{vuokrasopimus.id}</td>
              <td>
                {vuokrasopimus.asiakas.asiakasId} -{" "}
                {vuokrasopimus.asiakas.etunimi} {vuokrasopimus.asiakas.sukunimi}
              </td>
              <td>
                {vuokrasopimus.asunto.asuntoId} -{" "}
                {vuokrasopimus.asunto.osoite}, {vuokrasopimus.asunto.kaupunki}
              </td>
              <td>{vuokrasopimus.alkamisaika}</td>
              <td>{vuokrasopimus.paattymisaika || "Ei määritelty"}</td>
              <td>
                <button onClick={() => handleEdit(vuokrasopimus.id)}>Muokkaa</button>
                <button onClick={() => setDeleteId(vuokrasopimus.id)}>Poista</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Vuokrasopimuksen poistaminen */}
      {deleteId && (
        <div>
          <p>Oletko varma, että haluat poistaa vuokrasopimuksen ID:llä {deleteId}?</p>
          <button onClick={handleDelete}>Kyllä</button>
          <button onClick={() => setDeleteId("")}>Peruuta</button>
        </div>
      )}

      {/* Uuden vuokrasopimuksen luominen */}
      <div>
        <h2>Uusi vuokrasopimus</h2>
        <div>
          <input
            type="number"
            placeholder="Asiakas ID"
            value={uusivuokrasopimus.asiakasId}
            onChange={(e) => setUusivuokrasopimus({ ...uusivuokrasopimus, asiakasId: e.target.value })}
          />
        </div>
        <div>
          <input
            type="number"
            placeholder="Asunto ID"
            value={uusivuokrasopimus.asuntoId}
            onChange={(e) => setUusivuokrasopimus({ ...uusivuokrasopimus, asuntoId: e.target.value })}
          />
        </div>
        <div>
          <input
            type="date"
            placeholder="Aloituspäivämäärä"
            value={uusivuokrasopimus.aloitusPvm}
            onChange={(e) => setUusivuokrasopimus({ ...uusivuokrasopimus, aloitusPvm: e.target.value })}
          />
        </div>
        <div>
          <input
            type="date"
            placeholder="Loppupäivämäärä"
            value={uusivuokrasopimus.loppuPvm}
            onChange={(e) => setUusivuokrasopimus({ ...uusivuokrasopimus, loppuPvm: e.target.value })}
          />
        </div>
        <button onClick={handleCreate}>Luo vuokrasopimus</button>
      </div>

      {/* Vuokrasopimuksen muokkaaminen */}
      {editVuokrasopimus && (
        <div>
          <h2>Muokkaa vuokrasopimusta</h2>
          <div>
            <input
              type="number"
              value={editVuokrasopimus.asiakasId}
              onChange={(e) =>
                setEditVuokrasopimus({ ...editVuokrasopimus, asiakasId: e.target.value })
              }
            />
          </div>
          <div>
            <input
              type="number"
              value={editVuokrasopimus.asuntoId}
              onChange={(e) =>
                setEditVuokrasopimus({ ...editVuokrasopimus, asuntoId: e.target.value })
              }
            />
          </div>
          <div>
            <input
              type="date"
              value={editVuokrasopimus.aloitusPvm}
              onChange={(e) =>
                setEditVuokrasopimus({ ...editVuokrasopimus, aloitusPvm: e.target.value })
              }
            />
          </div>
          <div>
            <input
              type="date"
              value={editVuokrasopimus.loppuPvm}
              onChange={(e) =>
                setEditVuokrasopimus({ ...editVuokrasopimus, loppuPvm: e.target.value })
              }
            />
          </div>
          <button onClick={handleUpdate}>Päivitä vuokrasopimus</button>
        </div>
      )}

      <Link to="/index">Takaisin etusivulle</Link>
    </div>
  );
};

export default VuokrasopimusPage;