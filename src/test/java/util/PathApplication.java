package util;

import org.aooshi.j.util.PathHelper;
import org.aooshi.j.util.StringHelper;

public class PathApplication {

	
	public static void main( String[] args )
    {		
		String message = "xd09";
		
		Boolean result = StringHelper.isLetterOrDigit(message);
		System.out.println( "isLetterOrDigit:" + result);
		
		
		message = PathHelper.createSecondPath();
        System.out.println( "CreateSecondPath:" + message);
		
        message = PathHelper.getSecondPath(".ext");    	
        System.out.println( "getSecondPath(.ext):" + message);

        message = PathHelper.getExtension("file.ext");
        System.out.println( "GetExtension(file.ext):" + message);

        message = PathHelper.getExtension("file");
        System.out.println( "GetExtension(file):" + message);

        message = PathHelper.getFileName("file.ext");
        System.out.println( "GetFileName(file.ext):" + message);

        message = PathHelper.getFileName("file");
        System.out.println( "GetFileName(file):" + message);
        
	}
}
