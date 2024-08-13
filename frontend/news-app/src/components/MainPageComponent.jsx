import { Outlet } from "react-router-dom";
import CategoryNewsComponent from "./CategoryNewsComponent";
import CategoriesComponent from "./CategoriesComponent";
import HeaderComponent from "./HeaderComponent";

export default function AdminPageComponent(){
    return (
        <>
            <HeaderComponent />
            <Outlet />
        </>
    )
}
