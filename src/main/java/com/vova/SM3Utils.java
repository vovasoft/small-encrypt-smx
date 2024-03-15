package com.vova;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

public class SM3Utils {

    /**
     * 对字符串进行SM3哈希计算
     *
     * @param data 输入数据
     * @return 哈希值的十六进制字符串表示
     */
    public static String hash(String data) {
        try {
            SM3Digest digest = new SM3Digest();
            byte[] bytes = data.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            byte[] hash = new byte[digest.getDigestSize()];
            digest.doFinal(hash, 0);
            return Hex.toHexString(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
