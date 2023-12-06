package Model;

import Model.Node.Vertex;


public class Graph<K extends Comparable<K>, E> {
	private ArrayList<Vertex<K, E>> vertices = new ArrayList<>();

	public void addVertex(Vertex<K, E> vertex) {
		if (!vertices.contain(vertex)) {
			vertices.add(vertex);
		} else {
			throw new IllegalArgumentException("Each key is unique!");
		}
	}

	public void addEdge(Vertex<K, E> u, Vertex<K, E> v) {
		if (u != null && v != null) {
			u.addEdge(v, 0);
		}
	}

	public void addEdge(Vertex<K, E> u, Vertex<K, E> v, int weight) {
		if (u != null && v != null) {
			u.addEdge(v, weight);
		}
	}
}
