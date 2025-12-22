import { useState } from "react";

function CreateNotification(){

    const [email, setEmail] = useState("");
    const [subject, setSubject] = useState("");
    const [body, setBody] = useState("");

    const handleSubmit = () => {
        con
    }

    return(
        <div>
            <h2>Create Notification</h2>
            <div>
                <label>Email:</label>
                <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} ></input>
                <label>Subject:</label>
                <input type="text" value={subject} onChange={(e) => setSubject(e.target.value)} ></input>
                <label>Body:</label>
                <input type="text" value={body} onChange={(e) => setBody(e.target.value)} ></input>
            </div>
            <button onClick={(e) => handleSubmit} >Submit</button>
        </div>
    );
}
export default CreateNotification
