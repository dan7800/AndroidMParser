// Examines the specific source folder

// This will also hold information about the specific app.
//		The less kept in memory will likely be a good thing

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExamineSourceFolder {
	
	private String dbLocation;	// Location of DB File
	private String folderName;	// Location of folder being examined
	private String primaryFolderPath; // Location of all the files to be examined
	
	private List<String> javaFiles = new ArrayList<String>(); // All java files
	private List<String> keyJavaFiles = new ArrayList<String>(); // Only examine the files we actually care about 
	private List<AppPermission> appManifestPerm = new ArrayList<AppPermission>(); // Store the app Manifest Permissions requested by the app
	private List<RationalePermissions> rationalePermissions = new ArrayList<RationalePermissions>(); // Store the app Manifest Permissions requested by the app
	private List<CheckSelfPermissions> checkSelfPermissions = new ArrayList<CheckSelfPermissions>(); // Store the app Manifest Permissions requested by the app
	
	private String masterLogFileLocation;
	private List<String> MkeyWords = new ArrayList<String>(); // AndroidM Keywords to search for
	
	util u = new util();

	
	public void examineFolder(){
		//System.out.println("examine folder" + this.folderName);
		
		// Put this into a log file
		System.out.println("Examine folder: " + getEntireFolderLocation());
		u.updateLog(masterLogFileLocation, "ExamineFolder", "Examine folder: " + getEntireFolderLocation(), true);
		
		
		// Get information from the manifest file
		// This still neesd to be completed
//		examineManifestFile();
		
		// Examine the entire contents of the app for the specific messages that we are looking for
		examineSourceCode(getEntireFolderLocation()); // This will build a list of M related files
				
		
		
		// Now for each of the files in identified list, start gathering information on them
		examineMFiles();
	}
	
	
	
	
	
	
	
	// Get all the requested permissions in the source code of the app
	// THIS WILL LIKELY NEED TO BE TWEAKED
	private void getRequestedAllPermissionsInApp(File inputFile){
		
		String str = null;
		str = u.getContentsofFile(inputFile);
			
		// The goal of this is to locate all of the requested permissions in the app
		String findStr = "Manifest.permission.";
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){
		    lastIndex = str.indexOf(findStr,lastIndex);
		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		        appManifestPerm.add(new AppPermission(inputFile.getAbsolutePath(),str.substring(lastIndex,str.indexOf(")",lastIndex)),lastIndex));
		        // Instead of creating an object, maybe just add it to the DB here?
		    }
		}

		
		// Test cycling through the list appManifestPerm
		/*
		for (int x = 0; x < appManifestPerm.size(); x++) {
			System.out.println(appManifestPerm.get(x).getAllAppPermissionInfo());
			// Add all this to a database table
			// Once it is added to the database table, kill the object
			
		}
		*/
		
		
	}
	
	// Examine the files identified as being "M"
	// I broke this down into several steps in the hope that it would make things faster
	private void examineMFiles(){
		
		
		String fileContents = "";
		for(int i=0; i<keyJavaFiles.size(); i++){
			
				
			//System.out.println("String: " + keyJavaFiles.get(i));
			File ExaminedFile = new File(keyJavaFiles.get(i)); // Make a file of the item to be examined
			
// ENABLE THIS			getRequestedAllPermissionsInApp(ExaminedFile); // Get all permissions in the files
			
			
			fileContents = u.getContentsofFile(ExaminedFile);
			
			
			
		//	checkShouldShowRequestPermissionRationale(ExaminedFile);
		//	checkCheckSelfPermission(ExaminedFile);
			checkRequestPermissions(ExaminedFile);
			
			// Now that we have the file contents, check to see if the specific search criteria is all set.
			

			// Permissions in Manifest vs. What is in the requested files
			//	Scan all .Java files for ones which include permissions
			//		This should come from a list of permissions
			//		Make sure that this will work
			
			
		}
		
		
	}
	
	
	
