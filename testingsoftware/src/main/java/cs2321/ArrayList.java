package cs2321;

import java.util.Iterator;
import net.datastructures.List;
import java.util.NoSuchElementException;

/**
 * a arraylist implementation
 * 
 * Course: CS2321 Section ALL
 * Assignment: #6
 * @author Grant Walker
 */
public class ArrayList<E> implements List<E> {
	private int Capacity = 16;
	private E[] list;
	private int size = 0;
	public ArrayList() {
		this.list = (E[]) new Object[Capacity];
	}
	
	public ArrayList(int n) {
		this.list = (E[]) new Object[n];
		this.Capacity=n;
	}

	/**
	 * should return the number of elements in the list
	 * @return the size of the array list
	 */
	@Override
	public int size() {
		return this.size;
	}

	public E[] getlist() throws IndexOutOfBoundsException {
		return this.list;
	}
	
	/**
	 * Tests whether the list is empty.
	 * @return true if the list is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (this.size()==0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns (but does not remove) the element at index i.
	 * @param  i   the index of the element to return
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
	 */
	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		if (this.list[i] != null && i<this.size) {
			return this.list[i];
		}
		return null;
	}
	
	 /**
	  * Replaces the element at the specified index, and returns the element previously stored.
	  * @param  i   the index of the element to replace
	  * @param  e   the new element to be stored
	  * @return the previously stored element
	  * @throws IndexOutOfBoundsException if the index is negative or greater than size()-1
	  */
	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		if ( i>=this.size ||i > this.Capacity){
			throw new IndexOutOfBoundsException("");
		}
		E hold = this.list[i];
		this.list[i] = e;
		return hold;
	}
	/**
	 * Inserts the given element at the specified index of the list, shifting all
	 * subsequent elements in the list one position further to make room.
	 * @param  i   the index at which the new element should be stored
	 * @param  e   the new element to be stored
	 * @throws IndexOutOfBoundsException if the index is negative or greater than size()
	 */
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if (i<0) {
//			out of bounds exception
			throw new IndexOutOfBoundsException("input index is les than zero");
		}
		if (i>size) {
//			out of bounds exception
			throw new IndexOutOfBoundsException("input index is greater than size");
		} else if (i<this.Capacity) {
			if (this.list[i] == null) {
				this.list[i] = e;
				size++;
			} else {
				if (size==Capacity) {
//					makes new array with double capacity
					ArrayList<E> list2 = new ArrayList<E>(Capacity*2);
//					puts all the stuff in new array
					for (int p = 0; p < size; p++) {
						list2.list[p] = list[p];
					}
//					replaces old array with new array
					this.list = list2.list;
//					adds the thing to the new array
					Capacity *= 2;
					this.add(i,e);
				} else {
//					moves everything over to the right
					for(int z = size;z>i;z--) {
						this.list[z]=this.list[z-1];
					}
					this.list[i] = e;
					size++;
				}
			}
		} else if (i>=Capacity) {
//			makes new array with double capacity
			ArrayList<E> list2 = new ArrayList<E>(Capacity*2);
//			moves everything over to new array
			for (int p = 0; p < this.Capacity; p++) {
				list2.list[p] = list[p];
			}
//			replaces old list with new list
			this.list = list2.list;
//			doubles capacity for the array list
			this.Capacity *= 2;
//			then adds item to list
			this.add(i,e);
		}
	}

	  /**
	   * Removes and returns the element at the given index, shifting all subsequent
	   * elements in the list one position closer to the front.
	   * @param  i   the index of the element to be removed
	   * @return the element that had be stored at the given index
	   * @throws IndexOutOfBoundsException if the index is negative or greater than size()
	   */
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
//		throws exception for out of bounds
		if (i>=size || i>=Capacity || i < 0) {
			throw new IndexOutOfBoundsException("");
		}
		E hold = this.list[i];
		this.list[i] = null;
//		moves everything on the right of removed object to the left 
		for(int z = i;z<this.size-1;z++) {
			this.list[z] = this.list[z+1];
		}
		size--;
		return hold;
	}

	/**
	 * iterator class of array list
	 * @author grant walker
	 *
	 */
	class AIterator implements Iterator<E> {
		int n = 0;
		boolean removable = false;
		
		/**
		 * returns if there is more onjects in the iterator
		 * 
		 * @return true if not all elements have been traversed
		 */
		@Override
		public boolean hasNext() {
			return (n < size);
		}
		
		/**
		 * returns the next object in the array
		 */
		@Override
		public E next() throws NoSuchElementException {
			removable = true;
			return list[n++];
		}
		
		/**
		 * removes the most recently returned object from the array
		 */
		public void remove() throws IllegalStateException {
			if (removable == true) {
				ArrayList.this.remove(n-1);
				n--;
				removable = false;
			}
		}
		
		public AIterator() {
		}
	}
	
	/**
	 * Returns an iterator of the elements stored in the list.
	 * @return iterator of the list's elements
	 */
	public Iterator<E> iterator() {
		return new AIterator();
	}

	/**
	 * adds object to beginning of array
	 * @param e the object to be inserted
	 */
	public void addFirst(E e)  {
		this.add(0, e);
	}
	
	/**
	 * adds object to end of array
	 * @param e the object to be inserted
	 */
	public void addLast(E e)  {
		this.add(size, e);
	}
	
	/**
	 * removes and returns the first item of the array
	 * @return hold the object in the beginning of the array
	 * @throws IndexOutOfBoundsException if index is greater than size or capacity or less than zero
	 */
	public E removeFirst() throws IndexOutOfBoundsException {
		E hold = this.get(0);
		this.remove(0);
		return hold;
	}
	
	/**
	 * removes and returns the last non null item of the array
	 * @return hold the last non null item of the array
	 * @throws IndexOutOfBoundsException if index is greater than size or capacity or less than zero
	 */
	public E removeLast() throws IndexOutOfBoundsException {
		E hold = this.get(size-1);
		this.remove(size-1);
		return hold;
	}
	
	/**
	 * returns the max possible size of the array
	 * @return capacity the max possible size of the array
	 */
	public int capacity() {
		return Capacity;
	}
	
	
}
