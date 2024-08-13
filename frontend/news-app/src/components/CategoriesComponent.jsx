import { useEffect, useState } from "react";
import { listCategories } from "../services/Requests";
import { useNavigate } from "react-router-dom";

export default function CategoriesComponent(){
    const [categories,setCategories] = useState([]);
    useEffect(()=>{
        listCategories().then(response=>{
            setCategories(response.data);
        }).catch(error=>{
            console.log(error);
        });
        console.log(categories);
    },[]);

    const navigator = useNavigate();
    
    return(
        <>
            <div style={{display: "flex",gap:5,marginBottom:10}}>
                {categories.map(category=> <div key={category.id} onClick={()=>navigator(`/category/${category.id}`)}>{category.title}</div>)}
            </div>
        </>
    ) 
}
