import { useState } from "react";
import CreateNotification from "./components/CreateNotification";
import CreateUser from "./components/CreateUser";
import UserList from "./components/UserList";

function App() {
  const [screen, setScreen] = useState("");
  const [selectedUser, setSelectedUser] = useState(null);

  const handleSelectedUser = (user) => {
    setSelectedUser(user);
    setScreen("createNotification");
  }

  return (
    <div>
      <h1>Simply Notify</h1>
      
      <button onClick={() => setScreen("createUser")}>Create User</button>
      <button onClick={() => setScreen("userList")}  >User List</button>
      {screen === "createNotification" && <CreateNotification selectedUser={selectedUser} onBack={()=>setScreen("userList")} />}
      {screen === "createUser" && <CreateUser />}
      {screen === "userList" && <UserList onUserSelect={handleSelectedUser}  />}
    </div>
  );
}

export default App;
