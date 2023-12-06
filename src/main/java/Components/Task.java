package Components;

import Model.*;
import Service.components.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;


public class Task implements Comparable<Task> {
    private Tittle tittle = new Tittle();
    private Date startDay = Date.today();
    private Date endDay = Date.today();
    ArrayList<People> assignments = new ArrayList<>();
    Status status = Status.TODO;
    Label labels = new Label();
    Major majorLabel = new Major();
    private int time = 0;
    Set<Task> dependentTasks = new HashSet<>();
    int degree;

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

    public Task(int level){
        labels = new Label(level);
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

    public Task(Tittle tittle , Date startDay , Date endDay , ArrayList<People> assignments , Status status , Label labels , Major majorLabel , int time , Set<Task> dependentTasks , int degree) {
        this.tittle = tittle;
        this.startDay = startDay;
        this.endDay = endDay;
        this.assignments = assignments;
        this.status = status;
        this.labels = labels;
        this.majorLabel = majorLabel;
        this.time = time;
        this.dependentTasks = dependentTasks;
        this.degree = degree;
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
        this.assignments = new ArrayList<>();
        ArrayList<People> unSuitable = new ArrayList<>();
        for(People people: assignments){
            if (people.getMajors().contain(majorLabel) || people.getMajors().contain(new Major())){
                assignments.add(people);
            }else{
                unSuitable.add(people);
            }
        }
        System.out.println("These people can't assign this task because they don't have suitable major!");
        System.out.println(unSuitable);
    }

    public void setAssignments(ArrayList<People> assignments, boolean check) {
        this.assignments = new ArrayList<>();
        ArrayList<People> unSuitable = new ArrayList<>();
        for(People people: assignments){
            if (people.getMajors().contain(majorLabel) || check || people.getMajors().contain(new Major())){
                assignments.add(people);
            }else{
                unSuitable.add(people);
            }
        }
        if (!check){
            System.out.println("These people can't assign this task because they don't have suitable major!");
            System.out.println(unSuitable);
        }
    }


    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(int change) {
        if (change == 0){
            this.status = Status.IN_PROCESS;
        }else if (change < 0){
            this.status = Status.TODO;
        }else{
            this.status = Status.DONE;
        }
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

    public void addDependentTask(Task task){
        dependentTasks.add(task);
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
        return task.getTittle().compareTo(tittle);
    }

    public Major getMajorLabel() {
        return majorLabel;
    }

    public void setMajorLabel(Major majorLabel) {
        this.majorLabel = majorLabel;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
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
    public void display(){
        System.out.println("Tittle: " + tittle.getTittle() );
        System.out.println("Start day: " + getStartDay());
        System.out.println("End day: " + getEndDay());
        System.out.println("Assign: " + getAssignments());
        System.out.println("Status: " + getStatus());
        System.out.println("Label: " + labels.getType());
    }
}
