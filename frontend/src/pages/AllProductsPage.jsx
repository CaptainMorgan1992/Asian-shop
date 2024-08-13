import {useContext, useEffect, useState} from "react";
import GlobalContext from "../GlobalContext.jsx";
import SearchBar from "../components/SearchBar.jsx";
import {Link} from "react-router-dom";
import Dropdown from "../components/Dropdown.jsx";

export default function AllProductsPage() {
    const {products, loadProducts} = useContext(GlobalContext);
    const [searchTerm, setSearchTerm] = useState("");
    const [amounts, setAmounts] = useState({}); // Local state for amounts

    const filteredProducts = (products || []).filter((product) => product.productName.toLowerCase().includes(searchTerm.toLowerCase())
    );

    useEffect(() => {
        loadProducts();
    }, [loadProducts])

    const handleAmountChange = (productId, amount) => {
        setAmounts(prev => ({
            ...prev,
            [productId]: amount
        }));
    };

    const addToCart = (productId) => {
        const amount = amounts[productId] || 1;
        console.log("productId: " + productId + " amountOfProduct: " + amount);
    };

    return (
        <>
            <SearchBar searchTerm={searchTerm} onChange={setSearchTerm}/>
            <div id={"products-container"}>
                {filteredProducts.map((product) => (
                    <div className={"individual-product"} key={product.productId}>
                        <img id={"product-image"}
                            src={`data:image/jpeg;base64,${product.data}`}
                            alt={product.productName}
                        />
                        <Link to={`/product/${product.productId}`}>
                            <h2>{product.productName}</h2>
                        </Link>
                        <p> price: {product.price} $ </p>
                        <Dropdown
                            productId={product.productId}
                            onAmountChange={handleAmountChange}
                        />
                        <button onClick={() => addToCart(product.productId)}>Add to cart</button>
                        </div>
                ))}
            </div>
        </>
    )
}