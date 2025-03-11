"use client"

import Image from "next/image";
import useEmblaCarousel from "embla-carousel-react";
import axios from "axios";
import { FiMapPin, FiSearch, FiClock, FiMinusCircle, FiChevronRight, FiChevronLeft, FiMenu, FiShoppingCart, FiHome } from "react-icons/fi";
import { BASE_API_URL } from "@/apiUrl/apiUrl";
import foodTable from "../../../public/food-table.jpg";
import logo from "../../../public/logo.jpg";
import { useEffect, useState } from "react";
import ProductCard from "@/components/productCard";
import ScrollSpy from "react-scrollspy-navigation";
import { useUser } from "@/contexts/userContext";
import DrawerNavigation from "@/components/drawerNavigation";
import ProductModal from "@/components/productModal";

export default function Header() {

    const API_URL = `${BASE_API_URL}/categories`

    const [categories, setCategories] = useState([])
    const [filteredItems, setFilteredItems] = useState([])
    const [selectedProduct, setSelectedProduct] = useState()

    const { user } = useUser()

    const [menuIsOpen, setMenuIsOpen] = useState(false)
    const [modalIsOpen, setModalIsOpen] = useState(false)

    const [emblaRef, emblaApi] = useEmblaCarousel({
        loop: false,
        align: "start",
    })

    useEffect(() => {
        fetchCategories()
        console.log(user)
    }, [])

    async function fetchCategories() {
        const { data } = await axios.get(API_URL)
        setCategories(data)
        setFilteredItems(data)
    }

    function searchItems({ value }) {
        if (value == "") {
            setFilteredItems(categories)
            return
        }

        let temp = categories
            .map((category) => ({
                ...category,
                products: category.products.filter((product) =>
                    product.name.toLowerCase().includes(value.toLowerCase())
                )
            }))
            .filter((category) => category.products.length > 0)
        setFilteredItems(temp)
    }


    function scrollToCategory(categoryId) {
        const element = document.getElementById(`category-${categoryId}`)
        if (element) {
            element.scrollIntoView(false)
        }
    }

    function scrollNext() {
        emblaApi?.scrollNext()
    }

    function scrollPrev() {
        emblaApi?.scrollPrev()
    }

    function scrollTo(categoryId) {
        const id = parseInt(categoryId.slice(-1));
        if (!emblaApi.slidesInView().includes(id)) {
            emblaApi?.scrollTo(id);
        }

    }

    return (
        <div className="container w-full mx-auto px-2 max-w-screen-xl">
            <div className="flex sticky top-0 w-full justify-between p-4 bg-black">
                <button onClick={() => setMenuIsOpen(true)}>
                    <FiMenu className="w-8 h-fit" />
                </button>
                <FiShoppingCart className="w-8 h-fit" />
            </div>

            <DrawerNavigation isOpen={menuIsOpen} setIsOpen={setMenuIsOpen}/>

            <Image
                src={foodTable}
                alt="Cardápio Banner"
                className="w-full h-16 sm:h-32 object-cover rounded-md"
                priority
            />

            <div className="flex flex-col sm:flex-row items-center gap-1 mt-3">
                <Image
                    src={logo}
                    alt="Logo do Restaurante"
                    className="w-28 rounded-full"
                />
                <div className="w-full flex flex-col">
                    <div className="flex flex-col sm:flex-row justify-between items-center gap-1">
                        <h1 className="text-xl sm:text-2xl font-bold">Restaurante Sabor</h1>
                    </div>
                    <div className="flex flex-col sm:flex-row sm:justify-between justify-center gap-2 items-center">
                        <div className="flex items-center text-gray-500 gap-2">
                            <FiMapPin /> <span>Av. Fictícia, Quadra 00, n° 00</span>
                        </div>
                        <p className="bg-green-700 w-fit h-fit px-1 font-semibold text-sm rounded-md">Aberto</p>
                    </div>
                </div>
            </div>

            <div className="md:flex gap-2">
                <div className="bg-gray-900 flex items-center md:w-full px-4 py-3 rounded-xl mt-4 gap-3">
                    <FiSearch className="w-6 h-fit text-gray-400" />
                    <input className="w-full bg-gray-900 sm:text-lg outline-none" placeholder="Buscar no cardápio" onChange={({ target }) => searchItems(target)} />
                </div>
                <div className="flex gap-2 mt-4 font-semibold">
                    <div className="flex items-center justify-center p-3 w-2/3 md:w-max border border-gray-700 rounded-lg gap-3 text-sm text-red-600">
                        <FiMinusCircle />
                        Pedido mínimo R$ 20,00
                    </div>
                    <div className="flex items-center justify-center p-3 w-2/5 md:w-max border border-gray-700 text-sm rounded-lg gap-3">
                        <FiClock />
                        40 - 50min
                    </div>
                </div>
            </div>

            <div className="sticky top-16">
                <div className="flex items-center mt-7 w-full bg-gray-950 py-4">
                    <button onClick={scrollPrev}>
                        <FiChevronLeft className="w-7 h-fit" />
                    </button>

                    <ScrollSpy activeClass="bg-red-600" onChangeActiveId={scrollTo}>
                        <div className="overflow-hidden w-full" ref={emblaRef}>
                            <div className="flex gap-2">
                                {categories.map((category, index, array) => (
                                    <a key={index} href={`#category-${index}`} className="bg-gray-800 flex-[0_0_50%] sm:flex-[0_0_33.33%] md:flex-[0_0_25%] min-w-max p-3 text-center rounded-lg font-bold">
                                        <button onClick={() => scrollToCategory(index)}>
                                            {category.name}
                                        </button>
                                    </a>
                                ))}
                            </div>
                        </div>
                    </ScrollSpy>

                    <button onClick={scrollNext}>
                        <FiChevronRight className="w-7 h-fit" />
                    </button>
                </div>
            </div>
            
            <ProductModal product={selectedProduct} modalIsOpen={modalIsOpen} setModalIsOpen={setModalIsOpen}/>

            {filteredItems.map((category, index) => (
                <div key={index} id={`category-${index}`} className="my-7">
                    <ProductCard category={category} setSelectedProduct={setSelectedProduct} setModalIsOpen={setModalIsOpen}/>
                </div>
            ))}
        </div>
    )
}