//	I stopped here. 
	
	// requestPermissions(Activity activity, String[] permissions, int requestCode)
	// http://developer.android.com/reference/android/support/v4/app/ActivityCompat.html
	
	
	// Record the files "check self permission"
	private void checkRequestPermissions(File ExaminedFile){
		if(u.isContainSearchCriteria("checkSelfPermission",ExaminedFile)){
			
			String fileString = u.getContentsofFile(ExaminedFile);
		
						
			// Get all the permissions which are being checked to see if they should show the rationale
			fileString=fileString.toLowerCase().replace(" ","").replace("\n", "").replace("\r", ""); // remove spaces in the input file
		
			String StartPattern = " if (ActivityCompat.checkSelfPermission(".replace(" ", "");
			String endPatthern = ")";
			
			// 			http://stackoverflow.com/questions/11255353/java-best-way-to-grab-all-strings-between-two-strings-regex
			Pattern p = Pattern.compile(Pattern.quote(StartPattern.toLowerCase()) + "(.*?)" + Pattern.quote(endPatthern.toLowerCase()));
			Matcher m = p.matcher(fileString.toLowerCase());
			while (m.find()) {
				//GETTING A LINE NUMBER HERE WOULD BE HELPFUL
				
				//System.out.println(m.group(1));
				final String permission = m.group(1).trim().replace("this,", "");
	
				// ? Insert into SQL instead ?
			//	checkSelfPermissions.add(new CheckSelfPermissions(ExaminedFile.getAbsolutePath(),1,permission));  
				//System.out.println(permission);
				
			}
			
		}
		
		
		// Now test all of the CheckSelf information
		for (int i=0; i<checkSelfPermissions.size(); i++){
			System.out.println(checkSelfPermissions.get(i).getCheckSelfInfo());
		}
	}
	
	
	
	
	// Record the files "check self permission"
	private void checkCheckSelfPermission(File ExaminedFile){
		if(u.isContainSearchCriteria("checkSelfPermission",ExaminedFile)){
			
			String fileString = u.getContentsofFile(ExaminedFile);
		
						
			// Get all the permissions which are being checked to see if they should show the rationale
			fileString=fileString.toLowerCase().replace(" ","").replace("\n", "").replace("\r", ""); // remove spaces in the input file
		
			String StartPattern = " if (ActivityCompat.checkSelfPermission(".replace(" ", "");
			String endPatthern = ")";
			
			// 			http://stackoverflow.com/questions/11255353/java-best-way-to-grab-all-strings-between-two-strings-regex
			Pattern p = Pattern.compile(Pattern.quote(StartPattern.toLowerCase()) + "(.*?)" + Pattern.quote(endPatthern.toLowerCase()));
			Matcher m = p.matcher(fileString.toLowerCase());
			while (m.find()) {
				//GETTING A LINE NUMBER HERE WOULD BE HELPFUL
				
				//System.out.println(m.group(1));
				final String permission = m.group(1).trim().replace("this,", "");
	
				// ? Insert into SQL instead ?
				checkSelfPermissions.add(new CheckSelfPermissions(ExaminedFile.getAbsolutePath(),1,permission));  
				//System.out.println(permission);
				
			}
			
		}
		
		
		// Now test all of the CheckSelf information
		for (int i=0; i<checkSelfPermissions.size(); i++){
			System.out.println(checkSelfPermissions.get(i).getCheckSelfInfo());
		}
	}
	
	
	
	// This type of function will be called for all checks
	private void checkShouldShowRequestPermissionRationale(File ExaminedFile){
	//System.out.println(fileContents);
		if(u.isContainSearchCriteria("shouldShowRequestPermissionRationale",ExaminedFile)){
			//System.out.println("Yes - " + ExaminedFile.getName());
			// Now do stuff with that file
			
			
			
			String fileString = u.getContentsofFile(ExaminedFile);
		
			
			
			// Get all the permissions which are being checked to see if they should show the rationale
			fileString=fileString.toLowerCase().replace(" ","").replace("\n", "").replace("\r", ""); // remove spaces in the input file
								
			String StartPattern = "if(ActivityCompat.shouldShowRequestPermissionRationale(";
			String endPatthern = ")){";
			
			// 			http://stackoverflow.com/questions/11255353/java-best-way-to-grab-all-strings-between-two-strings-regex
			Pattern p = Pattern.compile(Pattern.quote(StartPattern.toLowerCase()) + "(.*?)" + Pattern.quote(endPatthern.toLowerCase()));
			Matcher m = p.matcher(fileString.toLowerCase());
			
			while (m.find()) {
				//GETTING A LINE NUMBER HERE WOULD BE HELPFUL
				
				System.out.println(m.group(1));
				final String permission = m.group(1).trim().replace("this,", "");
	
				// ? Insert into SQL instead ?
				rationalePermissions.add(new RationalePermissions(ExaminedFile.getAbsolutePath(),1,permission));  
				
				
			}
			
			
			
						
		}else{
			System.out.println("NO - " + ExaminedFile.getName());
		}
		
		
		// Now test all of the Rationale information
		for (int i=0; i<rationalePermissions.size(); i++){
			System.out.println(rationalePermissions.get(i).getRationaleInfo());
		}
		
	}
	
	
	
	
	
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
	    
		
	    // Now cycle through all the M Files and h
	}
	
	
	
	// Check to see if one of the keywords are contained in the file.
	//		This will merely create a list for analyzing later
	private boolean isFileContainsMFunctionality(File inputFile){

		String strInputFile = "";
		strInputFile = u.getContentsofFile(inputFile);
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
	
	// Start of *Dummy* Functions //
	
	
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


	// Todo
	//	Search all the files for "Manifest.permission." .. This will record the permissions the app actually uses
	//	
	
	
}
