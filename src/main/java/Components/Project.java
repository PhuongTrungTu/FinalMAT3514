package Components;

import Model.ArrayList;
import Model.HashMap;
import Service.components.Label;
import Service.components.Repository;
import Service.components.Tittle;

public class Project {
    private Tittle tittle;
    ArrayList<Task> tasks;
    Label label;
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

    public Tittle getTittle() {
        return tittle;
    }

    public void setTittle(Tittle tittle) {
        this.tittle = tittle;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

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
}