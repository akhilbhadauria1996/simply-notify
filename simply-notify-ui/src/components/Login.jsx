import { useState } from "react";

function Login({onLogin}) {

  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

    const handleLogin = async () => {
        const response = await fetch("http://localhost:8080/auth/login", {
          method : "POST",
          headers : {"Content-Type" : "application/json"},
          body : JSON.stringify({userName, password})
        })
        const data = await response.json();
        onLogin(data.token);
    }

  return (
    <div>
      <h2>Sign in</h2>
      <div>
        <div>
          <label>Username : </label>
          <input type="text" onChange={(e)=> setUserName(e.target.value)} ></input>
        </div>
        <div>
          <label>Password : </label>
          <input type="password" onChange={(e)=> setPassword(e.target.value)} ></input>
        </div>
        <button onClick={handleLogin} >Login</button>
      </div>
    </div>
  );
}
export default Login;
