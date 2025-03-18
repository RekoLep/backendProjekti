import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

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
      <Link to="/asiakkaat">Asiakkaat</Link>
      <br></br>
      <Link to="/asunnot">Asunnot</Link>
      <br></br>
      <Link to="/vuokrasopimukset">Vuokrasopimukset</Link>
    </div>
  );
}

export default App;
