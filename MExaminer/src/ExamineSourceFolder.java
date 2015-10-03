// Examines the specific source folder

// This will also hold information about the specific app.
//		The less kept in memory will likely be a good thing

import java.io.File;
import java.io.IOException;
public class ExamineSourceFolder {
	
	private String dbLocation;	// Location of DB File
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

	public ExamineSourceFolder(String dbLocation, String primaryFolderPath, String folderName) {
		this.dbLocation = dbLocation;
		this.primaryFolderPath = primaryFolderPath;
		this.folderName = folderName;
	}
	
	
	public void examineFolder(){
		//System.out.println("examine folder" + this.folderName);
		System.out.println("examine folder" + getEntireFolderLocation());
		
		
		// Get information from the manifest file
		examineManifestFile();
		
		// Examine the entire contents of the app for the specific messages that we are looking for
		
		
	}
	
	
	
	private void examineManifestFile(){
		String manifestFileLocation = getManifestFileLocation(getEntireFolderLocation()); // Where is the manifest file located
	
		
		// ?? What if the manifest file does not exist
		
		//System.out.println("Location:" + manifestFileLocation);
		
		// ?? Get permissions from the file, or should we rely on another script for this ?
		
		
		
		// Get the `M' checks in the source code
		
		
		// ContextCompat.checkSelfPermission
		// ActivityCompat.shouldShowRequestPermissionRationale
		// ActivityCompat.requestPermissions
		// onRequestPermissionsResult
		
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
		
		
		
		//
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Determine if the application is targeted toward `M'
	private boolean isAppM(String folderLocation){
		//		Not sure how this will be done yet
		return false;
	}
	
	
	
	// Parse the manifest file to find relevant information about it
	//		See if 
	
	

}
