package org.aooshi.j.util;

import java.io.UnsupportedEncodingException;

/**
 * big-endian
 *
 */

public class BaseDataConverter {

	public static byte[] getBytes(boolean x) {
		return new byte[] { (byte) (x ? 1 : 0) };
	}

	public static byte[] getBytes(char c) {
		return new byte[] { (byte) (c & 0xff), (byte) (c >> 8 & 0xff) };
	}

	public static byte[] getBytes(short x) {
		return new byte[] { (byte) (x >>> 8), (byte) x };
	}

	public static byte[] getBytes(int x) {
		return new byte[] { (byte) (x >>> 24), (byte) (x >>> 16),
				(byte) (x >>> 8), (byte) x };
	}

	public static byte[] getBytes(long x) {
		return new byte[] { (byte) (x >>> 56), (byte) (x >>> 48),
				(byte) (x >>> 40), (byte) (x >>> 32), (byte) (x >>> 24),
				(byte) (x >>> 16), (byte) (x >>> 8), (byte) x };
	}

	public static byte[] getBytes(float x) {
		return getBytes(Float.floatToRawIntBits(x));
	}

	public static byte[] getBytes(double x) {
		return getBytes(Double.doubleToRawLongBits(x));
	}

	public static byte[] getBytes(String x) {
		return x.getBytes();
	}

	public static long doubleToInt64Bits(double x) {
		return Double.doubleToRawLongBits(x);
	}

	public static double int64BitsToDouble(long x) {
		return (double) x;
	}

	public boolean toBoolean(byte[] bytes, int index) {
//		if (bytes.length != 1)
//			throw new Exception(
//					"The length of the byte array must be at least 1 byte long.");
		return bytes[index] != 0;
	}

	public char toChar(byte[] bytes, int index)  {
//		if (bytes.length != 2)
//			throw new Exception(
//					"The length of the byte array must be at least 2 bytes long.");
		return (char) ((0xff & bytes[index]) << 8 | (0xff & bytes[index + 1]) << 0);
	}

	public double toDouble(byte[] bytes, int index)  {
//		if (bytes.length != 8)
//			throw new Exception(
//					"The length of the byte array must be at least 8 bytes long.");
		return Double.longBitsToDouble(toInt64(bytes, index));
	}

	public static short toInt16(byte[] bytes, int index)  {
//		if (bytes.length != 8)
//			throw new Exception(
//					"The length of the byte array must be at least 8 bytes long.");
		return (short) ((0xff & bytes[index]) << 8 | (0xff & bytes[index + 1]) << 0);
	}

	public static int toInt32(byte[] bytes, int index)  {
//		if (bytes.length != 4)
//			throw new Exception(
//					"The length of the byte array must be at least 4 bytes long.");
		return (int) ((int) (0xff & bytes[index]) << 24
				| (int) (0xff & bytes[index + 1]) << 16
				| (int) (0xff & bytes[index + 2]) << 8 | (int) (0xff & bytes[index + 3]));
	}

	public static long toInt64(byte[] bytes, int index)  {
//		if (bytes.length != 8)
//			throw new Exception(
//					"The length of the byte array must be at least 8 bytes long.");
		return (long) ((long) (0xff & bytes[index]) << 56
				| (long) (0xff & bytes[index + 1]) << 48
				| (long) (0xff & bytes[index + 2]) << 40
				| (long) (0xff & bytes[index + 3]) << 32
				| (long) (0xff & bytes[index + 4]) << 24
				| (long) (0xff & bytes[index + 5]) << 16
				| (long) (0xff & bytes[index + 6]) << 8 | (long) (0xff & bytes[index + 7]));
	}

	public static float toFloat(byte[] bytes, int index)  {
//		if (bytes.length != 4)
//			throw new Exception(
//					"The length of the byte array must be at least 4 bytes long.");
		return Float.intBitsToFloat(toInt32(bytes, index));
	}

	public static String toString(byte[] bytes)  {
//		if (bytes == null)
//			throw new Exception("The byte array must have at least 1 byte.");
		return new String(bytes);
	}

	public static String toUTF8String(byte[] bytes) throws UnsupportedEncodingException  {
//		if (bytes == null)
//			throw new Exception("The byte array must have at least 1 byte.");
		return new String(bytes, "UTF-8");
	}

	public static byte getSignedByte(int data) {
		return (byte) data;
	}

	public static short getSignedShort(int data) {
		return (short) data;
	}

	public static int getSignedInt(long data) {
		return (int) data;
	}

	/**
	 * 转换为无符号字节值
	 * @param data 数据
	 * @return 结果
	 */
	public static int getUnsignedByte(byte data) {
		// 将data字节型数据转换为0~255 (0xFF 即BYTE)。
		return data & 0x0FF;
	}

