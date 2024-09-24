import {useContext} from "react";
import GlobalContext from "../GlobalContext.jsx";

export default function ProfilePage() {
    const {user} = useContext(GlobalContext);

    return <div>
        <p>{user.username}</p>
    </div>
}