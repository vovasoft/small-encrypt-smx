package com.vova;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.Security;
import java.security.SecureRandom;

public class SM2Utils {

    // 使用Bouncy Castle内置的SM2椭圆曲线参数
    private static final String SM2_CURVE_NAME = "sm2p256v1";
    private static ECDomainParameters domainParameters;

    static {
        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec(SM2_CURVE_NAME);
        domainParameters = new ECDomainParameters(spec.getCurve(), spec.getG(), spec.getN(), spec.getH());
    }

    // 生成密钥对的方法和之前保持不变
    public static AsymmetricCipherKeyPair generateKeyPair() {
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keyGenParams = new ECKeyGenerationParameters(domainParameters, new SecureRandom());
        generator.init(keyGenParams);
        return generator.generateKeyPair();
    }

    // 数据加密
    public static byte[] encrypt(byte[] data, ECPublicKeyParameters publicKey) throws Exception {
        SM2Engine engine = new SM2Engine();
        CipherParameters param = new ParametersWithRandom(publicKey, new SecureRandom());
        engine.init(true, param); // 使用包含随机数源的参数初始化
        return engine.processBlock(data, 0, data.length);
    }

    // 数据解密
    public static byte[] decrypt(byte[] data, ECPrivateKeyParameters privateKey) throws Exception {
        SM2Engine engine = new SM2Engine();
        engine.init(false, privateKey);
        return engine.processBlock(data, 0, data.length);
    }

    // 数据签名
    public static byte[] sign(byte[] data, ECPrivateKeyParameters privateKey) throws Exception {
        SM2Signer signer = new SM2Signer();
        signer.init(true, privateKey);
        signer.update(data, 0, data.length);
        return signer.generateSignature();
    }

    // 签名验证
    public static boolean verify(byte[] data, byte[] signature, ECPublicKeyParameters publicKey) throws Exception {
        SM2Signer signer = new SM2Signer();
        signer.init(false, publicKey);
        signer.update(data, 0, data.length);
        return signer.verifySignature(signature);
    }
}
