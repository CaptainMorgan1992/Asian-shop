
import {GlobalProvider} from "./GlobalContext.jsx";
import {Outlet} from "react-router-dom";
import Header from "./components/Header.jsx";
import "./styles/RegisterPage.css"
import "./styles/ProductPage.css"

function App() {
    return (
        <GlobalProvider>
            <Header/>
            <Outlet/>
        </GlobalProvider>

    );
}
export default App
