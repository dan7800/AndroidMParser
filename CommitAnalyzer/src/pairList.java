public class pairList<L,R> {

	
	private int commitID;
	private String oldDate;
	private String newDate;
	
	
	public pairList(int commitID, String oldDate) {
		super();
		this.commitID = commitID;
		this.oldDate = oldDate;
	}
	
	public int getCommitID() {
		return commitID;
	}
	public void setCommitID(int commitID) {
		this.commitID = commitID;
	}
	public String getOldDate() {
		return oldDate;
	}
	public void setOldDate(String oldDate) {
		this.oldDate = oldDate;
	}
	public String getNewDate() {
		return newDate;
	}
	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}
	
}