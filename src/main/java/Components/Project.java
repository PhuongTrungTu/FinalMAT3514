package Components;

import Model.Graph;
import Model.ArrayList;
import Model.BinarySearchingTree;
import Model.HashMap;
import Model.Node.Vertex;
import Service.Sort;
import Service.components.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {
    private Tittle tittle;
    private Graph<Tittle, Task> graph;
    private BinarySearchingTree<Tittle, Task> BST = new BinarySearchingTree<>();
    ArrayList<Task> tasks = new ArrayList<>();
    Label label = new Label();
    Repository repository = new Repository("" , "");
    private final Vertex<Tittle, Task> FINISH_TASK = new Vertex<>(new Tittle("End", ""), new Task());

    public Project() {
        Tittle tittle1 = new Tittle("End", "");
        FINISH_TASK.getNode().setKey(tittle1);
        FINISH_TASK.getNode().getData().setTittle(tittle1);
        FINISH_TASK.getNode().getData().setTime(0);
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
        this.label = new Label("Test" , "Description");
        this.repository = new Repository();
    }

    public void addTask(Task task) {
        tasks.add(task);
        Vertex<Tittle, Task> u = new Vertex<>(task.getTittle(), task);
        graph.addVertex(u);
        graph.addEdge(u, FINISH_TASK);
        BST.insert(task.getTittle(), task);
    }

    public void addDependentTask(int l, int r){
        tasks.get(l).setDependentTasks(tasks.get(r));
        tasks.get(l).getDependentTasks().remove(FINISH_TASK);
        Vertex<Tittle, Task> u = new Vertex<>(tasks.get(r).getTittle(), tasks.get(r));
        addDependentTask(u, FINISH_TASK);
    }

    public void addDependentTask(Vertex<Tittle, Task> u, Vertex<Tittle, Task> v){
        u.addEdge(v, u.getNode().getData().getTime());
    }

    public void deleteDependentTask(int id, int dependent){
        tasks.get(id).deleteDependent(tasks.get(dependent));
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        BST.delete(task.getTittle(), task);
        for (int i = 0; i < tasks.size(); i++){
            tasks.get(i).dependentTasks.remove(task);
        }
    }

    public ArrayList<Task> tasks() {
        return tasks;
    }

    public Task get(int index) {
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

    public HashMap<String> mapping() {
        HashMap<String> map = new HashMap<>();
        map.add("Tittle" , tittle.mapping().toString());
        map.add("Task" , new HashMap<>(tasks).toString());
        map.add("Label" , label.mapping().toString());
        return map;
    }

    private final Sort sort = new Sort(tasks);
    public void sortByTittle(){
        tasks = sort.sort();
    }

    public void sortByDay(){
        tasks = sort.sortByDay();
    }

    public void sortByTime(){
        tasks = sort.sortByTime();
    }

    public void sortByDegree(){
        tasks = sort.sortByDegree();
    }

    public void roadMapDisplayStyle(){
        sortByDay();
        int index = 0;
        for (int i = 0; i < getTasks().size(); i++){
            if(tasks.get(i).getEndDay().compareTo(Date.today()) >= 0){
                index = i;
                break;
            }
        }

        ArrayList<?> result = ArrayList.copy(tasks, index);
        System.out.println(tasks);
    }

    public void degreeDisplayStyle(){
        sortByDegree();
        System.out.println(tasks);
    }

    public void display() {
        for (int i = 0; i < tasks().size(); i++){
            System.out.println("Task " + (i + 1) + ": " + tasks.get(i).getTittle());
            System.out.println("End day: " + tasks.get(i).getEndDay());
        }
    }

    public Task search(String tittle){
        return BST.search(new Tittle(tittle, ""));
    }
}