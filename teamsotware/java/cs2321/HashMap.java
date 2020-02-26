package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A hash map implementation 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #6
 * @author Grant Walker
 */
public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {
	
	/* Use Array of UnorderedMap<K,V> for the Underlying storage for the map of entries.
	 * 
	 */
	public UnorderedMap<K,V>[]  table;
	int 	size = 0;  // number of mappings(entries) 
	int 	capacity = 17; // The size of the hash table. 
	int     DefaultCapacity = 17; //The default hash table size
	
	public Comparator<K> kc = new DefaultComparator<K>();
	
	/* Maintain the load factor <= 0.75.
	 * If the load factor is greater than 0.75, 
	 * then double the table, rehash the entries, and put then into new places. 
	 */
	double  loadfactor= 0.75;  
	
	/**
	 * Constructor that takes a hash size
	 * @param hashtable size: the number of buckets to initialize 
	 */
	public HashMap(int hashtablesize) {
		this.table =  (UnorderedMap<K, V>[]) new UnorderedMap[hashtablesize];
		this.capacity = hashtablesize;
		for(int i = 0; i < this.capacity;i++) {
			table[i] = new UnorderedMap<K, V>();
		}
	}
	
	/**
	 * Constructor that takes no argument
	 * Initialize the hash table with default hash table size: 17
	 */
	public HashMap() {
		this.table =  (UnorderedMap<K, V>[]) new UnorderedMap[17];
		for(int i = 0; i < this.capacity;i++) {
			table[i] = new UnorderedMap<K, V>();
		}
	}
	
	/* This method should be called by map an integer to the index range of the hash table 
	 */
	@TimeComplexity("O(1)")
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}
	
	/*
	 * The purpose of this method is for testing if the table was doubled when rehashing is needed. 
	 * Return the the size of the hash table. 
	 * It should be 17 initially, after the load factor is more than 0.75, it should be doubled.
	 */
	@TimeComplexity("O(1)")
	public int tableSize() {
		return capacity;
	}

	@TimeComplexity("O(1)")
	@Override
	public int size() {
		return size;
	}

	@TimeComplexity("O(1)")
	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@TimeComplexity("O(n)") // if all of the entries are on one of the maps then it could be of time O(n). 
//									even if the load preoperty is satisfied this can still happen ans since 
//									load facot is dependednt on size of the array it is proportional to n so it is of O(n)
	@Override
	public V get(K key) {
		return table[hashValue(key)].get(key);
	}

	@TimeComplexity("O(n)")
	@Override
	public V put(K key, V value) {
		/* TCJ
		 * it might have to rehash the map so its worst case is of time O(n)
		 */
//		if the key value pair is not in the table it should be in then we add the new key value pair into where it should be
		V oldvalue = table[hashValue(key)].put(key, value);
//		if there was no value to replace in the specific map then it means we added a new node to the list
		if(oldvalue==null) {
			size++;
		}
//		if the load factor property is not violated then we must rehash the map
		if((size/capacity)>=loadfactor) {
//			make new map wit double the capacity
			HashMap<K, V> table2 =  new HashMap<K, V>(this.capacity*2);
//			get all the keyvalue pairs from the map 
			Iterable<Entry<K, V>> eset = this.entrySet();
//			insert each old key value pair into the new map at its correct new place
			for(Entry<K,V> a : eset) {
				table2.put(a.getKey(), a.getValue());
			}
//			set size of this table to new size
			this.size = table2.size;
//			set the old capacity to the new capacity
			this.capacity = table2.capacity;
//			set the old table to the new table made
			this.table = table2.table;
		}
		return oldvalue;
	}

	@TimeComplexity("O(n)")
	@Override
	public V remove(K key) {
		/* TCJ
		 * since remove is of time n then this method must be at least of that time;
		 */
//		find the old value to return
		V oldvalue = table[this.hashValue(key)].remove(key);
//		if there was something to remove then decrease the size
		if(oldvalue!=null) {
			size--;
		}
		return oldvalue;
	}

	@TimeComplexity("O(n)")
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		/* TCJ
		 * it does have a loop in a loop but since if a maps size is zero its automatically 
		 * goes to the next one its time for that action is O(1). since it does have to go through all maps its O(n)
		 * but since it also has to go through all node is those mapps it just adds a time proportional to n and thus the
		 * total time is O(n) + O(n) which is O(n)
		 */
//		make a new arraylist
		ArrayList<Entry<K,V>> toreturn = new ArrayList<Entry<K,V>>();
//		go through each map
		for(int i = 0; i < table.length; i++) {
//			go through all elements of each table
			for(int j = 0; j < table[i].size(); j++) {
//				add all the key value pairs from that map into the new list
				toreturn.addLast(table[i].gettable().get(j));
			}
		}
//		return the list
		return toreturn;
	}
	

}
