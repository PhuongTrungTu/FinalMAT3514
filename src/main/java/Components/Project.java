/**
 * The {@code Project} class represents a project with tasks, dependencies, and various functionalities
 * for task management and assignment. It includes methods for task creation, dependency management,
 * progress tracking, sorting, and display styles.
 *
 * <p>This class utilizes a binary search tree for efficient task retrieval and manipulation.</p>
 *
 * <p>Instances of this class can be created with a title, a list of tasks, a label, and a repository.</p>
 *
 * @author Grizmo
 * @version 1.0
 */

package Components;

import Model.BinarySearchingTree;
import Model.ArrayList;
import Model.HashMap;
import Service.*;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Represents a project with tasks, dependencies, and various functionalities
 * for task management and assignment.
 */
public class Project {
    /**
     * The title of the project.
     */
    private Title title = new Title("");

    /**
     * The list of tasks in the project.
     */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * The label associated with the project.
     */
    private Label label = new Label();

    /**
     * The repository associated with the project.
     */
    private Repository repository = new Repository("", "");

    /**
     * A graph representation of task dependencies in the project.
     */
    private HashMap<Task, HashMap<Task, Integer>> graph = new HashMap<>();

    /**
     * A binary search tree for efficient task retrieval.
     */
    private BinarySearchingTree<Title, Task> tree = new BinarySearchingTree<>();

    /**
     * Default constructor for the {@code Project} class.
     */
    public Project() {
    }

    public Project(Title title, ArrayList<Task> tasks, Label label, Repository repository) {
        this.title = title;
        this.tasks = tasks;
        this.label = label;
        this.repository = repository;
    }

    public Project(Title title) {
        this.title = title;
        this.tasks = new ArrayList<>();
        this.label = new Label();
        this.repository = new Repository();
    }

    public void createNewTask(int type) {
        Task task = new Task(type);
        task.setMajorLabel(new Major(type));
        addTask(task);
    }

    public void createNewTask(String tittle) {
        Task task = new Task(tittle);
        addTask(task);
    }

    public void addTask(Task task) {
        tasks.add(task);
        graph.put(task, new HashMap<>());
        tree.insert(task.getTittle(), task);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
        for (Task task1: tasks){
            deleteDependentTask(task1, task);
        }
    }

    public void deleteDependentTask(Task task, Task dependent){
        task.getDependentTasks().remove(dependent);
    }

    public void addDependentTask(int task, int denpendenttask) {
        addDependentTask(tasks.get(task), tasks.get(denpendenttask));
    }

    public void addDependentTask(String task1, String task2) {
        addDependentTask(search(task1), search(task2));
    }

    public void addDependentTask(Task task1, Task task2) {
        if (!tasks.contain(task1) && !tasks.contain(task2)) {
            throw new IllegalArgumentException(
                    task1.getTittle().getTittle() + ", " + task2.getTittle() + "are not in project!");
        } else if (!tasks.contain(task1)) {
            addTask(task1);
        } else if (!tasks.contain(task2)) {
            addTask(task2);
        }
        graph.get(task1).put(task2, task1.getTime());

        task1.addDependentTask(task2);
    }

    public void update() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).updateProgress();
        }
    }

    public int maxDay() {
        ArrayList<Task> tasks = findLongestPath();
        if (tasks.isEmpty()){
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < tasks.size(); i++) {
            sum += tasks.get(i).getTime();
        }
        return sum;
    }

    public ArrayList<Task> findLongestPath() {
        update();

        // Sử dụng một map để lưu trữ tổng thời gian tốt nhất cho mỗi task
        HashMap<Task, Integer> maxTimeMap = new HashMap<>();

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
    private int findLongestPathForTask(Task task, HashMap<Task, Integer> maxTimeMap) {
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

        // Tổng thời gian cho task hiện tại là tổng thời gian của tất cả các task phụ
        // thuộc cộng với thời gian của task hiện tại
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

    public void deleteDependentTask(int id, int dependent) {
        tasks.get(id).deleteDependent(tasks.get(dependent));
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        for (int i = 0; i < tasks.size(); i++) {
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
    public Title getTittle() {
        return title;
    }

    public void setTittle(Title title) {
        this.title = title;
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

    public void sortByTittle() {
        tasks = Sort.sort(tasks);
    }

    public void sortByDay() {
        tasks = Sort.sortByDay(tasks);
    }

    public void sortByTime() {
        tasks = Sort.sortByTime(tasks);
    }

    public void sortByDegree() {
        tasks = Sort.sortByDegree(tasks);
    }

    public void sortByMajor() {
        tasks = Sort.sortByMajor(tasks);
    }

    public void roadMapDisplayStyle() {
        sortByDay();
        int index = 0;
        for (int i = 0; i < getTasks().size(); i++) {
            if (tasks.get(i).getEndDay().compareTo(Date.today()) >= 0) {
                index = i;
                break;
            }
        }

        ArrayList<?> result = ArrayList.copy(tasks, index);
        System.out.println(result);
    }

    public void degreeDisplayStyle() {
        sortByDegree();
        System.out.println(tasks);
    }

    public void display() {
        for (int i = 0; i < tasks().size(); i++) {
            System.out.println("Task: " + (i + 1));
            tasks.get(i).display();
        }
        System.out.println("************************");
    }

    public Task search(String tittle) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTittle().getTittle().equals(tittle)) {
                return tasks.get(i);
            }
        }
        throw new NullPointerException("Task " + tittle + " didn't created in project");
    }

    public Task search(Title title) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTittle().equals(title)) {
                return tasks.get(i);
            }
        }
        throw new NullPointerException("Task " + title.getTittle() + " didn't created in project");
    }

    public static ArrayList<People> sortPeopleByMajor(ArrayList<People> peoples) {
        ArrayList<People> result = new ArrayList<>();
        ArrayList<ArrayList<People>> container = new ArrayList<>();
        for (int i = 0; i < Major.MAX; i++) {
            container.add(new ArrayList<>());
        }
        for (People people : peoples) {
            for (int j = 0; j < container.size(); j++) {
                if (people.getMajors().contain(new Major(j))) {
                    container.get(j).add(people);
                }
            }
        }

        for (int i = 0; i < container.size(); i++) {
            result.addAll(container.get(i));
        }
        return result;
    }

    public void assignTasks(ArrayList<People> peopleList) {
        for (Task task : tasks) {
            assignPeopleToTask(peopleList, task);
        }
    }

    private void assignPeopleToTask(ArrayList<People> peopleList, Task task) {
        for (People person : peopleList) {
            if (person.getMajors().contain(task.getMajorLabel())) {
                task.addassignment(person, false);
                return; // Return once a suitable person is found for the task
            }
        }

        // If no suitable person is found, try to assign a person with major "All"
        for (People person : peopleList) {
            if (person.getMajors().contain(new Major(0)) && person.hasAssignedTask()) {
                task.addassignment(person, false);
                return; // Return once a person with major "All" is found
            }
        }
    }

}