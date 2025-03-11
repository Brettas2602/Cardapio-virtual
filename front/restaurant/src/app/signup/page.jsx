"use client"

import Image from "next/image";
import logo from "../../../public/logo.jpg"
import { useState } from "react";
import { BASE_API_URL } from "@/apiUrl/apiUrl";
import axios from "axios";
import { useRouter } from "next/navigation";

export default function SignUp() {

    const API_URL = `${BASE_API_URL}/users`

    const router = useRouter()
    
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [message, setMessage] = useState()

    async function saveUser() {
        const credentialsIsValid = await checkCredentials()

        if (credentialsIsValid) {
            await axios.post(API_URL, {
                email: email,
                password: password
            })
            router.replace('/')
        }
    }

    async function checkCredentials() {
        if (email.trim() == '' || password.trim() == '' || confirmPassword.trim() == '') {
            setMessage("Todos os campos devem ser preenchidos")
            return false
        }

        if (password != confirmPassword) {
            setMessage("As senhas não correspondem")
            return false
        }

        try {
            await axios.get(`${API_URL}/email/${email}`)
            setMessage('Esse email já existe')
            return false
        } catch (error) {
            return true
        }

    }

    return (
        <div className="max-w-screen-md mx-auto flex flex-col items-center py-8 px-8 gap-8">
            <Image
                src={logo}
                alt="Logo do Restaurante"
                className="w-36 rounded-full"
            />
            <p className="font-bold text-2xl">Cadastro</p>

            <input className="w-full px-4 py-3 rounded-xl bg-gray-900 sm:text-lg outline-none" placeholder="Email" onChange={({ target }) => setEmail(target.value)} />
            <input className="w-full px-4 py-3 rounded-xl bg-gray-900 sm:text-lg outline-none" placeholder="Senha" onChange={({ target }) => setPassword(target.value)} />
            <input className="w-full px-4 py-3 rounded-xl bg-gray-900 sm:text-lg outline-none" placeholder="Confirmar senha" onChange={({ target }) => setConfirmPassword(target.value)} />
            {message &&
                <p className="w-full bg-red-600 px-4 py-3 text-red-500 text-center rounded-xl bg-opacity-50 border-4 border-red-600">{message}</p>
            }

            <button className="w-full bg-blue-600 py-5 rounded-xl font-bold text-xl" onClick={saveUser}>
                Cadastrar-se
            </button>

            <p className="text-lg">
                Já possui uma conta?
                <a href="/login" className="text-blue-400"> Faça login</a>
            </p>
        </div>
    )
}