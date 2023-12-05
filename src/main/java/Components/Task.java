package Components;

import Model.*;
import Service.components.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;


public class Task implements Comparable<Task> {
    private Tittle tittle = new Tittle("New Task", "");
    private Date startDay = new Date(1,1,2023);
    private Date endDay = new Date(31,12,2023);
    ArrayList<People> assignments = new ArrayList<>();
    Status status = Status.TODO;
    Label labels = new Label();
    private int time = 1;
    Set<Task> dependentTasks = new HashSet<>();


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

    public Task(Tittle tittle , Date startDay , Date endDay , ArrayList<People> assignments) {
        this.tittle = tittle;
        this.startDay = startDay;
        this.endDay = endDay;
        this.assignments = assignments;
    }

    public Task(Tittle tittle , Date startDay , ArrayList<People> assignments , int time) {
        this.tittle = tittle;
        this.startDay = startDay;
        this.assignments = assignments;
        this.time = time;
    }

    @JsonProperty("tittle")
    public Tittle getTittle() {
        return tittle;
    }

    public void setTittle(Tittle tittle) {
        this.tittle = tittle;
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

    public Set<Task> getDependentTasks() {
        return dependentTasks;
    }

    public void setDependentTasks(Set<Task> dependentTasks) {
        this.dependentTasks = dependentTasks;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setDependentTasks(Task task){
        dependentTasks.add(task);
    }

    public void deleteDependent(Task task){
        dependentTasks.remove(task);
    }
    @Override
    public int compareTo(Task task) {
        return task.getTittle().getTittle().compareTo(tittle.getTittle());
    }

    @Override
    public String toString() {
        return mapping().toString();
    }

    public HashMap<String> mapping(){
        HashMap<String> map = new HashMap<>();
        map.add("Tittle", tittle.toString());
        map.add("EndDay", endDay.mapping().toString());
        map.add("Assignments", new HashMap<>(assignments).toString());
        map.add("Status", status.toString());
        map.add("Label", labels.toString());
        return map;
    }
}
