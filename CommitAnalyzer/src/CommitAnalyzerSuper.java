import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommitAnalyzerSuper {

	/*
	 * How to pick up if all permissions are removed in a manifest commit
	 * 
	 * 
	 */
	
	
	Util u = new Util();
	
//	private final String DbLocation = "E:\\Dropbox\\confpapers\\AndroidData\\F-Droid_LifeCycles\\data\\AndrosecData.sqlite";
	private final String DbLocation = "/Users/dxkvse/Dropbox/confpapers/AndroidData/F-Droid_LifeCycles/data/AndrosecData.sqlite";
		
	private int statementCount=0;
	private final String SQLOutput ="SQLoutput.txt";
	
	// create list of all dbInserts. Done in this way to try to eliminate potential locking and help debugging
	private List insertStatements = new ArrayList();
	
	
	public static void main(String[] args) {
		
		CommitAnalyzerSuper cas = new CommitAnalyzerSuper();
		cas.buildChangeList();
		
	}
	
	
	// Create the list of changes made to the DB
	private void buildChangeList(){
		
		// Clear contents of logging file
		u.clearFile(SQLOutput);
		clearDB();
		
		int AppID=-1; 	//define the initial appID
		int commitID=-1;//define the initial commitID
		
		// Create the list of permissions
		List permissionsList_Current = new ArrayList();
		List permissionsList_Prev = new ArrayList();
		
		int counter=0; // See how many values were examined
	
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		
		    	stmt = c.createStatement();
		    	final String sqlAllApps="select * from ManifestPermissionCommitt_view";
		  //  	final String sqlAllApps="select * from dummy"; // temp information
		    	

		    	ResultSet rsAllApps = stmt.executeQuery( sqlAllApps );
		    	
		    	 while (rsAllApps.next()) {
		    //		 System.out.println(rsAllApps.getString("commit_ID"));
		    		// System.out.println("77");
		    		 if(Integer.parseInt(rsAllApps.getString("appID"))!=AppID){

		    			 if(AppID > 0){ // don't run the 1st time
		    				 AnalyzeLists(permissionsList_Prev, permissionsList_Current, AppID, commitID);
		    			 }
		    			
		    			 AppID=Integer.parseInt(rsAllApps.getString("appID"));
		    			 commitID=Integer.parseInt(rsAllApps.getString("commit_ID"));

		    			 permissionsList_Current.clear();
		    			 permissionsList_Prev.clear();

		    			 // The appID is the initial appID

		    			 permissionsList_Current.add(Integer.parseInt(rsAllApps.getString("permission_ID")));

		    			 
		    		 }else{ // not new AppID

		    			 if(Integer.parseInt(rsAllApps.getString("Commit_ID"))!=commitID){  // new commitID
		    				 
		    				 AnalyzeLists(permissionsList_Prev, permissionsList_Current, AppID, commitID);
		    				 commitID=Integer.parseInt(rsAllApps.getString("commit_ID"));
		    				 permissionsList_Prev.clear();
		    				 permissionsList_Prev.addAll(permissionsList_Current); // setting them to be equal creates problems
		    				 permissionsList_Current.clear();
		    				 permissionsList_Current.add(Integer.parseInt(rsAllApps.getString("permission_ID")));
		    				 
		    			 }else{ // Commit ID is not new
		    				
		    				 permissionsList_Current.add(Integer.parseInt(rsAllApps.getString("permission_ID")));
		    			 }
		    			 counter++;
		    		 }
		   		 
		    	 }
	
		    	 AnalyzeLists(permissionsList_Prev, permissionsList_Current, AppID, commitID);
		    	// System.out.println("Final count:" + counter);

		    	 // close all the connections so the information can be written to the DB. Prevent locking
		    	 stmt.close();
		    	 rsAllApps.close();
		    	 c.close();
		    	 
		    	 
		   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
	
	
		// System.exit(0);
		 
		 // Create a set of insert statements since this could be used for debugging
		 for(int z=0; z<insertStatements.size(); z++){
			 addManifestChange(insertStatements.get(z).toString());
		 }
		 
		System.out.println("Statement count " + statementCount);
	}
	
	
	
	// Log all the created insert statements to the log file and then put them into the database
	private void addManifestChange(String sql){
		System.out.println("add change: " + sql);
		
		/*
		 PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(SQLOutput, true), true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      out.write(sql+"\n");
	      out.close();
*/
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		    	
		    	stmt = c.createStatement();
		       
		        stmt.executeUpdate(sql);
		        c.commit();
		    	
		        // Make sure to close the connections to prevent locking
		        stmt.close();
		        c.close();
		        
		    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
		
	}
	

	// Go through and build a list of all permission in each commit. This will tell you what changes
	private void AnalyzeLists(List la, List lb, int appID, int commitID){
	//	System.out.println("**********analyze Lists********" + la.size() + " " + lb.size());
		// get what was added
		getAdded(la, lb, appID, commitID);
		getMissing(la, lb, appID, commitID);
			
		// get what was removed
	}

	//Get what was added to the permissions list
	private void getAdded(List la, List lb, int appID, int commitID){
		
		// Loop through each of the items and then add them to the db
		List added = getAddedListValues(la, lb, appID, commitID);
		for(int i=0; i<added.size(); i++){
			System.out.println("Added: appID: " + appID + " CommitID:" + commitID + " perm: " + added.get(i));
			//alterAndroid_Manifest_Commit_Changes(appID, commitID, Integer.parseInt(added.get(i).toString()), "A");
			
			String sql = "insert into Android_Manifest_Commit_Changes (AppID, CommitID, PermissionID, Action) values ("+appID+","+commitID+","+added.get(i)+",'A');";
			insertStatements.add(sql);
			
			statementCount = statementCount+1;
		}
	}

	// Get what was removed from the list
	private void getMissing(List la, List lb, int appID, int commitID){
		List removed = getMissingListValues(la, lb);
		for(int i=0; i<removed.size(); i++){
//			System.out.println("Removed: appID: " + appID + " CommitID:" + commitID + " perm: " + removed.get(i));
		//	alterAndroid_Manifest_Commit_Changes(appID, commitID, Integer.parseInt(removed.get(i).toString()), "R");
			String sql = "insert into Android_Manifest_Commit_Changes (AppID, CommitID, PermissionID, Action) values ("+appID+","+commitID+","+removed.get(i)+",'R');";
			insertStatements.add(sql);
			
			statementCount = statementCount+1;
		}
	}

	// Create the list of added items
	private List getAddedListValues(List la, List lb, int appID, int commitID){
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
	
	// Clear the record table. This is just for cleaning up old results
	private void clearDB(){
		
		System.out.println("*** Clear DB table: Android_Manifest_Commit_Changes");
		
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

		
	}
	
	

