import java.io.File;

/*Todo
 * Create logs for everything
 * 
 * 
 */



public class ExaminerSupervisor {
	
	private final String apkLocation = "";	// Where are the apks to be examined
	//private final String masterLogFileLocation = "E:\\GIT\\GHResearch\\AndroidMParser\\Logs\\";
	private final String masterLogFileLocation = "../Logs/";
	
	// For mac
	//private final String SourceCodeLocation = "../exampleApps/";	// Where are the APK source is located to be examined
	
	// For windows
//	private final String SourceCodeLocation = "E:\\GIT\\GHResearch\\AndroidMParser\\exampleApps_smallDummy\\";	// Where are the APK source is located to be examined
	private final String SourceCodeLocation = "../exampleApps_smallDummy/";	// Where are the APK source is located to be examined
	
	
	private final String DBLocation = "DUMMYDBLOCATION";
	util u = new util();

	
	
	
	public static void main(String[] args) {
		ExaminerSupervisor es = new ExaminerSupervisor();
		es.Run();

	}

	
	private void Run(){
	
		// Create the log location if it does not exist
		new File(masterLogFileLocation).mkdir();
		
		
		// Loop through all source files
		File folder = new File(SourceCodeLocation);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
			//	System.out.println("examine " + listOfFiles[i].getName());  
				ExamineSourceFolder es = new ExamineSourceFolder(masterLogFileLocation, DBLocation, SourceCodeLocation, listOfFiles[i].getName());
				es.examineFolder();
				//System.out.println(es.getFolderLocation());
		    }
		}
		
		
	}
	
	
}