	/**
	 * 转换为无符号短整型值
	 * @param data 数据
	 * @return 结果
	 */
	public static int getUnsignedShort(short data) {
		// 将data字节型数据转换为0~65535 (0xFFFF 即 WORD)。
		return data & 0x0FFFF;
	}

	/**
	 * 转换为无符号整型值
	 * @param data 数据
	 * @return 结果
	 */
	public static long getUnsignedInt(int data) {
		// 将int数据转换为0~4294967295 (0xFFFFFFFF即DWORD)。
		return data & 0x0FFFFFFFF;
	}

	/**
	 * 转换为无符号长整型值
	 * @param v 值
	 * @return
	 *  结果
	 */
	public static java.math.BigInteger getUnsignedInt64(long v) {
		// 将int数据转换为0~4294967295 (0xFFFFFFFF即DWORD)。
		//return data & 0x0FFFFFFFF;
		// data & 0x0fffffffffffffff
		
		byte[] data = BaseDataConverter.getBytes((long)v);
		
		//增加符号位，表示正数
		byte[] d3 = new byte[9];
		d3[0] = 0;
		d3[1] = data[0];
		d3[2] = data[1];
		d3[3] = data[2];
		d3[4] = data[3];
		d3[5] = data[4];
		d3[6] = data[5];
		d3[7] = data[6];
		d3[8] = data[7];
		
		java.math.BigInteger bigint = new java.math.BigInteger(d3);
		//int signum2 = bigint2.signum();

		return bigint;
	}
	
	
	
	/*
	 * 
	//long类型转成byte数组
	public static byte[] longToBytes(long number) {
	        long temp = number;
	        byte[] b = new byte[8];
	        for (int i = 0; i < b.length; i++) {
	            b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位
	        temp = temp >> 8; // 向右移8位
	    }
	    return b;
	}
 
	//byte数组转成long
	public static long bytesToLong(byte[] b) {
	
	    long s = 0;
	    long s0 = b[0] & 0xff;// 最低位
	    long s1 = b[1] & 0xff;
	    long s2 = b[2] & 0xff;
	    long s3 = b[3] & 0xff;
	    long s4 = b[4] & 0xff;// 最低位
	    long s5 = b[5] & 0xff;
	    long s6 = b[6] & 0xff;
	    long s7 = b[7] & 0xff;
	
	    // s0不变
	    s1 <<= 8;
	    s2 <<= 16;
	    s3 <<= 24;
	    s4 <<= 8 * 4;
	    s5 <<= 8 * 5;
	    s6 <<= 8 * 6;
	    s7 <<= 8 * 7;
	    s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
	    return s;
	}
 
	public static byte[] intToBytes(int number) {
		
	    int temp = number;
	    byte[] b = new byte[4];
	
	    for (int i = 0; i < b.length; i++) {
	        b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位
	        temp = temp >> 8; // 向右移8位
	    }
	    return b;
	}
 
	public static int bytesToInt(byte[] b) {
	
	    int s = 0;
	    int s0 = b[0] & 0xff;// 最低位
	    int s1 = b[1] & 0xff;
	    int s2 = b[2] & 0xff;
	    int s3 = b[3] & 0xff;
	    s3 <<= 24;
	    s2 <<= 16;
	    s1 <<= 8;
	    s = s0 | s1 | s2 | s3;
	    return s;
	}
	
	//浮点到字节转换
	public static byte[] doubleToBytes(double d)
	{
		byte writeBuffer[]= new byte[8];
	     long v = Double.doubleToLongBits(d);
	        writeBuffer[0] = (byte)(v >>> 56);
	        writeBuffer[1] = (byte)(v >>> 48);
	        writeBuffer[2] = (byte)(v >>> 40);
	        writeBuffer[3] = (byte)(v >>> 32);
	        writeBuffer[4] = (byte)(v >>> 24);
	        writeBuffer[5] = (byte)(v >>> 16);
	        writeBuffer[6] = (byte)(v >>>  8);
	        writeBuffer[7] = (byte)(v >>>  0);
	        return writeBuffer;
 
	}
	
	//字节到浮点转换
	public static double bytesToDouble(byte[] readBuffer)
	{
	     return Double.longBitsToDouble((((long)readBuffer[0] << 56) +
	                ((long)(readBuffer[1] & 255) << 48) +
	                ((long)(readBuffer[2] & 255) << 40) +
	                ((long)(readBuffer[3] & 255) << 32) +
	                ((long)(readBuffer[4] & 255) << 24) +
	                ((readBuffer[5] & 255) << 16) +
	                ((readBuffer[6] & 255) <<  8) +
	                ((readBuffer[7] & 255) <<  0))
	          );
	}
	 * 
	 */
	
	
	
	
	
}