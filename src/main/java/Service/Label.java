package Service;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a label with a type and an optional description.
 */
public class Label {
	final String[] TYPES = { "Back end" , "Front End" , "Testing" , "Design" , "Marketing" , "Development" , "Untyped" };
	private String type = "Untyped";
	private String description = "";

	/**
	 * Default constructor for Label. Initializes type as "Untyped" and description as an empty string.
	 */
	public Label() {
	}

	/**
	 * Constructs a Label with the specified type level.
	 *
	 * @param level The level of the label type.
	 */
	public Label(int level) {
		if (level < 0 || level >= TYPES.length){
			level = TYPES.length - 1;
		}

		type = TYPES[level];
		description = "This is description for " + type;
	}

	/**
	 * Constructs a Label with the specified type.
	 *
	 * @param type The type of the label.
	 */
	public Label(String type) {
		this.type = type;
	}

	/**
	 * Constructs a Label with the specified type and description.
	 *
	 * @param type        The type of the label.
	 * @param description The description of the label.
	 */
	public Label(String type , String description) {
		this.type = type;
		this.description = description;
	}

	/**
	 * Gets the type of the label.
	 *
	 * @return The type of the label.
	 */
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of the label.
	 *
	 * @param type The new type for the label.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the description of the label.
	 *
	 * @return The description of the label.
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the label.
	 *
	 * @param description The new description for the label.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns a string representation of the label using the mapping.
	 *
	 * @return A string representation of the label.
	 */
	@Override
	public String toString() {
		return mapping().toString();
	}

	/**
	 * Maps the type and description of the label to a HashMap.
	 *
	 * @return A HashMap containing the type and description as keys with their respective values.
	 */
	public HashMap<String,String> mapping() {
		HashMap<String,String> map = new HashMap<>();
		map.put("Type" , type);
		map.put("Description" , description);
		return map;
	}

	/**
	 * Creates a copy of this label.
	 *
	 * @return A new Label object with the same type and description.
	 */
	public Label copy() {
		return new Label(this.type , description);
	}
}
