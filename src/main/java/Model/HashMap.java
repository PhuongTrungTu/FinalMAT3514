package Model;


import Model.Node.MapNode;

public class HashMap<K, E> {
	private ArrayList<MapNode<K, E>> container = new ArrayList<>();

	public HashMap(){

	}

	public int size(){
		return container.size();
	}

	public E get(int index){
		return container.get(index).getData();
	}

	public void add(K field, E data){
		container.add(new MapNode<>(field, data));
	}

	public String toString(){
		StringBuilder result = new StringBuilder("{");
		for (int i = 0 ; i < container.size(); i++){
			result.append(container.get(i).toString());
			if (i < container.size() - 1){
				result.append(",");
			}
		}
		return result + "}";
	}
}
