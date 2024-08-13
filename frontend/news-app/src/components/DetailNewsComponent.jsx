import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { showNewsItem } from "../services/Requests";
import MainPageLinkComponenet from "./MainPageLinkComponenet";

export default function DetailNewsComponent(){
    const [news,setNews] = useState({});

    const {id} = useParams();
    
    useEffect(()=>{
        showNewsItem(id).then(response=>{
            setNews(response.data)
        }).catch(error=>{
            console.log(error);
        })
    },[id])

    const navigator = useNavigate();
    
    return (
        <>
        <div>
            <div>Title: {news.title}</div>
            <div>Text: {news.text}</div>
            <div style={{marginTop:5}}>Views: {news.viewsCount}</div>
            <div style={{marginTop:15,marginBottom:10}}>In category <span style={{color:"pink"}}onClick={()=>navigator(`/category/${news.categoryId}`)}>{news.categoryTitle}</span></div>
            <MainPageLinkComponenet />
        </div>
        </>

    )
}