package com.example.kncr

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform