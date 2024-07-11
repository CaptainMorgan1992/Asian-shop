import React from 'react'
import ReactDOM, {createRoot} from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import {createBrowserRouter, createRoutesFromElements, Route, RouterProvider} from "react-router-dom";
import StartPage from "./pages/StartPage.jsx";
import Login from "./components/Login.jsx";
import ErrorBoundary from "./pages/ErrorBoundary.jsx";
import RegistrationPage from "./pages/RegistrationPage.jsx";
import AllProductsPage from "./pages/AllProductsPage.jsx";

const router = createBrowserRouter(
    createRoutesFromElements(
        <Route path="/" element={<App/>} errorElement={<ErrorBoundary/>}>
            <Route path="/" index element={<StartPage/>}/>
            <Route path={'login'} element={<Login/>}/>
            <Route path={'register'} element={<RegistrationPage/>}/>
            <Route path={'products'} element={<AllProductsPage/>}/>
        </Route>
    )
)

createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>
);