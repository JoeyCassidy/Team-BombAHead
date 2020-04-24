package teamsoftware;
import cs2321.DoublyLinkedList;
import cs2321.HashMap;
import cs2321.HeapPQ;
import cs2321.Travel;
import net.datastructures.Entry;

import javax.tools.StandardJavaFileManager;
import java.sql.SQLException;

public class pathfactory {

	public Travel campusMap;
	
	public void initializeMap(String[][] a) {
		campusMap = new Travel(a);
	}

	public void initializeMTUMap() {
		String[][] mtumap = {{"1","2","43"},
				{"hf","2","140"},
				{"hf","1","16"},
				{"2","3","70"},
				{"3","5","193"},
				{"5","105","226"},
				{"105","13","354"},
				{"13","14","53"},
				{"13","12","40"},
				{"12","14","37"},
				{"14","15","59"},
				{"15","17","107"},
				{"17","18","135"},
				{"18","19","40"},
				{"19","hillside_1","24"},
				{"19","20","50"},
				{"20","21","69"},
				{"21","22","37"},
				{"21","23","37"},
				{"23","24","88"},
				{"23","westmcnair","29"},
				{"westmcnair","24","50"},
				{"24","25","55"},
				{"25","26","31"},
				{"26","east mcnair","20"},
				{"26","27","32"},
				{"27","hillside_2","52"},
				{"25","mcnair dining_1","31"},
				{"22","23","41"},
				{"22","wads_1","96"},
				{"22","29","98"},
				{"29","28","239"},
				{"28","mcnair dinging_2","56"},
				{"30","29","99"},
				{"30","wads_2","28"},
				{"30","31","98"},
				{"31","wads_3","26"},
				{"31","66","49"},
				{"66","65","47"},
				{"65","67","116"},
				{"67","68","119"},
				{"68","roza_1","68"},
				{"65","63","80"},
				{"63","roza_2","20"},
				{"63","61","84"},
				{"66","64","70"},
				{"64","65","54"},
				{"64","60","119" },
				{"63","60","137"},
				{"60","61","156"},
				{"60","55","168"},
				{"55","54","18"},
				{"55","57","129"},
				{"61","62","45"},
				{"62","dhh_1","60"},
				{"59","62","119"},
				{"59","dhh_2","19" },
				{"57","59","51"},
				{"58","57","77"},
				{"57","56","71"},
				{"56","53","35"},
				{"54","53","92"},
				{"106","69","137"},
				{"69","mme_1","36"},
				{"69","70","186"},
				{"106","mme_2","16"},
				{"52","106","39"},
				{"53","52","37"},
				{"54","47","33"},
				{"52","51","50"},
				{"70","107","115"},
				{"107","mme_3","44"},
				{"50","107","39"},
				{"51","50","43"},
				{"51","48","80"},
				{"48","49","84"},
				{"50","92","55"},
				{"92","49","58"},
				{"49","91","110"},
				{"90","92","85"},
				{"91","93","89"},
				{"48","rekhi_1","66"},
				{"49","rekhi_2","58"},
				{"91","85","73"},
				{"91","90","53"},
				{"88","dow_1","43"},
				{"87","dow_2","40"},
				{"88","87","50"},
				{"88","89","47"},
				{"89","dow_3","10"},
				{"90","89","27"},
				{"90","86","74"},
				{"86","87","68"},
				{"85","86","71"},
				{"85","93","39"},
				{"86","83","44"},
				{"83","dow_4","26"},
				{"85","84","50"},
				{"83","84","58" },
				{"93","108","75"},
				{"84","108","123"},
				{"108","42","59"},
				{"108","43","90"},
				{"41","43","92"},
				{"42","41","24"},
				{"41","40","77"},
				{"42","43","47"},
				{"43","rekhi_3","82"},
				{"93","rekhi_4","16"},
				{"43","44","152"},
				{"44","rekhi_5","24"},
				{"44","45","49"},
				{"45","46","53" },
				{"46","47","122"},
				{"47","48","24"},
				{"33","45","55" },
				{"33","32","127"},
				{"32","31","232"},
				{"33","34","76"},
				{"34","35","43"},
				{"35","wads_4","19"},
				{"35","36","31"},
				{"36","37","169"},
				{"37","wads_5","36"},
				{"37","16","130"},
				{"16","wads_6","47"},
				{"16","17","80"},
				{"15","16","64"},
				{"38","34","192"},
				{"33","38","162"},
				{"38","40","54"},
				{"40","39","119"},
				{"39","10","136"},
				{"10","9","86"},
				{"9","8","112"},
				{"8","forestry","37"},
				{"10","11","34"},
				{"11","7","266"},
				{"7","6","164"},
				{"6","3","260"},
				{"3","4","81" },
				{"4","105","156"},
				{"11","12","391"},
				{"43","104","148"},
				{"104","78","178"},
				{"104","103","125"},
				{"103","mub_1","30"},
				{"102","103","29"},
				{"102","101","46"},
				{"102","mub_2","46"},
				{"101","mub_3","66"},
				{"101","95","175"},
				{"101","97","192"},
				{"97","95","71"},
				{"97","98","24"},
				{"98","99","78"},
				{"98","rotc","99"},
				{"rotc","marketing","99"},
				{"99","marketing","66"},
				{"99","admin","105"},
				{"99","109","125"},
				{"100","admin","31"},
				{"95","96","37"},
				{"96","mee","41"},
				{"96","94","35"},
				{"94","mun","43"},
				{"94","78","112"},
				{"78","93","69"},
				{"93","mub_4","56"},
				{"78","79","41"},
				{"78","77","35"},
				{"77","76","31"},
				{"76","75","28" },
				{"70","dow_5","155"},
				{"dow_5","71","235"},
				{"71","72","107"},
				{"72","75","80"},
				{"72","73","71"},
				{"73","74","36"},
				{"74","82","61"},
				{"73","75","116"},
				{"75","79","44"},
				{"75","80","86"},
				{"79","81","54" },
				{"81","library","39"},
				{"81","80","40"},
				{"80","82","26"},
				{"82","83","52"},
				{"81","84","88"},
				{"36","39","231"}};
		initializeMap(mtumap);
	}
	
