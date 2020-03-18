package cs2321;

import net.datastructures.*;
/**
 * A undirected and directed graph implementation using adjacent list structure
 * 
 * Course: CS2321 Section ALL
 * Assignment: #7
 * @author Grant Walker
 */
/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author CS2321 Instructor
 */
public class AdjListGraph<V, E> implements Graph<V, E> {

	public class vnode<V,E> implements Vertex<V>{
		
		public V data;
		
		public DoublyLinkedList<vnode<V,E>>.node<vnode<V,E>> position;
		
		public DoublyLinkedList<enode<V,E>> econnections = new DoublyLinkedList<enode<V,E>>(); 
		
		@Override
		public V getElement() {
			return data;
		}
		
	}
	
	public class enode<V,E> implements Edge<E>{
		
		public E data;

		public vnode<V,E> fromvertex;
		
		public vnode<V,E> tovertex;
		
		public DoublyLinkedList<enode<V,E>>.node<enode<V,E>> position;
		
		public DoublyLinkedList<enode<V,E>>.node<enode<V,E>> frompositionlist;
		
		public DoublyLinkedList<enode<V,E>>.node<enode<V,E>> topositionlist;
		
		@Override
		public E getElement() {
			return data;
		}
		
	}
	
	public DoublyLinkedList<enode<V,E>> enodelist = new DoublyLinkedList<enode<V,E>>();
	
	public DoublyLinkedList<vnode<V,E>> vnodelist = new DoublyLinkedList<vnode<V,E>>();
	
	public vnode<V,E> getvertex(V p) throws IllegalArgumentException {
		DoublyLinkedList<AdjListGraph<V, E>.vnode<V, E>>.node<AdjListGraph<V, E>.vnode<V, E>> current = vnodelist.first.bottomright;
		while(current.bottomright.getElement()!=null && !current.getElement().getElement().equals(p)) {
			current = current.bottomright;
		}
		if(current.getElement()==null || current.getElement().getElement()==null) {
			return null;
		}
		return current.getElement();
	}
	
	boolean isdirected = false;
	
	public AdjListGraph(boolean directed) {
		isdirected = directed;
	}

