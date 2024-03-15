# small-encrypt-smx
国密加密算法，包含SM2，SM3，SM4

## 首先需要增加pom依赖

```xml
<dependencies>
        <!-- Add Bouncy Castle dependency for cryptographic operations -->
        <dependency>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcprov-jdk15on</artifactId>
            <version>1.68</version>
        </dependency>
    </dependencies>
```