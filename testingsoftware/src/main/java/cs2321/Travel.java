package cs2321;
import java.util.Comparator;
import net.datastructures.Edge;
import net.datastructures.Vertex;
/**
 * breath and depth first traversal, and Dijkstra algorithm
 * 
 * Course: CS2321 Section ALL
 * Assignment: #8
 * @author Grant Walker
 */

/**
 * @author Ruihong Zhang
 * Reference textbook R14.16 P14.81 
 *
 */
public class Travel {
	
	public AdjListGraph<String,Integer> visited = new AdjListGraph<String,Integer>(); 
	
	public Comparator<String> comp = new DefaultComparator<String>();

	
	/**
	 * @param routes: Array of routes between cities. 
	 *                routes[i][0] and routes[i][1] represent the city names on both ends of the route. 
	 *                routes[i][2] represents the cost in string type. 
	 *                Hint: In Java, use Integer.valueOf to convert string to integer. 
	 */
	@TimeComplexity("O(m)")
	public Travel(String [][] routes) {
		HashMap<String,Vertex<String>> addresses = new HashMap<String,Vertex<String>>();
		for(int i = 0; i < routes.length; i++) {
			if(addresses.get(routes[i][0])==null) {
//				if the vertex that we want to make the edge from doesnt exists then make it
				addresses.put(routes[i][0], visited.insertVertex(routes[i][0]));
			}
			if(addresses.get(routes[i][1])==null) {
//				if the vertex that we want to make the edge to doesnt exists then make it
				addresses.put(routes[i][1], visited.insertVertex(routes[i][1]));
			}
//			make the edge
			visited.insertEdge(addresses.get(routes[i][0]), addresses.get(routes[i][1]), Integer.valueOf(routes[i][2]));
		}
	}
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @return Return the path from departure city to destination using Depth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	              
	 *              See the method sortedOutgoingEdges below. 
	 */
	@TimeComplexity("O(n+m)")
	public Iterable<String> DFSRoute(String departure, String destination ) {
//		sets up the lsit we will use to record
		DoublyLinkedList<String> paths = new DoublyLinkedList<String>();
//		sets up the hash map so we can do fast and easy checking to prevent cycling
		HashMap<String,Boolean> collecting = new HashMap<String,Boolean>(); 
//		gets our starting position
		AdjListGraph<String,Integer>.vnode<String,Integer> v = visited.getvertex(departure);
//		gets the path
		paths = DFSRouteG(visited,paths,v,collecting,destination);
		return paths;
	}
	
