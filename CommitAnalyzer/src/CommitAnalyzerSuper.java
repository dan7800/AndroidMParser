import java.sql.*;

public class CommitAnalyzerSuper {

	private final String DbLocation = "E:\\Dropbox\\confpapers\\AndroidData\\F-Droid_LifeCycles\\data\\AndrosecData.sqlite";
	public static void main(String[] args) {
		
		CommitAnalyzerSuper cas = new CommitAnalyzerSuper();
		//cas.Run();
		cas.dbConnect();
	}

	
	private void Run(){
		System.out.println("dan");
	}
	
	
	private void dbConnect(){
		
		Connection c = null;
	    Statement stmt = null;
		 try {
		    	Class.forName("org.sqlite.JDBC");
		      
		    	c = DriverManager.getConnection("jdbc:sqlite:E:\\Dropbox\\confpapers\\AndroidData\\F-Droid_LifeCycles\\data\\AndrosecData.sqlite");
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
	
	

