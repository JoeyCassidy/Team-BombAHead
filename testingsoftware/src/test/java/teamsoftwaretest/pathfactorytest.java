package teamsoftwaretest;
import org.junit.Test;
import teamsoftware.pathfactory;
import teamsoftware.place;
import static org.junit.Assert.assertEquals;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;

public class pathfactorytest {
	pathfactory p = new pathfactory();
	
	@Before
	public void setUp() throws Exception {
		p = new pathfactory();
	}
	
	@Test
	public void testDFSRoute1() {
		String routes[][] = {  {"A","B","8"},
				 {"A","D","1"},
				 {"B","C","7"},
				 {"C","D","1"}};

		p.initializeMap(routes);
		
		String[] a = {"A","B","C","D"};
		String[] b = p.makepath(a);
		String[] c = {"D", "B_C", "A_B", "C_D", "A", "B", "C"};
//		System.out.println("");
//		System.out.println("");
		for(int i = 0; i < b.length; i++) {
			assertEquals(b[i], c[i]);
		}
	}
	
	@Test
	public void testMPFUP() {
		place[] q = new place[9];
		for(int i = 0; i < q.length; i++) {
			String a = "also a banana";
			Calendar b = Calendar.getInstance();
			b.set(Calendar.HOUR_OF_DAY, i);
			String c = "a banana";
			Boolean[] d = {
					true,
					true,
					true,
					true,
					true
					};
			String e = Integer.toString(i);
			q[i] = new place("1", b, c, d, e); 
		}
		
		String[] f = p.MPFUP(q);
		
		for(int i = 0 ; i < f.length; i++) {
			System.out.println(f[i]);
		}
		
	}
	
}
