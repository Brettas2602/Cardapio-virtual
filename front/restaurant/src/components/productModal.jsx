"use client"
import Image from "next/image"
import { useEffect, useState } from "react"
import { FiChevronLeft } from "react-icons/fi"
import { BASE_API_URL } from "@/apiUrl/apiUrl"
import axios from "axios"
import { useUser } from "@/contexts/userContext"
import { useRouter } from "next/navigation"

export default function ProductModal({ product, modalIsOpen, setModalIsOpen }) {

    if (!product) return null

    const API_CART_ITEM_URL = `${BASE_API_URL}/cartItems`
    const API_CART_ITEM_CUSTOMIZATION_URL = `${BASE_API_URL}/cartItemCustomizations`

    const {user, cart} = useUser()

    const router = useRouter()

    const [selectedCustomizations, setSelectedCustomizations] = useState({})
    const [totalPrice, setTotalPrice] = useState(product.basePrice)

    function handleCustomization(e, value) {
        const input = e.target

        setSelectedCustomizations((prevCustomizations) =>
            addCustomization(prevCustomizations, input, value)
        )

        setTotalPrice((prevPrice) =>
            (input.checked)
                ? prevPrice + value.additionalPrice
                : prevPrice - value.additionalPrice
        )
    }

    function addCustomization(prevCustomizations, { name, type, checked }, value) {
        const currentCustomizationValues = prevCustomizations[name] || []

        if (type === "radio") {
            return { ...prevCustomizations, [name]: value.id }
        }

        if (type === "checkbox") {
            if (checked) {
                return { ...prevCustomizations, [name]: [...currentCustomizationValues, value.id] }
            } else {
                return { ...prevCustomizations, [name]: currentCustomizationValues.filter((item) => item !== value.id) }
            }
        }

        return prev
    }

    async function submitOrder() {
        if (user) {
            const {data: cartItem} = await postCartItem()
            cartItem.cartItemCustomizations = []
    
            cartItem.cartItemCustomizations = (await postCartItemCustomizations(cartItem)).data
        }else {
            router.push("/login")
        }
    }
    
    async function postCartItem() {
        return await axios.post(API_CART_ITEM_URL, {
            quantity: 1,
            totalPrice: totalPrice,
            cart: cart.id,
            product: product.id
        })
    }
    
    async function postCartItemCustomizations(cartItem) {
        const customizationsToBeAdded = []

        for (const [key, value] of Object.entries(selectedCustomizations)) {
                if (Array.isArray(value)) {
                    value.forEach((item) => customizationsToBeAdded.push({
                        cartItem: cartItem.id,
                        customizationValue: item
                    }))
                }else {
                    customizationsToBeAdded.push({
                        cartItem: cartItem.id,
                        customizationValue: value
                    })
                }
            }

        return await axios.post(API_CART_ITEM_CUSTOMIZATION_URL, customizationsToBeAdded)
    }

    return (
        <dialog open={modalIsOpen} className="z-10 text-white">
            <div onClick={() => setModalIsOpen(false)} className="fixed inset-0 bg-black/30" />
            <div className="fixed w-[90%] h-[90%] md:max-w-3xl pt-3 md:px-3 rounded-2xl flex flex-col bg-[#181827] left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 overflow-auto gap-5">

                <div className="flex items-center text-2xl gap-5 border-b border-b-gray-600">
                    <button onClick={() => setModalIsOpen(false)}>
                        <FiChevronLeft className="w-12 h-fit" />
                    </button>
                    <p>{product.name}</p>
                </div>

                <div className="flex flex-col md:flex-row gap-5">
                    <div className="flex flex-col md:max-w-[50%] px-5 items-center gap-3">
                        <Image
                            src="https://res.cloudinary.com/dusgvgbgf/image/upload/v1739903483/burguer.jpg"
                            width={1000}
                            height={0}
                            alt="burguer"
                            className="w-80 h-56 md:w-max md:h-fit object-cover rounded-lg"
                        />
                        <p className="text-lg text-gray-300">{product.description}</p>
                    </div>

                    <form method="dialog" onSubmit={submitOrder}>
                        {product.customizations.map((customization, index) =>
                            <div key={index} className="flex flex-col gap-3 mb-5">
                                <p className="bg-[#233023] px-8 py-3 text-xl font-bold">
                                    {customization.name.toUpperCase()}:
                                    <span className="text-base'">
                                        {customization.maxSelections ? ` (Escolha até ${customization.maxSelections})` : ""}
                                    </span>
                                </p>
                                {customization.customizationValues.map((value, index) =>
                                    <div key={index} className="flex px-8 text-lg justify-between">
                                        <div className="flex gap-5">
                                            <input
                                                type={customization.selectionType}
                                                value={value.name}
                                                id={index}
                                                name={customization.name}
                                                required={customization.isRequired}
                                                onChange={(e) => handleCustomization(e, value)}
                                                disabled={
                                                    customization.selectionType === "checkbox" &&
                                                    (
                                                        selectedCustomizations[customization.name]?.length >= customization?.maxSelections &&
                                                        !selectedCustomizations[customization.name]?.includes(value.id)
                                                    )
                                                }
                                                className="scale-[2]" />
                                            <label htmlFor={index} >{value.name}</label>
                                        </div>
                                        {
                                            value.additionalPrice
                                            ?
                                            <p>
                                                + R$ {value.additionalPrice.toFixed(2).replace('.', ',')}
                                            </p>
                                            :
                                            <div></div>
                                        }
                                    </div>
                                )}
                            </div>
                        )}

                        <button 
                            type="submit" 
                            className="sticky flex flex-col w-full py-2 mx-auto rounded-xl bg-red-600 bottom-0 text-xl font-bold">
                            <p>Adicionar</p>
                            <p>R$ {totalPrice.toFixed(2).replace('.', ',')}</p>
                        </button>
                    </form>

                </div>

            </div>
        </dialog>
    )
}