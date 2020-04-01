package cs2321;

import java.util.Comparator;

//import cs2321.UnorderedPQ.PQentry;
import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Grant Walker
 */

public class HeapPQ<K,V> implements AdaptablePriorityQueue<K,V> {

	public class HeapPQentry<K,V> implements Entry<K,V>{
		public K key;
		public V value;
		public int i;

		public HeapPQentry() {
		}

		@TimeComplexity("O(1)")
		public void setkey(K key) {
			this.key = key;
		}

		@TimeComplexity("O(1)")
		public void setvalue(V value) {
			this.value = value;
		}

		/**
		 * Returns the key stored in this entry.
		 * @return the entry's key
		 */
		@TimeComplexity("O(1)")
		public K getKey() {
			return this.key;
		}

		/**
		 * Returns the value stored in this entry.
		 * @return the entry's value
		 */
		@TimeComplexity("O(1)")
		public V getValue() {
			return this.value;
		}
	}

	public ArrayList<HeapPQentry<K,V>> list = new ArrayList<HeapPQentry<K,V>>();
	private int size = 0;
	private Comparator<K> comp = new DefaultComparator<K>();
	

	public HeapPQ() {
	}

//	public HeapPQ(Comparator<K> c) {
//		comp = c;
//	}

	public void swap(int i, int j) {
		HeapPQentry<K,V> temp = list.get(i);
		list.set(i, list.get(j));
		list.get(i).i = i;
		list.set(j, temp);
		list.get(j).i=j;
	}
	
//	@TimeComplexity("O(1)")
//	public boolean hasparent(int j) {
//		return (((j-1)/2)>=0);
//	}
	
	@TimeComplexity("O(1)")
	public int parent(int j) {
		return ((j-1)/2);
	}
	
	@TimeComplexity("O(1)")
	public int left(int j) {
		return ((j*2)+1);
	}

	@TimeComplexity("O(1)")
	public int right(int j) {
		return ((j*2)+2);
	}

	@TimeComplexity("O(1)")
	public boolean hasleft(int j) {
		return (left(j)<list.size());
	}

	@TimeComplexity("O(1)")
	public boolean hasright(int j) {
		return (right(j)<list.size());
	}

	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param j move the entry at index j higher if necessary, to restore the heap property
	 */
	@TimeComplexity("O(log(n))")
	public void upheap(int j){
		/* TCJ
		 * it is log(n) because it has to go through each row and the number of rows in a heap is log of the number of elements in the heap
		 * so the amount of rows the program has to go through is log of the number we are given 
		 */
		while(j>0) {
			int p = parent(j);
//			if the child is bigger than parent then its should stop because all is good
			if(comp.compare(list.get(j).key,list.get(p).key)>=0) break;
//			if the child smaller than parent then it swaps to maintain heap order
			swap(j,p);
//			goes to work on the swapped node so the make sure heap order is maintained
			j=p;
		}
	}

	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param j move the entry at index j lower if necessary, to restore the heap property
	 */
	@TimeComplexity("O(log(n))")
	public void downheap(int j){
		/* TCJ
		 * it is log(n) because it has to go through each row and the number of rows in a heap is log of the number of elements in the heap
		 * so the amount of rows the program has to go through is log of the number we are given 
		 */
//		checks if it even has children
		while(hasleft(j)) {
			int leftindex = left(j);
			int smalchildindex = leftindex;
			if (hasright(j)) {
				int rightindex = right(j);
//				finds the smallest child of parent
				if (comp.compare(list.get(leftindex).key, list.get(rightindex).key)>0) {
					smalchildindex = rightindex;
				}
			}
//			if the child is bigger than parent then everything is in order and it stops
			if (comp.compare(list.get(smalchildindex).key, list.get(j).key)>=0) {
				break;
			}
			swap(j,smalchildindex);
//			sets the child node that was replaced to be worked on to make sure the heap order that could have been changed is corrected
			j = smalchildindex;
		}
	}

	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return list.size();
	}

	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return (this.size()==0);
	}

	@cs2321.TimeComplexity("O(n)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		/* TCJ 
		 * all the actions are of a constant time except the up heap call is log of n
		 * and the ad last which is usually a constant but can be of time n in worst cases
		 * so the time is n
		 */
		if (key == null ) {
			throw new IllegalArgumentException("");
		}
//		makes the entry to be inserted
		HeapPQentry<K,V> newest = new HeapPQentry<K,V>();
		newest.setkey(key);
		newest.setvalue(value);
//		adds to end
		list.addLast(newest);
		list.get(list.size()-1).i=list.size()-1;
//		does upheap so its is in the correct position and maintain heap order
		upheap(list.size()-1);
		return newest;
	}

	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> min() {
		if(list.isEmpty()) {
			return null;
		}
//		returns the top of the list
		return list.get(0);
	}

	@cs2321.TimeComplexity("O(log(n))")
	@Override
	public Entry<K, V> removeMin() {
		/* TCJ
		 * all the actions are of a constant time except the down heap call is log of n
		 * so the time of the whole method is log of n
		 */
		if (list.isEmpty()) {
			return null;
		}
//		returns top entry
		HeapPQentry<K,V> answer = list.get(0);
//		swaps top and bottom of heap
		swap(0,list.size()-1);
//		removes last entry
		list.remove(list.size()-1);
//		remaintains heap order
		downheap(0);
		return answer;
	}

	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	
//	@TimeComplexity("O(log(n))")
//	@Override
//	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
//		/* TCJ 
//		 * because it only has to compare and ho a up or down heap then 
//		 */
//		HeapPQentry<K,V> current = (HeapPQ<K, V>.HeapPQentry<K, V>) entry;
////		check if the entry is null
//		if (entry == null ){
//			throw new IllegalArgumentException("");
//		}
////		checks if the key of that entry is null
//		if (entry.getKey() == null ){
//			throw new IllegalArgumentException("");
//		}
//		if (list.isEmpty()) {
//		} else {
//			current = list.get(current.i);
//			int i2 = current.i;
//			K s = current.getKey();
////			swaps the entry to be removed to the back 
//			swap(i2,list.size()-1);
////			removes the last entry
//			list.remove(list.size()-1);
////			if it needs to up heap then it will
//			upheap(i2);
////			if it needs to down heap it will
//			downheap(i2);
//		}
//	}

//	@TimeComplexity("O(log(n))")
//	@Override
//	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
//		/* TCJ
//		 * this only method call is up head and down heap which are both O(log(n))
//		 */
////		checks if the entry is null
//		if (entry == null ){
//			throw new IllegalArgumentException("");
//		}
////		checks if the key of that entry is null
//		if (entry.getKey() == null ){
//			throw new IllegalArgumentException("");
//		}
////		checks if the new key is null
//		if (key == null ){
//			throw new IllegalArgumentException("");
//		}
//		HeapPQentry<K,V> current = (HeapPQ<K, V>.HeapPQentry<K, V>) entry;
//		int i = current.i;
//		current.setkey(key);
////		if it needs to up heap then it will
//		upheap(i);
////		if it needs to down heap it will
//		downheap(i);
//	}

//	@TimeComplexity("O(1)")
//	@Override
//	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
//		if (entry == null ){
//			throw new IllegalArgumentException("");
//		}
//		if (entry.getKey() == null ){
//			throw new IllegalArgumentException("");
//		}
//		if (entry.getValue().getClass() != value.getClass()){
//			throw new IllegalArgumentException("");
//		}
//		HeapPQentry<K,V> current = (HeapPQ<K, V>.HeapPQentry<K, V>) entry;
//		current.setvalue(value);
//	}


}
