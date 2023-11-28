package Service.components;

import Model.HashMap;

public class Label implements Components {
    private String type;
    private String description = "";
    private Color color = new Color();

    public Label(String type) {
        this.type = type;
    }

    public Label(String type , String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
