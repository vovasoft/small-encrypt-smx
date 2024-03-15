package com.vova;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;

public class SM2Test {
    public static void main(String[] args) throws Exception {
        // 生成密钥对
        AsymmetricCipherKeyPair keyPair = SM2Utils.generateKeyPair();
        ECPublicKeyParameters publicKey = (ECPublicKeyParameters) keyPair.getPublic();
        ECPrivateKeyParameters privateKey = (ECPrivateKeyParameters) keyPair.getPrivate();

        // 测试数据
        String testData = "这是一段测试文本";
        System.out.println("原始数据: " + testData);
        byte[] testDataBytes = testData.getBytes("UTF-8");

        // 数据加密
        byte[] encryptedData = SM2Utils.encrypt(testDataBytes, publicKey);
        System.out.println("加密后的数据: " + new String(encryptedData));

        // 数据解密
        byte[] decryptedData = SM2Utils.decrypt(encryptedData, privateKey);
        System.out.println("解密后的数据: " + new String(decryptedData, "UTF-8"));

        // 数据签名
        byte[] signature = SM2Utils.sign(testDataBytes, privateKey);
        System.out.println("签名: " + new String(signature));

        // 签名验证
        boolean isVerified = SM2Utils.verify(testDataBytes, signature, publicKey);
        System.out.println("签名验证结果: " + isVerified);
    }
}
