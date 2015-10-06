import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


// Just a dummy, scratch pad for file analysis


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
		
		System.out.println(isFileContainsMFunctionality(javaFile));
		
		
		
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
		
		List<String> keyWords = new ArrayList<String>(); // AndroidM Keywords to search for
		keyWords.add("hahaahaha1hahahah");
		keyWords.add("ActivityCompat");
		keyWords.add("hahaahaha1hahahah");
		
		int i=0;
		while(i<keyWords.size() && retVal == false){
			if(strInputFile.toLowerCase().contains(keyWords.get(i).toLowerCase().toString())){
				retVal=true;
			}
			i++;
		}
				
		return retVal;
	}
	
	
}
