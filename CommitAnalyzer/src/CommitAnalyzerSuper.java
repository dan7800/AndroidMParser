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

	
	private void Run(){
		System.out.println("dan");
	}
	
	
	private boolean isInList(){
		return false;
	}
	
	
	
	// Create the list of changes made to the DB
	private void buildChangeList(){
		
		//1) Loop through all the commits
		
		int AppID=-1; //define the initial appID
		
		// Create the list of permissions
		List permissionsList = new ArrayList();
		List permissionsList_Temp = new ArrayList();
		
		
		// select Permission_ID, appID, commit_date from ManifestPermissionCommitt_view
		
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		
		    	
		    	stmt = c.createStatement();
		    	String sql1a="select Permission_ID, appID, commit_date from ManifestPermissionCommitt_view where appID =10";
		    	ResultSet rs2 = stmt.executeQuery( sql1a );
		    	 
		    	 while (rs2.next()) {
		    		
		    		 // Not a new AppID
		    		 if(Integer.parseInt(rs2.getString("appID"))==AppID){

		    			 

		    			 permissionsList_Temp = permissionsList; // set the temp list equal to the old list
		    			 
		    			 
		    			 
		    			// Check to see if the permission is in the list
		    			 
		    			 
		    			 // Next check to see if the old list was 
		    			
		    			 
		    			 
		    		//	 permissionsList.add(Integer.parseInt(rs2.getString("permission_ID")));
		    		
		    		 
		    		// New AppID
		    		 }else{
		    			 // Set a new appID and clear the list
		    			 AppID=Integer.parseInt(rs2.getString("appID"));
		    			// System.out.println(AppID);
		    			 permissionsList.clear();
		    		 }
		    		 
		    		
		    		// System.out.println(rs2.getString("Permission_ID") +" "+ rs2.getString("appID"));
		    	 }
		    	// System.out.println("dan");
		    	// showList(permissionsList);
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
	
	
	
	// good for testing
	private void dbConnect(){
		
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		    	
		    
		    	/*
		    	stmt = c.createStatement();
		        String sql = "delete from apkparser_intents";
		        System.out.println(sql);
		        stmt.executeUpdate(sql);
		        c.commit();
			     */
		    	
		    	stmt = c.createStatement();
		    	 String sql1a="select * from android_Manifest_permission_join where commit_ID = 51";
		    	 ResultSet rs2 = stmt.executeQuery( sql1a );
		    	 System.out.println(rs2.getString("Permission_ID"));
		    	 	
		    	//System.out.println("Files cleared");
		
		    	
		    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
			
		
		
		/*
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	  }
	  */
		
		/*
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM android_Manifest_permission_join;" );
	      while ( rs.next() ) {
	    	  System.out.println("Dan");
	    	  
	         int id = rs.getInt("id");
	         String  name = rs.getString("name");
	         int age  = rs.getInt("age");
	         String  address = rs.getString("address");
	         float salary = rs.getFloat("salary");
	         System.out.println( "ID += " + id );
	         System.out.println( "NAME = " + name );
	         System.out.println( "AGE = " + age );
	         System.out.println( "ADDRESS = " + address );
	         System.out.println( "SALARY = " + salary );
	         System.out.println();
	         
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	 
	*/
	}
		
		
	}
	
	

