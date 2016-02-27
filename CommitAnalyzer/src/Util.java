import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


public class Util {

	
	// empty the contents of the log file
	public void clearFile(String file){
		System.out.println("**clear log");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.close();
	}
	
	
	// only used for debugging
	public void showList(List list){
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<list.size();i++){
			//System.out.println(list.get(i));
			sb.append(list.get(i));
			sb.append(",");
		}
		System.out.println(sb.toString());
	}


	
}
