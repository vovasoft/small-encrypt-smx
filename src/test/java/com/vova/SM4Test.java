package com.vova;

import org.bouncycastle.util.encoders.Hex;

import java.security.SecureRandom;

public class SM4Test {

    public static String generate128BitKeyHex() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[16]; // 128位密钥
        random.nextBytes(keyBytes);
        return Hex.toHexString(keyBytes);
    }


    public static void main(String[] args) throws Exception {
        String key = generate128BitKeyHex();
        System.out.println("生成的128位密钥（十六进制）: " + key);


//        String key = "0123456789vovaeffedcba9876543210"; // 128位密钥
        String plaintext = "这是一段测试文本";

        String encryptedText = SM4Utils.encrypt(plaintext, key);
        System.out.println("加密后: " + encryptedText);

        String decryptedText = SM4Utils.decrypt(encryptedText, key);
        System.out.println("解密后: " + decryptedText);
    }
}
