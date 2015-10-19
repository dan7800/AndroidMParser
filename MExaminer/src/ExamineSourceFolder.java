// Examines the specific source folder

// This will also hold information about the specific app.
//		The less kept in memory will likely be a good thing

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExamineSourceFolder {
	
	private String dbLocation;	// Location of DB File
	private String folderName;	// Location of folder being examined
	private String primaryFolderPath; // Location of all the files to be examined
	
	private List<String> javaFiles = new ArrayList<String>(); // All java files
	private List<String> keyJavaFiles = new ArrayList<String>(); // Only examine the files we actually care about 
	private String masterLogFileLocation;
	
	private List<String> MkeyWords = new ArrayList<String>(); // AndroidM Keywords to search for
	
	
	
	
	util u = new util();

	// Just get the name of the folder being examined
	public String getFolderName() {
		return folderName;
	}

	// Get the total path of the folder being examined
	public String getEntireFolderLocation() {
		return primaryFolderPath + folderName;
	}

	public ExamineSourceFolder(String MasterLogFileLocation, String dbLocation, String primaryFolderPath, String folderName) {
		this.dbLocation = dbLocation;
		this.primaryFolderPath = primaryFolderPath;
		this.folderName = folderName;
		this.masterLogFileLocation = MasterLogFileLocation;
		buildMKeyWords();
	}
	
	
	// Create a common set of `M' keywords. 
	// This will likely be used several times
	private void buildMKeyWords(){
		MkeyWords.add("checkSelfPermission");
		MkeyWords.add("requestPermissions");
		MkeyWords.add("shouldShowRequestPermissionRationale");
				
		
		//MkeyWords.add("ActivityCompat."); // I believe this is the main generic class for M permissions?
		

	}
	
	
	public void examineFolder(){
		//System.out.println("examine folder" + this.folderName);
		
		// Put this into a log file
		System.out.println("Examine folder: " + getEntireFolderLocation());
	u.updateLog(masterLogFileLocation, "test", "Examine folder: " + getEntireFolderLocation(), true);
		
		
		// Get information from the manifest file
		// This still neesd to be completed
//		examineManifestFile();
		
		// Examine the entire contents of the app for the specific messages that we are looking for
		examineSourceCode(getEntireFolderLocation()); // This will build a list of M related files
				
		// Now for each of the files in identified list, start gathering information on them
		examineMFiles();
	}
	
		
	// Examine the files identified as being "M"
	// I broke this down into several steps in the hope that it would make things faster
	private void examineMFiles(){
		
		// Loop through all of the key files
		for(int i=0; i<keyJavaFiles.size(); i++){
			//System.out.println(keyJavaFiles.get(i).toString());
			// This is where specific rule checks go
			
			
			// Files might have many "M" commands
			// Loop through the master M list
			for(int x=0; x<MkeyWords.size();x++){
				//System.out.println("check to see if " + keyJavaFiles.get(i)  + " contains: " + MkeyWords.get(x));
				
				
				// Record that the file contains the item
				
				
				// Send it out to investigate the exact items in the file
				
				
			
			}
			
			
			
		}
		
	}
	
	
	/*
	// Check to see if a file contains a specific command
	private boolean isFileContainString(File inputFile, String checkString){
		try {
			final String strInputFile = u.getContentsofFile(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if(strInputFile.){
			
		//}
		
		
		return true;
	} 
	*/
	
	/*
	private void examineManifestFile(){
		String manifestFileLocation = getManifestFileLocation(getEntireFolderLocation()); // Where is the manifest file located
	
		
		// ?? What if the manifest file does not exist
		
		//System.out.println("Location:" + manifestFileLocation);
		
		// ?? Get permissions from the file, or should we rely on another script for this ?
		
		
		
		// Get the `M' checks in the source code
		
		// https://developer.android.com/training/permissions/best-practices.html
		// https://developer.android.com/training/permissions/requesting.html
		// ContextCompat.checkSelfPermission
		// ActivityCompat.shouldShowRequestPermissionRationale
		// ActivityCompat.requestPermissions
		// onRequestPermissionsResult
		
	}
	*/
	
	
	
	// find the manifest file. Return the location of the manifest file
	private String getManifestFileLocation(String folderLocation){
		
		String retVal="";
		
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			   
		      if (listOfFiles[i].isFile()) {
		        System.out.println("File " + listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
			    	  // now look into the app directory
			  			File folder_app = new File(listOfFiles[i].getAbsolutePath());
			  			File[] listOfFiles_app = folder_app.listFiles();
			  			 for (int ii = 0; ii < listOfFiles_app.length; ii++) {
			  				 
			  				 if(!listOfFiles_app[ii].getName().toLowerCase().contains("ds_store")){ // Ignore certain file types
					  			File manifestFile = new File(listOfFiles_app[ii].getAbsolutePath()+"/AndroidManifest.xml");
					  			//System.out.println(manifestFile.exists());
					  			if(!manifestFile.exists()){
					  				// It doesn't exist, so log a message
					  				System.out.println("----No Manifest file found for: ");
					  				System.out.println("--------AppName: " + listOfFiles[i].getName());
					  				System.out.println("--------CommitName: " + listOfFiles_app[ii].getName());
					  				System.out.println("--------" + listOfFiles_app[ii].getAbsolutePath());
					  				
					  				// Add this to a log file
					  				
					  				
					  			}else{
					  				// Add all the manifest files to the master list
					  				//MasterManifestList.add(new manifestItem(manifestFile, u.getContentsofFile(manifestFile), listOfFiles[i].getName(), listOfFiles_app[ii].getName()));
					  				//System.out.println(manifestFile);
					  				//retVal=manifestFile.getAbsolutePath();
					  				try {
										retVal=manifestFile.getCanonicalPath();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
					  			}
			  				 }else{
			  					 System.out.println("Skip file: " + listOfFiles_app[ii].getName());
			  				 }
				  		}  
			      }
		}
		
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	// Determine if the application is targeted toward `M'
	private boolean isAppM(String folderLocation){
		//		Not sure how this will be done yet.
		//		Hopefully we can do it through 
		return false;
	}
	
	
	
	
	// Perform the operations to the source code
	private void examineSourceCode(String folderLocation){
		
		// Put all the different modules I will be examining in here
		
	//	System.out.println("Examine folder");
		
		// Loop through every .java file
		//	Could put this into components, but the goal should probably be to keep things all in a single loop to save resources
		
	
		
		File[] files = new File(folderLocation).listFiles();
	    buildJavaFiles(files);
		
	    // test showing java files
	    for(int i =0; i<javaFiles.size(); i++){
	 //   	System.out.println(javaFiles.get(i));
//	    	File files = new File(javaFileLocation)
	    	if(isFileContainsMFunctionality(new File(javaFiles.get(i)))){
		    	// Build list of key java files
	    		keyJavaFiles.add(javaFiles.get(i));  // Build the list of files we care about. These can be examined later
	    		//System.out.println("add " + javaFiles.get(i));
	    	}
	    }
	      
	    
	    
	    
	    // Clear the list of java files when done?
	    
		
	}
	
	
	
	// Check to see if one of the keywords are contained in the file.
	//		This will merely create a list for analyzing later
	private boolean isFileContainsMFunctionality(File inputFile){

		String strInputFile = "";
		try {
			strInputFile = u.getContentsofFile(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean retVal = false;
		
		int i=0;
		while(i<MkeyWords.size() && retVal == false){
			if(strInputFile.toLowerCase().contains(MkeyWords.get(i).toLowerCase().toString())){
				retVal=true;
			}
			i++;
		}
				
		return retVal;
	}
	
	

	// Create the list of all the java files in the target directory
	private void buildJavaFiles(File[] files) {
	    for (File file : files) {
	        if (file.isDirectory()) {
	          //  System.out.println("Directory: " + file.getName());
	        	buildJavaFiles(file.listFiles()); // Calls same method again.
	        } else {
	            if(file.getName().contains(".java")){
	            	//System.out.println(file.getName());
	            	try {
						javaFiles.add(file.getCanonicalPath());
					} catch (IOException e) {
						// TODO: probably also log the issue
						e.printStackTrace();
					}
	            }
	        }
	    }
	}
	

}
