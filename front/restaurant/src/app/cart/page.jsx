"use client"

import { BASE_API_URL } from "@/apiUrl/apiUrl"
import CartProductCard from "@/components/cartProductCard"
import { useUser } from "@/contexts/userContext"
import axios from "axios"
import Image from "next/image"
import { useEffect } from "react"
import { FiChevronLeft } from "react-icons/fi"

export default function Cart() {

    const API_CART_URL = `${BASE_API_URL}/cart`

    const { user, cart, setCart, loading } = useUser()

    useEffect(() => {
        if (!loading) {
            loadCart()
        }
    }, [loading])

    if (loading) {
        return
    }


    async function loadCart() {
        const { data } = await axios.get(`${API_CART_URL}/${user.id}`)
        setCart(data)
    }

    async function submitOrder() {
        const {data: order} = await postOrder()

        const {data: orderItems} = await postOrderItems(order)

        postOrderItemCustomizations(orderItems)
    }

    async function postOrder() {
        return await axios.post(`${BASE_API_URL}/orders`, {
            "totalPrice": cart.totalPrice,
            "status": "Em preparo",
            "user": user.id
        })
    }

    async function postOrderItems(order) {
        const itemsToBeAdded = cart.cartItems.map((cartItem) => ({
            "quantity": cartItem.quantity,
            "totalPrice": cartItem.totalPrice,
            "order": order.id,
            "product": cartItem.product.id
        }))

        return await axios.post(`${BASE_API_URL}/orderItems`, itemsToBeAdded)
    }

    async function postOrderItemCustomizations(orderItems) {
        const customizationsToBeAdded = []

        cart.cartItems.forEach((cartItem, index) => {
            cartItem.cartItemCustomizations.forEach((customization) => {
                customizationsToBeAdded.push({
                    "orderItem": orderItems[index].id,
                    "customizationValue": customization.customizationValue.id
                })
            })
        })

        console.log(customizationsToBeAdded)

        return await axios.post(`${BASE_API_URL}/orderItemCustomizations`, customizationsToBeAdded)
    }

    return (
        <div className="container items-center w-full mx-auto py-2 px-2 max-w-screen-xl">
            <div className="flex items-center text-2xl font-bold justify-between pb-2 border-b-2 border-b-gray-600">
                <a href="/">
                    <FiChevronLeft className="w-12 h-fit" />
                </a>
                <p>Seu Carrinho</p>
                <div className="w-12" />
            </div>

            <div>
                {
                    cart.cartItems.length > 0
                        ?
                        <div>
                            {(cart.cartItems.map((cartItem, index) =>
                                <CartProductCard
                                    cartItem={cartItem}
                                    key={index}
                                />
                            ))}
                            <button
                                className="sticky bottom-2  left-0 w-full py-2 text-center text-xl font-bold bg-red-600 rounded-lg"
                                onClick={submitOrder}
                            >
                                <p>Total: R$ {cart.totalPrice.toFixed(2).replace('.', ',')}</p>
                                <p>Confirmar pedido</p>
                            </button>
                        </div>
                        :
                        <div className="flex flex-col items-center text-xl gap-7">
                            <p>Seu carrinho está vazio!</p>

                            <a href="/" className="bg-blue-600 px-3 py-3 rounded-2xl">
                                Ver produtos
                            </a>
                        </div>
                }
            </div>

        </div>
    )
}