	@TimeComplexity("O(n+m)")
	public DoublyLinkedList<String> DFSRouteG(AdjListGraph<String,Integer> G,
			DoublyLinkedList<String> paths, AdjListGraph<String,Integer>.vnode<String,Integer> v,
			HashMap<String,Boolean> collecting, String D) {
//		if we have found where we are post to be going then stop and start recording where we have come from
		if(comp.compare(D, v.getElement())==0) {
			paths.addLast(v.getElement());
			return paths;
		}
//		mark that we have already visited this vertex
		collecting.put(v.data,true);
//		for each vertex we connect to
		for(Edge<Integer> e : sortedOutgoingEdges(v)) {
//			if we are on the back tracking step then dont search anymore
			if(paths.last().getElement()!=null && comp.compare(D, paths.last().getElement())==0) {
				break;
			}
//			get the vertex we are going to
			AdjListGraph<String,Integer>.vnode<String,Integer> w = (AdjListGraph<String,Integer>.vnode<String,Integer>) G.opposite(v, e);
//			if we havent been to the new vertex before, then search down through it
			if(collecting.get(w.getElement()) == null) {
				DFSRouteG(G,paths,w,collecting,D);
			}
		}
//		if we are on the back tracking step then record where we are into the itinerary
		if(comp.compare(D, paths.last().getElement())==0) {
			paths.addFirst(v.getElement());
		}
		return paths;
	}
	
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
     * @return Return the path from departure city to destination using Breadth First Search algorithm. 
	 *         The path should be represented as ArrayList or DoublylinkedList of city names. 
	 *         The order of city names in the list should match order of the city names in the path.  
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	             
	 *              See the method sortedOutgoingEdges below. 
	 */
	@TimeComplexity("O(n+m)")
	public Iterable<String> BFSRoute(String departure, String destination ) {
//		maintain a hashmap to check whether we have visited vertex's
		HashMap<String,Boolean> visit = new HashMap<String,Boolean>();
//		maintain hash to get quick acces and search for previous connections made
		HashMap<String,VE> forest = new HashMap<String,VE>();
//		get the start position
		AdjListGraph<String, Integer>.vnode<String, Integer> v = visited.getvertex(departure);
//		creating a queue to deal with the vertex's
		DoublyLinkedList<AdjListGraph<String, Integer>.vnode<String, Integer>> q = new DoublyLinkedList<AdjListGraph<String, Integer>.vnode<String, Integer>>();
//		adding the start psotion to the beggining of the vertex queue
		q.addLast(v);
//		found is the vertex that is our destination
		AdjListGraph<String, Integer>.vnode<String, Integer> found = null;
//		to go through the pq of edges and stop if no more edges or if we have found the destination
		while(q.isEmpty()!=true && found == null) {
//			sote the vertex we will search though
			AdjListGraph<String, Integer>.vnode<String, Integer> w = 
					(AdjListGraph<String, Integer>.vnode<String, Integer>) q.removeFirst();
//			goes though all of the out edges from current vertex
			for(Edge<Integer> e : sortedOutgoingEdges(w)) {
//				finds the vertex from w by the edge e
				AdjListGraph<String, Integer>.vnode<String, Integer> p = 
						(AdjListGraph<String, Integer>.vnode<String, Integer>) visited.opposite(w, e);
//				if we havent met this vertex before then we add it to what we want to visit
				if(visit.get(p.data) == null) {
//					puts the edge connection into the connections hashmap
					forest.put(p.data, new VE(p,e));
//					put the vertex into the visited hashmap
					visit.put(p.data, true);
//					put the new vertex into the back of the the vertex queue
					q.addLast(p);
//					if the new vertex is the vertex we are looking for then store it into found
					if(comp.compare(p.data, destination)==0) {
						found = p;
					}
				}
			}
		}
//		make a arraylist to store the path
		ArrayList<String> table = new ArrayList<String>();
//		goes through all of the paths backwards and adds each to the begging of the path list
		while(comp.compare(found.data, departure)!=0) {
			table.addFirst(found.data);
			found = (AdjListGraph<String, Integer>.vnode<String, Integer>) visited.opposite(found,forest.get(found.data).E);
		}
//		adds the departure to the path list
		table.addFirst(found.data);
//		return the path list
		return table;
	}
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @param itinerary: an empty DoublylinkedList object will be passed in to the method. 
	 * 	       When a shorted path is found, the city names in the path should be added to the list in the order. 
	 * @return return the cost of the shortest path from departure to destination. 
	 *         
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              
	 *              See the method sortedOutgoingEdges below. 
	 */
	@TimeComplexity("O((n+m)*(Log(m)))")
	public int DijkstraRoute(String departure, String destination, DoublyLinkedList<String> itinerary ) {
//		create a heapq to record the minimum next edge
		HeapPQ<Integer,VE> pq = new HeapPQ<Integer,VE>();
//		set the start point
		AdjListGraph<String, Integer>.vnode<String, Integer> current = visited.getvertex(departure);
//		make a hash map of conenctions for us to go back through to get the path
		HashMap<String,Edge<Integer>> connectionslist = new HashMap<String,Edge<Integer>>();
//		start the search at the departure point
		pq.insert(0, new VE(current,null));
//		start a cost counter for each edge
		int currentcost = 0;
//		while there are edge to look through keep going
		while(!pq.isEmpty()) {
			System.out.print("*.");
//			get the cost of the edge
			currentcost = pq.min().getKey();
//			get the min cost path info
			VE currentve = pq.removeMin().getValue();
//			set current vertex we will work on
			current = (AdjListGraph<String, Integer>.vnode<String, Integer>) currentve.V;
//			add the to this vertex into the connection list
			connectionslist.put(current.data, currentve.E);
//			if we found what we are looking for stop looking
			if(comp.compare(current.data,destination)==0) {
				break;
			}
//			goo through each edge in the vertexs edges
			for( Edge<Integer> e : sortedOutgoingEdges(current)) {
//				if a connection to this point has already been made then dont make it
				if(connectionslist.get(visited.opposite(current, e).getElement())!=null) {
					continue;
				}
//				insert the edge into the heappq
				pq.insert(currentcost+e.getElement(), new VE(visited.opposite(current, e),e));
			}
		}
//		goes backwards through the connections and adds them to the list storing the path
		while(comp.compare(current.data, departure)!=0) {
			itinerary.addFirst(current.data);
			System.out.print(current);
			try
			{
			    Thread.sleep(200);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			current = (AdjListGraph<String, Integer>.vnode<String, Integer>) visited.opposite(current,connectionslist.get(current.data));
		}
//		add the departure point to the beggining of the path list
		itinerary.addFirst(current.data);
//		return the cost of getting to the found node
		return currentcost;
		
	}
	
	public class VE{
		public Vertex<String> V;
		public Edge<Integer> E;
		
		public VE(Vertex<String> Va, Edge<Integer> Ea) {
			V = Va;
			E = Ea;
		}
	}
	
	/**
	 * I strongly recommend you to implement this method to return sorted outgoing edges for vertex V
	 * You may use any sorting algorithms, such as insert sort, selection sort, etc.
	 * 
	 * @param v: vertex v
	 * @return a list of edges ordered by edge's name
	 */
	@TimeComplexity("O(n)")
	public Iterable<Edge<Integer>> sortedOutgoingEdges(Vertex<String> v)  {
//		cats to get method
		AdjListGraph<String, Integer>.vnode<String, Integer> p = (AdjListGraph<String,Integer>.vnode<String, Integer>) v; 
//		make a heap for sorting the edges
		HeapPQ<String,VE> sorting = new HeapPQ<String,VE>();
//		goes through all the dges
		for(AdjListGraph<String, Integer>.enode<String, Integer> e : p.econnections) {
			if(!visited.isdirected || e.fromvertex==p) {
//				set the to vertex
				Vertex<String> tovertex = visited.opposite(p, e);
//				insert the edge into the sort pq with the tovertex as a string key
				sorting.insert(tovertex.getElement(), new VE(tovertex,e));
			}
		}
//		make a list to contain the value to return
		ArrayList<Edge<Integer>> toreturn = new ArrayList<Edge<Integer>>();
//		go do a heappq sort on the edges
		while(!sorting.isEmpty()){
			toreturn.addLast(sorting.removeMin().getValue().E);
		}
		return toreturn;
	}
	
	
	
}
