package Components;

import Model.BinarySearchingTree;
import Model.Graph;
import Model.ArrayList;

import Model.Node.Vertex;
import Service.Sort;
import Service.components.*;
import Service.components.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Project {
    private Tittle tittle = new Tittle("");
    private ArrayList<Task> tasks = new ArrayList<>();
    private Label label = new Label();
    private Repository repository = new Repository("" , "");
    private Map<Task,Map<Task, Integer>> graph = new HashMap<>();
    private int maxTime = 0;
    private BinarySearchingTree<Tittle, Task> tree = new BinarySearchingTree<>();

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
        this.label = new Label();
        this.repository = new Repository();
    }

    public void createNewTask(int type){
        Task task = new Task(type);
        addTask(task);
    }

    public void createNewTask(String tittle){
        Task task = new Task(tittle);
        addTask(task);
    }

    public void addTask(Task task) {
        tasks.add(task);
        graph.put(task, new HashMap<>());
        tree.insert(task.getTittle(), task);
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
    }

    public void update(){
        for (int i = 0; i < tasks.size(); i++){
            tasks.get(i).updateProgress();
        }
    }

    public int maxDay(){
        ArrayList<Task> tasks = findLongestPath();
        int sum = 0;
        for (int i = 0; i < tasks.size(); i++){
            sum += tasks.get(i).getTime();
        }
        return sum;
    }
    public ArrayList<Task> findLongestPath() {
        update();

        // Sử dụng một map để lưu trữ tổng thời gian tốt nhất cho mỗi task
        Map<Task, Integer> maxTimeMap = new HashMap<>();

        // Tìm đường găng cho mỗi task
        for (Task task : tasks) {
            findLongestPathForTask(task, maxTimeMap);
        }

        // Tìm task có tổng thời gian lớn nhất
        Task maxTimeTask = null;
        int maxTime = 0;

        for (Task task : tasks) {
            if (maxTimeMap.containsKey(task) && maxTimeMap.get(task) > maxTime) {
                maxTime = maxTimeMap.get(task);
                maxTimeTask = task;
            }
        }

        // Xây dựng danh sách đường găng từ task có tổng thời gian lớn nhất
        ArrayList<Task> longestPath = new ArrayList<>();
        buildLongestPath(maxTimeTask, longestPath);

        return longestPath;
    }

    // Phương thức đệ quy để tìm đường găng cho mỗi task
    private int findLongestPathForTask(Task task, Map<Task, Integer> maxTimeMap) {
        // Nếu đã tính tổng thời gian cho task này rồi, trả về giá trị đã tính
        if (maxTimeMap.containsKey(task)) {
            return maxTimeMap.get(task);
        }

        int maxPathTime = 0;

        // Tính tổng thời gian cho tất cả các task phụ thuộc
        for (Task dependentTask : task.getDependentTasks()) {
            int pathTime = findLongestPathForTask(dependentTask, maxTimeMap);
            maxPathTime = Math.max(maxPathTime, pathTime);
        }

        // Tổng thời gian cho task hiện tại là tổng thời gian của tất cả các task phụ thuộc cộng với thời gian của task hiện tại
        maxTimeMap.put(task, maxPathTime + task.getTime());

        return maxTimeMap.get(task);
    }

    private void buildLongestPath(Task task, ArrayList<Task> longestPath) {
        if (task != null) {
            for (Task dependentTask : task.getDependentTasks()) {
                buildLongestPath(dependentTask, longestPath);
            }
            longestPath.add(task);
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
        }
        System.out.println("************************");
    }

//    public ArrayList<Task> filterTask(String tittle){
//        return tree.search(new Tittle(tittle));
//    }

    public Task search(String tittle){
        for (int i = 0; i < tasks.size(); i++){
            if (tasks.get(i).getTittle().getTittle().equals(tittle)){
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

    public void autoAssignTask(ArrayList<People> people){
    }
}