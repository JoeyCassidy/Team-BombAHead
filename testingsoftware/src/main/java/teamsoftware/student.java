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
	/**
	 * dont know what this is supposed to do . i think sql server stuff
	 * @return
	 */
	public student initstudent() {
		return null; // do this with the html guys
	}
	
	/**
	 * if a is true then it accepts friend. if a is false it denies the friend.
	 * @param a
	 * @param b
	 */
	public void acceptordenyfriends(Boolean a, student b) {
		if(b!=null) {
			if(a==true) {
				//			adding part
				friends.put(b.Email, friendsincoming.get(b.Email));
				//			deleting part
				friendsincoming.remove(b.Email);
				b.friendsoutgoing.remove(this.Email);
			}else if(a==false) {
				//			deleting part
				friendsincoming.remove(b.Email);
				b.friendsoutgoing.remove(this.Email);
			}
		}
	}
	
	/**
	 * this is a friend requesnt send
	 * @param a
	 */
	public void addfriends(student a) {
		if(a != null) {
			this.friendsoutgoing.put(a.Email, a);
			a.friendsincoming.put(this.Email, this);
		}
	}
	
	/**
	 * this delete the friend from this persons friend list but not the reverse
	 * does reverse need to be added
	 * @param a
	 * @return
	 */
	public student deletefriends(student a) {
		if(a != null) {
			this.friends.remove(a.Email);
//			this can be implemented but depends on if needed and server
//			a.friends.remove(this.Email);
		}
		return a;
	}
	
	/**
	 * adds the study group to this person and vice versa
	 * @param a
	 */
	public void addstudygroup(studygroup a) {
		if(a != null) {
			a.listofstudents.put(this.Email, this);
			this.listofstudygroups.put(a.identifier, a);
		}
	}
	
	/**
	 * remove the student from the grop and the group from the student
	 * @param a
	 * @return
	 */
	public studygroup deletestudygroup(studygroup a){
		if(a != null) {
			a.listofstudents.remove(this.Email);
			this.listofstudygroups.remove(a.identifier);
		}
		return null;
	}
}
