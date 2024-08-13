import { useContext, useState } from "react"
import { useNavigate, useParams } from "react-router-dom";
import { addNews, testAddNews } from "../services/Requests";
import { AuthContext } from "../App";
import { categories } from "./constants";
// import { categories } from "./constants";

export default function NewsForm() {
    const [title, setTitle] = useState('');
    const [text, setText] = useState('');
    const [category,setCategory] = useState('');

    const {auth,setAuth} = useContext(AuthContext);
    const {id} = useParams();

     const navigator = useNavigate();

    function saveNews(e){
        e.preventDefault();
        if(title && text && category){
            const obj = {
                title:title,
                text:text,
                category:category
            }
            addNews(obj,auth.token).then(()=>navigator("/admin")).catch(e=>console.log(e));
            // testAddNews().then(()=>navigator("/admin")).catch(e=>console.log(e));
        }
        // testAddNews().then(()=>navigator("/admin")).catch(e=>console.log(e));

    }

    if (!auth.admin){
        return;
    }

    return (
        <>

            <form>
                <div>
                    <div>News Title:</div>
                    <div><input type="text" onChange={(e)=>setTitle(e.target.value)}/></div>
                </div>
                <div>
                    <div>News Text:</div>
                    <div><input type="text" onChange={(e)=>setText(e.target.value)}/></div>
                </div>
                <div>
                    <div>Categories:</div>
                    <div>
                        <select name="category" id="" onChange={(e)=>setCategory(e.target.value)}>
                        {categories.map((category,index) => (
                            <option key={index} value={category}>
                                {category}
                            </option>
                        ))}
                        </select>

                    </div>
                </div>
                <div><button onClick={saveNews}>Save news</button></div>
            </form>
        </>
    )
}