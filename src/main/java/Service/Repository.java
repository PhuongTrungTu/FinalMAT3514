package Service;

import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Repository {
    String link = "";
    String tittle = "";

    public Repository(String link, String tittle) {
        this.link = link;
        this.tittle = tittle;
    }

    public Repository() {
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @JsonProperty("tittle")
    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    @Override
    public String toString() {
        return mapping().toString();
    }

    public HashMap<String, String> mapping() {
        HashMap<String, String> map = new HashMap<>();
        map.add("Tittle", tittle);
        map.add("link", link);
        return map;
    }
}
