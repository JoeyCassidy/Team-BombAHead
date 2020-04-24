package teamsoftwaretest;
import org.junit.Test;
import teamsoftware.pathfactory;
import teamsoftware.place;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.*;
import java.time.LocalTime;
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
		String[] c = {"A_B",
				"B",
				"B_C",
				"C",
				"C_D",
				"D"};
		for(int i = 0; i < b.length; i++) {
			assertEquals(b[i], c[i]);
		}
	}

	@Test
	public void testMPFUP() {
		pathfactory p = new pathfactory();
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

		place[] q = new place[9];
		for(int i = 0; i < q.length; i++) {
			String a = "also a banana";
			LocalTime b = LocalTime.now();
			b = b.withHour(i+1);
			String c = "a banana";
			Boolean[] d = {
					true,
					true,
					true,
					true,
					true
					};
			String e = Integer.toString(i+1);
			q[i] = new place(a, b, c, d, e, null);
		}

		String[] f = p.MPFUP(q);

		String[] v = {"1_2",
				"2",
				"2_3",
				"3",
				"3_4",
				"4",
				"4_5",
				"5",
				"5_6",
				"6",
				"6_7",
				"7",
				"7_8",
				"8",
				"8_9",
				"9"};

		for(int i = 0; i < v.length; i++) {
			assertEquals(f[i], v[i]);
		}

	}

	@Test
	public void testMPFUP2() {
		pathfactory p = new pathfactory();
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

		place[] q = new place[9];
		for(int i = 0; i < q.length; i++) {
			String a = "also a banana";
			LocalTime b = LocalTime.now();
			b = b.withHour(i+1);
			String c = "a banana";
			Boolean[] d = {
					true,
					true,
					true,
					true,
					true
			};
			String e = Integer.toString(i+1);
			q[i] = new place(a, b, c, d, e, Integer.toString(i));
		}

		String[][] f = p.pathing3(q, new Boolean[] {true,true,true,true,true});

		String[] v = {"1_2",
				"2",
				"2_3",
				"3",
				"3_4",
				"4",
				"4_5",
				"5",
				"5_6",
				"6",
				"6_7",
				"7",
				"7_8",
				"8",
				"8_9",
				"9"};

		for(int i = 0; i < f.length; i++) {
			assertArrayEquals(f[i], v);
		}

	}

	@Test
	public void testINITMAP() {
		pathfactory p = new pathfactory();
		p = new pathfactory();
		p.initializeMTUMap();
		String[] f = p.makepath(new String[]{"westmcnair","forestry"});

		String[] v = {"westmcnair_23",
					"23",
					"23_21",
					"21",
					"21_20",
					"20",
					"20_19",
					"19",
					"19_18",
					"18",
					"18_17",
					"17",
					"17_15",
					"15",
					"15_14",
					"14",
					"14_12",
					"12",
					"12_11",
					"11",
					"11_10",
					"10",
					"10_9",
					"9",
					"9_8",
					"8",
					"8_forestry",
					"forestry"};

		for(int i = 0; i < f.length; i++) {
			assertEquals(f[i], v[i]);
		}

	}

	@Test
	public void testsql() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa","");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String p = " delete from CLASSROOMPOST ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " delete from CLASSROOMREPLY ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " delete from FRIENDS ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " delete from SCHEDULE ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " delete from SETTINGS ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " delete from STUDENT ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " delete from STUDYGROUP ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " delete from STUDYGROUPLOG ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " insert into STUDENT values ('grant walker', 'grantwalker', 'grant at gmail'); ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		p = " insert into SCHEDULE "
				+ " values "
				+ " ('grant walker', 'hf', '12:30:30', '12:35:30', 'westmcnair', TRUE, TRUE, TRUE, TRUE, TRUE) " +
				", ('grant walker', 'cs', '15:30:30', '15:35:30', 'forestry', TRUE, TRUE, TRUE, TRUE, TRUE) ";
		stmt = conn.prepareStatement(p);
		stmt.execute();
		pathfactory p1 = new pathfactory();
		p1 = new pathfactory();
		p1.initializeMTUMap();
		String[][] printing = p1.SQLpathing3("grant walker", new Boolean[]{true, true, true, true, true});
		String[] v = {"westmcnair_23",
				"23",
				"23_21",
				"21",
				"21_20",
				"20",
				"20_19",
				"19",
				"19_18",
				"18",
				"18_17",
				"17",
				"17_15",
				"15",
				"15_14",
				"14",
				"14_12",
				"12",
				"12_11",
				"11",
				"11_10",
				"10",
				"10_9",
				"9",
				"9_8",
				"8",
				"8_forestry",
				"forestry"};
		for (int i = 0; i < printing.length; i++) {
			for (int j = 0; j < printing[i].length; j++) {
//				System.out.println(printing[i][j]);
			}
//			System.out.println("");
			assertArrayEquals(printing[i],v);
		}
	}




}
