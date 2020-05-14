package br.com.aleson.crypto.tools.aes

data class AESSetup(
    var iterationCount: Int = 2048,
    var keyStrength: Int = 256,
    var keyLength: Int = 16,
    var saltLength: Int = 8,
    var paddingScheme: String = "AES/CBC/PKCS5Padding",
    var keyDerivationFunction: String = "PBKDF2WithHmacSHA1"
)