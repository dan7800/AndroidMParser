import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class updateCommitOrder {

	
	private final String DbLocation = "/Users/dxkvse/Dropbox/confpapers/AndroidData/F-Droid_LifeCycles/data/AndrosecData.sqlite";
	private List updateStatementsList = new ArrayList();
	
	
	public static void main(String[] args) {
		
		updateCommitOrder uco = new updateCommitOrder();
		uco.Run();
	}
	

	
	
	private void Run(){

		// loop through the 
		
		int counter=0;
		int AppID=0;
		
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		
		    	stmt = c.createStatement();
		    	final String sqlAllApps="select * from CommitCount_view";
		  //  	final String sqlAllApps="select * from dummy"; // temp information
		    	

		    	ResultSet rsAllApps = stmt.executeQuery( sqlAllApps );
		    	
		    	 while (rsAllApps.next()) {
		    		 
		    		 if(Integer.parseInt(rsAllApps.getString("appID"))!=AppID){

		    			 
		    			 
		    			 AppID=Integer.parseInt(rsAllApps.getString("appID"));
		    			 counter=1;
		    		 }
		    		 
		    		 String sql="update android_Manifest_commitinfo set orderdate=" + counter + " where commit_ID= "+ rsAllApps.getString("commitID");
	    			 //System.out.println(sql);
		    		 updateStatementsList.add(sql); // loop through this later on to avoid locking
	    			
		    		 
		    	//	 System.out.println(rsAllApps.getString("commitID") + " " + counter);
		    		 
		    		 counter=counter+1;
		    	 }

		    	 // close all the connections so the information can be written to the DB. Prevent locking
		    	 stmt.close();
		    	 rsAllApps.close();
		    	 c.close();
		    	 
		    	 
		   } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
	
		
		// now execute the update statements
		 
		 for (int i=0; i<updateStatementsList.size();i++){
			 
			 System.out.println(updateStatementsList.get(i).toString());
			 
				Connection c2 = null;
			    Statement stmt2 = null;
				 try {
				    	Class.forName("org.sqlite.JDBC");
				      
				    	c2 = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
				    	c2.setAutoCommit(false);
				    	
				    	stmt2 = c2.createStatement();
				       
				        stmt2.executeUpdate(updateStatementsList.get(i).toString());
				        c2.commit();
				    	
				        // Make sure to close the connections to prevent locking
				        stmt2.close();
				        c2.close();
				        
				    } catch ( Exception e ) {
					      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
					      System.exit(0);
					    }

			 
			 
			 
		 }
		 
		 
		 
		 
		 
		 
	}


	



}
