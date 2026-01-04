import { useEffect, useState } from "react";
import { BASE_URL, BASE_URL_NOTIFICATION_SERVICE } from "../api/config";

function CreateNotification({ selectedUser, onBack}) {
  const [email, setEmail] = useState("");
  const [subject, setSubject] = useState("");
  const [body, setBody] = useState("");
  const [userId, setUserId] = useState("");
  const [loading, setLoading] = useState(false);
  const [successMsg, setSuccessMsg] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const token = localStorage.getItem("token");

  useEffect(() => {
    setUserId(selectedUser.id)
    setEmail(selectedUser.email)
  }, [selectedUser]);

  const handleSubmit = async () => {
    setLoading(true);
    setErrorMsg("");
    setSuccessMsg("");

    console.log({ email, subject, body });

    const payload = {
      userId: Number(userId),
      type: "EMAIL",
      to: email,
      subject: subject,
      body: body,
    };

    const url = `${BASE_URL}/notification`;

    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer "+ token
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        throw new Error("Failed to create notification");
      }

      const data = await response.json();
      console.log(data);

      setSuccessMsg("Notification created successfully");
      setUserId("");
      setEmail("");
      setSubject("");
      setBody("");
    } catch (error) {
      console.error(error);
      setErrorMsg("Failed to create Notification");
    } finally {
      setLoading(false);
    }
  };
 
  return (
    <div>
      <h2>Create Notification</h2>
      <button onClick={onBack} >← Back to User List</button>
      <div>
        <label>User Id:</label>
        <input
          type="number"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          disabled={!!selectedUser}
        ></input>
      </div>
      <div>
        <label>Email:</label>
        <input
          type="text"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          disabled={selectedUser !== null}
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
          value={body}
          onChange={(e) => setBody(e.target.value)}
        ></textarea>
      </div>
      <button onClick={handleSubmit} disabled={loading} >{loading ? "Creating..." : "Submit"}</button>
      {successMsg && <p style={{ color: "green" }}>{successMsg}</p>}
      {errorMsg && <p style={{ color: "red" }}>{errorMsg}</p>}
    </div>
  );
}
export default CreateNotification;
