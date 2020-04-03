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
		String[][] mtumap = new String[0][];
		initializeMap(mtumap);
	}
	
	public static String[][] SQLpathing3(int myid) throws SQLException {
		pathfactory using = new pathfactory();
		using.initializeMTUMap();
		schedule usingsched = new schedule();
		usingsched.SQLintitschedule(myid);
		return using.pathing3((place[]) usingsched.listOfListOfPlaces.toArray(), new Boolean[]{true,true,true,true,true});
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
		
		DoublyLinkedList<String> writing = new DoublyLinkedList<String>();
		
	for(int i = 0; i < a.length-1; i++) {
		writing = makesubpath(a[i], a[i+1], new DoublyLinkedList<String>());
		DoublyLinkedList<String>.node<String> place = writing.first.bottomright;
		while(place.bottomright.data != null) {
			returning.put(place.data, true);
			returning.put(place.data + "_" + place.bottomright.data, true);
			returning.put(place.bottomright.data, true);
			place = place.bottomright;
		}
		
//		System.out.println("************");
	}
//	for(String e: writing) {
//		System.out.println(e);
//	}
		String[] holding = new String[returning.size()];
		int i = 0;
		for(Entry<String, Boolean> e: returning.entrySet()) {
			holding[i] = e.getKey();
			i++;
		}
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
		return c;
	}
}
