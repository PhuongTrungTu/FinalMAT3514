package Components;

import Model.ArrayList;
import Model.HashMap;
import Service.components.Date;
import Service.components.Label;
import Service.components.People;


public class Task {
    private String tittle;
    private String description;
    private Date endDay;
    ArrayList<People> assignments;
    Status status = Status.TODO;
    ArrayList<Label> labels = new ArrayList<>();

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

    public Task(String tittle , String description , Date endDay , ArrayList<People> assignments) {
        this.tittle = tittle;
        this.description = description;
        this.endDay = endDay;
        this.assignments = assignments;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public ArrayList<People> getAssignments() {
        return assignments;
    }

    public void setAssignments(ArrayList<People> assignments) {
        this.assignments = assignments;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<Label> labels) {
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
        map.add("Label", new HashMap<>(labels).toString());
        return map;
    }
}
