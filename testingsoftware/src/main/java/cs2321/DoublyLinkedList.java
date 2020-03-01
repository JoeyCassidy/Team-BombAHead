package cs2321;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.Position;
import net.datastructures.PositionalList;

/**
 * A doubly linkedlist implementation
 * 
 * Course: CS2321 Section ALL
 * Assignment: #6
 * @author Grant Walker
 */
public class DoublyLinkedList<E> implements PositionalList<E>, Iterable<E> {

	public class node<E> implements Position<E>{
		public node<E> topleft;
		public node<E> bottomright;
		public E data = null;
		public node() {
			
		}
		
		/**
		 * @return data returns the informations stored in the node
		 */
		public E getElement() throws IllegalStateException {
			return data;
			
		}
		
		/**
		 * sets the topleft/previous connection from the node
		 * @param topleft the pointer to what you are setting the connection to
		 */
		public void settopleft(node<E> topleft) {
			this.topleft = topleft;
		}
		
		/**
		 * sets the bottomright/next connect from the node
		 * @param bottomright the pointer to what you are setting the connection to
		 */
		public void setbottomright(node<E> bottomright) {
			this.bottomright = bottomright;
		}
	}
	
	/*private*/ public node<E> first = new node();
	/*private*/ public node<E> last = new node();
	private int size = 0;
	public DoublyLinkedList() {
		first.bottomright = last;
		last.topleft = first;
	}

	/** 
	 * returns the the number of objects in the list
	 * @return size the number of objects in the list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * returns true if there are no objects in the list, false otherwise
	 * @return true if there are no objects in the list, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (this.size == 0 ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * returns the first non header object in the list
	 * @return the first non header object in the list
	 */
	@Override
	public Position<E> first() {
		return first.bottomright;
	}

	/**
	 * returns the last non header object in the list
	 * @return the last non header object in the list
	 */
	@Override
	public Position<E> last() {
		return last.topleft;
	}

	public Comparator<E> comp = new DefaultComparator<E>();
	
	
	/**
	 * returns the node before the given node p
	 * @param p the given node
	 * @return the node before the given node p
	 */
	@Override
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
//		throwing exceptions so we dont get cast problems
		if (!(p instanceof node)){
			throw new IllegalArgumentException("non castable to node");
		}
//		converts p to something usable
		node<E> q = (DoublyLinkedList<E>.node<E>) p;
		if (q.topleft.topleft==null) {
			return null;
		}
		return q.topleft;
	}

	/**
	 * returns the node after the given node p
	 * @param the given node p
	 * @return the node after the given node p
	 */
	@Override
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
//		throwing exceptions so we dont get cast problems
		if (!(p instanceof node)){
			throw new IllegalArgumentException("non castable to node");
		}
//		converts p to something usable
		node<E> q = (DoublyLinkedList<E>.node<E>) p;
		if (q.bottomright.bottomright==null) {
			return null;
		}
		return q.bottomright;
	}
	
	/**
	 * adds a object to the beginning of the list
	 * @param the information to put in the node
	 * @return the node added to the beginning of the list
	 */
	@Override
	public Position<E> addFirst(E e) {
		if (first.bottomright == null && last.topleft == null) {
//			this deals with the case of first and last arent connected
			node<E> hold = new node();
			hold.data = e;
//			this is just stitching the connections
			hold.topleft = first;
			first.bottomright = hold;
			last.topleft = hold;
			hold.bottomright = last;
		} else {
			node<E> holdold = first.bottomright;
			node<E> holdnew = new node();
			holdnew.data = e;
//			just stitching the connections together
			holdold.topleft = holdnew;
			holdnew.bottomright = holdold;
			holdnew.topleft = first;
			first.bottomright = holdnew;
		}
		size++;
		return first.bottomright;
	}

	/**
	 * adds a node to the end of the list and returns the new node
	 * @param the information to be put in the new node
	 * @return returns the nodes added to the end of the list
	 */
	@Override
	public Position<E> addLast(E e) {
		if (first.bottomright == null && last.topleft == null) {
//			deals with the case of if the last and first arent connected
			node<E> hold = new node();
			hold.data = e;
//			does all the stitching of connections
			hold.topleft = first;
			first.bottomright = hold;
			last.topleft = hold;
			hold.bottomright = last;
		} else {
			node<E> holdold = last.topleft;
			node<E> holdnew = new node();
			holdnew.data = e;
//			does all the stitching
			holdold.bottomright = holdnew;
			holdnew.topleft = holdold;
			holdnew.bottomright = last;
			last.topleft = holdnew;
		}
		size++;
		return last.topleft;
	}

	/**
	 * adds a node with information e, before the given node p. and returns the new node
	 * @param the node to add the new node before it
	 * @return the new added node with information e
	 */
	@Override
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
//		throws exceptions so it is castable
		if (!(p instanceof node)){
			throw new IllegalArgumentException("non castable to node");
		}
//		makes it into a usable form
		node<E> right = (DoublyLinkedList<E>.node<E>) p;
		node<E> left = right.topleft;
	    node<E> center = new node<E>();
	    center.data = e;
