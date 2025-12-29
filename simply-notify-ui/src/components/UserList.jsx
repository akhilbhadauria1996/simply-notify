import { useEffect, useState } from "react";
import { BASE_URL, BASE_URL_USER_SERVICE } from "../api/config";

function UserList({onUserSelect}) {
  const [loading, setLoading] = useState(false);
  const [users, setUsers] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    setLoading(true);
    setError("");
    setUsers([]);
    try {
      const response = await fetch(`${BASE_URL}/user`);
      if (!response.ok) {
        throw new Error("Failed to fetch users");
      }
      const data = await response.json();
      setUsers(data);
    } catch (e) {
      setError("Failed to load users");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div >
      <h2>User List</h2>
      <div>
        {loading && <p>Loading...</p>}
        {error && <p style={{ color: "red" }}>{error}</p>}
        {!loading && !error && users.length > 0 && (
          <table border="1">
            <thead>
              <tr>
                <th>SNo.</th>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user, index) => (
                <tr key={user.id} 
                onClick={ ()=> onUserSelect(user)} 
                style={{cursor:"pointer"}}
                
                 >
                  <td>{index + 1}</td>
                  <td>{user.id}</td>
                  <td>{user.name}</td>
                  <td>{user.email}</td>
                  <td>{user.phone}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
        {!loading && !error && users.length==0
            && (<p>No records available</p>)
        }
      </div>
    </div>
  );
}
export default UserList;
