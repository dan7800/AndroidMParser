import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Util {

	
	// empty the contents of the log file
	public void clearFile(String file){
		System.out.println("clear log");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();
	}
	
	
}
