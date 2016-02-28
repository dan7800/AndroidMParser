import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// temp file to update the CSV information

public class updateCSV {

//	private final String DbLocation = "E:\\Dropbox\\confpapers\\AndroidData\\F-Droid_LifeCycles\\data\\AndrosecData.sqlite";
	private final String DbLocation = "/Users/dxkvse/Dropbox/confpapers/AndroidData/F-Droid_LifeCycles/data/AndrosecData.sqlite";

	ArrayList<pairList> pairList = new ArrayList<pairList>();
	
	
	
	public static void main(String[] args) {
		updateCSV u = new updateCSV();
		u.Run();
	}
	
	private void Run(){
		
		updateView();
	}
	
	
	private void updateView(){
		
		// Loop through the view and update the order date field
		
		
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		
		    	stmt = c.createStatement();
		  // 	final String sqlAllApps="select * from ManifestPermissionCommitt_view where appID =10";
		    	final String sqlAllApps="select distinct commit_ID, appID  from ManifestPermissionCommitt_view where appid <14";
		    	
		    	ResultSet rsAllApps = stmt.executeQuery( sqlAllApps );	    	
		    	 while (rsAllApps.next()) {
		    		 // Build the list of all pair items
		    		 pairList.add(new pairList(Integer.parseInt(rsAllApps.getString("appID")) ,Integer.parseInt(rsAllApps.getString("commit_ID"))));
		    	 }
			    stmt.close();
			    rsAllApps.close();
			    c.close();
			    	 
			    	 
			   } catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				    }
		
		
		// now loop through the list 
		// System.out.println(pairList.size());
		// showList();
		 
		 int appID=-1;
		 int orderCount=1;
		 for (int i=0; i<pairList.size();i++){
			 if(appID!=pairList.get(i).getAppID()){
				 
				// System.out.println(pairList.get(i).getAppID());
				 
				 appID=pairList.get(i).getAppID();
				 updateValues(pairList.get(i).getAppID(),pairList.get(i).getCommitID(),orderCount);
				 
				 
				 orderCount=1;
			 }
			 
		 }
		 
		 
		
		 
		 
		 
		
	}
	
	
	private void updateValues(int appID, int commitID, int orderCount){
		
		final String updateSQL="update Android_Manifest_CommitInfo set orderDate = " + orderCount + " where appID= " + appID + " and commit_ID= "+ commitID;
		
		System.out.println(updateSQL);
		
		
	}
	
	
	// only used for debugging
		public void showList(){

			for (int i=0; i<pairList.size();i++){

				System.out.println(pairList.get(i).getAppID() + " " + pairList.get(i).getCommitID());
			}
		}
	

}
