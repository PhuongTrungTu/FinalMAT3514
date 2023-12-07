package Service.components;

import Model.ArrayList;
import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class People implements Components{
    private String name;
    private String id;
    private ArrayList<Major> majors = new ArrayList<>();

    public People(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public People(String name , String id , ArrayList<Major> majors) {
        this.name = name;
        this.id = id;
        this.majors = majors;
    }

    public People(String name , String id , Major major) {
        this.name = name;
        this.id = id;
        this.majors.add(major);
    }

    public ArrayList<Major> getMajors() {
        return majors;
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

    public HashMap<String, String> mapping() {
        HashMap<String, String> map = new HashMap<>();
        map.add("Name",name);
        map.add("Id", id);
        return map;
    }
}
