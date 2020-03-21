package cs2321;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cs2321.DoublyLinkedList.DLterable;
import cs2321.DoublyLinkedList.node;
import net.datastructures.Entry;
import net.datastructures.Map;
import net.datastructures.Position;
/**
 * A unordered map implmentation
 * 
 * Course: CS2321 Section ALL
 * Assignment: #6
 * @author Grant Walker
 */
public class UnorderedMap<K,V> extends AbstractMap<K,V> {

	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * Uncomment one of these two lines;
	 * private DoublyLinkedList<Entry<K,V>> table;
	 */
	private ArrayList<Entry<K,V>> table = new ArrayList<Entry<K,V>>(); 
	public Comparator<K> kc = new DefaultComparator<K>();
	
		
	public class node<K,V> implements Entry<K,V>{
		public K key;
		public V value;

		public node() {
		}
		
		public node(K k, V v) {
			key = k;
			value = v;
		}

		@TimeComplexity("O(1)")
		@Override
		public K getKey() {
			return key;
		}

		@TimeComplexity("O(1)")
		@Override
		public V getValue() {
			return value;
		}

	}

	public UnorderedMap() {
	}

	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return table.size();
	}

	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return table.isEmpty();
	}

	@TimeComplexity("O(1)")
	public ArrayList<Entry<K,V>> gettable() {
		return table;
	}

	@TimeComplexity("O(n)")
	@Override
	public V get(K key) {
		int i = 0;
//		looks through all the entries until it find the one that is equal to what we want
		while(i<table.size() && kc.compare(table.get(i).getKey(),key)!=0) {
			i++;
		}
//		if the entry is in the list than retun its value
		if(i<table.size() && kc.compare(table.get(i).getKey(),key)==0) {
			V returning = table.get(i).getValue();
			return returning;
		}
//		if the entry isnt in the list then return null
		return null;
	}

	@TimeComplexity("O(n)")
	@Override
	public V put(K key, V value) {
		int i = 0;
//		looks through all the entries until it find the one that is equal to what we want
		while(i<table.size() && kc.compare(table.get(i).getKey(),key)!=0) {
			i++;
		}
//		if the entry is in the list than replace the old value and return the old value
		if(i<table.size() && kc.compare(table.get(i).getKey(),key)==0) {
			V returning = table.get(i).getValue();
			node<K,V> p = (UnorderedMap<K, V>.node<K, V>) table.get(i);
			p.value = value;
			return returning;
		}
//		if the entry isnt in the list then return add it and return null
		table.addLast(new node<K,V>(key, value));
		return null;
	}

	@TimeComplexity("O(n)")
	@Override
	public V remove(K key) {
//		int i = 0;
////		looks through all the entries until it find the one that is equal to what we want
//		while(i<table.size() && kc.compare(table.get(i).getKey(),key)!=0) {
//			i++;
//		}
////		if the entry is in the list than remove the entry and return the old value
//		if(i<table.size() && kc.compare(table.get(i).getKey(),key)==0) {
//			V returning = table.get(i).getValue();
//			table.remove(i);
//			return returning;
//		}
////		if the entry isnt in the list then return null
		return null;
	}

	class UMterable implements Iterator<Entry<K,V>> {
//		int i = 0;
//		node<K,V> position = (UnorderedMap<K, V>.node<K, V>) table.get(i);
//		boolean removable = false;
//
//		/**
//		 * Whether there is another node
//		 * @return true if there is a next node, false if not
//		 */
//		@TimeComplexity("O(1)")
//		@Override
//		public boolean hasNext() {
//			return (i+1)<table.size();
//		}
//
//		/**
//		 * returns the next node in the list
//		 * @return the next node
//		 */
//		@TimeComplexity("O(1)")
//		@Override
//		public Entry<K,V> next() throws NoSuchElementException {
////			if there is a next entry then go to it and return it
//			if(hasNext()) {
//				i++;
//				position = (UnorderedMap<K, V>.node<K, V>) table.get(i);
//				removable = true;
//				return position;
//			}
////			if it doesnt have a next then throw exception
//			throw new NoSuchElementException("");
//		}
//
//		/**
//		 * removes the current node
//		 * @return nothing
//		 */
//		@TimeComplexity("O(n)")
//		public void remove() throws IllegalStateException {
////			if you can remove it then remove it and say you cant remove anymore
//			if (removable == true) {
//				table.remove(i);
//				removable = false;
//			}
//		}

		public UMterable() {
		}

@Override
public boolean hasNext() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Entry<K, V> next() {
	// TODO Auto-generated method stub
	return null;
}
	}


	class UMterable2 implements Iterable<Entry<K,V>>{

		/**
		 * Returns the positions of the list in iterable form from first to last.
		 * @return iterable collection of the list's positions
		 */
		@Override
		public Iterator<Entry<K,V>> iterator() {
			return new UMterable();
		}

	}

	@TimeComplexity("O(1))")
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new UMterable2();
	}

}
