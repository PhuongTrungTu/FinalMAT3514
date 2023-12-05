package Model;

import java.util.ArrayList;
import Model.Node.Vertex;

public class Graph<K, E> {
	ArrayList<Vertex<K, E>> vertices = new ArrayList<>();
	public void addVertex(Vertex<K, E> vertex){
		for (Vertex<K,E> keVertex: vertices){
			if (keVertex.equals(vertex)){
				throw new IllegalArgumentException("Each key is unique!");
			}
		}
		vertices.add(vertex);
	}

	public void addEdge(Vertex<K, E> u, Vertex<K, E> v){
		u.addEdge(v, 0);
	}

	public void addEdge(Vertex<K, E> u, Vertex<K, E> v, int weight){
		u.addEdge(v, weight);
	}
}
