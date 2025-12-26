import { useState } from "react";
import CreateNotification from "./components/CreateNotification";
import CreateUser from "./components/CreateUser";
import UserList from "./components/UserList";

function App() {
  const [screen, setScreen] = useState("");

  return (
    <div>
      <h1>Simply Notify</h1>
      <button onClick={() => setScreen("createNotification")}>Create Notification</button>
      <button onClick={() => setScreen("createUser")}>Create User</button>
      <button onClick={() => setScreen("userList")}>User List</button>
      {screen === "createNotification" && <CreateNotification />}
      {screen === "createUser" && <CreateUser />}
      {screen === "userList" && <UserList />}
    </div>
  );
}

export default App;
