import java.io.File;
import java.io.IOException;
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
		
		
		isFfileContainsMFunctionality(u.getContentsofFile(javaFile));
		
		
			}
	
	// Check to see if one of the keywords are contained in the file
	private void isFfileContainsMFunctionality(String str){
		//System.out.println(str);
		/*
	    String[] words = {"log", "Log", "word3", "word4", "word5"};  
	    //return (Arrays.asList(words).contains(str));
	    if (Arrays.asList(str).contains(str)){
	    	System.out.println("yes");
	    }else{
	    	System.out.println("no");
	    }
	    */
	//	System.out.printf("Matches - [%s]%n", str.contains("^.*?(log|Log|item3).*$"));
		
		
		boolean retVal = false;
		
		
		List<String> s = Arrays.asList("BAasdfasdfB", ".show()", "asdfasfasDAS");

			
		for(int i=0; i<s.size(); i++){
			if(str.toLowerCase().contains(s.get(i))){
				retVal=true;
				System.out.println("true!");	
			}else{
				System.out.println("false");
			}
		}
		
		System.out.println("RetVal=" + retVal);
		
		//System.out.println(str.toLowerCase().contains(".show()")); // true
		
		// only loop through the file if reVal is false
		
		
		
		
		
		
	}
	
	
}
