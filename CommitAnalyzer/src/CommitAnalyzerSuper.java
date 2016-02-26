import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommitAnalyzerSuper {

	private final String DbLocation = "E:\\Dropbox\\confpapers\\AndroidData\\F-Droid_LifeCycles\\data\\AndrosecData.sqlite";
	public static void main(String[] args) {
		
		CommitAnalyzerSuper cas = new CommitAnalyzerSuper();
		//cas.Run();
//		cas.dbConnect();
		
		
		// PrepDB(); // Prepare the database for inserting values
		cas.buildChangeList();
	}

	
	
	// Create the list of changes made to the DB
	private void buildChangeList(){
		
		//1) Loop through all the commits
		
		int AppID=-1; 	//define the initial appID
		int commitID=-1;//define the initial commitID
		
		// Create the list of permissions
		List permissionsList_Current = new ArrayList();
		List permissionsList_Prev = new ArrayList();
		
		
		// select Permission_ID, appID, commit_date from ManifestPermissionCommitt_view
		
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		
		    	stmt = c.createStatement();
		    	//String sql1a="select Permission_ID, commit_ID, appID, commit_date from ManifestPermissionCommitt_view where appID =1121";
		    	//ResultSet rs2 = stmt.executeQuery( sql1a );
		    	
		    	
		    	
		    	
		    
		    	String sqlAllApps="select * from ManifestPermissionCommitt_view where appID =10";
		    	ResultSet rsAllApps = stmt.executeQuery( sqlAllApps );
		    	
		    	while (rsAllApps.next()) {
		    		//System.out.println(rsAllApps.getString("commit_ID"));
		    		
		    		// Check to see if there is a new app ID
		    		if(Integer.parseInt(rsAllApps.getString("appID"))!=AppID){
		    			AppID=Integer.parseInt(rsAllApps.getString("appID"));
		    			commitID=-1;
		    			
		    			// add all permissions as new
		    			System.out.println("dan");
		    		
		    		
		    		// if new commit ID
		    		
		    			
		    			
		    			
		    		}
		    		
		    	
		    		
		    		
		    	}
		    	
		    	
		    	
		    	
		    	
		    	
		    	//inside every app id, get every commit
		    	//build the permissions for each commit
		    	// At the end, compoare the list of permissions in the commit to the old list
		    	// Log the differences
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
		
		
	}
	
	
	private void showList(List list){
		for (int i=0; i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
	
	
	// Go through and build a list of all permission in each commit. This will tell you what changes
		
	private void PrepDB(){
		
		
		// temporarily clear the record table. This is just for debugging
		
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		    	
		    	
		    	stmt = c.createStatement();
		        String sql = "delete from Android_Manifest_Commit_Changes";
		        System.out.println(sql);
		        stmt.executeUpdate(sql);
		        c.commit();
		    	
		    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
		
	}
	
	
	private void AnalyzeLists(List la, List lb){
		
		// get what was added
		getAdded(la, lb);
		getMissing(la, lb);
			
		// get what was removed
	}

	//Get what was added to the permissions list
	private void getAdded(List la, List lb){
		
		// Loop through each of the items and then add them to the db
		List added = getAddedListValues(la, lb);
		for(int i=0; i<added.size(); i++){
//			System.out.println("Added:" + added.get(i));
		}
	}

	// Get what was removed from the list
	private void getMissing(List la, List lb){
		List removed = getMissingListValues(la, lb);
		for(int i=0; i<removed.size(); i++){
			System.out.println("Removed:" + removed.get(i));
		}
	}

	// Create the list of added items
	private List getAddedListValues(List la, List lb){
		List toReturn = new ArrayList(lb);
		toReturn.removeAll(la);
		return toReturn;
	}


	// Create the list of removed items
	private List getMissingListValues(List la, List lb){
		List toReturn = new ArrayList(la);
		toReturn.removeAll(lb);
		return toReturn;
	}
		
	}
	
	

