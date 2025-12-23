import { use, useState } from "react";

function CreateNotification() {
  const [email, setEmail] = useState("");
  const [subject, setSubject] = useState("");
  const [body, setBody] = useState("");
  const [userId, setUserId] = useState("");
  const [loading, setLoading] = useState("");
  const [successMsg, setSuccessMsg] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  const handleSubmit = async () => {
    console.log({ email, subject, body });

    const payload = {
      userId: 1,
      type: "EMAIL",
      to: email,
      subject: subject,
      body: body,
    };

    const url = "http://localhost:8082/notification";

    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        throw new Error("Failed to create notification");
      }

      const data = await response.json();
      console.log(data);

      alert("Notification created !");
    } catch (error) {
      console.error(error);
      alert("Failed to create Notification");
    }
  };

  return (
    <div>
      <h2>Create Notification</h2>
      <div>
        <label>Email:</label>
        <input
          type="text"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        ></input>
      </div>
      <div>
        <label>Subject:</label>
        <input
          type="text"
          value={subject}
          onChange={(e) => setSubject(e.target.value)}
        ></input>
      </div>
      <div>
        <label>Body:</label>
        <textarea
          type="text"
          value={body}
          onChange={(e) => setBody(e.target.value)}
        ></textarea>
      </div>
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
}
export default CreateNotification;