	public static String[][] SQLpathing3(String myid, Boolean[] boolarray) throws SQLException, ClassNotFoundException {
		pathfactory using = new pathfactory();
		using.initializeMTUMap();
		schedule usingsched = new schedule();
		usingsched.SQLintitschedule(myid);
		place[] holding = new place[usingsched.listOfListOfPlaces.size()];
		int i = 0;
		for (place p: usingsched.listOfListOfPlaces) {
			holding[i] = p;
			i++;
		}

		return using.pathing3(holding, boolarray);
	}

	/**
	 * accepts all of the place values and uses a boolean array to make paths for each of the days that coresponds to a true value in the boolean array
	 * for example if b[0] is false then it will not make a path for monday as it is the first day of the week.
	 * 		but if b[1] is true then it will make an return a schedule for tuesday
	 * @param a
	 * @param b
	 * @return
	 */
	public String[][] pathing3(place[] a, Boolean[] b) {
		HashMap<String, place>[] holding = (HashMap<String, place>[]) new HashMap[5];
		for(int i = 0; i < holding.length; i++) {
			holding[i] = new HashMap<String, place>();
		}
		for(int i = 0; i < b.length; i++) {
			if(b[i] == true) {
				for(int p = 0; p < a.length; p++) {
					if(a[p].getListOfDays()[i] == true) {
						holding[i].put(a[p].getIdentifier(), a[p]);
					}
				}
			}
		}
		place[][] holding2 = new place[holding.length][];
		for(int i = 0; i < b.length; i++) {
			int c = 0;
			for(place e: holding[i].values()) {
				c++;
			}
			holding2[i] = new place[c];
			c = 0;
			for(place e: holding[i].values()) {
				holding2[i][c] = e;
				c++;
			}
		}
		String[][] returning = new String[5][];
		for(int i = 0; i < b.length; i++) {
			returning[i] = MPFUP(holding2[i]);
		}
		return returning;
	}
		
	/**
	 * make path from unsorted place = M P F U P
	 * makes a path from a unsorted list of place objects
	 * @param a
	 * @return
	 */
	public String[] MPFUP(place[] a) {
		HeapPQ<Integer, place> heappq = new HeapPQ<Integer, place>();
		for(place e: a) {
			heappq.insert(e.getTime().getHour(), e);
		}
//		System.out.println("");
		String[] p = new String[a.length]; 
		for(int i = 0; i < a.length; i++) {
//			System.out.print("*"+heappq.min().getValue().location+"*");
			p[i] = heappq.removeMin().getValue().getLocation();
		}
//		System.out.println("");
//		for(String e: p) {
//			System.out.print(e);
//		}
//		System.out.println("");
//		String[] z = {"1","2","3","4","5","6","7","8","9"};
//		for(String e: z) {
//			System.out.print(e);
//		}
//		System.out.println("");
//		for(String e : p) {
//			int b = e.hashCode();
//		}
		return makepath(p);
	}
	
	/**
	 * makes a path from a list of sorted strings passed on order to move to
	 * @param a
	 * @return
	 */
	public String[] makepath(String[] a) {
		HashMap<String, Boolean> returning = new HashMap<String, Boolean>();
		DoublyLinkedList<String> returning2 = new DoublyLinkedList<String>();
		DoublyLinkedList<String> writing = new DoublyLinkedList<String>();
		
	for(int i = 0; i < a.length-1; i++) {
		writing = makesubpath(a[i], a[i+1], new DoublyLinkedList<String>());
		DoublyLinkedList<String>.node<String> place = writing.first.bottomright;
//		returning2.addLast(place.data);
		while(place.bottomright.data != null) {
			returning.put(place.data, true);
			returning.put(place.data + "_" + place.bottomright.data, true);
			returning.put(place.bottomright.data, true);
//			if (place.topleft!=null && place.topleft.data!=null && !place.topleft.data.equals(place.data)) {
//				returning2.addLast(place.data);
//			}
			returning2.addLast(place.data + "_" + place.bottomright.data);
			returning2.addLast(place.bottomright.data);
			place = place.bottomright;
		}
		
//		System.out.println("************");
	}
//	for(String e: writing) {
//		System.out.println(e);
//	}
//		String[] holding = new String[returning.size()];
//		int i = 0;
//		for(Entry<String, Boolean> e: returning.entrySet()) {
//			holding[i] = e.getKey();
//			i++;
//		}
		String[] holding = new String[returning2.size()];
		int i = 0;
		for(String s: returning2) {
			holding[i] = s;
			i++;
		}
//		for(String s: returning2){
//			System.out.println("***"+s+"***");
//		}
		return holding;
	}
	
	/**
	 * makes a path from a to b and records it using c
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public DoublyLinkedList<String> makesubpath(String a, String b, DoublyLinkedList<String> c) {
		campusMap.DijkstraRoute(a, b, c);
//		System.out.println("***"+a+"***");
//		System.out.println("***"+b+"***");
////		System.out.println("***");
//		for (String s: c) {
//			System.out.println("***"+s+"***");
//		}
//		System.out.println("***"+"***");
		return c;
	}
}
