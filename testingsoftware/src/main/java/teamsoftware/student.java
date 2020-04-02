package teamsoftware;
import cs2321.HashMap;
import java.sql.*;

public class student {
	int ID = -1; //******************************************************************************
	String Email;
	schedule Schedule;
	setting Setting;
	HashMap<String,student> friends = new HashMap<String,student>();
	HashMap<String,student> friendsIncoming = new HashMap<String,student>();
	HashMap<String,student> friendsOutgoing = new HashMap<String,student>();
	HashMap<String,studyGroup> listOfStudyGroups = new HashMap<String,studyGroup>();

	/**
	 * don't know what this is supposed to do.. I think sql server stuff?
	 * @return - the specified student
	 * @throws SQLException 
	 */
	public student initStudent(int studentID) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from STUDENT "
				+ " where ID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, studentID);
		rs = stmt.executeQuery();
		ID = rs.getInt(1);
		Email = rs.getString(2);
		return this;
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
	 * This method sorts out when adding a friend will send the request
	 * to the acceptees database, and when he accepts or declines the
	 * friend request, the requests will clear out of both databases and the friends will be added.
	 * @param accept - boolean that accepts friend is true, and denies friend if false
	 * @param user - determines which student is under consideration for the friend request
	 * @throws SQLException 
	 */
	public void SQLacceptOrDenyFriends(Boolean accept, int myid, int friendid) throws SQLException {
		student mystudent = new student().initStudent(myid);
		student friendstudent = new student().initStudent(friendid);
//		loads friends
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, friendid);
		stmt.setInt(2, 1);
		rs = stmt.executeQuery();
		while(rs.next()) {
			mystudent.friends.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
		}
		stmt.setInt(1, friendid);
		rs = stmt.executeQuery();
		while(rs.next()) {
			mystudent.friends.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
		}
//		loads incoming for me
		stmt.setInt(1, myid);
		stmt.setInt(2, 2);
		rs = stmt.executeQuery();
		while(rs.next()) {
			mystudent.friendsIncoming.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
		}
//		loads outgoing for them
		stmt.setInt(1, friendid);
		stmt.setInt(2, 3);
		rs = stmt.executeQuery();
		while(rs.next()) {
			mystudent.friendsOutgoing.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
		}
		if(friendstudent!=null) {
			if(accept==true) {
				//			adding part
				mystudent.friends.put(friendstudent.Email, mystudent.friendsIncoming.get(friendstudent.Email));
				//			deleting part
				mystudent.friendsIncoming.remove(friendstudent.Email);
				friendstudent.friendsOutgoing.remove(this.Email);
				p = " update FRIENDS "
				  + " set STATUS = 1 "
				  + " where STUDENTID = ?"
				  + " and FRIENDID = ?";
				stmt = conn.prepareStatement(p);
				stmt.setInt(1, myid);
				stmt.setInt(2, friendid);
				stmt.executeUpdate();
			}else if(accept==false) {
				//			deleting part
				mystudent.friendsIncoming.remove(mystudent.Email);
				friendstudent.friendsOutgoing.remove(this.Email);
				p = " delete "
				  + " from FRIENDS "
				  + " where STUDENTID = ? "
				  + " and FRIENDID = ?";
				stmt = conn.prepareStatement(p);
				stmt.setInt(1, myid);
				stmt.setInt(2, friendid);
				stmt.execute();
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
	 * returns the email of the user with the passed in id
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public String SQLgetEmail(int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from STUDENT "
				+ " where ID = ? ";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		rs = stmt.executeQuery();
		return rs.getString(3);
	}

	public schedule getSchedule(int myid) throws SQLException {
		return new schedule().SQLintitschedule(myid);
	}

	public Boolean[] SQLgetSetting(int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from SETTINGS "
				+ " where STUDENTID = ? ";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		rs = stmt.executeQuery();
		return new Boolean[]{rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9), rs.getBoolean(10)};
	}

	/**
	 * returns a array of arrays that hold studentid, name, and email of the users friends all as strings
	 * {
	 *     {friend id, friend name, friend email}
	 * }
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public String[][] SQLgetFriends(int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select count(*) "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		stmt.setInt(2, 1);
		rs = stmt.executeQuery();
		String[][] holdingfriends = new String[rs.getInt(1)][3];
		       p =" select * "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		stmt.setInt(2, 1);
		rs = stmt.executeQuery();
		int i = 0;
		while(rs.next()) {
			holdingfriends[i] = new String[]{Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3)};
		}
		return holdingfriends;
	}

	/**
	 * returns a array of arrays that hold studentid, name, and email of the users incoming friends all as strings
	 * {
	 *     {friend id, friend name, friend email}
	 * }
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public String[][] SQLgetFriendsIncoming(int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select count(*) "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		stmt.setInt(2, 2);
		rs = stmt.executeQuery();
		String[][] holdingfriends = new String[rs.getInt(1)][3];
		p =" select * "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		stmt.setInt(2, 2);
		rs = stmt.executeQuery();
		int i = 0;
		while(rs.next()) {
			holdingfriends[i] = new String[]{Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3)};
		}
		return holdingfriends;
	}

	/**
	 * returns a array of arrays that hold studentid, name, and email of the users outgoing friends all as strings
	 * {
	 *     {friend id, friend name, friend email}
	 * }
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public String[][] SQLgetFriendsOutgoing(int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select count(*) "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		stmt.setInt(2, 1);
		rs = stmt.executeQuery();
		String[][] holdingfriends = new String[rs.getInt(1)][3];
		p =" select * "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		stmt.setInt(2, 1);
		rs = stmt.executeQuery();
		int i = 0;
		while(rs.next()) {
			holdingfriends[i] = new String[]{Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3)};
		}
		return holdingfriends;
	}

	/**
	 * returns a array of arrays that hold groupid and name of the study groups the user is in as strings
	 * {
	 *     {study group id, study group name}
	 * }
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public String[][] SQLgetListOfStudyGroups(int userid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select count(*) "
				+ " from STUDYGROUP "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		rs = stmt.executeQuery();
		String[][] holdinggroups = new String[rs.getInt(1)][2];
		      p = " select * "
				+ " from STUDYGROUP "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, userid);
		rs = stmt.executeQuery();
		int i = 0;
		while(rs.next()) {
			holdinggroups[i] = new String[]{Integer.toString(rs.getInt(1)), rs.getString(2)};
		}
		return holdinggroups;
	}

	/**
	 * This method send a friend request to another user
	 * @param user - the friend that is receiving the friend request
	 * @throws SQLException 
	 */
	public void SQLaddFriends(int myid, int friendid) throws SQLException {
//		create myself
		student mystudent = new student().initStudent(myid);
		student friendstudent = new student().initStudent(friendid);
//		loads friends
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, myid);
		stmt.setInt(2, 3);
		rs = stmt.executeQuery();
		while(rs.next()) {
			mystudent.friendsOutgoing.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
		}
		stmt.setInt(1, friendid);
		stmt.setInt(2, 2);
		rs = stmt.executeQuery();
		while(rs.next()) {
			friendstudent.friendsIncoming.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
		}
		if(friendstudent.ID != -1) {
//		add to my outgoing
			mystudent.friendsOutgoing.put(friendstudent.Email, friendstudent);
			friendstudent.friendsIncoming.put(mystudent.Email, mystudent);
//		add to table
			p = " insert ignore into FRIENDS "
  			  + " values(?, ?, ?)";
			stmt = conn.prepareStatement(p);
//			adds from me to you as outgoing
			stmt.setInt(1, myid);
			stmt.setInt(2, friendid);
			stmt.setInt(3, 3);
			stmt.execute();
//			adds from you to me as incoming
			stmt.setInt(1, friendid);
			stmt.setInt(2, myid);
			stmt.setInt(3, 2);
			stmt.execute();
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
	 * This method deletes the friend from this persons friend list but not the reverse
	 * does reverse need to be added?
	 * @param deletee - the user being deleted from the users friend list
	 * @return - the deleted friend
	 * @throws SQLException 
	 */
	public student SQLdeleteFriends(int myid, int friendid) throws SQLException {
		student mystudent = new student().initStudent(myid);
		student friendstudent = new student().initStudent(friendid);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, myid);
		stmt.setInt(2, 1);
		rs = stmt.executeQuery();
		while(rs.next()) {
			mystudent.friends.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
		}
		stmt.setInt(1, friendid);
		stmt.setInt(2, 1);
		rs = stmt.executeQuery();
		while(rs.next()) {
			friendstudent.friends.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
		}
		if(friendstudent.ID != -1) {
			mystudent.friends.remove(friendstudent.Email);
			friendstudent.friends.remove(this.Email);
			p = " delete "
			  + " from FRIENDS "
			  + " where STUDENTID = ? "
			  + " and FRIENDID = ?";
			stmt = conn.prepareStatement(p);
			stmt.setInt(1, myid);
			stmt.setInt(2, friendid);
			stmt.execute();
			stmt.setInt(1, friendid);
			stmt.setInt(2, myid);
			stmt.execute();
		}
		return friendstudent;
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
	
//	/**
//	 * This method adds the study group to this person and vice versa
//	 * @param a - the created study group
//	 */
//	public void SQLaddStudyGroup(int studygroupid) {
////		******************************************************************************************
//		if(group != null) {
//			group.listOfStudents.put(this.Email, this);
//			this.listOfStudyGroups.put(group.identifier, group);
//		}
//	}
	
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
	
//	/**
//	 * This method removes the student from the group and the group from the student
//	 * @param group - the group which the student will be deleted from
//	 * @return the deleted studyGroup / user[?]
//	 */
//	public studyGroup SQLdeleteStudyGroup(studyGroup group){
////		******************************************************************************************
//		if(group != null) {
//			group.listOfStudents.remove(this.Email);
//			this.listOfStudyGroups.remove(group.identifier);
//		}
//		return null;
//	}
	
}
