package br.com.aleson.crypto.tools.exception

class CryptoNoPublicKeyProvidedException(override var message: String = "No public key provided") : Exception()