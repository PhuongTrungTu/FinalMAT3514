package Components;

import Model.ArrayList;
import Model.HashMap;
import Service.components.*;
import com.fasterxml.jackson.annotation.JsonProperty;



public class Task implements Comparable<Task>,Cloneable  {
    private Tittle tittle = new Tittle();
    private Date startDay = Date.today();
    private Date endDay = Date.today();
    private ArrayList<People> assignments = new ArrayList<>();
    private Status status = Status.TODO;
    private Label labels = new Label();
    private Major majorLabel = new Major();
    private int time = 0;
    private ArrayList<Task> dependentTasks = new ArrayList<Task>();
    private int degree;

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

    public Task(Tittle tittle , Date startDay , Label labels , Major majorLabel , int time) {
        this.tittle = tittle;
        this.startDay = startDay;
        this.labels = labels;
        this.majorLabel = majorLabel;
        endDay = new Date(startDay.day() + time, startDay.month(), startDay.year());
        this.time = time;
    }

    public Task(Tittle tittle , Date startDay , ArrayList<People> assignments , int time) {
        this.tittle = tittle;
        this.startDay = startDay;
        this.assignments = assignments;
        this.time = time;
    }

    public Task(Tittle tittle , Date startDay , Date endDay , ArrayList<People> assignments , Status status , Label labels , Major majorLabel , int time , ArrayList<Task> dependentTasks , int degree) {
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

    public void addAssignMent(People people, boolean unsuitalbe){
        if ((people.getMajors().contain(majorLabel) || people.getMajors().contain(new Major())) || unsuitalbe){
            assignments.add(people);
        }else{
            System.out.println("Can't assign " + tittle.getTittle() + " for " + people.getName());
        }
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

    public void setAssignments(ArrayList<People> assignments, boolean suitable) {
        this.assignments = new ArrayList<>();
        ArrayList<People> unSuitable = new ArrayList<>();
        for(People people: assignments){
            if (people.getMajors().contain(majorLabel) || suitable || people.getMajors().contain(new Major())){
                assignments.add(people);
            }else{
                unSuitable.add(people);
            }
        }
        if (!suitable){
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

    public ArrayList<Task> getDependentTasks() {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: dependentTasks){
            if (task != null){
                result.add(task);
            }
        }
        return result;
    }

    public void setDependentTasks(ArrayList<Task> dependentTasks) {
        this.dependentTasks = dependentTasks;
        updateProgress();
    }

    public void addDependentTask(Task task){
        dependentTasks.add(task);
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
        updateProgress();
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        endDay = new Date(startDay.day() + time, startDay.month(), startDay.year());
    }

    public void setDependentTasks(Task task){
        dependentTasks.add(task);
        updateProgress();
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
        updateProgress();
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public Task copy() {
        try {
            Task clonedTask = (Task) super.clone();
            // Copy các đối tượng tham chiếu (nếu cần)
            clonedTask.tittle = this.tittle.copy();
            clonedTask.startDay = startDay.copy();
            clonedTask.endDay = endDay.copy();
            clonedTask.assignments = assignments.copy();
            clonedTask.labels = this.labels.copy();
            clonedTask.majorLabel = this.majorLabel.copy();
            clonedTask.dependentTasks = dependentTasks.copy();
            return clonedTask;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void display(){
        System.out.println("Tittle: " + tittle.getTittle() );
        System.out.println("Start day: " + getStartDay());
        System.out.println("End day: " + getEndDay());
        System.out.println("Assign: " + getAssignments());
        System.out.println("Status: " + getStatus());
        System.out.println("Label: " + labels.getType());
    }

    public void updateProgress() {
        Date today = Date.today();

        // Kiểm tra nếu trạng thái là TODO và đã qua endDay
        if (status == Status.TODO && endDay.compareTo(today) < 0) {
            startDay = today;
            endDay = new Date(today.day() + time, today.month(), today.year());
        }

        // Cập nhật tiến độ cho các task phụ thuộc
        for (Task dependentTask : dependentTasks) {
            dependentTask.updateProgress();
        }
    }
}
