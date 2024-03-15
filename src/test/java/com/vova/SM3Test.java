package com.vova;

public class SM3Test {

    public static void main(String[] args) {
        // 测试数据
        String testData = "这是一段测试文本";

        // 计算哈希值
        String hash = SM3Utils.hash(testData);
        System.out.println("原始数据: " + testData);
        System.out.println("SM3哈希值: " + hash);
    }
}
