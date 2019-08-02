package cn.com.emindsoft.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;

/**
 *  用户登录的时候将用户  明文密码 + salt 进行SHA-512迭代256次后的数据存储到数据库中
 * 
 * @author tianyu
 *
 */
public class PasswordUtil {

	/**
	 * 密码明文加密存入数据库
	 * 
	 * @param salt
	 * @param plainPassword
	 * @return
	 */
	public static String hashPassword(String plainPassword, String salt) {
		DefaultHashService hashService = new DefaultHashService();
		HashRequest request = new HashRequest.Builder().setAlgorithmName("SHA-512")
				.setSource(ByteSource.Util.bytes(plainPassword)).setSalt(ByteSource.Util.bytes(salt.getBytes()))
				.setIterations(256).build();
		String hex = hashService.computeHash(request).toHex();
		return hex;
	}

	/**
	 * 生成用户的私盐，每个用户都不一样
	 * 
	 * @return
	 */
	public static String generatetPrivateSalt() {
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		ByteSource privateSalt = randomNumberGenerator.nextBytes();
		String salt = BytesToHex.fromBytesToHex(privateSalt.getBytes());
		System.out.println("私盐:" + salt);
		return salt;
	}
	/**
	 *  用户传入的密码经过加密后和数据库中存储的密码进行比较
	 * 
	 * @param dbPassword 数据库中加密的密码
	 * @param cliperPassword 密码算法加密后的密码
	 * @return
	 */
	public static boolean matchPassword(String dbPassword,String cliperPassword) {
		if(dbPassword.equals(cliperPassword)) {
			return true;
		} else {
			return false;
		}
	}
}
