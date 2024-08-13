import { useContext, useState } from "react";
import { login } from "../services/Requests";
import { AuthContext } from "../App";
import { useNavigate } from "react-router-dom";
import { ROLE_ADMIN } from "./constants";

function LoginComponent(){
    const [username,setUsername] = useState("");
    const [password,setPassword] = useState("");

    const {auth,setAuth} = useContext(AuthContext);
    const navigator = useNavigate();

    function handleLogin(e){
        e.preventDefault();
        if(username && password){
            login({username,password}).then(response=>{
                const {username, token,role} = response.data;
                setAuth({
                    username,
                    token,
                    admin: role == ROLE_ADMIN ? true : false
                });
                navigator("/");
            }).catch(error=>console.log(error));
        }
        
    }

    return (
        <>
        <form>
            <div>Username:</div>
            <div><input type="text" name="username" required onChange={e=>setUsername(e.target.value)} /></div>
            <div>Password:</div>
            <div><input type="password" name="password" required onChange={e=>setPassword(e.target.value)}/></div>
            <div><button onClick={handleLogin}>Login</button></div>
        </form>
        </>

    )
}

export default LoginComponent;