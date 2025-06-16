import { BASE_API_URL } from "@/apiUrl/apiUrl";
import { useUser } from "@/contexts/userContext";
import axios from "axios";
import Image from "next/image";
import { useState } from "react";
import { FiEdit, FiMinus, FiPlus, FiTrash2 } from "react-icons/fi";

export default function CartProductCard({ cartItem }) {

    const { cartItemCustomizations, product } = cartItem
    const { user, cart, setCart } = useUser()
    const [isLoading, setIsLoading] = useState(false)

    const API_CART_URL = `${BASE_API_URL}/cart/${user.id}`
    const API_CART_ITEM_URL = `${BASE_API_URL}/cartItems`

    async function increaseProductQuantity() {
        const newQuantity = cartItem.quantity + 1
        await axios.put(API_CART_ITEM_URL, {
                "id": cartItem.id,
                "quantity": newQuantity,
                "totalPrice": cartItem.totalPrice,
                "cart": cart.id,
                "product": cartItem.product.id
            }
        )

        const { data } = await axios.get(API_CART_URL)
        setCart(data)
    }

    async function decreaseProductQuantity() {
        const newQuantity = cartItem.quantity - 1

        if (newQuantity < 1) {
            await axios.delete(`${API_CART_ITEM_URL}/${cartItem.id}`)
        } else {
            await axios.put(API_CART_ITEM_URL, {
                    "id": cartItem.id,
                    "quantity": newQuantity,
                    "totalPrice": cartItem.totalPrice,
                    "cart": cart.id,
                    "product": cartItem.product.id
                }
            )
        }

        const { data } = await axios.get(API_CART_URL)
        setCart(data)
    }

    async function deleteProduct() {
        await axios.delete(`${API_CART_ITEM_URL}/${cartItem.id}`)

        const { data } = await axios.get(API_CART_URL)
        setCart(data)
    }

    return (
        <div className="flex flex-col md:flex-row my-5 py-3 px-3 gap-4 border border-gray-700 rounded-lg justify-between">
            <div className="flex md:w-max gap-5">
                <Image
                    src="https://res.cloudinary.com/dusgvgbgf/image/upload/v1739903483/burguer.jpg"
                    width={1000}
                    height={0}
                    alt="burguer"
                    className="max-w-32 h-28 object-cover rounded-lg"
                />

                <div className="w-full">
                    <div className="flex flex-col items-start py-3">
                        <p className="text-lg sm:text-2xl text-left">
                            {product.name}
                        </p>
                    </div>

                    {cartItemCustomizations.map(({ customizationValue }, key) =>
                        <div
                            className="flex flex-row w-full h-max md:gap-2 items-center justify-between text-gray-400 text-sm"
                            key={key}
                        >
                            <li className="flex items-center gap-1 before:content-['•'] before:text-gray-400">
                                <p>
                                    {customizationValue.name}
                                </p>
                            </li>
                            {customizationValue.additionalPrice != 0 && (
                                <p className="w-fit self-start whitespace-nowrap">
                                    + R$ {customizationValue.additionalPrice.toFixed(2).replace('.', ',')}
                                </p>
                            )}
                        </div>

                    )}
                </div>
            </div>

            <div className="flex flex-col items-center w-full md:w-1/3 gap-4">
                <div className="flex w-full justify-between gap-3 text-2xl">
                    <button
                        className="flex w-20 justify-center h-fit p-1 border-2 border-gray-500 rounded-md"
                        onClick={increaseProductQuantity}
                    >
                        <FiPlus />
                    </button>

                    <span>{cartItem.quantity}</span>

                    <button
                        className="flex w-20 justify-center h-fit p-1 border-2 border-gray-500 rounded-md"
                        onClick={decreaseProductQuantity}
                    >
                        <FiMinus />
                    </button>
                </div>

                <div className="flex w-full gap-3">
                    <button className="w-full flex justify-center py-2 border border-blue-400 rounded-md">
                        <FiEdit className="w-6 h-fit text-blue-400" />
                    </button>

                    <button
                        className="w-full flex justify-center py-2 border border-red-600 rounded-md"
                        onClick={deleteProduct}
                    >
                        <FiTrash2 className="w-6 h-fit text-red-600" />
                    </button>
                </div>

                <div className="flex items-center justify-center py-3 w-full bg-gray-600 text-xl font-bold rounded-md">
                    R$ {cartItem.totalPrice.toFixed(2).replace('.', ',')}
                </div>
            </div>
        </div>
    )
}