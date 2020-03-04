package teamsoftware;
import java.sql.Date;
import java.util.ArrayList;

import cs2321.HashMap;

public class post {
	String title;
	String description;
	HashMap<String,post> listofposts;
	student user;
	Date time;
	Boolean anonswitch;
	
	public void makepost(String a, String b, HashMap<String,post> c, student d, Date e, Boolean f) {
		/*
		 * what was the post[] for again? its for the thing to reply to ...
		 */
		
		title = a;
		description = b;
		listofposts = c; // might need to fix this
		user = d;
		time = e;
		anonswitch = f;
	}
	
	public void addresponse(String a, String b, HashMap<String,post> c, student d, Date e, Boolean f) {
		post G = new post(a,b,c,d,e,f);
		listofposts.put(d.Email + "_" + e.toString(), G);
	}
	
	public post(String title, String description, HashMap<String, post> listofposts, student user, Date time,
			Boolean anonswitch) {
		super();
		this.title = title;
		this.description = description;
		this.listofposts = listofposts;
		this.user = user;
		this.time = time;
		this.anonswitch = anonswitch;
	}

	public post deletepost(post a) {
		return this.listofposts.remove(a.user.Email + "_" + a.time.toString());
	}
}
