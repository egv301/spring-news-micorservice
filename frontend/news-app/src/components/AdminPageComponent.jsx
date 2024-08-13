import { useContext } from "react";
import { Outlet, useNavigate } from "react-router-dom";
import { AuthContext } from "../App";

export default function AdminPageComponent() {

    const navigator = useNavigate();

    const {auth} = useContext(AuthContext);

    

    return (
        <>
        {auth.admin && 
        <div>
        <div>Admin page</div>
        <div onClick={()=>navigator('/admin/news-form')}>Add news</div>
        <Outlet />
        </div>}
        
        </>
    )
}