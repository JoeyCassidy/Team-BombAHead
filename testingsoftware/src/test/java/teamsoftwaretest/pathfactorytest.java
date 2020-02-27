package teamsoftwaretest;
import org.junit.Test;
import teamsoftware.pathfactory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class pathfactorytest {
	pathfactory p = new pathfactory();
	
	@Before
	public void setUp() throws Exception {
		/*
		 *    A------1-----
		 *    |             \
 		 *    8              \ 
		 *    |               \
		 *    B --11-- C --1-- D
		 */
		String routes[][] = {  {"A","B","8"},
								 {"A","D","1"},
								 {"B","C","7"},
								 {"C","D","1"}};
		
		p.initializeMap(routes);;
	}
	
	@Test
	public void testDFSRoute1() {
		String[] a = {"A","B","C","D"};
		String[] b = p.makepath(a);
		String[] c = {"D", "B_C", "A_B", "C_D", "A", "B", "C"};
//		System.out.println("");
//		System.out.println("");
		for(int i = 0; i < b.length; i++) {
			assertEquals(b[i], c[i]);
		}
	}
}
