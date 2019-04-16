package util;

public class Base64Application {

	
	public static void main( String[] args )
    {
		String message = "dadfasd";
        System.out.println( "message:" +message);
		
    	String encode = org.aooshi.j.util.Base64Helper.Encode(message);    	
        System.out.println( "encode:" +encode);
        
    	String decode = org.aooshi.j.util.Base64Helper.Decode(encode);
        System.out.println( "decode" +decode);
	}
}
