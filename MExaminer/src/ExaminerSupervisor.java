import java.io.File;

/*Todo
 * Create logs for everything
 * 
 * 
 */



public class ExaminerSupervisor {
	
	private final String apkLocation = "";	// Where are the apks to be examined
	
	// For mac
	//private final String SourceCodeLocation = "../exampleApps/";	// Where are the APK source is located to be examined
	
	// For windows
	private final String SourceCodeLocation = "E:\\GIT\\GHResearch\\AndroidMParser\\exampleApps_smallDummy\\";	// Where are the APK source is located to be examined
	
	private final String DBLocation = "DUMMYDBLOCATION";


	public static void main(String[] args) {
		ExaminerSupervisor es = new ExaminerSupervisor();
		es.Run();

	}

	
	private void Run(){
	
		// Loop through all source files
		File folder = new File(SourceCodeLocation);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
			//	System.out.println("examine " + listOfFiles[i].getName());  
				ExamineSourceFolder es = new ExamineSourceFolder(DBLocation, SourceCodeLocation, listOfFiles[i].getName());
				es.examineFolder();
				//System.out.println(es.getFolderLocation());
		    }
		}
		
		
	}
	
	
}
