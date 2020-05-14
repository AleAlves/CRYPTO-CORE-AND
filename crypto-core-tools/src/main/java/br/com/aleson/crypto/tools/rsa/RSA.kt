package br.com.aleson.crypto.tools.rsa

interface RSA {

    fun publicKey(): String

    fun encrypt(data: String): String

    fun encrypt(data: Any): String

    fun encrypt(publicKey: String, data: String): String

    fun encrypt(publicKey: String, data: Any): String

}