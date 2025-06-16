"use client"

import Image from "next/image"
import logo from "../../../public/logo.jpg"
import { BASE_API_URL } from "@/apiUrl/apiUrl"
import axios from "axios"
import { useState } from "react"
import { useRouter } from "next/navigation"
import { useUser } from "@/contexts/userContext"

export default function Login() {

    const API_USER_URL = `${BASE_API_URL}/users`
    const API_CART_URL = `${BASE_API_URL}/cart`

    const router = useRouter()

    const {setUser, setCart} = useUser()

    const [email, setEmail] = useState()
    const [password, setPassword] = useState('')
    const [message, setMessage] = useState('')

    async function checkUserData() {
        try {
            const {data: user} = await axios.get(`${API_USER_URL}/email/${email}`)
            console.log(user)
            const {email: userEmail, password: userPassword} = user
    
            if (userEmail != email || userPassword != password) {
                setMessage("Usuário ou senha incorretos")
                return
            }
            
            setUser(user)
            await setUserCart(user.id)
            router.replace("/")
        } catch (error) {
            setMessage("Usuário ou senha incorretos")
            console.log(error)
            return
        }

    }

    async function setUserCart(userId) {
        const {data: cart} = await axios.get(`${API_CART_URL}/${userId}`)
        console.log(cart)
        setCart(cart)
    }

    return (
        <div className="max-w-screen-md mx-auto flex flex-col items-center py-8 px-8 gap-8">
            <Image
                src={logo}
                alt="Logo do Restaurante"
                className="w-36 rounded-full"
            />
            <p className="font-bold text-2xl">Login</p>

            <input className="w-full px-4 py-3 rounded-xl bg-gray-900 sm:text-lg outline-none" type="email" placeholder="Email" onChange={({target}) => setEmail(target.value)} />
            <input className="w-full px-4 py-3 rounded-xl bg-gray-900 sm:text-lg outline-none" type="password" placeholder="Senha" onChange={({target}) => setPassword(target.value)} />
            {message && 
                <p className="w-full bg-red-600 px-4 py-3 text-red-500 text-center rounded-xl bg-opacity-50 border-4 border-red-600">{message}</p>
            }

            <button className="w-full bg-blue-600 py-5 rounded-xl font-bold text-xl" onClick={checkUserData}>
                Login
            </button>

            <p className="text-lg">
                Não possui uma conta?
                <a href="/signup" className="text-blue-400"> Cadastre-se</a>
            </p>
        </div>
    )
}