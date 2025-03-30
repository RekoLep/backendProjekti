import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const ListaAsunnoista = () => {
  const [asunnot, setAsunnot] = useState([]);
  const [uusiAsunto, setUusiAsunto] = useState({
    osoite: "",
    kaupunki: "",
    kaupunginOsa: "",
    status: "",
  });
  const [editAsunto, setEditAsunto] = useState(null)
  const [deleteId, setDeleteId] = useState("");
  // Hakee kaikki asunnot
  useEffect(() => {
    fetch("http://localhost:8080/api/asunnot")
      .then((response) => response.json())
      .then((data) => setAsunnot(data))
      .catch((error) => console.error("Virhe asunotjen hakemisessa:", error));
  }, []);

  // Lisää uuden asunnon

  const handleCreate = () => {
    const uusiAsuntoData = {
      osoite: uusiAsunto.osoite,
      kaupunki: uusiAsunto.kaupunki,
      kaupunginOsa: uusiAsunto.kaupunginOsa,
      status: uusiAsunto.status,
    };

    fetch("http://localhost:8080/api/asunnot", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(uusiAsuntoData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Virhe palvelimella: " + response.status);
        }
        return response.json();
      })
      .then((data) => {
        setAsunnot([...asunnot, data]);
        setUusiAsunto({
          osoite: "",
          kaupunki: "",
          kaupunginOsa: "",
          status: "",
        });
      })
  }

  // Päivittää tietoja

  const handleUpdate = () => {
    fetch(`http://localhost:8080/api/asunnot/${editAsunto.asuntoId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        asuntoId: Number(editAsunto.asuntoId),
        osoite: editAsunto.osoite,
        kaupunki: editAsunto.kaupunki,
        kaupunginOsa: editAsunto.kaupunginOsa,
        status: editAsunto.status
      })
    })
      .then(response => {
        if (!response.ok) {
          return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
      })
      .then(data => console.log("Päivitetty asunto:", data))
      .catch(error => console.error("Virhe päivityksessä:", error));
  }

  // Poistaa asunnon
  const handleDelete = () => {
    fetch(`http://localhost:8080/api/asunnot/${deleteId}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Virhe palvelimella: " + response.status);
        }
        return response.text();
      })
      .then(() => {
        setAsunnot(asunnot.filter((v) => v.id !== deleteId));
        setDeleteId("");
      })
      .catch((error) =>
        console.error("Virhe vuokrasopimuksen poistamisessa:", error)
      );
  }

  return (
    <div>
      <h1>Asunnot</h1>

      {/*Asuntojen listaaminen*/}
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
              <td>
                <button onClick={() => setDeleteId(asunto.asuntoId)}>Poista</button>
                <button onClick={() => setEditAsunto(asunto)}>Päivitä</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Asuntojen poistaminen poistaminen */}
      {deleteId && (
        <div>
          <p>Oletko varma, että haluat poistaa asunnon ID:llä {deleteId}?</p>
          <button onClick={handleDelete}>Kyllä</button>
          <button onClick={() => setDeleteId("")}>Peruuta</button>
        </div>
      )}

      {/* Muokkaa asunnon tietoja */}
      {editAsunto && (
        <div>
          <h2>Muokkaa asunnon tietoja</h2>
          <div>
            <input
              type="text"
              value={editAsunto.osoite}
              onChange={(e) =>
                setEditAsunto({
                  ...editAsunto,
                  osoite: { ...editAsunto.osoite, osoite: Number(e.target.value) }
                })
              }
            />
          </div>
          <div>
            <input
              type="text"
              placeholder="Kaupunki"
              value={editAsunto.kaupunki}
              onChange={(e) =>
                setEditAsunto({ ...editAsunto, kaupunki: e.target.value })
              }
            />
          </div>
          <div>
            <input
              type="text"
              placeholder="Kaupunginosa"
              value={editAsunto.kaupunginOsa}
              onChange={(e) =>
                setEditAsunto({ ...editAsunto, kaupunginOsa: e.target.value })
              }
            />
          </div>
          <div>
            <input
              type="text"
              placeholder="Status"
              value={editAsunto.status}
              onChange={(e) =>
                setEditAsunto({ ...editAsunto, status: e.target.value })
              }
            />
          </div>
          <div>
            <button onClick={handleUpdate}>Päivitä</button>
          </div>
        </div>
      )}


      {/*Uuden asunnon lisäys*/}
      <div>
        <h2>Lisää uusi asunto</h2>
        <div>
          <input
            type="text"
            placeholder="Osoite"
            value={uusiAsunto.osoite}
            onChange={(e) => setUusiAsunto({ ...uusiAsunto, osoite: e.target.value })}
          />
        </div>
        <div>
          <input
            type="text"
            placeholder="Kaupunki"
            value={uusiAsunto.kaupunki}
            onChange={(e) => setUusiAsunto({ ...uusiAsunto, kaupunki: e.target.value })}
          />
        </div>
        <div>
          <input
            type="text"
            placeholder="Kaupunginosa"
            value={uusiAsunto.kaupunginOsa}
            onChange={(e) => setUusiAsunto({ ...uusiAsunto, kaupunginOsa: e.target.value })}
          />
        </div>
        <div>
          <input
            type="text"
            placeholder="Status"
            value={uusiAsunto.status}
            onChange={(e) => setUusiAsunto({ ...uusiAsunto, status: e.target.value })}
          />
        </div>
        <button onClick={handleCreate}>Lisää</button>
      </div>






      <Link to="/index">Takaisin etusivulle</Link>
    </div>
  );
};

export default ListaAsunnoista;