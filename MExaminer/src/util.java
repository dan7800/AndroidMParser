
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;


public class util {
/*
	public String readDoc(String inputFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		StringBuilder text = new StringBuilder();
        String line = "";
        try{
        	line = br.readLine();
	
        	while(line != null){
        		text.append(line);
        		text.append("\n"); //added this line to fix comparision problem
        		line = br.readLine();
        	}
        }catch (Exception e){
        	e.printStackTrace();
        }finally{
        	br.close();
        	}
        return text.toString();
	}
	*/
	
	public String getContentsofFile(File inputFile) throws IOException{
		  int len;
	      char[] chr = new char[4096];
	      final StringBuffer buffer = new StringBuffer();
	      final FileReader reader = new FileReader(inputFile);
	      try {
	          while ((len = reader.read(chr)) > 0) {
	              buffer.append(chr, 0, len);
	          }
	      } finally {
	          reader.close();
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
}
