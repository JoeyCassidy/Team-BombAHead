package teamsoftware;
import cs2321.DoublyLinkedList;
import cs2321.HashMap;
import cs2321.Travel;
import net.datastructures.Entry;

public class pathfactory {

	public Travel campusMap;
	
	public void initializeMap(String[][] a) {
		campusMap = new Travel(a);
	}
	
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
//		for(String e: writing) {
//			System.out.println(e);
//		}
//		System.out.println("");
	}
		String[] holding = new String[returning.size()];
		int i = 0;
		for(Entry<String, Boolean> e: returning.entrySet()) {
			holding[i] = e.getKey();
			i++;
		}
		return holding;
	}
	
	public DoublyLinkedList<String> makesubpath(String a, String b, DoublyLinkedList<String> c) {
		campusMap.DijkstraRoute(a, b, c);
		return c;
	}
}
