package Components;

import Model.Graph;
import Model.ArrayList;

import Model.Node.Vertex;
import Service.Sort;
import Service.components.Date;
import Service.components.Label;
import Service.components.Repository;
import Service.components.Tittle;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Project {
    private Tittle tittle = new Tittle("");
    private ArrayList<Task> tasks = new ArrayList<>();
    private Label label = new Label();
    private Repository repository = new Repository("" , "");
    private final Task FINISH_TASK = new Task();

    private Map<Task,Map<Task, Integer>> graph = new HashMap<>();

    public Project() {
        Tittle tittle1 = new Tittle("End", "");
        FINISH_TASK.setTittle(tittle1);
        FINISH_TASK.setTime(0);
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
        this.label = new Label();
        this.repository = new Repository();
    }

    public void createNewTask(int type){
        Task task = new Task(type);
        addTask(task);
    }

    public void createNewTask(String tittle){
        Task task = new Task();
        task.setTittle(new Tittle(tittle));
        addTask(task);
    }

    public void addTask(Task task) {
        task.addDependentTask(FINISH_TASK);
        tasks.add(task);
        graph.put(task, new HashMap<>());
    }

    public void addDependentTask(int task, int denpendenttask){
        addDependentTask(tasks.get(task), tasks.get(denpendenttask));
    }

    public void addDependentTask(String task1, String task2){
        addDependentTask(search(task1),  search(task2));
    }

    public void addDependentTask(Task task1, Task task2){
        if (!tasks.contain(task1) && !tasks.contain(task2)){
            throw new IllegalArgumentException(task1.getTittle().getTittle() + ", " + task2.getTittle() + "are not in project!");
        } else if (!tasks.contain(task1)){
            addTask(task1);
        } else if (! tasks.contain(task2)){
            addTask(task2);
        }
        graph.get(task1).put(task2, task1.getTime());

        task1.addDependentTask(task2);
        task1.deleteDependent(FINISH_TASK);
    }

    public ArrayList<Task> findLongestPath() {
        // Tạo bản sao của danh sách tasks để tránh ảnh hưởng đến dữ liệu gốc
        ArrayList<Task> clonedTasks = tasks.copy();

        Sort.sortByTime(clonedTasks);

        // Khởi tạo bảng lưu trữ tổng thời gian tốt nhất cho mỗi task
        Map<Task, Integer> bestTimes = new HashMap<>();

        // Duyệt qua từng task và cập nhật tổng thời gian tốt nhất
        for (Task task : clonedTasks) {
            int maxTime = task.getTime();

            // Duyệt qua các dependentTasks để cập nhật tổng thời gian tốt nhất
            for (Task dependentTask : task.getDependentTasks()) {
                int totalTime = bestTimes.getOrDefault(dependentTask, 0) + dependentTask.getTime();
                maxTime = Math.max(maxTime, totalTime);
            }

            bestTimes.put(task, maxTime);
        }

        // Tìm task có tổng thời gian lớn nhất
        Task maxTimeTask = Collections.max(bestTimes.entrySet(), Map.Entry.comparingByValue()).getKey();

        // Tạo danh sách chứa đường găng có tổng thời gian lớn nhất
        ArrayList<Task> longestPath = new ArrayList<>();
        addLongestPath(longestPath, maxTimeTask, bestTimes);

        return longestPath;
    }

    private void addLongestPath(ArrayList<Task> longestPath, Task currentTask, Map<Task, Integer> bestTimes) {
        longestPath.add(currentTask);

        for (Task dependentTask : currentTask.getDependentTasks()) {
            if (bestTimes.get(dependentTask) + dependentTask.getTime() == bestTimes.get(currentTask)) {
                addLongestPath(longestPath, dependentTask, bestTimes);
                break;
            }
        }
    }

    public void deleteDependentTask(int id, int dependent){
        tasks.get(id).deleteDependent(tasks.get(dependent));
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        for (int i = 0; i < tasks.size(); i++){
            tasks.get(i).getDependentTasks().remove(task);
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


//    @Override
//    public String toString() {
//        return mapping().toString();
//    }
//
//    public HashMap<String> mapping() {
//        HashMap<String> map = new HashMap<>();
//        map.add("Tittle" , tittle.mapping().toString());
//        map.add("Task" , new HashMap<>(tasks).toString());
//        map.add("Label" , label.mapping().toString());
//        return map;
//    }

    public void sortByTittle(){
        tasks = Sort.sort(tasks);
    }

    public void sortByDay(){
        tasks = Sort.sortByDay(tasks);
    }

    public void sortByTime(){
        tasks = Sort.sortByTime(tasks);
    }

    public void sortByDegree(){
        tasks = Sort.sortByDegree(tasks);
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
            System.out.println("Task: " + (i + 1));
            tasks.get(i).display();
            System.out.println("______________________");
        }
    }

    public Task search(String tittle){
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getTittle().getTittle().equalsIgnoreCase(tittle)){
                return tasks.get(i);
            }
        }
        return null;
    }

    public Task search(Tittle tittle){
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getTittle().equals(tittle)){
                return tasks.get(i);
            }
        }
        return null;
    }
}