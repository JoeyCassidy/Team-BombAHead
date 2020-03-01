package cs2321;

import java.util.Comparator;

import cs2321.DoublyLinkedList.node;
import cs2321.UnorderedPQ.PQentry;
import net.datastructures.*;
/**
 * A PriorityQueue based on an ordered Doubly Linked List. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author Grant Walker
 */

public class OrderedPQ<K,V> implements PriorityQueue<K,V> {

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

	public OrderedPQ() {
		first.bottomright = last;
		last.topleft = first;
	}

	public OrderedPQ(Comparator<K> c) {
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
		return (this.size==0);
	}

	@TimeComplexity("O(n)")
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		/* TCJ
		 * it is O(n) because it has to search through a unknown number of elements in the list , so it could be between 1 and n for any call on insert
		 */
		if (key == null ) {
			throw new IllegalArgumentException("");
		}
		PQentry<K,V> addrefernce = new PQentry<K,V>();
		PQentry<K,V> current = this.first.bottomright;
		//			searches through the list for the right place to add the new node
		while(current.bottomright != null && comp.compare(key, current.key)>=0) {
			current = current.bottomright;
		}
		PQentry<K,V> adding = new PQentry<K,V>();
		//			does all the stiching for the new node
		current.topleft.bottomright = adding;
		adding.bottomright = current;
		adding.topleft = current.topleft;
		current.topleft = adding;
		adding.key = key;
		adding.value = value;
		addrefernce = adding;
		this.size++;
		return addrefernce;
	}

	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> min() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.first.bottomright;
		}
	}

	@TimeComplexity("O(1)")
	@Override
	public Entry<K, V> removeMin() {
		if (this.isEmpty()) {
			return null;
		} else {
			//			sets points for the nodes to do stitching with
			PQentry<K,V> remove = this.first.bottomright;
			PQentry<K,V> after = this.first.bottomright.bottomright;
			//			does all the stitching
			this.first.bottomright = after;
			after.topleft = this.first;
			this.size--;
			return remove;
		}
	}
}
