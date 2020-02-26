package cs2321;

import net.datastructures.Position;

public class partition<E> {
	public class node<E> {
		public E elem;
		public node<E> parent;
		public int size;
		public node(E elem1) {
			elem = elem1;
			parent = this;
			size = 1;
		}
	}

	public node<E> createcluster(E e){
		return new node<E>(e);
	}

	public node<E> find(node<E> start){
		if(start.parent!=start) {
			start.parent = find(start.parent);
		}
		return start.parent;
	}

	public void union(node<E> p, node<E> q) {
		node<E> a = find(p);
		node<E> b = find(q);
		if(a!=b) {
			if(a.size>b.size) {
				b.parent = a;
				a.size+=b.size;
			} else {
				a.parent = b;
				b.size+=a.size;
			}
		}
	}

}
