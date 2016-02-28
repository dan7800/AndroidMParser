public class pairList<L,R> {

	private int appID;
	private int commitID;
	
	public pairList(int appID, int commitID) {
		super();
		this.appID = appID;
		this.commitID = commitID;
	}
		
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public int getCommitID() {
		return commitID;
	}
	public void setCommitID(int commitID) {
		this.commitID = commitID;
	}
	
	
	/*
	private L l;
    private R r;
    public pairList(L l, R r){
        this.l = l;
        this.r = r;
    }
    public L getL(){ return l; }
    public R getR(){ return r; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
    */
}