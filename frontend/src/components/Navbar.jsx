import {Link} from "react-router-dom";
import GlobalContext from "../GlobalContext.jsx";
import {useContext} from "react";

export default function Navbar() {

    const {validateResponse, handleLogout} = useContext(GlobalContext)
    return (
        <nav className={'navbar'}>
            {!validateResponse && (
                <Link to={'login'}>
                    <button>Login</button>
                </Link>
            )}

            {validateResponse && (
                <>
                    <Link to={'myProfile'}>
                        <button>My profile</button>
                    </Link>
                    <Link to={'orderHistory'}>
                        <button>My orders</button>
                    </Link>
                    <Link to={'logout'}>
                        <button onClick={handleLogout}>Logout</button>
                    </Link>
                </>
            )}

            <Link to={'allPosts'}>
                <button>All products</button>
            </Link>

            <Link to={'productsByCategory'}>
                <button>Categories</button>
            </Link>
            {!validateResponse && (
                <>
                    <Link to={'register'}>
                        <button>Register</button>
                    </Link>
                    <Link to={'contactUs'}>
                        <button>Contact us</button>
                    </Link>
                </>
            )}
        </nav>
    );

}