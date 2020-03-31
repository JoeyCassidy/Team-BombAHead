package teamsoftware;
import cs2321.HashMap;

public class student {
	String Email;
	schedule Schedule;
	setting Setting;
	HashMap<String,student> friends;
	HashMap<String,student> friendsIncoming;
	HashMap<String,student> friendsOutgoing;
	HashMap<String,studyGroup> listOfStudyGroups;

	/**
	 * don't know what this is supposed to do.. I think sql server stuff?
	 * @return - the specified student
	 */
	public student initStudent() {
		return null; // do this with the html guys
	}
	
	/**
	 * This method sorts out when adding a friend will send the request
	 * to the acceptees database, and when he accepts or declines the
	 * friend request, the requests will clear out of both databases and the friends will be added.
	 * @param accept - boolean that accepts friend is true, and denies friend if false
	 * @param user - determines which student is under consideration for the friend request
	 */
	public void acceptOrDenyFriends(Boolean accept, student user) {
		if(user!=null) {
			if(accept==true) {
				//			adding part
				friends.put(user.Email, friendsIncoming.get(user.Email));
				//			deleting part
				friendsIncoming.remove(user.Email);
				user.friendsOutgoing.remove(this.Email);
			}else if(accept==false) {
				//			deleting part
				friendsIncoming.remove(user.Email);
				user.friendsOutgoing.remove(this.Email);
			}
		}
	}
	
	/**
	 * This method send a friend request to another user
	 * @param user - the friend that is receiving the friend request
	 */
	public void addFriends(student user) {
		if(user != null) {
			this.friendsOutgoing.put(user.Email, user);
			user.friendsIncoming.put(this.Email, this);
		}
	}
	
	/**
	 * This method deletes the friend from this persons friend list but not the reverse
	 * does reverse need to be added?
	 * @param deletee - the user being deleted from the users friend list
	 * @return - the deleted friend
	 */
	public student deleteFriends(student deletee) {
		if(deletee != null) {
			this.friends.remove(deletee.Email);
//			this can be implemented but depends on if needed and server
//			a.friends.remove(this.Email);
		}
		return deletee;
	}
	
	/**
	 * This method adds the study group to this person and vice versa
	 * @param a - the created study group
	 */
	public void addStudyGroup(studyGroup group) {
		if(group != null) {
			group.listOfStudents.put(this.Email, this);
			this.listOfStudyGroups.put(group.identifier, group);
		}
	}
	
	/**
	 * This method removes the student from the group and the group from the student
	 * @param group - the group which the student will be deleted from
	 * @return the deleted studyGroup / user[?]
	 */
	public studyGroup deleteStudyGroup(studyGroup group){
		if(group != null) {
			group.listOfStudents.remove(this.Email);
			this.listOfStudyGroups.remove(group.identifier);
		}
		return null;
	}
}
