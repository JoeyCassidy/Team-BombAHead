package cs2321;

import java.util.Comparator;

import net.datastructures.Edge;
import net.datastructures.Entry;
import net.datastructures.Vertex;
/**
 * kruskals algorithm
 * 
 * Course: CS2321 Section ALL
 * Assignment: #8
 * @author Grant Walker
 */

/**
 * @author Ruihong Zhang
 * Reference: Textbook R-14.27 on page 679
 *
 */
public class Islands  {

	public AdjListGraph<String,Integer> visited = new AdjListGraph<String,Integer>(); 
	
	public Comparator<String> comp = new DefaultComparator<String>();
	
	/**
	 * @param numOfIslands: total number of islands. It will be numbered as 0,1,2,...
	 * @param distance: distance[i][j] represents the distance between island[i] and island[j]. 
	 * 					-1 means there is no edge between island[i] and island[j]. 
	 */
	public Islands(int numOfIslands, int distance[][]) {
		HashMap<Integer,AdjListGraph<String,Integer>.vnode<String,Integer>> table = new HashMap<Integer,AdjListGraph<String,Integer>.vnode<String,Integer>>();
//		inserts all of the vertexs into the graph
		for(int i = 0; i<numOfIslands;i++) {
			table.put(i, (AdjListGraph<String,Integer>.vnode<String,Integer>) visited.insertVertex(String.valueOf(i)));
		}
//		inserts all of the edges into the graph
		for(int i = 0; i < numOfIslands;i++) {
			for(int j = 0; j < numOfIslands; j++) {
				if(distance[i][j]!=-1) {
					visited.insertEdge(table.get(i), table.get(j), distance[i][j]);
				}
			}
		}
		
	}


	/**
	 * @return the cost of minimum spanning tree using Kruskal's algorithm. 
	 */
	@TimeComplexity("O(m*log(n))")
	public int Kruskal() {
//		maintains the connections of the mst
		DoublyLinkedList<Edge<Integer>> tree = new DoublyLinkedList<Edge<Integer>>();
//		creates the way we will sort through the edges
		HeapPQ<Integer,Edge<Integer>> pq = new HeapPQ<Integer,Edge<Integer>>();
//		gets us the way to deal with the clouds
		partition<Vertex<String>> forest = new partition<Vertex<String>>();
//		easy and quick reference to find the cloud for each vertex
		HashMap<String,partition<Vertex<String>>.node<Vertex<String>>> positions = new HashMap<String,partition<Vertex<String>>.node<Vertex<String>>>();
//		making all of the clouds
		for(Vertex<String> v : visited.vertices()) {
			positions.put(v.getElement(), forest.createcluster(v));
		}
//		puts all the edges in to the heappq to be sorted
		for(Edge<Integer> e : visited.edges()) {
			pq.insert(e.getElement(), e);
		}
//		records the number of vertices for quick access
		int size = visited.numVertices();
		while(tree.size()!=size-1 && !pq.isEmpty()) {
//			gets the entry holding the edge
			Entry<Integer, Edge<Integer>> entry = pq.removeMin();
//			gets the edge out of the entry
			Edge<Integer> edge = entry.getValue();
//			gets the vertexs the edge connects
			Vertex<String>[] endpoints = visited.endVertices(edge);
//			find the top of the cloud for the first vertex
			partition<Vertex<String>>.node<Vertex<String>> a = forest.find(positions.get(endpoints[0].getElement()));
//			find the top of the cloud for the second vertex
			partition<Vertex<String>>.node<Vertex<String>> b = forest.find(positions.get(endpoints[1].getElement()));
//			if they arent in the same cloud then merge the clouds
			if(a!=b) {
//				adds the dge to the mst
				tree.addLast(edge);
//				merges the clouds
				forest.union(a, b);
			}
		}
//		starts counting the cost
		int totalcost = 0;
//		goes through all the edges of the mst and adds up all the costs
		while(!tree.isEmpty()) {
			totalcost += tree.removeFirst().getElement();
		}
		return totalcost;
	}
	
	
	public class VE{
		public Vertex<String> V;
		public Edge<Integer> E;
		
		public VE(Vertex<String> Va, Edge<Integer> Ea) {
			V = Va;
			E = Ea;
		}
	}
		
}

//		i am not letting go of these, i worked far to hard to make them, to simply throw them away

//		HashMap<String,HashMap<String,Boolean>> shm = new HashMap<String,HashMap<String,Boolean>>();
//		int totalcost = 0;
//		for(Vertex<String> v : visited.vertices()) {
//			HeapPQ<Integer,VE> pq = new HeapPQ<Integer,VE>();
//			AdjListGraph<String, Integer>.vnode<String, Integer> current = (AdjListGraph<String, Integer>.vnode<String, Integer>) v;
//			HashMap<String,Edge<Integer>> connectionslist = new HashMap<String,Edge<Integer>>();
//			HashMap<String,Boolean> curshm = new HashMap<String,Boolean>();
//			if(shm.get(current.data)!=null) {
//				continue;
//			}
//			pq.insert(0, new VE(current,null));
//			int currentcost = 0;
//			while(!pq.isEmpty()) {
//				currentcost = pq.min().getKey();
//				VE currentve = pq.removeMin().getValue();
//				current = (AdjListGraph<String, Integer>.vnode<String, Integer>) currentve.V;
//				if(shm.get(current.data)!=null && currentve.E!=null) {
////					pq.removeMin();
//					continue;
//				}
//				if(curshm.get(current.data)!=null) {
//					continue;
//				}
//				totalcost += currentcost;
//				curshm.put(current.data, true);
//				shm.put(current.data, curshm);
//				connectionslist.put(current.data, currentve.E);
//				for( Edge<Integer> e : visited.outgoingEdges(current)) {
//					if(connectionslist.get(visited.opposite(current, e).getElement())!=null) {
//						continue;
//					}
//					pq.insert(e.getElement(), new VE(visited.opposite(current, e),e));
//				}
//			}
//		}

//		
//		HeapPQ<Integer,AdjListGraph<String,Integer>.enode<String,Integer>> edgesort = new HeapPQ<Integer,AdjListGraph<String,Integer>.enode<String,Integer>>();
//		HashMap<String,Boolean> vertexcheck = new HashMap<String,Boolean>();
//		for(Edge<Integer> e : visited.edges()) {
//			edgesort.insert(e.getElement(),(AdjListGraph<String,Integer>.enode<String,Integer>) e);
//		}
////		AdjListGraph<String,Integer>.enode<String,Integer>
//		int i = 0;
//		while(!edgesort.isEmpty()) {
//			if(vertexcheck.get(edgesort.min().getValue().fromvertex.data)==null || vertexcheck.get(edgesort.min().getValue().tovertex.data)==null) {
//				vertexcheck.put(edgesort.min().getValue().fromvertex.data,true);
//				vertexcheck.put(edgesort.min().getValue().tovertex.data,true);
//				i+=edgesort.removeMin().getKey();
//			} else {
//				edgesort.removeMin();
//			}
//		}
//		return i;
//		
//	}