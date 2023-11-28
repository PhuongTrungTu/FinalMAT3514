package Model;


import Model.Node.MapNode;

public class HashMap<E> {
	private ArrayList<MapNode<E>> container = new ArrayList<>();

	public HashMap(){

	}

	public int size(){
		return container.size();
	}

	public MapNode<E> get(int index){
		return container.get(index);
	}

	public HashMap(ArrayList<E> data){
		String name = data.getClassName();
		for (int i = 0; i < data.size(); i++){
			container.add(new MapNode<>(name + " " + (i + 1), data.get(i)));
		}
	}

	public void add(String field, E data){
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
