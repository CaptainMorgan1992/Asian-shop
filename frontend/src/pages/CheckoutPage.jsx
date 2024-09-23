import {useContext} from "react";
import GlobalContext from "../GlobalContext.jsx";

export default function CheckoutPage() {
    const {user, cart} = useContext(GlobalContext);

        return (
            <div>
                <h1>You have the following items in your cart:</h1>
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
                )
                {cart.length > 0 && (
                    <div>
                        <h2>Total Amount: ${totalAmount.toFixed(2)}</h2>
                    </div>
                )}
            <p>Ship to: {user}</p>
            </div>
        );
}