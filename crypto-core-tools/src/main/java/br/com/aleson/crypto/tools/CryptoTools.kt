package br.com.aleson.crypto.tools

import br.com.aleson.crypto.tools.aes.AES
import br.com.aleson.crypto.tools.aes.AESSetup
import br.com.aleson.crypto.tools.aes.AESTools
import br.com.aleson.crypto.tools.rsa.RSA
import br.com.aleson.crypto.tools.rsa.RSASetup
import br.com.aleson.crypto.tools.rsa.RSATools

class CryptoTools(aesSetup: AESSetup, rsaSetup: RSASetup) {

    var aes: AES = AESTools(aesSetup)
    var rsa: RSA = RSATools(rsaSetup)

    class Builder {

        private var aesSetup = AESSetup()
        private var rsaSetup = RSASetup()
        private val tools = CryptoTools(aesSetup, rsaSetup)


        fun rsaPublicKey(publicKey: String): Builder = apply {
            this.rsaSetup.publicKey = publicKey
        }

        fun rsaPaddingScheme(publicKey: String): Builder = apply {
            this.rsaSetup.paddingScheme = publicKey
        }

        fun aesInteractionCount(interactions: Int): Builder = apply {
            aesSetup.iterationCount = interactions
        }

        fun aesKeyStrength(strength: Int): Builder = apply {
            aesSetup.keyStrength = strength
        }

        fun aesKeyLength(length: Int): Builder = apply {
            aesSetup.keyLength = length
        }

        fun aesSaltLength(length: Int): Builder = apply {
            aesSetup.saltLength = length
        }

        fun build(): CryptoTools {
            return tools
        }
    }

}