package Model.Node;

import java.util.HashMap;
import java.util.Objects;

public class Vertex<K, E> {

	private GraphNode<K, E> graphNode;
	private HashMap<Vertex<K, E>, Integer> neighbors = new HashMap<>();

	public Vertex(K key , E value , HashMap<Vertex<K, E>, Integer> neighbors) {
		graphNode = new GraphNode<>(key, value);
		this.neighbors = neighbors;
	}

	public Vertex(K key, E value){
		graphNode = new GraphNode<>(key, value);
	}

	public GraphNode<K, E> getNode() {
		return graphNode;
	}

	public void setNode(GraphNode<K, E> graphNode) {
		this.graphNode = graphNode;
	}

	public HashMap<Vertex<K,E>,Integer> getNeighbors() {
		return neighbors;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(graphNode + "" );
		for (Vertex<K, E> key: neighbors.keySet()){
			result.append(key).append(" ");
		}
		return result.toString();
	}


	public void addEdge(Vertex<K, E> v, int weight){
		neighbors.put(v, weight);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (! (o instanceof Vertex<?,?> vertex)){
			return false;
		}
		return Objects.equals(getNode().getKey(), vertex.getNode().getKey());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getNode() , getNeighbors());
	}
}
