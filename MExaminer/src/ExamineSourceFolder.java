// Examines the specific source folder

// This will also hold information about the specific app.
//		The less kept in memory will likely be a good thing

public class ExamineSourceFolder {
	
	private String folderName;	// Location of folder being examined
	private String primaryFolderPath; // Location of all the files to be examined
		

	// Just get the name of the folder being examined
	public String getFolderName() {
		return folderName + "dan";
	}

	// Get the total path of the folder being examined
	public String getEntireFolderLocation() {
		return primaryFolderPath + folderName;
	}

	public ExamineSourceFolder(String primaryFolderPath, String folderName) {
		this.primaryFolderPath = primaryFolderPath;
		this.folderName = folderName;
	}
	
	
	public void examineFolder(){
		//System.out.println("examine folder" + this.folderName);
		System.out.println("examine folder" + getEntireFolderLocation());
		
		
		
		
	}
	
	
	
	

}
