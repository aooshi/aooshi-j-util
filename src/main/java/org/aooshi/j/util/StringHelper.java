package org.aooshi.j.util;

import java.io.UnsupportedEncodingException;


public class StringHelper {
        
	
	/**
	 * 验证是否为空字符串  null / empty
	 * @param input
	 * 	输入
	 * @return
	 * 	是否为空
	 */
	public static boolean isEmpty(String input)
	{
		if (input == null)
			return true;
		
		if ("".equals(input))
			return true;

		return false;
	}
	
	/**
	 * 验证是否为空字符串或空格 null / empty / space
	 * @param input
	 * 	输入
	 * @return
	 *  结果
	 */
	public static boolean isEmptyOrSpace(String input)
	{
		if (isEmpty(input))
			return true;
		
		return "".equals(input.trim());
	}

	/**
	 * 验证字符串是否为全数字
	 * @param str 输入
	 * @return
	 *  结果
	 */
    public static boolean isNumeric(String str){
    	if (str == null) return false;
        for (int i = str.length();--i>=0;){    
         if (!Character.isDigit(str.charAt(i))){  
          return false;  
         }  
        }  
        return true;  
    }

	/**
	 * 验证字符串是否为数字与字母
	 * @param str 输入
	 * @return
	 *  结果
	 */
    public static boolean isLetterOrDigit(String str){
    	if (str == null) 
    		return false;
    	if (str == "")
    		return false;
    	
        for (int i = str.length();--i>=0;){    
         if (!Character.isLetterOrDigit(str.charAt(i))){  
          return false;
         }  
        }  
        return true;  
    }  
    
	
    /**
     * 以UTF8字符集将字符串转为字节数组,转换失败返回 null
	 * @return
	 * 结果
	 *
	 * @param data 数据
     */
    public static byte[] getBytes(String data){
        return getBytes(data, "UTF-8");
    }
        
    /**
     * 字符串转为字节数组,转换失败返回
	 * @param charset
	 * 	字符集
	 *
	 * @param data
	 *  数据
	 *
	 * @return
	 *  结果
     */
    public static byte[] getBytes(String data, String charset){
    	if (data == null)
    		return null;
    	
    	if ("".equals(data))
    		return data.getBytes();

        try {
            return data.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
        	return null;
        }
    }

} 