import {Link} from "react-router-dom";

export default function LogoutPage() {

    return (
        <div>
        <h1>You have successfully logged out. </h1>
        <p>You need to <Link to={'login'}>login</Link> again if you want to make an order.</p>
        </div>
)
}