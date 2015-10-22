import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


// Just a dummy, scratch pad for file analysis
// Test commit

public class Dummy {

	util u = new util();
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Dummy d = new Dummy();
		d.Run();
	}

	private void Run() throws IOException{
		//System.out.println("dummy");
		
		// Create dummy file
		
		// Get list of strings
		
		final String folderLocation = "E:\\GIT\\GHResearch\\AndroidMParser\\exampleApps_smallDummy\\android-RuntimePermissions\\src\\main\\java\\com\\example\\android\\system\\runtimepermissions\\MainActivity_small.java";
		File javaFile = new File(folderLocation);
		
		//System.out.println(u.getContentsofFile(javaFile));
		
		File file = new File("E:\\GIT\\GHResearch\\AndroidMParser\\exampleApps_smallDummy\\android-RuntimePermissions\\src\\main\\java\\com\\example\\android\\system\\runtimepermissions\\MainActivity.java");
		//System.out.println(isFileContainSearchCriteria(file,"example"));
		
		
		
		
		
		
		}
	
	
	// Not working
	public String findStringBetween(){
		String findStr = "Manifest.permission.";
		int lastIndex = 0;
		int count = 0;
	
		while(lastIndex != -1){
		    lastIndex = findStr.indexOf(findStr,lastIndex);
		    if(lastIndex != -1){
		        count ++;
		        lastIndex += findStr.length();
		        //appManifestPerm.add(new AppPermission(inputFile.getAbsolutePath(),str.substring(lastIndex,str.indexOf(")",lastIndex)),lastIndex));
		        // Instead of creating an object, maybe just add it to the DB here?
		    }
		}
		return "";
	}
	
	
	/*
	// A more generic, reusable way of searching for criteria in a string
	private boolean isContainSearchCriteria(String searchCriteria, String inputFile){
		boolean retVal=false;
		
	//	searchCriteria=searchCriteria.toLowerCase(); // Make sure that each are converted to lowercase
	//	inputFile = inputFile.toLowerCase();

		if(searchCriteria.toLowerCase().contains(inputFile.toLowerCase())){
			retVal = true;
		}
		
		
		return retVal;
	}
	
	*/
	
	
	
}
