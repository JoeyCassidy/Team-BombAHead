package teamsoftware;
import java.sql.Date;
import java.util.ArrayList;
import cs2321.HashMap;

public class classroom {
	
	HashMap<String,post> listofposts = new HashMap<String,post>();

	public void postquestion(String a, String b, post[] c, student d, Date e, Boolean f) {
		post G = new post(a,b, new HashMap<String,post>() ,d,e,f);
		listofposts.put(d.Email + "_" + e.toString(), G);
	}
	
	public void answerquestion(String a, String b, post c, student d, Date e, Boolean f) {
//		might need to change due to potentially misunder standing concept
		post G = new post(a,b, new HashMap<String,post>() ,d,e,f);
		c.listofposts.put(d.Email + "_" + e.toString(), G);
	}
}
