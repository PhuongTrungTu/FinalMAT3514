/**
 * The Task class represents a task in a project management system.
 * It includes information such as title, start day, end day, assignments,
 * status, labels, major label, time required, dependent tasks, and degree.
 * This class provides methods to manipulate and access task-related information.
 *
 * @author Grizmo
 * @version 1.0
 */
package Components;

import Model.ArrayList;
import Service.*;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Task implements Comparable<Task> {
    /**
     * The title of the task
     */
    private Title title = new Title();
    /**
     * The start date of the task
     */
    private Date startDay = Date.today();
    /**
     * The end date of the task
     */
    private Date endDay = Date.today();
    /**
     * List of people assigned to the task
     */
    private ArrayList<People> assignments = new ArrayList<>();
    /**
     * The status of the task
     */
    private Status status = Status.TODO;
    /**
     * Labels associated with the task
     */
    private Label labels = new Label();
    /**
     * The major label associated with the task
     */
    private Major majorLabel = new Major();
    /**
     * The time required to complete the task
     */
    private int time = 0;
    /**
     * List of tasks dependent on this task
     */
    private ArrayList<Task> dependentTasks = new ArrayList<>();
    /**
     * degree level of each task
     */
    private int degree;

    /**
     * Enumeration representing the status of a task.
     */
    public enum Status {
        TODO(-1), IN_PROCESS(0), DONE(1);

        private final int value;

        /**
         * Constructor for Status enumeration.
         *
         * @param value The numerical value associated with the status.
         */
        Status(int value) {
            this.value = value;
        }

        /**
         * Gets the numerical value associated with the status.
         *
         * @return The numerical value of the status.
         */
        public int getValue() {
            return value;
        }

        /**
         * Gets the string representation of the status.
         *
         * @return The string representation of the status.
         */
        public String status() {
            if (value < 0) {
                return "TODO";
            } else if (value == 0) {
                return "IN PROCESS";
            }
            return "DONE";
        }
    }

    /**
     * Creates a task with the specified title.
     *
     * @param title The title of the task.
     */
    public Task(String title) {
        this.title = new Title(title);
    }

    /**
     * Creates a task with the specified level.
     *
     * @param level The level of the task.
     */
    public Task(int level) {
        labels = new Label(level);
    }

    /**
     * Creates a task with specified parameters.
     *
     * @param title        The title of the task.
     * @param startDay     The start date of the task.
     * @param endDay       The end date of the task.
     * @param assignments  List of people assigned to the task.
     */
    public Task(Title title, Date startDay, Date endDay, ArrayList<People> assignments) {
        this.title = title;
        this.startDay = startDay;
        this.endDay = endDay;
        this.assignments = assignments;
    }

    /**
     * Creates a task with specified parameters.
     *
     * @param title       The title of the task.
     * @param startDay    The start date of the task.
     * @param labels      Labels associated with the task.
     * @param majorLabel  The major label associated with the task.
     * @param time        The time required to complete the task.
     */
    public Task(Title title, Date startDay, Label labels, Major majorLabel, int time) {
        this.title = title;
        this.startDay = startDay;
        this.labels = labels;
        this.majorLabel = majorLabel;
        endDay = new Date(startDay.day() + time, startDay.month(), startDay.year());
        this.time = time;
    }

    /**
     * Creates a task with specified parameters.
     *
     * @param title        The title of the task.
     * @param startDay     The start date of the task.
     * @param assignments  List of people assigned to the task.
     * @param time         The time required to complete the task.
     */
    public Task(Title title, Date startDay, ArrayList<People> assignments, int time) {
        this.title = title;
        this.startDay = startDay;
        this.assignments = assignments;
        this.time = time;
    }

    /**
     * Creates a task with specified parameters.
     *
     * @param title           The title of the task.
     * @param startDay        The start date of the task.
     * @param endDay          The end date of the task.
     * @param assignments     List of people assigned to the task.
     * @param status          The status of the task.
     * @param labels          Labels associated with the task.
     * @param majorLabel      The major label associated with the task.
     * @param time            The time required to complete the task.
     * @param dependentTasks  List of tasks dependent on this task.
     * @param degree          The degree of the task.
     */
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

    /**
     * Gets the title of the task.
     *
     * @return The title of the task.
     */
    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    /**
     * Sets the title of the task.
     *
     * @param title The title to set.
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * Gets the end day of the task.
     *
     * @return The end day of the task.
     */
    @JsonProperty("endDay")
    public Date getEndDay() {
        return endDay;
    }

    /**
     * Sets the end day of the task.
     *
     * @param endDay The end day to set.
     */
    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    /**
     * Gets the assignments associated with the task.
     *
     * @return The assignments associated with the task.
     */
    @JsonProperty("assignments")
    public ArrayList<People> getAssignments() {
        return assignments;
    }

    /**
     * Adds an assignment to the task.
     *
     * @param people     The person to assign to the task.
     * @param unsuitable A flag indicating if the person is unsuitable for the task.
     */
    public void addAssignment(People people, boolean unsuitable) {
        if ((people.getMajors().contain(majorLabel) || people.getMajors().contain(new Major())) || unsuitable) {
            assignments.add(people);
            people.assign(this);
        } else {
            System.out.println("Can't assign " + title.getTittle() + " for " + people.getName());
        }
    }

    /**
     * Sets the assignments associated with the task.
     *
     * @param assignments The assignments to set.
     */
    public void setAssignments(ArrayList<People> assignments) {
        this.assignments = new ArrayList<>();
        ArrayList<People> unsuitablePeople = new ArrayList<>();
        for (People people : assignments) {
            if (people.getMajors().contain(majorLabel) || people.getMajors().contain(new Major())) {
                this.assignments.add(people);
                people.assign(this);
            } else {
                unsuitablePeople.add(people);
            }
        }
        System.out.println("These people can't assign this task because they don't have a suitable major!");
        System.out.println(unsuitablePeople);
    }

    /**
     * Sets the assignments associated with the task.
     *
     * @param assignments The assignments to set.
     * @param suitable    A flag indicating if the assignments are suitable for the task.
     */
    public void setAssignments(ArrayList<People> assignments, boolean suitable) {
        this.assignments = new ArrayList<>();
        ArrayList<People> unsuitablePeople = new ArrayList<>();
        for (People people : assignments) {
            if (people.getMajors().contain(majorLabel) || suitable || people.getMajors().contain(new Major())) {
                this.assignments.add(people);
                people.assign(this);
            } else {
                unsuitablePeople.add(people);
            }
        }
        if (!suitable) {
            System.out.println("These people can't assign this task because they don't have a suitable major!");
            System.out.println(unsuitablePeople);
        }
    }

    /**
     * Gets the status of the task.
     *
     * @return The status of the task.
     */
    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the status of the task based on the given change value.
     *
     * @param change The change value to determine the status.
     */
    public void setStatus(int change) {
        if (change == 0) {
            this.status = Status.IN_PROCESS;
        } else if (change < 0) {
            this.status = Status.TODO;
        } else {
            this.status = Status.DONE;
        }
    }

    /**
     * Gets the labels associated with the task.
     *
     * @return The labels associated with the task.
     */
    @JsonProperty("labels")
    public Label getLabels() {
        return labels;
    }

    /**
     * Sets the labels associated with the task.
     *
     * @param labels The labels to set.
     */
    public void setLabels(Label labels) {
        this.labels = labels;
    }

    /**
     * Gets the dependent tasks of the task.
     *
     * @return The dependent tasks of the task.
     */
    @JsonProperty("dependentTasks")
    public ArrayList<Task> getDependentTasks() {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : dependentTasks) {
            if (task != null) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Sets the dependent tasks of the task.
     *
     * @param dependentTasks The dependent tasks to set.
     */
    public void setDependentTasks(ArrayList<Task> dependentTasks) {
        this.dependentTasks = dependentTasks;
        updateProgress();
    }

    /**
     * Adds a dependent task to the task.
     *
     * @param task The dependent task to add.
     */
    public void addDependentTask(Task task) {
        dependentTasks.add(task);
    }

    /**
     * Gets the start day of the task.
     *
     * @return The start day of the task.
     */
    @JsonProperty("startDay")
    public Date getStartDay() {
        return startDay;
    }

    /**
     * Sets the start day of the task.
     *
     * @param startDay The start day to set.
     */
    public void setStartDay(Date startDay) {
        this.startDay = startDay;
        updateProgress();
    }

    /**
     * Gets the time required for the task.
     *
     * @return The time required for the task.
     */
    @JsonProperty("time")
    public int getTime() {
        return time;
    }

    /**
     * Sets the time required for the task.
     *
     * @param time The time to set.
     */
    public void setTime(int time) {
        this.time = time;
        endDay = new Date(startDay.day() + time, startDay.month(), startDay.year());
    }

    /**
     * Sets the dependent tasks of the task.
     *
     * @param task The dependent task to set.
     */
    public void setDependentTasks(Task task) {
        dependentTasks.add(task);
        updateProgress();
    }

    /**
     * Deletes a dependent task from the task.
     *
     * @param task The dependent task to delete.
     */
    public void deleteDependent(Task task) {
        dependentTasks.remove(task);
    }

    /**
     * Compares the task to another task based on their titles.
     *
     * @param task The task to compare to.
     * @return An integer representing the result of the comparison.
     */
    @Override
    public int compareTo(Task task) {
        return task.getTitle().compareTo(title);
    }


    /**
     * Gets the major label of the task.
     *
     * @return The major label of the task.
     */
    @JsonProperty("majorLabel")
    public Major getMajorLabel() {
        return majorLabel;
    }

    /**
     * Sets the major label of the task.
     *
     * @param majorLabel The major label to set.
     */
    public void setMajorLabel(Major majorLabel) {
        this.majorLabel = majorLabel;
        this.labels = new Label(majorLabel.getMAJOR());
        updateProgress();
    }

    /**
     * Gets the degree of the task.
     *
     * @return The degree of the task.
     */
    @JsonProperty("degree")
    public int getDegree() {
        return degree;
    }

    /**
     * Sets the degree of the task.
     *
     * @param degree The degree to set.
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "Tittle: " + title.getTittle() + "\n" +
                "Start day: " + getStartDay() + "\n" +
                "End day: " + getEndDay() + "\n" +
                "Assign: " + getAssignments() + "\n" +
                "Status: " + getStatus() + "\n" +
                "Label: " + labels.getType();
    }

    /**
     * Displays information about the task.
     */
    public void display() {
        System.out.println("Tittle: " + title.getTittle());
        System.out.println("Start day: " + getStartDay());
        System.out.println("End day: " + getEndDay());
        System.out.println("Assign: " + getAssignments());
        System.out.println("Status: " + getStatus());
        System.out.println("Label: " + labels.getType());
        System.out.println("_________________________");
    }

    /**
     * Updates the progress of the task.
     */
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
