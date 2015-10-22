
// Holds information about each of the requested permissions and information in the rationale
//		Will later be used to put this data into a database
public class RationalePermissions {
	
	private String classPath; //Path of the file with permissions
	private int lineNumber;		// Line number of requested permission
	private String[] Permissions;
	
	public RationalePermissions(String classPath, int lineNumber, String[] permissions) {
		this.classPath = classPath;
		this.lineNumber = lineNumber;
		Permissions = permissions;
	}	
		
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String[] getPermissions() {
		return Permissions;
	}
	public void setPermissions(String[] permissions) {
		Permissions = permissions;
	}

	private String getAllPermissions(){
		StringBuilder sb = new StringBuilder();
		for (int x=0; x<Permissions.length; x++){
			sb.append(Permissions[x]).append(" ");
		}
		return sb.toString();
	}
	
	public String getRationaleInfo(){
		return lineNumber + " " + classPath + " " + getAllPermissions();
	}
	
	
	
	
}
