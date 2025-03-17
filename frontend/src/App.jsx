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

export default App;
