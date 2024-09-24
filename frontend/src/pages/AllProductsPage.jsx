import {useContext, useEffect, useState} from "react";
import GlobalContext from "../GlobalContext.jsx";
import SearchBar from "../components/SearchBar.jsx";
import {Link} from "react-router-dom";
import Dropdown from "../components/Dropdown.jsx";

export default function AllProductsPage() {
    const {products, loadProducts, addProductToCart, validateResponse, user} = useContext(GlobalContext);
    const [searchTerm, setSearchTerm] = useState("");
    const [amounts, setAmounts] = useState({});
    const filteredProducts = (products || []).filter((product) => product.productName.toLowerCase().includes(searchTerm.toLowerCase())
    );

    console.log(user);
    
    useEffect(() => {
        loadProducts();
    }, [loadProducts])

    const handleAmountChange = (productId, amount) => {
        setAmounts(prev => ({
            ...prev,
            [productId]: amount
        }));
    };

    return (
        <>
            <SearchBar searchTerm={searchTerm} onChange={setSearchTerm} />
            <div id={"products-container"}>
                {filteredProducts.map((product) => (
                    <div className={"individual-product"} key={product.productId}>
                        <img
                            id={"product-image"}
                            src={`data:image/jpeg;base64,${product.data}`}
                            alt={product.productName}
                        />
                        <Link to={`/product/${product.productId}`}>
                            <h2>{product.productName}</h2>
                        </Link>
                        <p> price: {product.price} $ </p>
                        {validateResponse && (
                            <>
                            <Dropdown
                            productId={product.productId}
                            onAmountChange={handleAmountChange}

                            />
                            </>
                        )}

                        {validateResponse && (
                        <button onClick={() => addProductToCart(product.productId)}>
                            Add to cart
                        </button>
                      )}
                    </div>
                ))}
            </div>
        </>
    );
}