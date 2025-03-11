"use client"
import { useSearchParams } from "next/navigation"
import { BASE_API_URL } from "@/apiUrl/apiUrl"
import { useEffect, useState } from "react"
import { FiChevronLeft } from "react-icons/fi";
import axios from "axios"
import Image from "next/image"
import Link from "next/link";

export default function Product() {
    const searchParams = useSearchParams()
    const productId = searchParams.get("productId")

    const API_URL = `${BASE_API_URL}/products/${productId}`

    const [product, setProduct] = useState()

    useEffect(() => {
        fetchProduct()
    }, [])

    async function fetchProduct() {
        const { data } = await axios.get(API_URL)
        console.log(data)
        setProduct(data)
    }

    return (
        <div className="container xl:max-w-screen-xl overflow-hidden w-full mx-auto px-10">
            <div className="flex sticky top-0 w-full justify-start items-center p-4 bg-black">
                <Link href={'/'}>
                    <FiChevronLeft className="w-8 h-fit"/>  
                </Link>
                <p className="mx-auto text-2xl font-bold">{product?.name}</p>
            </div>
            <Image
                src="https://res.cloudinary.com/dusgvgbgf/image/upload/v1739903483/burguer.jpg"
                alt="Cardápio Banner"
                className="w-full max-h-80 lg:object-cover object-contain rounded-md"
                width={1000}
                height={0}
                priority
            />
        </div>
    )
}