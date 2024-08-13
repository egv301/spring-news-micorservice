import { useContext } from "react"
import { AuthContext } from "../App"
import { useNavigate } from "react-router-dom"

export default function HeaderComponent(){
    
    const {auth,setAuth} = useContext(AuthContext)

    const navigator = useNavigate();

    function handleLogout(){
        setAuth({username:'',token:''})
    }

    return (
        <>
            {
            auth.username ?
            <div>
            <div onClick={handleLogout}>Logout</div>
            <div>Username:{auth.username}</div>
            <div>Token:{auth.token}</div>
            {auth.admin && <div onClick={()=>navigator("/admin")}>Admin page</div>}
            </div>
            :
            <div>
            <div onClick={()=>navigator("/register")}>Register</div>
            <div onClick={()=>navigator("/login")}>Login</div>
            </div>
            }
        </>
    )
}