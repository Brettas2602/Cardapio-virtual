"use client"
import Image from "next/image"
import { useEffect, useState } from "react"
import { FiChevronLeft } from "react-icons/fi"

export default function ProductModal({ product, modalIsOpen, setModalIsOpen }) {

    if (!product) return null

    const [customizationValues, setCustomizationValues] = useState([])
    const [totalPrice, setTotalPrice] = useState(product.basePrice)

    function addCustomization(e, value) {
        const name = e.target.value
        setTotalPrice(totalPrice + value.additionalPrice)
    }

    return (
        <dialog open={modalIsOpen} className="z-10 text-white">
            <div onClick={() => setModalIsOpen(false)} className="fixed inset-0 bg-black/30" />
            <div className="fixed w-[90%] h-[90%] sm:w-max pt-3 rounded-2xl flex flex-col bg-[#181827] left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2 overflow-auto gap-5">

                <div className="flex items-center text-2xl gap-5 border-b border-b-gray-600">
                    <button onClick={() => setModalIsOpen(false)}>
                        <FiChevronLeft className="w-12 h-fit" />
                    </button>
                    <p>{product.name}</p>
                </div>

                <div className="flex flex-col gap-5">
                    <div className="flex flex-col px-5 items-center gap-3">
                        <Image
                            src="https://res.cloudinary.com/dusgvgbgf/image/upload/v1739903483/burguer.jpg"
                            width={1000}
                            height={0}
                            alt="burguer"
                            className="w-80 h-56 object-cover rounded-lg"
                        />
                        <p className="text-lg text-gray-300">{product.description}</p>
                    </div>

                    <form method="dialog">
                        {product.customizations.map((customization, index) =>
                            <div key={index} className="flex flex-col gap-3 mb-5">
                                <p className="bg-[#233023] px-8 py-3 text-xl font-bold">
                                    {customization.name.toUpperCase()}:
                                </p>
                                    {customization.customizationValues.map((value, index) =>
                                        <div key={index} className="flex px-8 text-lg justify-between">
                                            <div className="flex gap-5">
                                                <input type={customization.selectionType} value={value.name} id={index} name={customization.name} required={customization.isRequired} onChange={(e) => addCustomization(e, value, customization)} className="scale-[2]"/>
                                                <label htmlFor={index} >{value.name}</label>
                                            </div>
                                            {value.additionalPrice 
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

                        <button type="submit" className="sticky flex flex-col w-full py-2 mx-auto rounded-xl bg-red-600 bottom-0 text-xl font-bold">
                            <p>Adicionar</p>
                            <p>R$ {totalPrice.toFixed(2).replace('.', ',')}</p>
                        </button>
                    </form>

                </div>

            </div>
        </dialog>
    )
}