package Service.components;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Tittle implements Components, Comparable<Tittle> {
	private String tittle = "Untitled";
	private String description = "";

	public Tittle(String tittle , String description) {
		this.tittle = tittle;
		this.description = description;
	}

	public Tittle(String tittle) {
		this.tittle = tittle;
	}

	public Tittle() {
	}

	@JsonProperty("tittle")
	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return mapping().toString();
	}

	@Override
	public HashMap<String> mapping(){
		HashMap<String> map = new HashMap<>();
		map.add("Tittle",tittle);
		map.add("Description", description);
		return map;
	}

	@Override
	public int compareTo(Tittle o) {
		return tittle.compareTo(o.getTittle());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (! (o instanceof Tittle tittle1)){
			return false;
		}
		return Objects.equals(getTittle() , tittle1.getTittle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTittle() , getDescription());
	}
}
