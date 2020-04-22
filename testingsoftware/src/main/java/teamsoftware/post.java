package teamsoftware;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

import cs2321.HashMap;

public class post {
	String title;
	String description;
	HashMap<String,post> listOfPosts;
	student user;
	LocalTime time;
	Boolean anonSwitch;
	String username; /// might need to fix and not yet implemented

	/**
	 * This method generates a post for a specified classroom (instantiates a post from the post method)
	 * @param title - the title of the post
	 * @param description - the body of the post
	 * @param listOfPosts - shows the list of all the posts
	 * @param user - shows which user added the post
	 * @param time - shows the time in which the post was added
	 * @param anonSwitch - boolean determines if they want to show up anonymous or not
	 */
	public void makePost(String title, String description, HashMap<String,post> listOfPosts, student user, LocalTime time, Boolean anonSwitch) {
		
	}

	/**
	 * This method generates a post for a specified classroom (instantiates a post from the post method)
	 * @param title - the title of the post
	 * @param description - the body of the post
	 * @param time - shows the time in which the post was added
	 */
	public void SQLmakePost(String title, String description, String userid, Timestamp time) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " insert into CLASSROOMPOST values (? , ? , ? , ?)";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		stmt.setString(2, title);
		stmt.setString(3, description);
		stmt.setTimestamp(4, time);
		stmt.execute();
	}

	/**
	 * This method generates a post for a specified classroom (instantiates a post from the post method)
	 * @param title - the title of the post
	 * @param description - the body of the post
	 * @param time - shows the time in which the post was added
	 */
	public void SQLmakeReply(String title, String description,String userid, Timestamp time) throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " insert into CLASSROOMREPLY values (? , ? , ? , ?)";
		stmt = conn.prepareStatement(p);
		stmt.setString(1, userid);
		stmt.setString(2, title);
		stmt.setString(3, description);
		stmt.setTimestamp(4, time);
		stmt.execute();
	}

	/**
	 * This method generates a response for a specified post
	 * @param description - the body of the post
	 * @param listOfPosts - shows the list of all the posts
	 * @param user - shows which user added the post
	 * @param time - shows the time in which the post was added
	 * @param anonSwitch - boolean determines if they want to show up anonymous or not
	 */
	public void addResponse(String description, HashMap<String,post> listOfPosts, student user, LocalTime time, Boolean anonSwitch) {
		post newPost = new post(null, description, listOfPosts, user, time, anonSwitch);
		listOfPosts.put(user.Email + "_" + time.toString(), newPost);
	}

	/**
	 * This method is a constructor for posts
	 * @param title - the title of the post
	 * @param description - the body of the post
	 * @param listOfPosts - shows the list of all the posts
	 * @param user - shows which user added the post
	 * @param time - shows the time in which the post was added
	 * @param anonSwitch - boolean determines if they want to show up anonymous or not
	 */
	public post(String title, String description, HashMap<String, post> listOfPosts, student user, LocalTime time,
			Boolean anonSwitch) {
		super();
		this.title = title;
		this.description = description;
		this.listOfPosts = listOfPosts;
		this.user = user;
		this.time = time;
		this.anonSwitch = anonSwitch;
	}

	/**
	 * This method deletes a specified post
	 * @param delPost - specifies the post that you want to delete
	 * @return - after deleting the post, returns the post that was deleted
	 */
	public post deletePost(post delPost) {
		return this.listOfPosts.remove(delPost.user.Email + "_" + delPost.time.toString());
	}
}
