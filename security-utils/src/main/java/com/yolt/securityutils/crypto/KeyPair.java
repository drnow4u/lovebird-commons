package com.yolt.securityutils.crypto;

public class KeyPair {
    public PrivateKey getPrivateKey() {
        return PrivateKey.from("");
    }

    public PublicKey getPublicKey() {
        return PublicKey.createPublicKeyFromPem("");
    }
}
