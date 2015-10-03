import java.io.File;


public class ExaminerSupervisor {
	
	private final String apkLocation = "";	// Where are the apks to be examined
	private final String SourceCodeLocation = "../exampleApps/";	// Where are the APK source is located to be examined
	private final String DBLocation = "";


	public static void main(String[] args) {
		ExaminerSupervisor es = new ExaminerSupervisor();
		es.Run();

	}

	
	private void Run(){
		System.out.println("hello world");
		
		// Loop through all source files
		
		File folder = new File(SourceCodeLocation);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isDirectory()) {
				System.out.println("examine " + listOfFiles[i].getName());  
				ExamineSourceFolder es = new ExamineSourceFolder(SourceCodeLocation, listOfFiles[i].getName());
				es.examineFolder();
				//System.out.println(es.getFolderLocation());
		    }
		}
		
		
	}
	
	
}
