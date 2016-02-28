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
		
		
/*
		System.out.println(isCorrectFormat("  Mon Feb 6 23:34:20 2012 +0000"));
		System.out.println(isCorrectFormat("commit 78938fe9e549ea96ba67fe876fa77ae98c804a4d"));
		System.out.println(isCorrectFormat(""));
		System.out.println(isCorrectFormat("  Wed Jan 19 11:41:25 2011 -0800"));
		
		System.out.println(isCorrectFormat("    Added TechTalkDemo from TFG"));
		
	*/	
		
		
		
		
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
		    //	final String sqlAllApps="select distinct commit_ID, appID  from ManifestPermissionCommitt_view where appid <14 order by appID, commit_ID";
		    	
		    //	final String sqlAllApps="select * from Android_Manifest_CommitInfo where commit_Date is not null and commit_ID =2";
		    	final String sqlAllApps="select * from Android_Manifest_CommitInfo where commit_Date is not null";
		    	
		    	
		    	ResultSet rsAllApps = stmt.executeQuery( sqlAllApps );	    	
		    	 while (rsAllApps.next()) {
		    		 // Build the list of all pair items
		
		    		 if (isCorrectFormat(rsAllApps.getString("commit_Date").trim())){ // check to make sure the date is in the correct format
		    			 pairList.add(new pairList(Integer.parseInt(rsAllApps.getString("commit_ID")) ,rsAllApps.getString("commit_Date").trim()));
		    		 }
		    		 
		    	 }
			    stmt.close();
			    rsAllApps.close();
			    c.close();
			    	 
			    	 
			   } catch ( Exception e ) {
				      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				      System.exit(0);
				    }
		
		
		// now loop through the list to update the dates
		 
		 for (int i=0; i<pairList.size();i++){
			 
		//	 System.out.println(getNewDate(pairList.get(i).getOldDate()));
			 
			 
			 updateValues(pairList.get(i).getCommitID(), getNewDate(pairList.get(i).getOldDate()));
			 
		 }
			 
}
	
	// Determine if the value is in the correct format to be considered as a date
	private boolean isCorrectFormat(String inputDate){
		boolean retVal=true;

		if(inputDate.length()<1){
			retVal=false;
		}

		if(inputDate.contains("commit")){
			retVal=false;
		}

		if(inputDate.contains("commit")){
			retVal=false;
		}

		if(!inputDate.toLowerCase().contains("jan") && !inputDate.toLowerCase().contains("feb") && !inputDate.toLowerCase().contains("mar") && !inputDate.toLowerCase().contains("apr") && !inputDate.toLowerCase().contains("may") && !inputDate.toLowerCase().contains("jun") && !inputDate.toLowerCase().contains("jul") && !inputDate.toLowerCase().contains("aug") && !inputDate.toLowerCase().contains("sep") && !inputDate.toLowerCase().contains("oct") && !inputDate.toLowerCase().contains("nov") && !inputDate.toLowerCase().contains("dec")){
			retVal=false;
		}
		
		
		
	//	System.out.println(retVal + " " + inputDate);

		return retVal;
	}
	
	
	
//	  Sat Jun 14 21:06:01 2014 -0400
	
		 // Convert the old date to the new date format
		private String getNewDate(String oldDate){
			String retVal="";
			
			
			if(oldDate.length()>1){ // Make sure that there is a value being passed in
			
				System.out.println(oldDate);
				String iMonth, iDay, iTime, iYear; // Set initial values
				iMonth = oldDate.split(" ")[1];
				iDay = oldDate.split(" ")[2];
				iTime = oldDate.split(" ")[3];
				iYear = oldDate.split(" ")[4];
				/*
				System.out.println(iMonth);
				System.out.println(iDay);
				System.out.println(iTime);
				System.out.println(iYear);
				*/
				// -- 2016-02-28 16:31:00
				
				// Done to help formatting decicions
				if(iDay.length() < 2){
					iDay="0"+iDay;
				}
				
				
				//return convertMonthString(iMonth)+"/"+iDay+"/"+iYear+" " + iTime;
				retVal= iYear+"-"+convertMonthString(iMonth.toLowerCase())+"-"+iDay+" " + iTime;
			}
			
			return retVal;
		}
		
		
	private int convertMonthString(String monthString){
		int retMonth = -1;
		
		if(monthString.equals("jan")){
			retMonth = 1;
		}else if(monthString.equals("feb")){
			retMonth = 2;
		}else if(monthString.equals("mar")){
			retMonth = 3;
		}else if(monthString.equals("apr")){
			retMonth = 4;
		}else if(monthString.equals("may")){
			retMonth = 5;
		}else if(monthString.equals("jun")){
			retMonth = 6;
		}else if(monthString.equals("jul")){
			retMonth = 7;
		}else if(monthString.equals("aug")){
			retMonth = 8;
		}else if(monthString.equals("sep")){
			retMonth = 9;
		}else if(monthString.equals("oct")){
			retMonth = 10;
		}else if(monthString.equals("nov")){
			retMonth = 11;
		}else if(monthString.equals("dec")){
			retMonth = 12;
		}else{
			retMonth=-2;
		}
			
		return retMonth;
	}
		
		

	private void updateValues(int commitID, String newDate){
		
		final String updateSQL="update Android_Manifest_CommitInfo set alteredDate = '" + newDate + "' where commit_ID= "+ commitID;
		
		System.out.println(updateSQL);
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:"+DbLocation);
		    	c.setAutoCommit(false);
		    	
		    	stmt = c.createStatement();
		       
		        stmt.executeUpdate(updateSQL);
		        c.commit();
		    	
		        // Make sure to close the connections to prevent locking
		        stmt.close();
		        c.close();
		        
		    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }

		
		
	}
	
	
	// only used for debugging
		public void showList(){

			for (int i=0; i<pairList.size();i++){

			//	System.out.println(pairList.get(i).getAppID() + " " + pairList.get(i).getCommitID());
			}
		}
	

}
