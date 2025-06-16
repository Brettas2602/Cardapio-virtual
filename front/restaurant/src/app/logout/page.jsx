"use client"

import { useUser } from "@/contexts/userContext"
import { useEffect } from "react"


export default function Logout() {

    const {logout} = useUser()

    useEffect(() => {
        logout()
    }, [])

    return (
        <div>logout</div>
    )
}