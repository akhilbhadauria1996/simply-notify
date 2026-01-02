function login() {


    const handleLogin = () => {
        fetch()
    }

  return (
    <div>
      <h2>Sign in</h2>
      <div>
        <div>
          <label>Username : </label>
          <input type="text"></input>
        </div>
        <div>
          <label>Password : </label>
          <input type="text"></input>
        </div>
        <button onClick={handleLogin} >Login</button>
      </div>
    </div>
  );
}
export default login;
