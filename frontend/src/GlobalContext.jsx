import {createContext, useCallback, useEffect, useState} from "react";

const GlobalContext = createContext(null);

export const GlobalProvider = ({children}) => {
    const initialValidateResponse = JSON.parse(localStorage.getItem("validateResponse"))
    const [user, setUser] = useState(null);
    const [csrfToken, setCsrfToken] = useState(null);
    const [validateResponse, setValidateResponse] = useState(initialValidateResponse);
    const [products, setProducts] = useState([]);
    const [cart, setCart] = useState([]);


    useEffect(() => {
        setValidateResponse(validateResponse);
        void loadProducts();
        fetchCsrfToken();
    }, [validateResponse])
    const fetchCsrfToken = async () => {
        try {
            const csrfRes = await fetch("http://localhost:8080/csrf", { credentials: "include" });
            const token = await csrfRes.json();
            setCsrfToken(token.token);
        } catch (error) {
            console.error(error);
        }
    };

    const addToCart = (newItem) => {
        setCart((prevCart) => {
            const existingItem = prevCart.find(item => item.productId === newItem.productId);
            if (existingItem) {
                // If the product is already in the cart, update the amount
                return prevCart.map(item =>
                    item.productId === newItem.productId
                        ? { ...item, amount: item.amount + newItem.amount }
                        : item
                );
            } else {
                // If it's a new product, add it to the cart
                return [...prevCart, newItem];
            }
        });
    };

    const registerUser = async (userData) => {
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        };

        try {
            const response = await fetch('http://localhost:8080/api/user/register', requestOptions);

            return response; // Return the entire response object
        } catch (error) {
            console.error(error);
            return null; // Return null in case of an error
        }
    };

    const submitLogin = async (username, password) => {
        try {
            const response = await fetch("http://localhost:8080/api/user/login", {
                method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': csrfToken,
                },
                body: JSON.stringify({username, password}),
                credentials: 'include'
            });

            setUser(username);
            setValidateResponse(response.ok)
            localStorage.setItem("validateResponse", JSON.stringify(response.ok));

        } catch (error) {
            console.error(error);
        }
    }


    const handleLogout = async () => {
        try {
            await fetch("http://localhost:8080/api/user/logout", {
                method: 'POST',
                headers: {'Content-Type': 'application/json'
                },
                credentials: 'include' });

            setValidateResponse(false);
            localStorage.removeItem("validateResponse")
            console.log(validateResponse)
        }

        catch (error) {
            console.error(error);
        }
    }

    const loadProducts = async () => {
        try {
            const response = await fetch("http://localhost:8080/api/product/all");

            if(!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            const result = await response.json();
            setProducts(result);
        } catch(error) {
            console.error(error);
        }
    }

    return (
        <GlobalContext.Provider
            value={{
                validateResponse,
                setValidateResponse,
                user,
                setUser,
                submitLogin,
                handleLogout,
                registerUser,
                loadProducts,
                products,
                setProducts,
                cart,
                setCart,
                addToCart,

            }}
            >
            {children}
        </GlobalContext.Provider>
    )
}

export default GlobalContext;