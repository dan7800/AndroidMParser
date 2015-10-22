
// Will hold information about each app's requested permissions

public class AppPermission {
	
	private String classPath; //Path of the file with permissions
	private String permissionName;  // Name of the permission
	private int lineNumber;		// Line number of requested permission
	
	public AppPermission(String classPath, String permissionName, int LineNumber) {
		this.classPath = classPath;
		this.permissionName = permissionName;
		this.lineNumber = LineNumber;
	}
	
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public String getPermissionName() {
		return permissionName;
	}
	
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}	
	
	public String getAllAppPermissionInfo(){
		return classPath + " " + permissionName + " " + lineNumber;
	}
	
}
