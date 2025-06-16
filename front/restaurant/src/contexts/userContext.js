"use client"
import { createContext, useContext, useEffect, useState } from "react";

const UserContext = createContext()

export function UserProvider({ children }) {
    const [user, setUser] = useState()
    const [cart, setCart] = useState()
    const [loading, setLoading] = useState(true)

    useEffect(() => {
        const storedUser = localStorage.getItem("user")
        const storedCart = localStorage.getItem("cart")

        if (storedUser) setUser(JSON.parse(storedUser))
        if (storedCart) setCart(JSON.parse(storedCart))

        setLoading(false)
    }, [])

    useEffect(() => {
        if (user) localStorage.setItem("user", JSON.stringify(user))
    }, [user])

    useEffect(() => {
        if (cart) localStorage.setItem("cart", JSON.stringify(cart))
    }, [cart])

    function logout() {
        localStorage.removeItem("user")
        localStorage.removeItem("cart")

        setUser(undefined)
        setCart(undefined)
    }

    return (
        <UserContext.Provider value={{ user, setUser, cart, setCart, loading, logout }}>
            {children}
        </UserContext.Provider>
    )
}

export function useUser() {
    return useContext(UserContext)
}