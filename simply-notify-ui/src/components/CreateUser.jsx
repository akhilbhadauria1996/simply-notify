import { useState } from "react";

function CreateUser() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [successMsg, setSuccessMsg] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {

    setLoading(true);

    const payload = {
      name: name,
      email: email,
      phone: phone,
    };

    try {
      const response = await fetch("http://localhost:8081/user", {
        method: "POST",
        body: JSON.stringify(payload),
        headers:{
            "Content-Type": "application/json",
        } 
      });

      if(!response.ok){
          throw new Error("Failed to save User");
      }

      const data = await response.json();

      setName("");
      setEmail("");
      setPhone("");
      setSuccessMsg("User Created Successfully");
    } catch(e) {
        setErrorMsg("Failed to save User");
    }finally{
        setLoading(false);
    }
  };

  return (
    <div>
      <h2>Create User</h2>
      <div>
        <label>Name: </label>
        <input type="text" onChange={(e) => setName(e.target.value)}></input>
      </div>
      <div>
        <label>Email: </label>
        <input type="text" onChange={(e) => setEmail(e.target.value)}></input>
      </div>
      <div>
        <label>Phone: </label>
        <input type="number" onChange={(e) => setPhone(e.target.value)}></input>
      </div>
      <div>
        <button onClick={handleSubmit} disabled={loading} >{loading ? "Creating...": "Submit"}</button>
        {successMsg && <p style={{color:"green"}} >{successMsg}</p> } 
        {errorMsg && <p style={{color:"red"}} >{errorMsg}</p> }
      </div>
    </div>
  );
}
export default CreateUser;
