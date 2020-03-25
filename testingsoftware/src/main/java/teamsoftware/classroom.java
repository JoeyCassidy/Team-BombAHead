package teamsoftware;
import java.sql.Date;
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
	public void postQuestion(String title, String description, HashMap<String,post> listOfPosts, student user, Date time, Boolean anonSwitch) {
		post newPost = new post(title, description, new HashMap<String,post>() , user,time,anonSwitch);
		listOfPosts.put(user.Email + "_" + time.toString(), newPost);
		/**
		 * TODO (for HashMap<String,post> listOfPosts:
		 * "i would just need to make it so the contents of the array get
		 * put into the hash table. make it easier on the server and front end"
		 */
	}

	/**
	 * This method will respond to an existing post (**NOTE: for all responses we deemed it unecessary to add titles to them)
	 * @param description - the body of the new post
	 * @param listOfPosts - shows the list of all the posts in the classroom discussion
	 * @param user - shows which user added the new post
	 * @param time - shows the time in which the new post was added
	 * @param anonSwitch - boolean determines if they want to show up anonymous or not
	 */
	public void answerQuestion(String description, post selPost, student user, Date time, Boolean anonSwitch) {
//		might need to change due to potentially misunder standing concept
		post newReply = new post(description, /*new HashMap<String,post>() */ new selPost,user,time,anonSwitch);
		selPost.listOfPosts.put(user.Email + "_" + time.toString(), newReply);
	}
}
