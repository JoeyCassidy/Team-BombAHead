package teamsoftwaretest;
import org.junit.Test;
import teamsoftware.pathfactory;
import teamsoftware.place;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import org.junit.Before;

public class pathfactorytest {
	pathfactory p = new pathfactory();
	
	@Before
	public void setUp() throws Exception {
		p = new pathfactory();
	}
	
//	@Test
//	public void testDFSRoute1() {
//		String routes[][] = {  {"A","B","8"},
//				 {"A","D","1"},
//				 {"B","C","7"},
//				 {"C","D","1"}};
//
//		p.initializeMap(routes);
//		
//		String[] a = {"A","B","C","D"};
//		String[] b = p.makepath(a);
//		String[] c = {"D", "B_C", "A_B", "C_D", "A", "B", "C"};
//		System.out.println("**************************");
////		System.out.println("");
//		for(int i = 0; i < b.length; i++) {
//			System.out.println(b[i]);
//		}
//		for(int i = 0; i < b.length; i++) {
//			assertEquals(b[i], c[i]);
//		}
//	}
	
	@Test
	public void testMPFUP() {
		p = new pathfactory();
		String routes[][] = {  
//				from, to, cost
				{"1","2","1"},
				{"2","3","1"},
				{"3","4","1"},
				{"4","5","1"},
				{"5","6","1"},
				{"6","7","1"},
				{"7","8","1"},
				{"8","9","1"},
				{"1","3","3"},
				{"2","4","3"},
				{"2","8","3"},
				{"9","3","3"},
				{"4","6","3"},
				{"3","5","3"},
				{"3","7","3"},
				{"8","6","3"}
				};
		p.initializeMap(routes);
		
		
		String[] z = {"1","2","3","4","5","6","7","8","9"};
		String[] x = p.makepath(z);
		
		for(int i = 0 ; i < x.length; i++) {
			System.out.println(x[i]);
		}
		
		place[] q = new place[9];
		for(int i = 0; i < q.length; i++) {
			String a = "also a banana";
			Calendar b = Calendar.getInstance();
			b.set(Calendar.HOUR_OF_DAY, i+1);
			String c = "a banana";
			Boolean[] d = {
					true,
					true,
					true,
					true,
					true
					};
			String e = Integer.toString(i+1);
			q[i] = new place(a, b, c, d, e); 
		}
		
		System.out.println("");
		
		for(place e: q) {
			System.out.print(e.getLocation() +" _ "+ e.getName() +" _ "+ e.getType());
			System.out.print(" _ "+ e.getListofdays()[0] + " " + e.getListofdays()[1] + " " + e.getListofdays()[2] + " " + e.getListofdays()[3] + " " + e.getListofdays()[4] +" _ " + e.getTime().getTime());
			System.out.println();
			System.out.println("");
		}
		
		String[] f = p.MPFUP(q);
		System.out.println("**************************");
		
		for(int i = 0 ; i < f.length; i++) {
			System.out.println(f[i]);
		}
		
		assertTrue(true);
		
	}
	
}
