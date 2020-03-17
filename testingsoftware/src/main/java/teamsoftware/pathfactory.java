package teamsoftware;
import java.util.Calendar;

import cs2321.DoublyLinkedList;
import cs2321.HashMap;
import cs2321.HeapPQ;
import cs2321.Travel;
import net.datastructures.Entry;

public class pathfactory {

	public Travel campusMap;
	
	public void initializeMap(String[][] a) {
		campusMap = new Travel(a);
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
			heappq.insert(e.time.get(Calendar.HOUR_OF_DAY), e);
		}
//		System.out.println("");
		String[] p = new String[a.length]; 
		for(int i = 0; i < a.length; i++) {
//			System.out.print("*"+heappq.min().getValue().location+"*");
			p[i] = heappq.removeMin().getValue().location;
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
