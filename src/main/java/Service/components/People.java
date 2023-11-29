package Service.components;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class People implements Components{
    private String name;
    private String id;

    public People(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return mapping().toString();
    }

    public HashMap<String> mapping() {
        HashMap<String> map = new HashMap<>();
        map.add("Name",name);
        map.add("Id", id);
        return map;
    }
}
