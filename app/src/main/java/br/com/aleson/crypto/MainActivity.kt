package br.com.aleson.crypto

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.aleson.crypto.tools.CryptoTools

class MainActivity : AppCompatActivity() {


    private val crypto = CryptoTools.Builder().rsaPublicKey("").build()

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
}
