import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import NewsListComponent from "./NewsListComponent";
import { listAllNews } from "../services/Requests";

export default function AdminNewsList(){
    const [news,setNews] = useState([]);
    useEffect(()=>{
        listAllNews().then(response=>{
            setNews(response.data);
        }).catch(error=>{
            console.log(error);
        });
        console.log(news);
    },[]);

    return <NewsListComponent newsList={news}/>
}