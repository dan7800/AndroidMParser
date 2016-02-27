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

//	private final String DbLocation = "E:\\Dropbox\\confpapers\\AndroidData\\F-Droid_LifeCycles\\data\\AndrosecData.sqlite";
	private final String DbLocation = "/Users/dxkvse/Dropbox/confpapers/AndroidData/F-Droid_LifeCycles/data/AndrosecData.sqlite";
		
	private int statementCount=0;
	
	
	// create list of all dbInserts -- Prevents locking
	private List insertStatements = new ArrayList();
	
	
	public static void main(String[] args) {
		
		CommitAnalyzerSuper cas = new CommitAnalyzerSuper();
		// PrepDB(); // Prepare the database for inserting values
		cas.buildChangeList();
		
		
		
		
	}
	
	
	// Create the list of changes made to the DB
	private void buildChangeList(){
		
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
		  //  	String sqlAllApps="select * from ManifestPermissionCommitt_view";
		    	final String sqlAllApps="select * from dummy";
		    	ResultSet rsAllApps = stmt.executeQuery( sqlAllApps );
		    	
		    	 while (rsAllApps.next()) {
		    		 
		    		 if(Integer.parseInt(rsAllApps.getString("appID"))!=AppID){

		    			 if(AppID > 0){ // don't run the 1st time
		    				 AnalyzeLists(permissionsList_Prev, permissionsList_Current, AppID, commitID);
		    			 }
		    			
		    			 AppID=Integer.parseInt(rsAllApps.getString("appID"));
		    			 commitID=Integer.parseInt(rsAllApps.getString("commit_ID"));

		    			 permissionsList_Current.clear();
		    			 permissionsList_Prev.clear();
		    			 
		    			 permissionsList_Current.add(Integer.parseInt(rsAllApps.getString("permission_ID")));
		    			 
		    		 }else{ // not new AppID

		    			 if(Integer.parseInt(rsAllApps.getString("Commit_ID"))!=commitID){  // new commitID
		    				 commitID=Integer.parseInt(rsAllApps.getString("commit_ID"));
		    				 
		    				 AnalyzeLists(permissionsList_Prev, permissionsList_Current, AppID, commitID);

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

		   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
	
	
		 
		 for(int z=0; z<insertStatements.size(); z++){
			// addManifestChange(insertStatements.get(z).toString());
			// System.out.println(insertStatements.size());
		 }
		 
			
		System.out.println("Statement count " + statementCount);
	}
	
	
	
	private void addManifestChange(String sql){
		System.out.println("add change " + sql);
		
		
		 PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("output.txt", true), true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      out.write(sql);
	      out.close();
		
		
		/*
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		    	
		    	stmt = c.createStatement();
		       
		        stmt.executeUpdate(sql);
		        c.commit();
		    	
		    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
	
		*/
		
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
	
	
	// make the necessary changes 
	private void alterAndroid_Manifest_Commit_Changes(int appID, int commitID, int permissionID, String change){
		//System.out.println(appID + " " +commitID+ " " + permissionID+ " " + change);
		

		String sql = "insert into Android_Manifest_Commit_Changes (AppID, CommitID, PermissionID, Action) values ("+appID+","+commitID+","+permissionID+",'"+change+"');";
		insertStatements.add(sql);
		
		/*
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		    	
		    	
		    	stmt = c.createStatement();
		       // String sql = "delete from Android_Manifest_Commit_Changes";
		    	String sql = "insert into Android_Manifest_Commit_Changes (AppID, CommitID, PermissionID, Action) values ("+appID+","+commitID+","+permissionID+",'"+change+"');";
		        stmt.executeUpdate(sql);
		        c.commit();
		    	
		    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
		
		*/
	}
	
	
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
			System.out.println("Added:" + added.get(i) + " appID: " + appID + " CommitID:" + commitID);
			alterAndroid_Manifest_Commit_Changes(appID, commitID, Integer.parseInt(added.get(i).toString()), "A");
			statementCount = statementCount+1;
		}
	}

	// Get what was removed from the list
	private void getMissing(List la, List lb, int appID, int commitID){
		List removed = getMissingListValues(la, lb);
		for(int i=0; i<removed.size(); i++){
			System.out.println("appID: " + appID + " CommitID:" + commitID + " permissionID:" + );
			alterAndroid_Manifest_Commit_Changes(appID, commitID, Integer.parseInt(removed.get(i).toString()), "R");
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
		
	}
	
	

