import GlobalContext from "../GlobalContext.jsx";
import {useContext} from "react";
import {Link} from "react-router-dom";

export default function CartPage() {
    const {cart} = useContext(GlobalContext);
    return (
        <div>
            <h1>You have the following items in your cart:</h1>
            {cart.length === 0 ? (
                <p>Your cart is empty.</p>
            ) : (
                <ul>
                    {cart.map((item, index) => (
                        <li key={index}>
                            <p>
                                <strong>{item.name}</strong> - {item.amount} pcs - ${item.price} each
                            </p>
                            <p>Total: ${item.amount * item.price}</p>
                        </li>
                    ))}
                </ul>
            )}
            <Link to={"/checkout"}>
            <button>Proceed to checkout</button>
            </Link>
        </div>
    );

}