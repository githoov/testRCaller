import java.io.File;
import javax.swing.ImageIcon;
import java.util.Random; 
import rcaller.RCaller;
import rcaller.RCode;

public class stringTest {
	public static void main(String[] args) {
		
		// create RCaller and RCode
        RCaller caller = new RCaller();
        RCode code = new RCode();  
        
        // tell java to run specified code in R
        caller.setRscriptExecutable("/usr/bin/Rscript");
        code.clear();
        caller.setRCode(code);
        
        // create string array
		String[] str = {"2/13/2013", "February 13, 2013", "02/13/2013"};
		
		// clean up dates with regular expressions and change string array into R date format
		code.addStringArray("dates", str);
		code.addRCode("dates <- gsub('February ', '2/', dates)");
		code.addRCode("dates <- gsub(', ', '/', dates)");
		code.addRCode("dates <- as.character(as.Date(dates, format='%m/%d/%Y'))");
		
		// set code
		caller.setRCode(code);
		
		// extract R string array
		caller.runAndReturnResult("dates");
		
		// create and print string array in java
		String[] dates = caller.getParser().getAsStringArray("dates");
		for (int i = 0; i < dates.length; i++){
			System.out.println(dates[i]);
		}
        
	}

}
