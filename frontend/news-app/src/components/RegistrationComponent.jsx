import { useState } from "react";
import { register } from "../services/Requests";
import { useNavigate } from "react-router-dom";

function RegistrationComponent(){
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');

    const [admin, setAdmin] = useState(false);

    const navigator = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        if(username && email && password){
            register({username,password,admin}).then(()=>{
                navigator('/');
            }).catch(error=>console.log(error))
        }
    } 


    return (
        <>
        <form>
            <div>Username:</div>
            <div><input type="text" name="username" required onChange={e=>setUsername(e.target.value)} /></div>
            <div><input type="email" name="email" required onChange={e=>setEmail(e.target.value)} /></div>
            <div>Password:</div>
            <div><input type="password" name="password" required onChange={e=>setPassword(e.target.value)}/></div>
            <div>Admin?:</div>
            <div><input type="checkbox" name="admin" onChange={()=>setAdmin(!admin)}/></div>
            <div><button onClick={handleSubmit}>Register</button></div>
        </form>
        </>
    )
}

export default RegistrationComponent;