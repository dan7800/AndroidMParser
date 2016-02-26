import java.util.ArrayList;
import java.util.List;


// Dummy test class

public class Dummy {

	public static void main(String[] args) {
	Dummy d = new Dummy();
	//cas.Run();
//	cas.dbConnect();
	
	
	// PrepDB(); // Prepare the database for inserting values
	d.Run();
}
	
	
	
private void Run(){
		
	List oldPermissions = new ArrayList();
	List newPermissions = new ArrayList();
	
	oldPermissions.add(1);
	oldPermissions.add(2);
	oldPermissions.add(3);
	
	
	newPermissions.add(2);
	newPermissions.add(3);
	newPermissions.add(4);
	newPermissions.add(5);
	
	
//	showList(a1);
//	showList(a2);
	
//	AnalyzeLists(oldPermissions, newPermissions);
	System.out.println(isExistInList(oldPermissions, 0));
	System.out.println(isExistInList(oldPermissions, 2));
	System.out.println(isExistInList(oldPermissions, 3));
	System.out.println(isExistInList(oldPermissions, 4));
	}


// Detter
private boolean isExistInList(List a, int item){
	boolean retVal=false;
	if(a.contains(item)){
		retVal=true;
	}
	
	return retVal;
}



private void AnalyzeLists(List la, List lb){
	
	// get what was added
	getAdded(la, lb);
	getMissing(la, lb);
		
	// get what was removed
}

//Get what was added to the permissions list
private void getAdded(List la, List lb){
	
	// Loop through each of the items and then add them to the db
	List added = getAddedListValues(la, lb);
	for(int i=0; i<added.size(); i++){
		System.out.println("Added:" + added.get(i));
	}
}

// Get what was removed from the list
private void getMissing(List la, List lb){
	List removed = getMissingListValues(la, lb);
	for(int i=0; i<removed.size(); i++){
		System.out.println("Removed:" + removed.get(i));
	}
}

// Create the list of added items
private List getAddedListValues(List la, List lb){
	List toReturn = new ArrayList(lb);
	toReturn.removeAll(la);
	return toReturn;
}


// Create the list of removed items
private List getMissingListValues(List la, List lb){
	List toReturn = new ArrayList(la);
	toReturn.removeAll(lb);
	return toReturn;
}



	
	private void showList(List list){
		for (int i=0; i<list.size();i++){
			System.out.println(list.get(i));
		}
	}
	
}
