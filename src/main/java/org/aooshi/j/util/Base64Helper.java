package org.aooshi.j.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Helper
{

	/**
	 * encode
	 * @param data
	 * @return
	 *     failure return null.
	 */
	public static byte[] Encode(byte[] data)
	{
		if (data == null)
			return null;
		
		Base64 base64 = new Base64();
		return base64.encode(data);
	}	
	
	/**
	 * encode
	 * @param input
	 * @return
	 *     failure return null.
	 */
	public static String Encode(String input)
	{
		if (input == null)
			return null;
		
		String encoding = "UTF-8";
		return Base64Helper.Encode(input,encoding);
	}
	
	/**
	 * encode
	 * @param input
	 * @return
	 *     failure return null.
	 */
	public static String Encode(String input, String encoding)
	{
		if (input == null)
			return null;
		if (encoding == null)
			return null;
		
		byte[] data;
		try {
			data = input.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		
		//编码
		return Base64Helper.EncodeAsString(data);
	}


	/**
	 * encode as utf8
	 * @param data
	 * @return
	 *     failure return null.
	 */
	public static String EncodeAsString(byte[] data)
	{
		String encoding = "UTF-8";
		return Base64Helper.EncodeAsString(data, encoding);
	}

	/**
	 * encode as string
	 * @param data
	 * @param encoding
	 * @return
	 * 	failure return null.
	 */
	public static String EncodeAsString(byte[] data,String encoding)
	{
		if (data == null)
			return null;
		
		Base64 base64 = new Base64();
		byte[] rdata = base64.encode(data);

		//
		String output = null;
		try {
			output = new String(rdata, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return output;
	}		

	/**
	 * decode
	 * @param base64String
	 * @return
	 *     failure return null.
	 */
	public static String Decode(String base64String)
	{
		if (base64String == null)
			return null;
				
		String encoding = "UTF-8";
		return Base64Helper.DecodeAsString(base64String, encoding);
	}
	
	/**
	 * decode
	 * @param base64Bytes
	 * @return
	 *     failure return null.
	 */
	public static byte[] Decode(byte[] base64Bytes)
	{
		if (base64Bytes == null)
			return null;
		
		Base64 base64 = new Base64();
		return base64.decode(base64Bytes);
	}

	/**
	 * decode as bytes
	 * @param encoding
	 * @return
	 *     failure return null.
	 */
	public static byte[] DecodeAsBytes(String base64String)
	{
		if (base64String == null)
			return null;
		
		Base64 base64 = new Base64();
		return base64.decode(base64String);
	}

	/**
	 * decode as string by UTF-8
	 * @param base64String
	 * @return
	 *     failure return null.
	 */
	public static String DecodeAsString(String base64String)
	{
		 String encoding = "UTF-8";
		 return Base64Helper.DecodeAsString(base64String, encoding);
	}

	/**
	 * decode as string
	 * @param base64String
	 * @param encoding
	 * @return
	 *     failure return null.
	 */
	public static String DecodeAsString(String base64String, String encoding)
	{
		if (base64String == null)
			return null;
		if (encoding == null)
			return null;
		
		Base64 base64 = new Base64();
		byte[] data = base64.decode(base64String);

		//
		String output = null;
		try {
			output = new String(data,encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	/**
	 * 使用utf-8编码可用的URL字符串
	 * @param urlString
	 * @return
	 *     failure return null.
	 */
	public static String EncodeUrl(String urlString)
	{		
		return Base64Helper.EncodeUrl(urlString, "UTF-8");
	}

	/**
	 * 使用Base64编码可用的URL字符串
	 * @param urlString
	 * @param encoding
	 * @return
	 *     failure return null.
	 */
	public static String EncodeUrl(String urlString, String encoding)
	{
		if (urlString == null || urlString == "")
		{
		   return urlString;
		}
		
		String base64 = Base64Helper.Encode(urlString, encoding);
				
		// "+" 换成 "-"
		// "/" 换成 "_"
		// 去掉 "="
		
		base64 = base64.replace('+', '-');
		base64 = base64.replace('/', '_');
		base64 = base64.replace("=", "");
		
		return base64;
	}
	
	/**
	 * 使用UTF-8编码将UrlBase64编码串转换为源字符串
	 * @param base64UrlString
	 * @return
	 */
	public static String DecodeUrl(String base64UrlString)
	{
		return Base64Helper.DecodeUrl(base64UrlString, "UTF-8");
	}

	/**
	 * 将UrlBase64编码串转换为源字符串
	 * @param base64UrlString
	 * @param encoding
	 * @return
	 */
	public static String DecodeUrl(String base64UrlString, String encoding)
	{
		if (base64UrlString == null || base64UrlString == "")
		{
		   return base64UrlString;
		}
		
		// "-" 换成 "+"
		// "_" 换成 "/"
		base64UrlString = base64UrlString.replace('-', '+');
		base64UrlString = base64UrlString.replace('_', '/');
		
		// 添加"="
		int mod = base64UrlString.length() % 4;
		while(mod > 0)
		{
            base64UrlString += "=";
            mod --;
		}
		
		return Base64Helper.DecodeAsString(base64UrlString, encoding);
	}
}
