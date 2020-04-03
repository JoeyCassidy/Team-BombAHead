package teamsoftware;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import cs2321.HashMap;

public class studyGroup {
	String identifier; // this has to be implemented
	HashMap<String,student> listOfStudents;
	Boolean availability;
	Boolean visibility;
	HashMap<String,post> listOfMessages;
	
	public studyGroup SQLinitstudygroup(int groupid, int studentid) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" select * "
				+ " from STUDYGROUPLOGS "
				+ " where GROUPID = ?"
				+ " and STUDENTID = ?";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, groupid);
		stmt.setInt(2, studentid);
		rs = stmt.executeQuery();
		while(rs.next()){
			post using = new post(null, rs.getString(2), null, null, rs.getTimestamp(4).toLocalDateTime().toLocalTime(), null); 
			//***************************************************************************************************************************************************************************
			listOfMessages.put(rs.getString(4), using);
		}
		return this;
	}

	/**
	 * This method adds a post to the specified chat log
	 * @param newPost - the new post being added to the chat log
	 * @return the new post
	 */
	public post addToChatLog(post newPost) {
		listOfMessages.put(newPost.user.Email, newPost);
		return newPost;
	}

	public post SQLaddToChatLog(int groupid, int studentid, String message, Timestamp time) throws SQLException, ClassNotFoundException {
		post using = new post(null, message, null, new student().initStudent(studentid), time.toLocalDateTime().toLocalTime(), null);
		listOfMessages.put(using.user.Email, using);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " insert into GROUPSTUDYLOG "
	  			 + " values(?, ?, ?, ?)";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, groupid);
		stmt.setInt(2, studentid);
		stmt.setString(3, message);
		stmt.setTimestamp(4, time);
		return using;
	}
	
	/**
	 * This method will add a student to the chat study group chat log
	 * @param newStudent - the new student being added to the chat log
	 * @return the new student that was added
	 */
	public student addStudent(student newStudent) {
		listOfStudents.put(newStudent.Email, newStudent);
		return listOfStudents.get(newStudent.Email);
	}
	
//	/**
//	 * This method will add a student to the chat study group chat log
//	 * @param newStudent - the new student being added to the chat log
//	 * @return the new student that was added
//	 */
//	public student SQLaddStudent(int groupid, int studentid) {
////		*********************************************************************************************
//		listOfStudents.put(newStudent.Email, newStudent);
//		return listOfStudents.get(newStudent.Email);
//	}

	/**
	 * This method will delete a specified student from the chat log
	 * @param delStudent - student being deleted
	 * @return the deleted student
	 */
	public student deleteStudent(student delStudent) {
		return listOfStudents.remove(delStudent.Email);
	}
	
//	/**
//	 * This method will delete a specified student from the chat log
//	 * @param delStudent - student being deleted
//	 * @return the deleted student
//	 */
//	public student SQLdeleteStudent(student delStudent) {
////		********************************************************************************************
//		return listOfStudents.remove(delStudent.Email);
//	}

	/**
	 * This method deletes a specified post from a chat log
	 * @param delPost - the post being deleted from the chat log
	 * @return the deleted post
	 */
	public post deleteChatLog(post delPost) {
		return listOfMessages.remove(delPost.user.Email);
	}
	
	/**
	 * This method deletes a specified post from a chat log
	 * @param delPost - the post being deleted from the chat log
	 * @return the deleted post
	 * @throws SQLException 
	 */
	public post SQLdeleteChatLog(int groupid, int studentid, String message, Timestamp time) throws SQLException, ClassNotFoundException {
		post using = new post(null, message, null, new student().initStudent(studentid), time.toLocalDateTime().toLocalTime(), null);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " delete "
				 + " from STUDYGROUPLOG "
				 + " where GROUPID = ? "
				 + " and STUDENTID = ? "
				 + " and MESSAGE = ? "
				 + " TIMECREATED = ? ";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, groupid);
		stmt.setInt(2, studentid);
		stmt.setString(3, message);
		stmt.setTimestamp(4, time);
		return listOfMessages.remove(using.user.Email);
	}
	
}
