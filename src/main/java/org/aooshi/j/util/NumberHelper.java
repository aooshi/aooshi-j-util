package org.aooshi.j.util;

public class NumberHelper {

	public final static int MaxByte = 127;
    public final static int MinByte = -128;
	
	public final static int MaxUByte = 255;
    public final static int MinUByte = 0;
	
	public final static int MaxInt16 = 32767;
    public final static int MinInt16 = -32768;
	
	public final static int MaxUInt16 = 65535;
    public final static int MinUInt16 = 0;
	
	public final static int MaxInt32 = 2147483647;
    public final static int MinInt32 = -2147483648;
	
	public final static long MaxUInt32 = 4294967295L;
    public final static long MinUInt32 = 0;
	
	public final static long MaxInt64 = 9223372036854775807L;
    public final static long MinInt64 = -9223372036854775808L;
	
	public final static java.math.BigInteger MaxUInt64 = new java.math.BigInteger("18446744073709551615");
    public final static java.math.BigInteger MinUInt64 = new java.math.BigInteger("0");
	
	public final static float MaxFloat = Float.MAX_VALUE;
    public final static float MinFloat = Float.MIN_VALUE;
	
	public final static double MaxDouble = Double.MAX_VALUE;
    public final static double MinDouble = Double.MIN_VALUE;

	/**
	 * 将16进制数值转换为Byte数组
	 * 
	 * @param hexString
	 * 		16进制码
	 * @return
	 * 		转换结果
	 */
	public static byte[] HexToBytes(String hexString) {
		hexString = hexString.toUpperCase();
		int len = hexString.length() / 2;
		if (hexString.length() % 2 != 0) {
			throw new java.lang.IllegalArgumentException(
					"hexString not hex string");
		}

		byte[] result = new byte[len];
		char[] achar = hexString.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;

		// //
		// byte[] ret = new byte[hexString.length() / 2];
		// for (int i = 0, l = ret.length; i < l; i++)
		// {
		// char[] chrs = new char[2];
		// chrs[0] = hexString.charAt(i);
		// chrs[1] = hexString.charAt(i + 1);
		//
		// String str = new String(chrs);
		//
		// ret[i] = Byte.valueOf(str, 16);
		// }
		// return ret;
	}

	private static byte toByte(char c) {
		byte b = (byte) ("0123456789ABCDEF".indexOf(c));
		return b;
	}

	/**
	 * 将Byte数组转换为16进制字符串
	 * 
	 * @param bytes
	 * 	待转换数据
	 * @return String
	 * 	结果
	 */
	public static String BytesToHex(byte[] bytes) {
		StringBuffer sb = new StringBuffer(bytes.length);
		String sTemp;
		for (int i = 0; i < bytes.length; i++) {
			sTemp = Integer.toHexString(0xFF & bytes[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();

		// char[] chars = new char[bytes.length * 2];
		// for (int i = 0, l = bytes.length; i < l; i++)
		// {
		// chars[i * 2] = HexToChar(bytes[i] >> 4);
		// chars[i * 2 + 1] = HexToChar(bytes[i]);
		// }
		// String str2 = new String(chars);
		// return str2;
	}

	// private static char HexToChar(int a)
	// {
	// a = a & 0xf;
	// return (char)((a > 9) ? a - 10 + 0x61 : a + 0x30);
	// }
	

	/**
	 * 检查是否超过 无符号 short 范围
	 * @param value
	 * 	检查值
	 */
	public static void CheckUInt16(int value)
	{
		if (value > MaxInt16 || value < MinInt16)
		{
			throw new java.lang.IllegalArgumentException("value");
		}
	}
	
	/**
	 * 检查是否超过 无符号 int 范围
	 * @param value
	 * 	检查值
	 */
	public static void CheckUInt32(long value)
	{
		if (value > MaxUInt32 || value < MinUInt32)
		{
			throw new java.lang.IllegalArgumentException("value");
		}
	}
	
	/**
	 * 检查是否超过 无符号 Byte 范围
	 * @param value
	 * 	检查值
	 */
	public static void CheckUByte(int value)
	{
		if (value > MaxUByte || value < MinUByte)
		{
			throw new java.lang.IllegalArgumentException("value");
		}
	}
}
