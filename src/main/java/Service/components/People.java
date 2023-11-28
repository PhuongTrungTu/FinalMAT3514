package Service.components;

import Model.HashMap;

public class People implements Components{
    private String name;
    private Date dob;
    private String id;

    public People(String name , Date dob , String id) {
        this.name = name;
        this.dob = dob;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

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
