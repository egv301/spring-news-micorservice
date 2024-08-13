import { useEffect, useState } from "react";
import { listNewsByCategory } from "../services/Requests";
import NewsListComponent from "./NewsListComponent";
import { useParams } from "react-router-dom";


export default function CategoryNewsComponent(){

    const [news,setNews] = useState([])

    const {id} = useParams();

    useEffect(()=>{
        listNewsByCategory(id).then(response=>{
            setNews(response.data)
        }).catch(error=>{
            console.log(error)
        })
    },[id])
    
    return <NewsListComponent newsList={news} />
}

