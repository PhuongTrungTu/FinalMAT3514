package Model.Node;

public class MapNode<E> {
	private String field;
	private E data;

	public MapNode(String field , E data) {
		this.field = field;
		this.data = data;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "\"" + field + "\":" + data;

	}
}
