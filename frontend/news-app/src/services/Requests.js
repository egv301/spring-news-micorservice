import axios from "axios";



const TOPNEWS_URL      = "http://localhost:8080/news/top-news";
const ALLNEWS_URL      = "http://localhost:8080/news/all";
const DETAIL_NEWS_URL  = "http://localhost:8080/news";
const ADD_NEWS_URL     = "http://localhost:8080/news/add-news";

const TEST_ADD_URL = "http://localhost:8083/test/print";

const DELETE_NEWS_URL  = "http://localhost:8080/news";

const CATEGORY_URL     = "http://localhost:8080/category/category-list";
const NEWS_BY_CATEGORY = "http://localhost:8080/category";


// news
export const listAllNews = (token) => axios.get(ALLNEWS_URL,{headers: { Authorization: `Bearer ${token}` }});
export const listCategories = () => axios.get(CATEGORY_URL);
export const listTopNews = () => axios.get(TOPNEWS_URL);
export const showNewsItem = (id) => axios.get(DETAIL_NEWS_URL+`/${id}`);
export const addNews = (data,token) => {
    console.log("jwt token is ",token);
    return axios.post(ADD_NEWS_URL,data,{headers: { Authorization: `Bearer ${token}` }});
};


export const testAddNews = () => {
    return axios.post(TEST_ADD_URL,{name:"Murat",age:28});
};



export const deleteNews = (id,token) => axios.delete(DELETE_NEWS_URL+`/${id}`,{headers: { Authorization: `Bearer ${token}` }});

// categories
export const listNewsByCategory = (id) => axios.get(NEWS_BY_CATEGORY+`/${id}`);




// auth
const REGISTER_URL     = "http://localhost:9898/auth/register";
const LOGIN_URL     = "http://localhost:9898/auth/login";
export const register = (data) => axios.post(REGISTER_URL,data);
export const login = (data) => axios.post(LOGIN_URL,data);