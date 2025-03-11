import { useEffect, useState } from "react"
import { FiHome, FiMenu, FiUser } from "react-icons/fi"
import { useUser } from "@/contexts/userContext"
import { TbReceipt } from "react-icons/tb";
import logo from "../../public/logo.jpg"
import Image from "next/image";
import { Dialog } from "@headlessui/react";

export default function DrawerNavigation({ isOpen, setIsOpen }) {

    const { user } = useUser()

    return (
        <dialog open={isOpen} className="z-10 w-full">
            <div onClick={() => setIsOpen(false)} className="fixed inset-0 bg-black/30" />
            <div className="fixed inset-y-0 w-64 md:w-72 bg-[#181827] px-7 py-7 text-white text-lg">
                <div className="flex items-center gap-2 mb-8">
                    <FiMenu className="w-8 h-fit text-gray-500" />
                    <p className="text-md text-gray-500">Menu</p>
                </div>
                <nav>
                    <ul className="flex flex-col gap-8">
                        <a href="/">
                            <li className="flex items-center gap-2">
                                <FiHome className="w-8 h-fit" />
                                Home
                            </li>
                        </a>

                        <a href={user ? "/orders" : "/login"}>
                            <li className="flex items-center gap-2">
                                <TbReceipt className="w-8 h-fit" />
                                Pedidos
                            </li>
                        </a>

                        <a href={user ? "/user" : "/login"}>
                            <li className="flex items-center gap-2">
                                <FiUser className="w-8 h-fit" />
                                Perfil
                            </li>
                        </a>
                    </ul>
                </nav>

            </div>
        </dialog>
    )
}