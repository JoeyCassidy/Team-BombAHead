package teamsoftware;
import cs2321.HashMap;
import java.sql.*;

public class student {
	public String ID = ""; //******************************************************************************
	public String Email;
	public schedule Schedule;
	public setting Setting;
	public HashMap<String,student> friends = new HashMap<String,student>();
	public HashMap<String,student> friendsIncoming = new HashMap<String,student>();
	public HashMap<String,student> friendsOutgoing = new HashMap<String,student>();
	public HashMap<String,studyGroup> listOfStudyGroups = new HashMap<String,studyGroup>();

	/**
	 * reports the studentid, name, and email
	 * @return - the specified student
	 * @throws SQLException
	 */
	public void SQLaddStudent(String studentID, String studentName, String studentEmail) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " insert into STUDENT values (? , ? , ?)";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, studentID);
		stmt.setString(2, studentName);
		stmt.setString(3, studentEmail);
		stmt.execute();

	}

	/**
	 * intitalizes the student object from server using the students id
	 * @return - the specified student
	 * @throws SQLException 
	 */
	public student initStudent(String studentID) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from STUDENT "
				+ " where ID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, studentID);
		rs = stmt.executeQuery();
		if(rs.next()) {
			ID = rs.getString(1);
			Email = rs.getString(3);
		}
		return this;
	}

	/**
	 * reports the studentid, name, and email
	 * @return - the specified student
	 * @throws SQLException
	 */
	public String[] SQLinitStudent(String studentID) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from STUDENT "
				+ " where ID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, studentID);
		rs = stmt.executeQuery();
		String[] returning = null;
		if(rs.next()){
			returning = new String[]{rs.getString(1),
					rs.getString(2),rs.getString(3)};
			ID = rs.getString(1);
			Email = rs.getString(3);
		}
		return returning;
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
	 * @throws SQLException
	 */
	public void SQLacceptOrDenyFriends(Boolean accept, String myid, String friendid) throws SQLException, ClassNotFoundException {
//		loads friends
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p;
			if(accept==true) {
				//			adding part
				//			deleting part
				p = " update FRIENDS "
				  + " set STATUS = 0 "
				  + " where STUDENTID = ?"
				  + " and FRIENDID = ?";
				stmt = conn.prepareStatement(p);
				stmt.setString(1, myid);
				stmt.setString(2, friendid);
				stmt.executeUpdate();
				stmt.setString(2, myid);
				stmt.setString(1, friendid);
				stmt.executeUpdate();
			}else if(accept==false) {
				//			deleting part
				p = " delete "
				  + " from FRIENDS "
				  + " where STUDENTID = ? "
				  + " and FRIENDID = ?";
				stmt = conn.prepareStatement(p);
				stmt.setString(1, myid);
				stmt.setString(2, friendid);
				stmt.execute();
			}
	}
	
//	/**
//	 * This method send a friend request to another user
//	 * @param user - the friend that is receiving the friend request
//	 */
//	public void addFriends(student user) {
//		if(user != null) {
//			this.friendsOutgoing.put(user.Email, user);
//			user.friendsIncoming.put(this.Email, this);
//		}
//	}

	/**
	 * This method send a friend request to another user
	 */
	public void SQLaddFriends(String myid, String friendid) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " insert into FRIENDS " +
				" values (?, ?, 2) ";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, myid);
		stmt.setString(2, friendid);
		stmt.execute();

		p = " insert into FRIENDS " +
				" values (?, ?, 1) ";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, friendid);
		stmt.setString(2, myid);
		stmt.execute();

	}

	/**
	 * returns the email of the user with the passed in id
	 * @param userid
	 * @return
	 * @throws SQLException
	 */
	public String SQLgetEmail(String userid) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from STUDENT "
				+ " where ID = ? ";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		rs = stmt.executeQuery();
		rs.next();
		return rs.getString(3);
	}

//	public schedule getSchedule(int myid) throws SQLException {
//		return new schedule().SQLintitschedule(myid);
//	}

	public Boolean[] SQLgetSetting(String userid) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from SETTINGS "
				+ " where STUDENTID = ? ";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
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
	public String[][] SQLgetFriends(String userid) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select count(*) "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		stmt.setInt(2, 0);
		rs = stmt.executeQuery();
		rs.next();
		String[][] holdingfriends = new String[rs.getInt(1)][4];
		       p = " select * " +
				   " from ( select FRIENDID, STATUS " +
				   " from FRIENDS " +
				   " where STUDENTID = ? " +
				   " and STATUS = ? ) as ID natural join STUDENT";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		stmt.setInt(2, 0);
		rs = stmt.executeQuery();
		int i = 0;
		while(rs.next()) {
			holdingfriends[i] = new String[]{Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(4), rs.getString(5)};
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
	public String[][] SQLgetFriendsIncoming(String userid) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select count(*) "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		stmt.setInt(2, 2);
		rs = stmt.executeQuery();
		String[][] holdingfriends = new String[rs.getInt(1)][3];
		p =" select * "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
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
	public String[][] SQLgetFriendsOutgoing(String userid) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select count(*) "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		stmt.setInt(2, 1);
		rs = stmt.executeQuery();
		String[][] holdingfriends = new String[rs.getInt(1)][3];
		p =" select * "
				+ " from FRIENDS "
				+ " where STUDENTID = ?"
				+ " and STATUS = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
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
	public String[][] SQLgetListOfStudyGroups(String userid) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select count(*) "
				+ " from STUDYGROUP "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		rs = stmt.executeQuery();
		String[][] holdinggroups = new String[rs.getInt(1)][2];
		      p = " select * "
				+ " from STUDYGROUP "
				+ " where STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		rs = stmt.executeQuery();
		int i = 0;
		while(rs.next()) {
			holdinggroups[i] = new String[]{Integer.toString(rs.getInt(1)), rs.getString(2)};
		}
		return holdinggroups;
	}

//	/**
//	 * This method deletes the friend from this persons friend list but not the reverse
//	 * does reverse need to be added?
//	 * @param deletee - the user being deleted from the users friend list
//	 * @return - the deleted friend
//	 */
//	public student deleteFriends(student deletee) {
//		if(deletee != null) {
//			this.friends.remove(deletee.Email);
////			this can be implemented but depends on if needed and server
////			a.friends.remove(this.Email);
//		}
//		return deletee;
//	}
	
	/**
	 * This method deletes the friend from this persons friend list but not the reverse
	 * does reverse need to be added?
	 * @return - the deleted friend
	 * @throws SQLException 
	 */
	public void SQLdeleteFriends(String myid, String friendid) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " delete "
			  + " from FRIENDS "
			  + " where STUDENTID = ? "
			  + " and FRIENDID = ?";
			stmt = conn.prepareStatement(p);
			stmt.setString(1, myid);
			stmt.setString(2, friendid);
			stmt.execute();
			stmt.setString(1, friendid);
			stmt.setString(2, myid);
			stmt.execute();
	}
	
	/**
	 * This method adds the study group to this person and vice versa
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
