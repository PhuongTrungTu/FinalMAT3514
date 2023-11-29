package Components;

import Model.ArrayList;
import Model.HashMap;
import Service.components.Date;
import Service.components.Label;
import Service.components.People;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Task {
    private String tittle = "Task";
    private String description = "";
    private Date endDay = new Date(31,12,2023);
    ArrayList<People> assignments = new ArrayList<>();
    Status status = Status.TODO;
    Label labels = new Label();

    public void setTitle(String title) {
        this.tittle = title;
    }

    public enum Status{
        TODO(-1),IN_PROCESS(0),DONE(1);
        private final int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        public String status(){
            if (value < 0){
                return "TODO";
            } else if (value == 0){
                return "IN PROCESS";
            }return "DONE";
        }
    }

    public Task() {
    }

    public Task(String tittle , String description , Date endDay , ArrayList<People> assignments) {
        this.tittle = tittle;
        this.description = description;
        this.endDay = endDay;
        this.assignments = assignments;
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

    @JsonProperty("endDay")
    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    @JsonProperty("assignments")
    public ArrayList<People> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<People> assignments) {
        this.assignments = assignments;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("label")
    public Label getLabels() {
        return labels;
    }

    public void setLabels(Label labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return mapping().toString();
    }

    public HashMap<String> mapping(){
        HashMap<String> map = new HashMap<>();
        map.add("Tittle", tittle);
        map.add("EndDay", endDay.mapping().toString());
        map.add("Assignments", new HashMap<>(assignments).toString());
        map.add("Status", status.toString());
        map.add("Label", labels.toString());
        return map;
    }
}
