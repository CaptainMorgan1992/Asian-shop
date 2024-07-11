import {useContext, useEffect, useState} from "react";
import GlobalContext from "../GlobalContext.jsx";
import SearchBar from "../components/SearchBar.jsx";
import {Link} from "react-router-dom";

export default function AllProductsPage() {
    const {products, loadProducts} = useContext(GlobalContext);
    const [searchTerm, setSearchTerm] = useState("");

    const filteredProducts = (products || []).filter((product) => product.productName.toLowerCase().includes(searchTerm.toLowerCase())
    );

    useEffect(() => {
        loadProducts();
    }, [loadProducts])

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
                        <p> price: {product.price} </p>
                        </div>
                ))}
            </div>
        </>
    )
}