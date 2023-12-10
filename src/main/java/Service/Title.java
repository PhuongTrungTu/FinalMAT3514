package Service;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Represents a title with an optional description.
 */
public class Title implements Comparable<Title> {
	private String title = "Untitled";
	private String description = "";

	/**
	 * Constructs a Title object with the specified title and description.
	 *
	 * @param title       The title.
	 * @param description The description.
	 */
	public Title(String title, String description) {
		this.title = title;
		this.description = description;
	}

	/**
	 * Constructs a Title object with the specified title and an empty description.
	 *
	 * @param title The title.
	 */
	public Title(String title) {
		this.title = title;
	}

	/**
	 * Constructs a Title object with the default "Untitled" title and an empty description.
	 */
	public Title() {
	}

	/**
	 * Gets the title.
	 *
	 * @return The title.
	 */
	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title The new title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the description.
	 *
	 * @return The description.
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description The new description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns a string representation of the title and description using the mapping.
	 *
	 * @return A string representation of the title and description.
	 */
	@Override
	public String toString() {
		return mapping().toString();
	}

	/**
	 * Maps the title and description to a HashMap.
	 *
	 * @return A HashMap containing the title and description as keys with their respective values.
	 */
	public HashMap<String, String> mapping() {
		HashMap<String, String> map = new HashMap<>();
		map.put("Title", title);
		map.put("Description", description);
		return map;
	}

	/**
	 * Compares this title to another title lexicographically.
	 *
	 * @param o The title to compare.
	 * @return A negative integer, zero, or a positive integer as this title is less than, equal to, or greater than the specified title.
	 */
	@Override
	public int compareTo(Title o) {
		return title.compareTo(o.getTitle());
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param o The object to compare.
	 * @return True if the objects are equal; false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Title title1)) {
			return false;
		}
		return Objects.equals(getTitle(), title1.getTitle());
	}

	/**
	 * Returns a hash code value for the title.
	 *
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getTitle(), getDescription());
	}

	/**
	 * Creates a copy of this title.
	 *
	 * @return A new Title object with the same title and description.
	 */
	public Title copy() {
		return new Title(title, description);
	}
}
