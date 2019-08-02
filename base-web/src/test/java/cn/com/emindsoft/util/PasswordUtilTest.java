package cn.com.emindsoft.util;

import org.apache.shiro.codec.Base64;
import org.junit.Test;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class PasswordUtilTest {

    @Test
    public void generateSaltTest() {
        PasswordUtil.generatetPrivateSalt();
    }

    @Test
    public void encryptPasswordTest() {
        String salt = PasswordUtil.generatetPrivateSalt();

        String cliperPassword = PasswordUtil.hashPassword("EmindSoft@123", "ec5b9b03e718dc5eaa194b42476a09f4");

        System.out.println("salt:" + salt);
        System.out.println("password:" + cliperPassword);
    }

    @Test
    public void encryptRSATest() {
        try {
            String password = "123456";
            System.out.println("明文:" + password);

            // 初始化公钥和私钥
            Map<String, Object> encryptKey = RSAUtil.initKey();
            RSAPublicKey publicKey = RSAUtil.getPublicKey(encryptKey);
            System.out.println("公钥:"+ BytesToHex.fromBytesToHex(publicKey.getEncoded()));

            String encodedPublicKey = Base64.encodeToString(publicKey.getEncoded());

            System.out.println("编码后公钥" + encodedPublicKey);

            RSAPrivateKey privateKey = RSAUtil.getPrivateKey(encryptKey);
            System.out.println("私钥:" + BytesToHex.fromBytesToHex(privateKey.getEncoded()));

    //		byte[] decodecPublicKey = Base64.decode(encodedPublicKey);

    //		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    //		PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(privateKey.getEncoded());
    //		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodecPublicKey);
    //
    //		RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(keySpec);
    //		System.out.println("工厂类生成公钥:" + BytesToHex.fromBytesToHex(key.getEncoded()));
    //		RSAPrivateKey keyPrivate = (RSAPrivateKey) keyFactory.generatePrivate(keySpecPrivate);
    //		System.out.println("工厂类生成私钥:" + BytesToHex.fromBytesToHex(keyPrivate.getEncoded()));

            byte[] cliperTextByte = RSAUtil.encryptData(publicKey, password.getBytes());
            String cliperText = Base64.encodeToString(cliperTextByte);
            System.out.println("加密后的密文:" + cliperText);

            byte[] decodeTextByte = RSAUtil.descryptData(privateKey,Base64.decode(cliperText));
            System.out.println("解密后的明文:" + new String(decodeTextByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
