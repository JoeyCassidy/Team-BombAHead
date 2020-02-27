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
	
	public void makepost(String a, String b, ArrayList<post> c, student d, Date e, Boolean f) {
	}
	
	public void addresponse(String a, String b, ArrayList<post> c, student d, Date e, Boolean f) {
	}
	
	public post deletepost(post a) {
		return null;
	}
}
