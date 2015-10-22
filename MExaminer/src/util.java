
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;


public class util {
	
	public String getContentsofFile(File inputFile){
		  int len;
	      char[] chr = new char[4096];
	      final StringBuffer buffer = new StringBuffer();
	      FileReader reader = null;
		try {
			reader = new FileReader(inputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      try {
	          while ((len = reader.read(chr)) > 0) {
	              buffer.append(chr, 0, len);
	          }
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	          try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	      return buffer.toString();
	
	}
	
	
	// Functionality for storing log information about the application
	public void updateLog(String masterLogLocation, String FileName, String Message, boolean isAppend){
		//System.out.println(masterLogLocation + FileName + Message);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		
		// Create the file if it does not exist
		final String logLocation = masterLogLocation+"\\"+FileName+".txt";
		File logFile = new File(logLocation);
		if(!logFile.exists()) {
		    try {
		    	logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		// Check to see if the file should be removed
		if(isAppend==false){
			// Clear the contents of the log file
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(logLocation);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.close();
		}
		
		try
		{
		    FileWriter fw = new FileWriter(logLocation,true); //the true will append the new data
		    fw.write(dateFormat.format(cal.getTime()) + " " + Message + "\n");
		   // fw.write("add a line\n");//appends the string to the file
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}

	}
	
	// Check to see if the file you are analyzing contains the specific search critiera
	public  boolean isFileContainSearchCriteria(File inputFile, String searchCriteria){
		boolean retVal=false;
			
		try {
		    Scanner scanner = new Scanner(inputFile);

		    //now read the file line by line...
		    int lineNum = 0;
		    while (scanner.hasNextLine()) {
		        String line = scanner.nextLine();
		        lineNum++;
		       // if(<some condition is met for the line>) { 
		       //     System.out.println("ho hum, i found it on line " +lineNum);
		       // }
		      //  System.out.println(line);
		        if(line.toLowerCase().contains(searchCriteria.toLowerCase())){
		        	retVal =  true;
		        }
		    }
		} catch(FileNotFoundException e) { 
		    //handle this
		}
			return retVal;
		}

	
	public String findStringBetween(){
		
		return "";
	}
	

}
