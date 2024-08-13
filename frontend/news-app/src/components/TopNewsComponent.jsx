import { useEffect, useState } from "react";
import { listTopNews } from "../services/Requests";
import { useNavigate } from "react-router-dom";
import NewsListComponent from "./NewsListComponent";

export default function TopNewsComponent(){
    const [topNews,setTopnews] = useState([]);
    useEffect(()=>{
        listTopNews().then(response=>{
            setTopnews(response.data);
        }).catch(error=>{
            console.log(error);
        });
        console.log(topNews);
    },[]);

    const navigator = useNavigate();

    const navigate = (cat_id) => navigator(`/catgy/${cat_id}`);

    return <NewsListComponent newsList={topNews}/>
}

