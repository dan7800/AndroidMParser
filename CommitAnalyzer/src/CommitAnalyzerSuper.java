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
		
		int counter=0;
		// select Permission_ID, appID, commit_date from ManifestPermissionCommitt_view
		
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		
		    	stmt = c.createStatement();
		    	String sqlAllApps="select * from ManifestPermissionCommitt_view";
		    	//String sqlAllApps="select * from dummy";
		    	ResultSet rsAllApps = stmt.executeQuery( sqlAllApps );
		    	
		    	 while (rsAllApps.next()) {
		    		 
		    		 if(Integer.parseInt(rsAllApps.getString("appID"))!=AppID){

		    			 if(AppID > 0){ // don't run the 1st time
		    				 AnalyzeLists(permissionsList_Prev, permissionsList_Current);
		    			 }
		    			
		    			 AppID=Integer.parseInt(rsAllApps.getString("appID"));
		    			 commitID=Integer.parseInt(rsAllApps.getString("commit_ID"));

		    			 permissionsList_Current.clear();
		    			 permissionsList_Prev.clear();
		    			 
		    			 permissionsList_Current.add(Integer.parseInt(rsAllApps.getString("permission_ID")));
		    	//		 showList(permissionsList_Current);
		    			 
		    		 }else{ // not new AppID

		    			 if(Integer.parseInt(rsAllApps.getString("Commit_ID"))!=commitID){  // new commitID
		    				 commitID=Integer.parseInt(rsAllApps.getString("commit_ID"));
		    				 
		    				 AnalyzeLists(permissionsList_Prev, permissionsList_Current);

		    				 permissionsList_Prev.clear();
		    				 permissionsList_Prev.addAll(permissionsList_Current);
		    				 permissionsList_Current.clear();
		    				 permissionsList_Current.add(Integer.parseInt(rsAllApps.getString("permission_ID")));
		    				 
		    			 }else{ // Commit ID is not new
		    				 permissionsList_Current.add(Integer.parseInt(rsAllApps.getString("permission_ID")));
		    				 
		    			 }
		    			 counter++;
		    		 }
		    		 
		    		 
		    		 
		    		 
		    		 
		    		 
		    		// Loop through all view records
				    	
				    	
				    	// If new appID
				    	
					    	// Do the comparision of the old and new temps
					    	// set the old = new 
				    		// Clear out new
					    	
					    	//	Set appID=new
					    	//	Set commitID = new
					    	// 	Add the permission to the list
					    	
				    	
				    	
				    	// if not new appID
				    	
				    		// if new commitID
				    			// set commitID= new
				    	 		//Do the comparision of the old and new temps
					    		// set the old = new 
				    			// Clear out new
				    			// Add the new permission to the list
				    	
				    		// If not new commit iD
				    			// Add the permission to the list
		    		 
		    		 
		    		 
		    		 
		    		 
		    		 
		    		 
		    	 }
	//	    	System.out.println("done");
	//	    	System.out.println(permissionsList_Prev.size());
	//	    	System.out.println(permissionsList_Current.size());
		    	 AnalyzeLists(permissionsList_Prev, permissionsList_Current);
		    
		    
		    	
		    	
		    	
		  
		    	
		    	
		    	
		    	
		    	
		    	
		    	
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
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<list.size();i++){
			//System.out.println(list.get(i));
			sb.append(list.get(i));
			sb.append(",");
		}
	
		System.out.println(sb.toString());
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
		System.out.println("**********analyze Lists********" + la.size() + " " + lb.size());
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
	
	
