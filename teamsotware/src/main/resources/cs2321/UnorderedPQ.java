package cs2321;

import java.util.Comparator;

import cs2321.OrderedPQ.PQentry;
//import cs2321.OrderedPQ.PQentry;
import net.datastructures.*;
/**
 * A PriorityQueue based on an Unordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Grant Walker
 */

public class UnorderedPQ<K,V> implements PriorityQueue<K,V> {

	public class PQentry<K,V> implements Entry<K,V>{
		public PQentry<K,V> topleft;
		public PQentry<K,V> bottomright;
		public K key;
		public V value;
		public PQentry() {

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

	public PQentry<K,V> first = new PQentry<K,V>();
	public PQentry<K,V> last = new PQentry<K,V>();
	private int size = 0;
	private Comparator<K> comp = new DefaultComparator();

	public UnorderedPQ() {
		first.bottomright = last;
		last.topleft = first;
	}

	public UnorderedPQ(Comparator<K> c) {
		first.bottomright = last;
		last.topleft = first;
		comp = c;
	}

	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return this.size;
	}

	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return (this.size()==0);
	}

	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		if (key == null ) {
			throw new IllegalArgumentException("");
		}
//		set the nodes to do the stiching arround and then does the stiching
		PQentry<K,V> current = this.last.topleft;
		PQentry<K,V> adding = new PQentry<K,V>();
		adding.topleft = current;
		adding.bottomright = this.last;
		this.last.topleft = adding;
		current.bottomright = adding;
		adding.key = key;
		adding.value = value;
		this.size++;
		return adding;
	}

	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> min() {
		/* TCJ
		 * it is O(n) because it has to search through all the elements in the array, so it could be between 1 and n for any call on min
		 */
		if (this.isEmpty()) {
			return null;
		} else {
			PQentry<K,V> current = this.first.bottomright;
			PQentry<K,V> currentmin = this.first.bottomright;
//			searches through entire list for min node
			while(current.bottomright!=null) {
//				if new node is less than min node then set min to the new node
				if(comp.compare(current.getKey(), currentmin.getKey())<0) {
					currentmin = current;
				}
//				moves to next node
				current = current.bottomright;
			}
			return currentmin;
		}
	}

	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> removeMin() {
		/* TCJ
		 * it is O(n) because it has to search through all the elements in the list, so it could be between 1 and n for any call on removemin
		 */
		if (this.isEmpty()) {
			return null;
		} else {
			PQentry<K,V> current = this.first.bottomright;
			PQentry<K,V> currentmin = this.first.bottomright;
//			searches through entire list for min node
			while(current.bottomright!=null) {
//				if new node is less than min node then set min to the new node
				if(comp.compare(current.getKey(), currentmin.getKey())<0) {
					currentmin = current;
				}
//				moves to next node
				current = current.bottomright;
			}
//			does all the stitching
			PQentry<K,V> left = currentmin.topleft;
			PQentry<K,V> after = currentmin.bottomright;
			left.bottomright = after;
			after.topleft = left;
			this.size--;
			return currentmin;
		}
	}



}
