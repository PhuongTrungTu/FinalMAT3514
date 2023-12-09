package Components;

import Model.ArrayList;
import Service.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task implements Comparable<Task>, Cloneable {
    private Title title = new Title();
    private Date startDay = Date.today();
    private Date endDay = Date.today();
    private ArrayList<People> assignments = new ArrayList<>();
    private Status status = Status.TODO;
    private Label labels = new Label();
    private Major majorLabel = new Major();
    private int time = 0;
    private ArrayList<Task> dependentTasks = new ArrayList<Task>();
    private int degree;

    public enum Status {
        TODO(-1), IN_PROCESS(0), DONE(1);

        private final int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String status() {
            if (value < 0) {
                return "TODO";
            } else if (value == 0) {
                return "IN PROCESS";
            }
            return "DONE";
        }
    }

    public Task(String tittle) {
        this.title = new Title(tittle);
    }

    public Task(int level) {
        labels = new Label(level);
    }

    public Task(Title title, Date startDay, Date endDay, ArrayList<People> assignments) {
        this.title = title;
        this.startDay = startDay;
        this.endDay = endDay;
        this.assignments = assignments;
    }

    public Task(Title title, Date startDay, Label labels, Major majorLabel, int time) {
        this.title = title;
        this.startDay = startDay;
        this.labels = labels;
        this.majorLabel = majorLabel;
        endDay = new Date(startDay.day() + time, startDay.month(), startDay.year());
        this.time = time;
    }

    public Task(Title title, Date startDay, ArrayList<People> assignments, int time) {
        this.title = title;
        this.startDay = startDay;
        this.assignments = assignments;
        this.time = time;
    }

    public Task(Title title, Date startDay, Date endDay, ArrayList<People> assignments, Status status, Label labels,
            Major majorLabel, int time, ArrayList<Task> dependentTasks, int degree) {
        this.title = title;
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
    public Title getTittle() {
        return title;
    }

    public void setTittle(Title title) {
        this.title = title;
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

    public void addassignment(People people, boolean unsuitalbe) {
        if ((people.getMajors().contain(majorLabel) || people.getMajors().contain(new Major())) || unsuitalbe) {
            assignments.add(people);
            people.assign(this);
        } else {
            System.out.println("Can't assign " + title.getTittle() + " for " + people.getName());
        }
    }

    public void setAssignments(ArrayList<People> assignments) {
        this.assignments = new ArrayList<>();
        ArrayList<People> unSuitable = new ArrayList<>();
        for (People people : assignments) {
            if (people.getMajors().contain(majorLabel) || people.getMajors().contain(new Major())) {
                assignments.add(people);
                people.assign(this);
            } else {
                unSuitable.add(people);
            }
        }
        System.out.println("These people can't assign this task because they don't have suitable major!");
        System.out.println(unSuitable);
    }

    public void setAssignments(ArrayList<People> assignments, boolean suitable) {
        this.assignments = new ArrayList<>();
        ArrayList<People> unSuitable = new ArrayList<>();
        for (People people : assignments) {
            if (people.getMajors().contain(majorLabel) || suitable || people.getMajors().contain(new Major())) {
                assignments.add(people);
                people.assign(this);
            } else {
                unSuitable.add(people);
            }
        }
        if (!suitable) {
            System.out.println("These people can't assign this task because they don't have suitable major!");
            System.out.println(unSuitable);
        }
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(int change) {
        if (change == 0) {
            this.status = Status.IN_PROCESS;
        } else if (change < 0) {
            this.status = Status.TODO;
        } else {
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
        for (Task task : dependentTasks) {
            if (task != null) {
                result.add(task);
            }
        }
        return result;
    }

    public void setDependentTasks(ArrayList<Task> dependentTasks) {
        this.dependentTasks = dependentTasks;
        updateProgress();
    }

    public void addDependentTask(Task task) {
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

    public void setDependentTasks(Task task) {
        dependentTasks.add(task);
        updateProgress();
    }

    public void deleteDependent(Task task) {
        dependentTasks.remove(task);
    }

    @Override
    public int compareTo(Task task) {
        return task.getTittle().compareTo(title);
    }

    public Major getMajorLabel() {
        return majorLabel;
    }

    public void setMajorLabel(Major majorLabel) {
        this.majorLabel = majorLabel;
        this.labels = new Label(majorLabel.getMAJOR());
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
            clonedTask.title = this.title.copy();
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Tittle: ").append(title.getTittle()).append("\n");
        result.append("Start day: " + getStartDay()).append("\n");
        result.append("End day: " + getEndDay()).append("\n");
        result.append("Assign: " + getAssignments()).append("\n");
        result.append("Status: " + getStatus()).append("\n");
        result.append("Label: " + labels.getType());
        return result.toString();
    }

    public void display() {
        System.out.println("Tittle: " + title.getTittle());
        System.out.println("Start day: " + getStartDay());
        System.out.println("End day: " + getEndDay());
        System.out.println("Assign: " + getAssignments());
        System.out.println("Status: " + getStatus());
        System.out.println("Label: " + labels.getType());
        System.out.println("_________________________");
    }

    public void updateProgress() {
        Date today = Date.today();
        if (status == Status.TODO && endDay.compareTo(today) < 0) {
            startDay = today;
            endDay = new Date(today.day() + time, today.month(), today.year());
        }

        for (Task dependentTask : dependentTasks) {
            dependentTask.updateProgress();
        }
    }
}
