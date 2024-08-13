import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { createContext, useState } from 'react'
import MainPageComponent from './components/MainPageComponent'
import { BrowserRouter, Outlet, Route, Router, Routes } from 'react-router-dom'
import LoginComponent from './components/LoginComponent'
import RegistrationComponent from './components/RegistrationComponent'
import TopNewsComponent from './components/TopNewsComponent'
import CategoryNewsComponent from './components/CategoryNewsComponent'
import DetailNewsComponent from './components/DetailNewsComponent'
import AdminPageComponent from './components/AdminPageComponent'
import AdminNewsList from './components/AdminNewsList'
import NewsForm from './components/NewsForm'

export const AuthContext = createContext();

const AuthProvider = ({ value, children }) => {
  return (
      <AuthContext.Provider value={value}>
          <Outlet />
          {children}
      </AuthContext.Provider>
  )
}

function App() {
const [auth, setAuth] = useState({
  token: "",
  username: "",
  admin:true
});
return (
    <BrowserRouter>
      <AuthProvider value={{auth,setAuth}}>
        <div className='container'>
          <Routes>
            <Route path="/login" element={<LoginComponent/>}></Route>
            <Route path="/register" element={<RegistrationComponent/>}></Route>
            <Route path="/" element={<MainPageComponent/>}>
              <Route path="" element={<TopNewsComponent/>}></Route>
              <Route path="/news/:id" element={<DetailNewsComponent/>}></Route>
              <Route path="/category/:id" element={<CategoryNewsComponent/>}></Route>
            </Route>
            <Route path="/admin" element={<AdminPageComponent/>}>
              <Route path="" element={<AdminNewsList/>}></Route>
              <Route path="/admin/news-form" element={<NewsForm/>}></Route>
            </Route>
          </Routes>
        </div>
        </AuthProvider>
    </BrowserRouter>
  )
}

export default App
