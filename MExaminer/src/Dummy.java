import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
		
		
		
		
		match();
		
		}
	
	private void match(){
		
		
		/*
		String pattern1 = "Start";
		String pattern2 = "End";
		String text = "Start Dan End Start Krutz End";
		*/
		
		String pattern1 = "if(ActivityCompat.shouldShowRequestPermissionRationale(";
		String pattern2 = ")){";
		String text = "if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){DaniscoolActivityCompat.requestPermissions(MainActivity,DONOTSHOW,DONOTSHOW);DaniscoolManifest.permission.CAMERA)){";
		
		
		
		
		Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
		Matcher m = p.matcher(text);
		while (m.find()) {
		  System.out.println(m.group(1));
		}
		
		
		
	}
	
	
	
}
