
# Crypto Core Android

## Description
 Tool for AES and RSA Cryptography.
 See also: [https://github.com/AleAlves/BASE-TYPESCRIPT-API](https://github.com/AleAlves/BASE-TYPESCRIPT-API)

## Requirements
-   MinSdk 21+ (Android 5.0)

## Installation

This module is provided by JitPack.io

```css

allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

```
In the app's level gradle:

```css

dependencies {
	implementation 'com.github.AleAlves:CRYPTO-CORE-AND:1.0.0'
}

```


## Usage
The avarege use requires only the RSA's public key.

```koltin

private val publicKey = "-----BEGIN PUBLIC KEY-----
MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqRq032vsyC1w+BirZn62o50P1
5xWZLFXr22Cj7jDDOI1UrWOSCKAQ0j83pyB+sE7CDj7335urWL/qPc0nOoikq39G
lL2OGqavvm86UmyyqZgmp6lteS+Pk5ERD/n+EosrCN8dubjzQCN0YPf7OGE1G4qj
lrNsX5rjOpJAo5DtPQIDAQAB
-----END PUBLIC KEY-----"

private val crypto = CryptoTools.Builder().rsaPublicKey(publicKey).build()

```

AES's use:

```koltin

val originalMessage = "Cryptography"
val encryptedText = crypto.aes.encrypt(plainText)
val decryptedMessage = = crypto.aes.decrypt(encryptedText)

```

RSA's can only encrypt data at the moment:

```koltin

val encrypted = crypto.rsa.encrypt("Cryptography")

```
