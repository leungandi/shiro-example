package com.szl.shiro.example.chapter5.test;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.format.Shiro1CryptFormat;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.szl.exalple.common.BaseTest;

/**
 * 
 * @author SongZhangLiang
 * @date 2017年8月24日 下午3:15:05
 */

public class PasswordTest {
	
	@Test
	public void testGeneratePassword() {
		String algorithmName = "SHA-256";
		String username = "liu";
		String password = "123";
		String private_salt = "root";
		//默认使用SHA-512
		DefaultHashService defaultHashService = new DefaultHashService();
		defaultHashService.setHashAlgorithmName("SHA-512");
		defaultHashService.setPrivateSalt(null); //私盐，默认无  
		defaultHashService.setGeneratePublicSalt(false);//是否生成公盐，默认false  
		defaultHashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());//用于生成公盐。默认就这个  
		defaultHashService.setHashIterations(1); 
		HashRequest request = new HashRequest.Builder().setAlgorithmName(null).setSource(ByteSource.Util.bytes(password))
				.setSalt(null).setIterations(0).build();
		
		Hash hash = defaultHashService.computeHash(request);
		System.out.println(hash.toString());
		Shiro1CryptFormat format = new Shiro1CryptFormat();
		String string = format.format(hash);
		System.out.println(string);
		
	}
	
	@Test
	public void testGeneratePassword1() {
		System.out.println(new SimpleHash("SHA-512", "123").toString());
		
	}
	

	@Test
	public void testPasswordSalt() {
	    BeanUtilsBean.getInstance().getConvertUtils().register(new EnumConverter(), JdbcRealm.SaltStyle.class);
		String configFile = "classpath:shiro-jdbc-password.ini";
		BaseTest.testBase(configFile, "liu", "123");
		Subject subject = BaseTest.getSubject();

		Assert.assertTrue(subject.isAuthenticated());

	}

	private class EnumConverter extends AbstractConverter {
		@Override
		protected String convertToString(final Object value) throws Throwable {
			return ((Enum) value).name();
		}

		@Override
		protected Object convertToType(final Class type, final Object value) throws Throwable {
			return Enum.valueOf(type, value.toString());
		}

		@Override
		protected Class getDefaultType() {
			return null;
		}

	}

	@After
	public void after() {
		BaseTest.after();
	}

}
