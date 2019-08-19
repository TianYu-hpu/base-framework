package cn.com.emindsoft.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * Unit test for simple App.
 */
@Slf4j
public class PasswordUtilTest {

    @Test
    public void generateSaltTest() {
        PasswordUtil.generatetPrivateSalt();
    }

    @Test
    public void encryptPasswordTest() {
        String salt = PasswordUtil.generatetPrivateSalt();

        String cliperPassword = PasswordUtil.hashPassword("EmindSoft@123", "ebfa50fb91c526a6e4fc31c7ba22403d");

        log.info("salt:" + salt);
        log.debug("debug =====================");
        log.info("info =======================");
        log.warn("warn =======================");
        log.error("error =====================");
        log.info("password:" + cliperPassword);
    }

    @Test
    public void encryptTranslateCloudPasswordTest() {
        String salt = "11cc343c7000b61c85bc831097b5b61a";
        String password = new SimpleHash("md5", "ChenNan@123", ByteSource.Util.bytes("chennan" + salt), 2).toHex();
        log.info("salt:" + salt);
        log.info("password:" + password);
    }

    @Test
    public void encryptRSATest() {
        try {
            String password = "123456";
            log.info("明文:" + password);

            // 初始化公钥和私钥
            Map<String, Object> encryptKey = RSAUtil.initKey();
            RSAPublicKey publicKey = RSAUtil.getPublicKey(encryptKey);
            log.info("公钥:"+ BytesToHex.fromBytesToHex(publicKey.getEncoded()));

            String encodedPublicKey = Base64.encodeToString(publicKey.getEncoded());

            log.info("编码后公钥" + encodedPublicKey);

            RSAPrivateKey privateKey = RSAUtil.getPrivateKey(encryptKey);
            log.info("私钥:" + BytesToHex.fromBytesToHex(privateKey.getEncoded()));

    //		byte[] decodecPublicKey = Base64.decode(encodedPublicKey);

    //		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    //		PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(privateKey.getEncoded());
    //		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodecPublicKey);
    //
    //		RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(keySpec);
    //		log.info("工厂类生成公钥:" + BytesToHex.fromBytesToHex(key.getEncoded()));
    //		RSAPrivateKey keyPrivate = (RSAPrivateKey) keyFactory.generatePrivate(keySpecPrivate);
    //		log.info("工厂类生成私钥:" + BytesToHex.fromBytesToHex(keyPrivate.getEncoded()));

            byte[] cliperTextByte = RSAUtil.encryptData(publicKey, password.getBytes());
            String cliperText = Base64.encodeToString(cliperTextByte);
            log.info("加密后的密文:" + cliperText);

            byte[] decodeTextByte = RSAUtil.descryptData(privateKey,Base64.decode(cliperText));
            log.info("解密后的明文:" + new String(decodeTextByte));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
