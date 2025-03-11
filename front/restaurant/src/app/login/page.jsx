"use client"

import Image from "next/image"
import logo from "../../../public/logo.jpg"
import { BASE_API_URL } from "@/apiUrl/apiUrl"
import axios from "axios"
import { useState } from "react"
import { useRouter } from "next/navigation"
import { useUser } from "@/contexts/userContext"

export default function Login() {

    const API_URL = `${BASE_API_URL}/users`

    const router = useRouter()

    const [email, setEmail] = useState()
    const [password, setPassword] = useState('')
    const [message, setMessage] = useState('')
    
    const {setUser} = useUser()

    async function checkUserData() {
        try {
            const {data} = await axios.get(`${API_URL}/email/${email}`)
            const {email: userEmail, password: userPassword} = data
    
            if (userEmail != email || userPassword != password) {
                setMessage("Usuário ou senha incorretos")
                return
            }
            
            setUser(data)
            router.replace("/")
        } catch (error) {
            setMessage("Usuário ou senha incorretos")
            return
        }

    }

    return (
        <div className="max-w-screen-md mx-auto flex flex-col items-center py-8 px-8 gap-8">
            <Image
                src={logo}
                alt="Logo do Restaurante"
                className="w-36 rounded-full"
            />
            <p className="font-bold text-2xl">Login</p>

            <input className="w-full px-4 py-3 rounded-xl bg-gray-900 sm:text-lg outline-none" placeholder="Email" onChange={({target}) => setEmail(target.value)} />
            <input className="w-full px-4 py-3 rounded-xl bg-gray-900 sm:text-lg outline-none" placeholder="Senha" onChange={({target}) => setPassword(target.value)} />
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