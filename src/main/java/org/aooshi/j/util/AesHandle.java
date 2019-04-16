package org.aooshi.j.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * default:
 * 
 * cipher: AES/CBC/PKCS5Padding ,charset : utf-8 ,iv =0000000000000000
 * 
 * https://blog.csdn.net/l1028386804/article/details/42706039
 */
public class AesHandle {

	private static final String KEY_ALGORITHM = "AES";

	private String iv = "0000000000000000";
	private String password = "";
	// private int size = 256;
	private String charset = "UTF-8";
	private String cipher = "AES/CBC/PKCS5Padding";
	private Exception lastException = null;

	public Exception getLastException() {
		return lastException;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getCipher() {
		return cipher;
	}

	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	/**
	 * initialize new instance
	 * 
	 * @param password
	 */
	public AesHandle(String password) {
		this.password = password;
	}
	
	/**
	 * initialize new instance
	 * 
	 * @param password
	 * @param iv
	 */
	public AesHandle(String password,String iv) {
		this.password = password;
		this.iv = iv;
	}

	/**
	 * encrypt
	 * 
	 * @param content
	 * @return failure return null
	 */
	public String encryptToHex(String content) {
		byte[] data = this.stringToBytes(content);
		if (data == null)
			return null;

		data = encrypt(data);
		if (data == null)
			return null;

		return NumberHelper.BytesToHex(data);
	}

	/**
	 * encrypt
	 * 
	 * @param data
	 * @return failure return null
	 */
	public String encryptToHex(byte[] data) {

		data = encrypt(data);
		if (data == null)
			return null;

		return NumberHelper.BytesToHex(data);
	}

	/**
	 * encrypt
	 * 
	 * @param content
	 * @return failure return null
	 */
	public String encryptToBase64(String content) {
		byte[] data = this.stringToBytes(content);
		if (data == null)
			return null;

		data = encrypt(data);
		if (data == null)
			return null;

		 String encodeString = Base64Helper.EncodeAsString(data);
		 return encodeString;
	}

	/**
	 * encrypt
	 * 
	 * @param data
	 * @return failure return null
	 */
	public String encryptToBase64(byte[] data) {

		data = encrypt(data);
		if (data == null)
			return null;

		 String encodeString = Base64Helper.EncodeAsString(data);
		 return encodeString;
	}

	/**
	 * encrypt
	 * 
	 * @param data
	 * @return failure return null
	 */
	public byte[] encrypt(String content) {
		byte[] data = this.stringToBytes(content);
		if (data == null)
			return null;

		data = encrypt(data);

		return data;
	}

	/**
	 * encrypt
	 * 
	 * @param data
	 * @return failure return null
	 */
	public byte[] encrypt(byte[] data) {
		if (data == null)
			return null;

		byte[] encrypted = null;
		try {
			// "AES/CBC/PKCS5Padding"
			Cipher cipherInst = Cipher.getInstance(this.cipher);
			byte[] raw = this.password.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			IvParameterSpec ivSpec = new IvParameterSpec(this.iv.getBytes());
			cipherInst.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
			encrypted = cipherInst.doFinal(data);
		} catch (java.lang.Exception e) {
			this.lastException  = e;
			encrypted = null;
		}

		return encrypted;
	}

	/**
	 * decrypt
	 * 
	 * @param hexcontent
	 * @return failure return null
	 */
	public byte[] decryptHexToBytes(String hexcontent) {

		byte[] data = NumberHelper.HexToBytes(hexcontent);
		if (data == null)
			return null;

		data = decrypt(data);

		return data;
	}

	/**
	 * decrypt
	 * 
	 * @param hexcontent
	 * @return failure return null
	 */
	public String decryptHexToString(String hexcontent) {

		byte[] data = NumberHelper.HexToBytes(hexcontent);
		if (data == null)
			return null;

		data = decrypt(data);
		if (data == null)
			return null;

		return this.bytesToString(data);
	}

	/**
	 * decrypt
	 * 
	 * @param base64
	 * @return failure return null
	 */
	public byte[] decryptBase64ToBytes(String base64) {

		byte[] data = Base64Helper.DecodeAsBytes(base64);
		if (data == null)
			return null;

		data = decrypt(data);

		return data;
	}

	/**
	 * decrypt
	 * 
	 * @param base64
	 * @return failure return null
	 */
	public String decryptBase64ToString(String base64) {

		byte[] data = Base64Helper.DecodeAsBytes(base64);
		if (data == null)
			return null;

		data = decrypt(data);
		if (data == null)
			return null;

		return this.bytesToString(data);
	}

	/**
	 * decrypt
	 * 
	 * @param data
	 * @return failure return null
	 */
	public String decryptToString(byte[] data) {
		data = decrypt(data);

		return this.bytesToString(data);
	}

	/**
	 * decrypt
	 * 
	 * @param data
	 * @return failure return null
	 */
	public byte[] decrypt(byte[] data) {
		if (data == null)
			return null;

		byte[] encrypted = null;
		try {
			// "AES/CBC/PKCS5Padding"
			Cipher cipherInst = Cipher.getInstance(this.cipher);
			byte[] raw = this.password.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM);
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			IvParameterSpec ivSpec = new IvParameterSpec(this.iv.getBytes());
			cipherInst.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
			encrypted = cipherInst.doFinal(data);
		} catch (java.lang.Exception e) {
			this.lastException  = e;
			encrypted = null;
		}

		return encrypted;
	}

	private String bytesToString(byte[] data) {
		String content = null;
		if (data != null) {
			try {
				content = new String(data, this.charset);
			} catch (UnsupportedEncodingException e) {
				// e.printStackTrace();
				this.lastException  = e;
				content = null;
			}
		}
		return content;
	}

	private byte[] stringToBytes(String content) {
		byte[] data = null;
		if (content != null) {
			try {
				data = content.getBytes(this.charset);
			} catch (UnsupportedEncodingException e) {
				// e.printStackTrace();
				this.lastException  = e;
				data = null;
			}
		}
		return data;
	}
}
