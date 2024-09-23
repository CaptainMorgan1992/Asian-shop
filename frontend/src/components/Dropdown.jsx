import {useContext, useState} from "react";

export default function Dropdown({ productId, onAmountChange }) {
    const [localAmount, setLocalAmount] = useState(1);

    const handleAmountChange = (e) => {
        const selectedAmount = e.target.value;
        setLocalAmount(selectedAmount);
        onAmountChange(productId, selectedAmount); // Pass this to the parent
    };

    return (
        <div id={"dropdownDiv"}>
            <select value={localAmount} onChange={handleAmountChange}>
                {[...Array(10).keys()].map(i => (
                    <option key={i + 1} value={i + 1}>{i + 1}</option>
                ))}
            </select>
        </div>
    );
}