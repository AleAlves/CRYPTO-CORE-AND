package br.com.aleson.crypto.tools.rsa

import android.util.Base64
import br.com.aleson.crypto.tools.exception.CryptoNoPublicKeyProvidedException
import com.google.gson.Gson
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

class RSATools(private var rsaSetup: RSASetup) : RSA {

    val rsaKdf = "RSA"
    val publicKeyClenRegex = "(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)".toRegex()

    private fun clearPublicKey(publicKey: String): String {
        return publicKey.replace(publicKeyClenRegex, "")
    }

    private fun clearDataBreakLines(data: String): String {
        return data.replace("\n", "").replace("\n", "")
    }

    private fun encodeBase64(encrypted: ByteArray?): String {
        return Base64.encodeToString(encrypted, 0).replace("\n", "")
    }

    override fun publicKey(): String {
        return rsaSetup.publicKey
    }

    override fun encrypt(data: String): String {
        if (rsaSetup.publicKey.isEmpty()) {
            throw CryptoNoPublicKeyProvidedException()
        }
        val publicKey = clearPublicKey(this.rsaSetup.publicKey)
        val plainData = clearDataBreakLines(data)
        val keyBytes = Base64.decode(publicKey, Base64.DEFAULT)
        val spec = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance(rsaKdf)
        val pk = kf.generatePublic(spec)
        var cipherText: ByteArray?
        val cipher = Cipher.getInstance(rsaSetup.paddingScheme)
        cipher.init(Cipher.ENCRYPT_MODE, pk)
        cipherText = cipher.doFinal(plainData.toByteArray())
        return encodeBase64(cipherText)
    }

    override fun encrypt(data: Any): String {
        if (rsaSetup.publicKey.isEmpty()) {
            throw CryptoNoPublicKeyProvidedException()
        }
        val publicKey = clearPublicKey(this.rsaSetup.publicKey)
        val plainData = clearDataBreakLines(Gson().toJson(data))
        val keyBytes = Base64.decode(publicKey, Base64.DEFAULT)
        val spec = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance(rsaKdf)
        val pk = kf.generatePublic(spec)
        val cipherText: ByteArray?
        val cipher = Cipher.getInstance(rsaSetup.paddingScheme)
        cipher.init(Cipher.ENCRYPT_MODE, pk)
        cipherText = cipher.doFinal(plainData.toByteArray())
        return encodeBase64(cipherText)
    }

    override fun encrypt(publicKey: String, data: String): String {
        val key = clearPublicKey(publicKey)
        val plainData = clearDataBreakLines(data)
        val keyBytes = Base64.decode(key, Base64.DEFAULT)
        val spec = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance(rsaKdf)
        val pk = kf.generatePublic(spec)
        val cipherText: ByteArray?
        val cipher = Cipher.getInstance(rsaSetup.paddingScheme)
        cipher.init(Cipher.ENCRYPT_MODE, pk)
        cipherText = cipher.doFinal(plainData.toByteArray())
        return encodeBase64(cipherText)
    }

    override fun encrypt(publicKey: String, data: Any): String {
        val key = clearPublicKey(publicKey)
        val plainData = clearDataBreakLines(Gson().toJson(data))
        val keyBytes = Base64.decode(key, Base64.DEFAULT)
        val spec = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance(rsaKdf)
        val pk = kf.generatePublic(spec)
        val cipherText: ByteArray?
        val cipher = Cipher.getInstance(rsaSetup.paddingScheme)
        cipher.init(Cipher.ENCRYPT_MODE, pk)
        cipherText = cipher.doFinal(plainData.toByteArray())
        return encodeBase64(cipherText)
    }


}