	public AdjListGraph() {
		
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#edges()
	 */
	@TimeComplexity("O(m)")
	public Iterable<Edge<E>> edges() {
//		makes a arraylist to return all of the edges
		ArrayList<Edge<E>> toreturn = new ArrayList<Edge<E>>();
//		sets a start edge to work form
		DoublyLinkedList<AdjListGraph<V, E>.enode<V, E>>.node<AdjListGraph<V, E>.enode<V, E>> first = enodelist.first.bottomright;
//		goes through the list and adds every edge
		while(first != null && first != enodelist.last) {
			toreturn.addLast(first.data);
			first = first.bottomright;
		}
		return toreturn;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 */
	@TimeComplexity("O(1)")
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
//		throws an error if e is of the wrong type
		if(!(e instanceof enode)) {
			throw new IllegalArgumentException("");
		}
//		makes the array to store the vertexs
		Vertex[] toreturn = new Vertex[2];
//		does some casting so we can use methods and variables
		AdjListGraph<V, E>.enode<V, E> p = (enode<V,E>) e;
//		always deals as if its the directed case since the directed case always works 
//				as the undirected case but no the other way around
		toreturn[0] = p.fromvertex;
		toreturn[1] = p.tovertex;
		return toreturn;
	}


	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex, net.datastructures.Vertex, java.lang.Object)
	 */
	@TimeComplexity("O(deg(u)+deg(v))") 
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o)
			throws IllegalArgumentException {
//		makes sure u and v are of the correct type
		if(!(v instanceof vnode) || !(u instanceof vnode)) {
			throw new IllegalArgumentException("");
		}
//		makes sure that a edge between them doesnt already exist
		if(this.getEdge(u,v)!=null || this.getEdge(v,u)!=null) {
			throw new IllegalArgumentException("");
		}	
//		makes the node to insert
		enode<V,E> toinsert = new enode<V,E>();
//		does some casting so methods can be accessed and gotten
		toinsert.fromvertex = (AdjListGraph<V, E>.vnode<V, E>) u;
//		does some casting so methods can be accessed and gotten
		toinsert.tovertex = (AdjListGraph<V, E>.vnode<V, E>) v;
//		sets the edges data
		toinsert.data = o;
//		adds the edges into the edge list
		enodelist.addLast(toinsert);
//		sets the edges position refernce to where it is in the edge list
		toinsert.position = enodelist.last.topleft;
//		does some casting so methods can be accessed and gotten
		vnode<V,E> u2 = (AdjListGraph<V, E>.vnode<V, E>) u;
//		does some casting so methods can be accessed and gotten
		vnode<V,E> v2 = (AdjListGraph<V, E>.vnode<V, E>) v;
//		adds the edge to the places it is connecting
		u2.econnections.addLast(toinsert);
//		adds the edge to the places it is connecting
		v2.econnections.addLast(toinsert);
//		makes sure the edge has a position refernce to where it is in the edge list of the from vertex
		toinsert.frompositionlist = u2.econnections.last.topleft;
//		makes sure the edge has a position refernce to where it is in the edge list of the to vertex
		toinsert.topositionlist =  v2.econnections.last.topleft;
//		return the edge
		return toinsert;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> insertVertex(V o) {
//		makes the vertex to insert
		vnode<V,E> toinsert = new vnode<V,E>();
//		ptus the data in the new node
		toinsert.data = o;
//		adds the vertex to the vertex list
		vnodelist.addLast(toinsert);
//		add the position refernce of where the vertex is in the vertex list, to the new vertex
		toinsert.position = vnodelist.last.topleft;
		return toinsert;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numEdges()
	 */
	@TimeComplexity("O(1)")
	public int numEdges() {
		return enodelist.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#numVertices()
	 */
	@TimeComplexity("O(1)")
	public int numVertices() {
		return vnodelist.size();
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex, net.datastructures.Edge)
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
			throws IllegalArgumentException {
//		makes sure v and e are of the correct types
		if(!(v instanceof vnode) || !(e instanceof enode)) {
			throw new IllegalArgumentException("");
		}
//		does some casting to get access to methods and variables
		enode<V,E> theedge = (AdjListGraph<V, E>.enode<V, E>) e;
//		returns the opposite of the vertex in the edge
		if(v == theedge.fromvertex) {
			return theedge.tovertex;
		}else if(v == theedge.tovertex) {
			return theedge.fromvertex;
		}
		return null; //*************************************************************?????????? does it return null if it isnt either
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 */
	@TimeComplexity("O(1)")
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
//		makes sure e is of the correct type
		if(!(e instanceof enode)) {
			throw new IllegalArgumentException("");
		}
//		does some casting to get access to methods and variables
		enode<V,E> toremove = (AdjListGraph<V, E>.enode<V, E>) e;
//		removes the edge from the edge list
		enodelist.remove(toremove.position);
//		removes the edge from where it is in the connections of the from vertex
		toremove.fromvertex.econnections.remove(toremove.frompositionlist);
//		removes the edge from where it is in the connections of the to vertex
		toremove.tovertex.econnections.remove(toremove.topositionlist);
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 */
	@TimeComplexity("O(deg(v))")
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
//		makes sure v is of the correct type
		if(!(v instanceof vnode)) {
			throw new IllegalArgumentException("");
		}
//		does some casting to get access to methods and variables
		vnode<V,E> toremove = (AdjListGraph<V, E>.vnode<V, E>) v;
//		removes all the edges that connect to this vertex in any way
		while(toremove.econnections.first.bottomright!=null && (toremove.econnections.first.bottomright != toremove.econnections.last)) {
			removeEdge((Edge<E>)toremove.econnections.first.bottomright);
		}
//		removes the vertex from the vertex list
		vnodelist.remove(toremove.position);
	}

	/* 
     * replace the element in edge object, return the old element
     */
	@TimeComplexity("O(1)")
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
//		makes sure o and e are of the correct types
		if(!(e instanceof enode) || !(o.getClass() == e.getElement().getClass())) {
			throw new IllegalArgumentException("");
		}
//		does some casting to get access to methods and variables
		enode<V,E> toreplace = (AdjListGraph<V, E>.enode<V, E>) e;
//		stores the old value
		E oldvalue = toreplace.data;
//		puts in the new value
		toreplace.data = o;
//		returns the old value
		return oldvalue;
	}

    /* 
     * replace the element in vertex object, return the old element
     */
	@TimeComplexity("O(1)")
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
//		makes sure o and v are of the correct types
		if(!(v instanceof vnode) || !(o.getClass() == v.getElement().getClass())) {
			throw new IllegalArgumentException("");
		}
//		does some casting to get access to methods and variables
		vnode<V,E> toreplace = (AdjListGraph<V, E>.vnode<V, E>) v;
//		stores the old value
		V oldvalue = toreplace.data;
//		puts in the new value
		toreplace.data = o;
//		returns the old value
		return oldvalue;
	}

	/* (non-Javadoc)
	 * @see net.datastructures.Graph#vertices()
	 */
	@TimeComplexity("O(n)")
	public Iterable<Vertex<V>> vertices() {
//		makes a arraylist to return all of the vertex's
		ArrayList<Vertex<V>> toreturn = new ArrayList<Vertex<V>>(); 
//		sets a start vertex to work form
		DoublyLinkedList<AdjListGraph<V, E>.vnode<V, E>>.node<AdjListGraph<V, E>.vnode<V, E>> start = vnodelist.first.bottomright;
//		goes through the list and adds every vertex
		while(start != vnodelist.last) {
			toreturn.addLast((Vertex<V>)start.data);
			start = start.bottomright;
		}
		return toreturn;
	}

	@Override
	@TimeComplexity("O(deg(u))")
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)
			throws IllegalArgumentException {
//		makes sure u and v are of the correct types
		if(!(u instanceof vnode) || !(v instanceof vnode)) {
			throw new IllegalArgumentException("");
		}
//		casts the vertex so we can use methods
		vnode<V,E> tostart = (AdjListGraph<V, E>.vnode<V, E>) u;
//		finds the first edge in the connections of the tostart vertex
		DoublyLinkedList<AdjListGraph<V, E>.enode<V, E>>.node<AdjListGraph<V, E>.enode<V, E>> start = tostart.econnections.first.bottomright;
//		goes through the list and does the operation for each edge it connect to
		while(start != tostart.econnections.last) {
//			if this edge connects from u to v the return it
			if(tostart == start.data.fromvertex && v == start.data.tovertex) {
				return start.data;
//			if this edge connects from v to u
			}else if(tostart == start.data.tovertex && v == start.data.fromvertex) {
				return start.data;
			}
//			then goes to the next edges
			start = start.bottomright;
		}
		return null;
	}



}
