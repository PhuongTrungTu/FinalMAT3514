package Service;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Title implements Comparable<Title> {
	private String tittle = "Untitled";
	private String description = "";

	public Title(String tittle, String description) {
		this.tittle = tittle;
		this.description = description;
	}

	public Title(String tittle) {
		this.tittle = tittle;
	}

	public Title() {
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

	public HashMap<String, String> mapping() {
		HashMap<String, String> map = new HashMap<>();
		map.put("Tittle", tittle);
		map.put("Description", description);
		return map;
	}

	@Override
	public int compareTo(Title o) {
		return tittle.compareTo(o.getTittle());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Title title1)) {
			return false;
		}
		return Objects.equals(getTittle(), title1.getTittle());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTittle(), getDescription());
	}

	public Title copy() {
		return new Title(tittle, description);
	}
}
