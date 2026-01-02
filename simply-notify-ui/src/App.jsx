import { useEffect, useState } from "react";
import CreateNotification from "./components/CreateNotification";
import CreateUser from "./components/CreateUser";
import UserList from "./components/UserList";
import Login from "./components/Login";

function App() {
  const [screen, setScreen] = useState("login");
  const [selectedUser, setSelectedUser] = useState(null);
  const [token, setToken] = useState();

  const handleSelectedUser = (user) => {
    setSelectedUser(user);
    setScreen("createNotification");
  };

  const handleLogin = (jwt) =>{

    localStorage.getItem("token", jwt)
    setToken(jwt)
    setScreen("userList")

  }

  return (
    <div>
      <h1>Simply Notify</h1>

      {!token && screen === "login" && <Login onLogin={handleLogin} />}

      {token && (
        <>
          <button onClick={() => setScreen("createUser")}>Create User</button>
          <button onClick={() => setScreen("userList")}>User List</button>
          {screen === "createNotification" && (
            <CreateNotification
              selectedUser={selectedUser}
              onBack={() => setScreen("userList")}
            />
          )}
          {screen === "createUser" && <CreateUser />}
          {screen === "userList" && (
            <UserList onUserSelect={handleSelectedUser} />
          )}
        </>
      )}
    </div>
  );
}

export default App;