//	    does all the stitching of conenctions
		right.topleft = center;
		center.bottomright = right;
		left.bottomright = center;
		center.topleft = left;
		size++;
		return center;
	}
	
	/**
	 * adds a node with information e, after the given node p. and returns the new node
	 * @param the node to add the new node after it
	 * @return the new added node with information e
	 */
	@Override
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
//		throws exceptions if p isnt castable
		if (!(p instanceof node)){
			throw new IllegalArgumentException("non castable to node");
		}
//		makes p into a useable form
		node<E> left = (DoublyLinkedList<E>.node<E>) p;
	    node<E> center = new node<E>();
	    center.data = e;
//	    does all the stitching
	    node<E> right = left.bottomright;
		right.topleft = center;
		center.bottomright = right;
		left.bottomright = center;
		center.topleft = left;
		size++;
		return center;
	}

	/**
	 * replaces the data of the given node p with information e and returns the value of the old data
	 * @param p the node to work on
	 * @param e the information to add in
	 * @return E the information replaced
	 */
	@Override
	public E set(Position<E> p, E e) throws IllegalArgumentException {
//		throws exceptions if p isnt castable
		if (!(p instanceof node)){
			throw new IllegalArgumentException("non castable to node");
		}
//		makes p into a useable form
		node<E> q = (DoublyLinkedList<E>.node<E>) p;
		E olde = q.data;
		q.data = e;
		return olde;
	}

	/**
	 * removes the given node p from the linked list and reutns the data inside of it
	 * @param p
	 * @return the data inside of that node
	 */
	@Override
	public E remove(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof node)){
			throw new IllegalArgumentException("non castable to node");
		}
		node<E> q = (DoublyLinkedList<E>.node<E>) p;
		E olde = q.data;
//		just stiching together the conenctions
		node<E> left = q.topleft;
		node<E> right = q.bottomright;
		left.bottomright = right;
		right.topleft = left;
		size--;
		return olde;
	}

	class DLterator implements Iterator<E> {
		node<E> position = first;
		boolean removable = false;
		
		/**
		 * Whether there is another node
		 * @return true if there is a next node, false if not
		 */
		@Override
		public boolean hasNext() {
			return (position.bottomright.bottomright != null );
		}
		
		/**
		 * returns the information in the next node in the list
		 * @return the information in the next node
		 */
		@Override
		public E next() throws NoSuchElementException {
			position = position.bottomright;
			removable = true;
			return position.data;
		}
		/**
		 * removes the current node
		 * @return nothing
		 */
		public void remove() throws IllegalStateException {
			if (removable == true) {
				node<E> left = position.topleft;
				node<E> right = position.bottomright;
//				just does stitching
				left.bottomright = right;
				right.topleft = left;
				removable = false;
			}
		}
	}
		
	class DLterable implements Iterator<Position<E>> {
			node<E> position = first;
			boolean removable = false;
			
			/**
			 * Whether there is another node
			 * @return true if there is a next node, false if not
			 */
			@Override
			public boolean hasNext() {
				return (position.bottomright.bottomright != null);
			}
			
			/**
			 * returns the next node in the list
			 * @return the next node
			 */
			@Override
			public Position<E> next() throws NoSuchElementException {
				position = position.bottomright;
				removable = true;
				return position;
			}
			
			/**
			 * removes the current node
			 * @return nothing
			 */
			public void remove() throws IllegalStateException {
				if (removable == true) {
					node<E> left = position.topleft;
					node<E> right = position.bottomright;
//					this does the stitching
					left.bottomright = right;
					right.topleft = left;
					removable = false;
				}
			}
		
		public DLterable() {
		}
	}
	
	
	class DLterable2 implements Iterable<Position<E>>{
		
		/**
		 * Returns the positions of the list in iterable form from first to last.
		 * @return iterable collection of the list's positions
		 */
		@Override
		public Iterator<Position<E>> iterator() {
			return new DLterable();
		}
		
	}
	
	
	/**
	   * Returns an iterator of the elements stored in the list.
	   * @return iterator of the list's elements
	   */
	@Override
	public Iterator<E> iterator() {
		return new DLterator();
	}
	
	/**
	   * Returns the positions of the list in iterable form from first to last.
	   * @return iterable collection of the list's positions
	   */
	@Override
	public Iterable<Position<E>> positions() {
		return new DLterable2();
	}
	
	
	/**
	 * removes the first object in the list and returns its data
	 * @return the data of the removed node
	 * @throws IllegalArgumentException
	 */
	public E removeFirst() throws IllegalArgumentException {
		node<E> q = first.bottomright;
		E olde = q.data;
		node<E> left = q.topleft;
		node<E> right = q.bottomright;
		left.bottomright = right;
		right.topleft = left;
		size--;
		return olde;
	}
	
	/**
	 * removes the last object in the list and returns its data
	 * @return the data of the removed node
	 * @throws IllegalArgumentException
	 */
	public E removeLast() throws IllegalArgumentException {
		node<E> q = last.topleft;
		E olde = q.data;
		node<E> left = q.topleft;
		node<E> right = q.bottomright;
		left.bottomright = right;
		right.topleft = left;
		size--;
		return olde;
	}

}
