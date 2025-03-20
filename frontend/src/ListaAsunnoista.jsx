import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const ListaAsunnoista = () => {
  const [asunnot, setAsunnot] = useState([]);
  const [uusiAsunto, setUusiAsunto] = useState({
    osoite: "",
    kaupunki: "",
    kaupunginosa: "",
    status: "",
  });
  const [editAsunto, setEditAsunto] = useState(null)
  const [deleteId, setDeleteId] = useState("");
  const handleEdit = (id) => {
    const asunto = asunnot.find((v) => v.id === id);
    if (asunto) {
      setEditAsunto({ ...asunto });
    }
  };
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
      osoite: "",
      kaupunki: "",
      kaupunginosa: "",
      status: "",
    };

    fetch("http://localhost:8080/api/asunnot", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON - stringify(uusiAsuntoData),
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
          asiakasId: "",
          asuntoId: "",
          aloitusPvm: "",
          loppuPvm: "",
        });
      })
  }

  // Päivittää tietoja

  const handleUpdate = () => {
    fetch('http://localhost:8080/api/asunnot/${editAsunto.id}', {
      method: "PUT", headers: { "Content-Type": "application/json", },
      body: JSON.stringify({
        id: editAsunto.id,
        osoite: editAsunto.osoite,
        kaupunki: editAsunto.kaupunki,
        kaupunginosa: editAsunto.kaupunginosa,
        status: editAsunto.status,
      })
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Päivitys epäonnistui.");
        }
        return response.json();
      })
      .then((data) => {
        setAsunnot(
          asunnot.map((v) =>
            v.id === data.id ? data : v
          )
        );
        setEditAsunto(null);
      })
      .catch((error) => console.error("Virhe vuokrasopimuksen päivittämisessä:", error));
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




      <Link to="/index">Takaisin etusivulle</Link>
    </div>
  );
};

export default ListaAsunnoista;