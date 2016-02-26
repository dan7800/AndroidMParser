// hold the pair for all commit values

public class AppIDCommitPair {
	
	int appID;
	int CommitID;
	
	
	public AppIDCommitPair(int appID, int commitID) {
		super();
		this.appID = appID;
		CommitID = commitID;
	}
	
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public int getCommitID() {
		return CommitID;
	}
	public void setCommitID(int commitID) {
		CommitID = commitID;
	}
}
