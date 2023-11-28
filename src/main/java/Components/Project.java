package Components;

import Model.ArrayList;
import Model.HashMap;
import Service.components.Label;
import Service.components.Repository;
import Service.components.Tittle;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {
    private Tittle tittle;
    ArrayList<Task> tasks = new ArrayList<>();
    Label label = new Label();
    Repository repository = new Repository("", "");
    public Project() {
    }

    public Project(Tittle tittle , ArrayList<Task> tasks , Label label , Repository repository) {
        this.tittle = tittle;
        this.tasks = tasks;
        this.label = label;
        this.repository = repository;
    }

    public Project(Tittle tittle) {
        this.tittle = tittle;
        this.tasks = new ArrayList<>();
        this.label = new Label("Test", "Description");
        this.repository = new Repository();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public ArrayList<Task> tasks (){
        return tasks;
    }

    public Task get(int index){
        return tasks.get(index);
    }

    @JsonProperty("tittle")
    public Tittle getTittle() {
        return tittle;
    }

    public void setTittle(Tittle tittle) {
        this.tittle = tittle;
    }

    @JsonProperty("tasks")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @JsonProperty("label")
    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @JsonProperty("repository")
    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }


    @Override
    public String toString() {
        return mapping().toString();
    }

    public HashMap<String> mapping(){
        HashMap<String> map = new HashMap<>();
        map.add("Tittle", tittle.mapping().toString());
        map.add("Task", new HashMap<>(tasks).toString());
        map.add("Label", label.mapping().toString());
        return map;
    }

    public void display(){
        for (int i = 0; i < tasks().size(); i++){
            System.out.println("Task " + (i + 1) + ": " + tasks.get(i).getTittle());
            System.out.println("End day: " + tasks.get(i).getEndDay());
        }
    }
}