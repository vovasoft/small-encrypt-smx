package com.vova;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;

import java.security.SecureRandom;

public class SM4Utils {

    // CBC模式下的初始化向量，实际使用中应随机生成
    private static final byte[] iv = new byte[16];

    // 加密
    public static String encrypt(String plaintext, String key) throws Exception {
        byte[] keyBytes = Hex.decode(key);
        byte[] plaintextBytes = plaintext.getBytes("UTF-8");

        SM4Engine engine = new SM4Engine();
        CBCBlockCipher blockCipher = new CBCBlockCipher(engine);
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(blockCipher);
        KeyParameter keyParameter = new KeyParameter(keyBytes);
        ParametersWithIV parametersWithIV = new ParametersWithIV(keyParameter, iv);

        cipher.init(true, parametersWithIV);
        byte[] output = new byte[cipher.getOutputSize(plaintextBytes.length)];
        int length1 = cipher.processBytes(plaintextBytes, 0, plaintextBytes.length, output, 0);
        cipher.doFinal(output, length1);

        return Hex.toHexString(output);
    }

    // 解密
    public static String decrypt(String ciphertext, String key) throws Exception {
        byte[] keyBytes = Hex.decode(key);
        byte[] ciphertextBytes = Hex.decode(ciphertext);

        SM4Engine engine = new SM4Engine();
        CBCBlockCipher blockCipher = new CBCBlockCipher(engine);
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(blockCipher);
        KeyParameter keyParameter = new KeyParameter(keyBytes);
        ParametersWithIV parametersWithIV = new ParametersWithIV(keyParameter, iv);

        cipher.init(false, parametersWithIV);
        byte[] output = new byte[cipher.getOutputSize(ciphertextBytes.length)];
        int length1 = cipher.processBytes(ciphertextBytes, 0, ciphertextBytes.length, output, 0);
        int length2 = cipher.doFinal(output, length1);

        return new String(output, 0, length1 + length2, "UTF-8");
    }
}
