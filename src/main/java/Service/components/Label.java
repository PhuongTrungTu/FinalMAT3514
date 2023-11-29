package Service.components;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Label implements Components {
    private String type = "Default";
    private String description = "";

    public Label() {
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

    public HashMap<String> mapping() {
        HashMap<String> map = new HashMap<>();
        map.add("Type", type);
        map.add("Description", description);
        return map;
    }
}
