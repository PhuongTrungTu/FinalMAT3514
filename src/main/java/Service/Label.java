package Service;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Label {
    private String type = "Untyped";
    private String description = "";

    public Label() {
    }
    final String[] TYPES = {"Back end", "Front End", "Testing",
            "Design", "Marketing", "Development", "Untyped"};
    public Label(int level){
        if (level < 0 || level >= TYPES.length){
            level = TYPES.length - 1;
        }

        type = TYPES[level];
        description = "This is description for " + type;
    }

    public Label(String type) {
        this.type = type;
    }

    public Label(String type , String description) {
        this.type = type;
        this.description = description;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
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
        map.add("Type", type);
        map.add("Description", description);
        return map;
    }

	public Label copy() {
        return new Label(this.type, description);
	}
}
