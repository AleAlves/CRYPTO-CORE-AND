package br.com.aleson.crypto.tools.aes

import android.util.Base64
import android.util.Base64.DEFAULT
import com.google.gson.Gson
import java.nio.charset.Charset
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec


class AESTools(setup: AESSetup) : AES {

    val aesAlgorithm = "AES"
    val standarChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"

    private var cipher: Cipher? = null
    private var secretKeySpec: SecretKeySpec? = null
    private var ivParameterSpec: IvParameterSpec? = null
    private var key: String
    private var salt: String
    private var iv: ByteArray?

    init {
        salt = generateSalt(setup.saltLength)
        key = generateKey(setup.keyLength)
        iv = generateIv(setup.keyLength).toByteArray(Charset.defaultCharset())
        val factory = SecretKeyFactory.getInstance(setup.keyDerivationFunction)
        val spec = PBEKeySpec(
            key.toCharArray(),
            salt.toByteArray(),
            setup.iterationCount,
            setup.keyStrength
        )
        val secretKey = factory.generateSecret(spec)
        secretKeySpec = SecretKeySpec(secretKey.encoded, aesAlgorithm)
        cipher = Cipher.getInstance(setup.paddingScheme)
        cipher?.init(Cipher.ENCRYPT_MODE, secretKeySpec)
        ivParameterSpec = IvParameterSpec(iv)
    }


    private fun generateSalt(length: Int) = generateKey(length)

    private fun generateIv(length: Int) = generateKey(length)

    private fun generateKey(length: Int): String {
        val salt = StringBuilder()
        val random = SecureRandom()
        while (salt.length < length) {
            val index = (random.nextFloat() * standarChars.length).toInt()
            salt.append(standarChars[index])
        }
        return salt.toString()
    }

    private fun clearData(data: ByteArray): String {
        return String(data).replace("\n", "")
    }

    override fun encrypt(data: String): String {
        cipher?.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        return clearData(Base64.encode(cipher?.doFinal(data.toByteArray()), DEFAULT))
    }

    override fun decrypt(data: String?): String? {
        cipher?.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
        return cipher?.doFinal(Base64.decode(data?.toByteArray(), 0))?.let { String(it) }
    }

    override fun encrypt(data: Any): String {
        val plainData = clearDataBreakLines(Gson().toJson(data))
        cipher?.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        return clearData(Base64.encode(cipher?.doFinal(plainData.toByteArray()), DEFAULT))
    }

    override fun decrypt(data: Any?): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun key(): String {
        return this.key
    }

    override fun salt(): String {
        return this.salt
    }

    override fun iv(): String {
        return this.iv?.let { String(it, Charset.defaultCharset()) }.toString()
    }

    private fun clearDataBreakLines(data: String): String {
        return data.replace("\n", "").replace("\n", "")
    }
}