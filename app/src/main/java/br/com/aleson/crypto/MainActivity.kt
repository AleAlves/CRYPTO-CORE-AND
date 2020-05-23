package br.com.aleson.crypto

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.aleson.crypto.tools.CryptoTools

class MainActivity : AppCompatActivity() {

    private val pulickKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqRq032vsyC1w+BirZn62o50P1\n" +
            "5xWZLFXr22Cj7jDDOI1UrWOSCKAQ0j83pyB+sE7CDj7335urWL/qPc0nOoikq39G\n" +
            "lL2OGqavvm86UmyyqZgmp6lteS+Pk5ERD/n+EosrCN8dubjzQCN0YPf7OGE1G4qj\n" +
            "lrNsX5rjOpJAo5DtPQIDAQAB\n" +
            "-----END PUBLIC KEY-----"

    private val crypto = CryptoTools.Builder().rsaPublicKey(pulickKey).build()

    private lateinit var buttonEncrypt: Button
    private lateinit var buttonDeccrypt: Button
    private lateinit var plainText: EditText
    private lateinit var cipherText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonEncrypt = findViewById(R.id.encrypt_button)
        buttonDeccrypt = findViewById(R.id.decrypt_button)
        plainText = findViewById(R.id.plain_text)
        cipherText = findViewById(R.id.chiper_text)

        buttonEncrypt.setOnClickListener {
            encrypt()
        }

        buttonDeccrypt.setOnClickListener {
            decrypt()
        }

        rsa()
    }

    fun encrypt() {
        if (plainText.text.toString().isEmpty()) {
            return
        }
        cipherText.setText(crypto.aes.encrypt(plainText.text.toString()))
    }

    fun decrypt() {
        if (cipherText.text.toString().isEmpty()) {
            return
        }
        plainText.setText(crypto.aes.decrypt(cipherText.text.toString()))
    }

    fun rsa() {
        val encrypted = crypto.rsa.encrypt("Cryptography")
        Toast.makeText(this, encrypted, Toast.LENGTH_SHORT).show()
    }
}
