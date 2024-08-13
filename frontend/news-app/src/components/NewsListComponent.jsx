import { useContext } from "react";
import { useNavigate } from "react-router-dom"
import { AuthContext } from "../App";
import { deleteNews } from "../services/Requests";

export default function NewsListComponent({newsList}){

    const navigator = useNavigate();

    const {auth} = useContext(AuthContext);

    function removeNews(id){
        deleteNews(id).then(
            ()=>{
                console.log('news deleted');
            }
        ).catch(error=>console.log(error))
    }

    return(
        <>
            <div>
                {newsList.map(
                    news=> 
                    <div style={{marginBottom: 10,border: "2px solid blue"}} key={news.id} onClick={()=>navigator(`/news/${news.id}`)}>
                        {news.title}
                        {auth.admin && 
                        <div>
                            <div onClick={()=>navigator("/admin/news-form/"+news.id)}>Edit</div>
                            <div onClick={()=>removeNews(news.id)}>Remove</div>
                        </div>
                        }
                    </div>)}
            </div>
        </>
    )
}