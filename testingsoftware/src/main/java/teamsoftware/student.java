package teamsoftware;
import cs2321.HashMap;

public class student {
	String Email;
	schedule Schedule;
	setting Setting;
	HashMap<String,student> friends;
	HashMap<String,student> friendsincoming;
	HashMap<String,student> friendsoutgoing;
	HashMap<String,studygroup> listofstudygroups;
	
	public student initstudent() {
		return null; // do this with the html guys
	}
	
	public void acceptordenyfriends(Boolean a, student b) {
		
	}
	
	public void addfriends(student a) {
	}
	
	public student deletefriends(student a) {
		return null;
	}
	
	public void addstudygroup(studygroup a) {
	}
	
	public studygroup deletestudygroup(studygroup a){
		return null;
	}
}
