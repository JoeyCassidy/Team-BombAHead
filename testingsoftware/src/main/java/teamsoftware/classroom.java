package teamsoftware;
import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import cs2321.HashMap;

public class classroom {

	HashMap<String,post> listOfPosts = new HashMap<String,post>();

	/**
	 * This method will post a new question (post) inside of the classroom discussion board
	 * @param title - the title of the new post
	 * @param description - the body of the new post
	 * @param listOfPosts - shows the list of all the posts
	 * @param user - shows which user added the post
	 * @param time - shows the time in which the post was added
	 * @param anonSwitch - boolean determines if they want to show up anonymous or not
	 */
	public void postQuestion(String title, String description, HashMap<String,post> listOfPosts, student user, LocalTime time, Boolean anonSwitch) {
		post newPost = new post(title, description, new HashMap<String,post>() , user,time,anonSwitch);
		listOfPosts.put(user.Email + "_" + time.toString(), newPost);
		/**
		 * TODO (for HashMap<String,post> listOfPosts:
		 * "i would just need to make it so the contents of the array get
		 * put into the hash table. make it easier on the server and front end"
		 */
	}

	/**
	 * posts a question using the userid as the creator of the post, the title as the title,  and description as the message to go in the post
	 * @param userid
	 * @param title
	 * @param description
	 * @throws SQLException
	 */
	public void SQLpostQuestion(int userid, String title, String description) throws SQLException {
//		to make: postid, studentid, title, message, posttime
//		post id needs to be autoincremented
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" insert ignore into CLASSROOMPOST " +
				  " values (?, ?, ?, ?) ";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1,userid);
		stmt.setString(2, title);
		stmt.setString(3, description);
		Timestamp using = new Timestamp(System.currentTimeMillis());
		stmt.setTimestamp(4, using);
		stmt.execute();
	}

	/**
	 * This method will respond to an existing post (**NOTE: for all responses we deemed it unecessary to add titles to them)
	 * @param description - the body of the new post
	 * @param user - shows which user added the new post
	 * @param time - shows the time in which the new post was added
	 * @param anonSwitch - boolean determines if they want to show up anonymous or not
	 */
	public void answerQuestion(String description, post selPost, student user, LocalTime time, Boolean anonSwitch) {
//		might need to change due to potentially misunder standing concept
		post newReply = new post(null,description, new HashMap<String,post>() ,user,time,anonSwitch);
		selPost.listOfPosts.put(user.Email + "_" + time.toString(), newReply);
	}

	/**
	 * answers a question taking in its postid and the userid of the one responding and then the message to reply with
	 * @param respondtoid
	 * @param userid
	 * @param message
	 * @throws SQLException
	 */
	public void SQLanswerQuestion(int respondtoid, int userid, String message) throws SQLException {
//		to make: postid, studentid, title, message, posttime
//		post id needs to be autoincremented
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p =" insert into CLASSROOMPOSTREPLY " +
				" values (?, ?, ?, ?) ";
		stmt = conn.prepareStatement(p);
		stmt.setInt(1, respondtoid);
		stmt.setInt(2, userid);
		stmt.setString(3, message);
		Timestamp using = new Timestamp(System.currentTimeMillis());
		stmt.setTimestamp(4, using);
		stmt.execute();
	}

	public String[][] getListOfPosts(int groupid) {
//		**********************************************************************************************************************************************************************************************************
		ArrayList<Integer> postidlist = new ArrayList<Integer>();
		ArrayList<Integer> useridlist = new ArrayList<Integer>();
		ArrayList<String> messagelist = new ArrayList<String>();
		ArrayList<Timestamp> posttimelist = new ArrayList<Timestamp>();

//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		String p =" select * " +
//				  " from CLASSROOMPOST " +
//				  " where ";
//		stmt = conn.prepareStatement(p);
//		stmt.setInt(1, myid);
//		stmt.setInt(2, 3);
//		rs = stmt.executeQuery();
//		while(rs.next()) {
//			mystudent.friendsOutgoing.put(rs.getString(2), new student().initStudent(rs.getInt(2)));
//		}
//
//		return listOfPosts;
		return new String[0][];
	}

}
