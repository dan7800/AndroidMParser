import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommitAnalyzerSuper {

	private final String DbLocation = "E:\\Dropbox\\confpapers\\AndroidData\\F-Droid_LifeCycles\\data\\AndrosecData.sqlite";
	
	private List<appIDInfo> appIDList=new ArrayList<appIDInfo>();
	
	
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
		    	
		    	
		    	
		    	
		    
		    	String sqlAllApps="select distinct(commit_ID) as commit_ID, appID from ManifestPermissionCommitt_view where appid =35";
		    	ResultSet rsAllApps = stmt.executeQuery( sqlAllApps );
		    	
		    	while (rsAllApps.next()) {
		    		// build list of all appids
		    		
		    		//appIDCommitListList.add(new AppIDCommitPair(Integer.parseInt(rsAllApps.getString("appID")), Integer.parseInt(rsAllApps.getString("Commit_ID"))));	
		    		appIDList.add(new appIDInfo(Integer.parseInt(rsAllApps.getString("appID"))));
		    	
		    	}
		    	

		    	
		    	
		    	
		   
		    	// now loop through the created objects and add all commit items
		    	for(int a=0; a<appIDList.size(); a++){
		    		String sqlAppInfo="select * from ManifestPermissionCommitt_view where appid =" + appIDList.get(a).getAppID();
			    //	System.out.println(sqlAppInfo);
		    		ResultSet rsAppInfo = stmt.executeQuery( sqlAppInfo );
		    		
			    	while (rsAppInfo.next()) {
			    	//	System.out.println("add item");
			    		// add the commitInfo to the list
			    		appIDList.get(a).addCommitInfoList(Integer.parseInt(rsAppInfo.getString("Commit_ID")));
			    		
			    	}
			    	
		    	}
		   
		    	
		    	// Now loop through the created appID object and created a bunch of permission objects
		    	
		    	for(int a=0; a<appIDList.size(); a++){
		    		
		    		//System.out.println("AppID" + appIDList.get(a).getAppID() + " CommitID: "+ appIDList.get(a).();
		    		
		    		for(int b=0; b<appIDList.get(a).getCommitInfo().size(); b++){
		    			System.out.println("AppID" + appIDList.get(a).getAppID() + " CommitID: "+ appIDList.get(a).getCommitInfo().get(b));
		    		}
		    		
		    		
		    	}
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    	
		    //	System.out.println(appIDList.get(0).getCommitInfo().size());
		    	
		    	
		    	
		    //	System.out.println(appIDCommitListList.size());
		    	
		    	// Loop through this list and build up the permissions for each pair
		    	//for(int a=0; a<appIDCommitListList.size(); a++){
		    		//System.out.println(appIDCommitListList.get(i).getCommitID());
		    		
		    	
		    		
		    		
		    		
		    		
		    		
		    		
		    		
		   // 	}
		    	
		    	
		    	
		    	
		    	
		    	
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
			System.out.println("Added:" + added.get(i));
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
	
	

