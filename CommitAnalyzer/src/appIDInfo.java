import java.util.ArrayList;
import java.util.List;



public class appIDInfo {
	
	private List commitInfo = new ArrayList();
	int appID;
	
	public appIDInfo(int appID) {
		super();
		this.appID = appID;
	}
	

	
	public int getAppID() {
		return appID;
	}
	public void setAppID(int appID) {
		this.appID = appID;
	}
	public List getCommitInfo() {
		return commitInfo;
	}
	public void setCommitInfo(List commitInfo) {
		this.commitInfo = commitInfo;
	}
	
	public void addCommitInfoList(int item){
		commitInfo.add(item);
	}
	
	
}
