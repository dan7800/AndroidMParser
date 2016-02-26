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
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	  }
		
		
	}
	
	

