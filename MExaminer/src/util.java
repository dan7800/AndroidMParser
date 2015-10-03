
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class util {
/*
	public String readDoc(String inputFile) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		StringBuilder text = new StringBuilder();
        String line = "";
        try{
        	line = br.readLine();
	
        	while(line != null){
        		text.append(line);
        		text.append("\n"); //added this line to fix comparision problem
        		line = br.readLine();
        	}
        }catch (Exception e){
        	e.printStackTrace();
        }finally{
        	br.close();
        	}
        return text.toString();
	}
	*/
	
	public String getContentsofFile(File inputFile) throws IOException{
		  int len;
	      char[] chr = new char[4096];
	      final StringBuffer buffer = new StringBuffer();
	      final FileReader reader = new FileReader(inputFile);
	      try {
	          while ((len = reader.read(chr)) > 0) {
	              buffer.append(chr, 0, len);
	          }
	      } finally {
	          reader.close();
	      }
	      return buffer.toString();
	
	}

}