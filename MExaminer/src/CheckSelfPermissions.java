
// Holds information about each of the requested permissions and information in the rationale
//		Will later be used to put this data into a database

// Might not be a bad idea to combine the classes


public class CheckSelfPermissions {
	
	private String classPath; //Path of the file with permissions
	private int lineNumber;		// Line number of requested permission
	private String permission;
	
	public CheckSelfPermissions(String classPath, int lineNumber, String Permission) {
		this.classPath = classPath;
		this.lineNumber = lineNumber;
		permission = Permission;
	}	
		
	public String getClassPath() {
		return classPath;
	}
	
	
	public int getLineNumber() {
		return lineNumber;
	}

	public String getPermission() {
		return permission;
	}
	
	// I don't think there is a reason to set these anywhere but in the setter
	/*
	public void setPermission(String permission) {
		Permission = permission;
	}
	
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	 */
	
	// I do not believe there is a need for the array list of items
	/*
	private String getAllPermissions(){
		StringBuilder sb = new StringBuilder();
		for (int x=0; x<Permissions.length; x++){
			sb.append(Permissions[x]).append(" ");
		}
		return sb.toString();
	}
	*/
	
	public String getCheckSelfInfo(){
		return lineNumber + " " + classPath + " " + permission;
	}
	
	
	
	
}
