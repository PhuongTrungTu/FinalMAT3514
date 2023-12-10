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

    /**
     * Constructs a Project with the specified parameters.
     *
     * @param title      The title of the project.
     * @param tasks      The list of tasks in the project.
     * @param label      The label associated with the project.
     * @param repository The repository associated with the project.
     */
    public Project(Title title, ArrayList<Task> tasks, Label label, Repository repository) {
        this.title = title;
        this.tasks = tasks;
        this.label = label;
        this.repository = repository;
    }

    /**
     * Constructs a Project with a specified title.
     *
     * @param title The title of the project.
     */
    public Project(Title title) {
        this.title = title;
        this.tasks = new ArrayList<>();
        this.label = new Label();
        this.repository = new Repository("");
    }

    /**
     * Creates a new task of the specified type and adds it to the project.
     *
     * @param type The type of the task to be created.
     */
    public void createNewTask(int type) {
        Task task = new Task(type);
        task.setMajorLabel(new Major(type));
        addTask(task);
    }

    /**
     * Creates a new task with the specified title and adds it to the project.
     *
     * @param title The title of the task to be created.
     */
    public void createNewTask(String tittle) {
        Task task = new Task(tittle);
        addTask(task);
    }

    /**
     * Adds the specified task to the project.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        graph.put(task, new HashMap<>());
        tree.insert(task.getTitle(), task);
    }


    /**
     * Deletes a task from the project, its dependencies, and associated data structures.
     *
     * @param task The task to be deleted from the project.
     */
    public void deleteTask(Task task) {
        tasks.remove(task);

        // Delete dependencies for all tasks in the project
        for (Task task1 : tasks) {
            deleteDependentTask(task1, task);
        }

        // Remove the task from the tree data structure
        if (tree.search(getTittle()).size() == 1) {
            tree.delete(task.getTitle());
        } else {
            tree.search(task.getTitle()).remove(task);
        }

        // Remove the task from the graph data structure
        graph.remove(task);
    }


    /**
     * Deletes the dependency between two tasks.
     *
     * @param task      The task from which the dependency is removed.
     * @param dependent The task that is dependent on the first task.
     */
    public void deleteDependentTask(Task task, Task dependent){
        task.getDependentTasks().remove(dependent);
    }


    /**
     * Adds a dependency between two tasks by their indices.
     *
     * @param taskIndex      The index of the task.
     * @param dependentIndex The index of the dependent task.
     */
    public void addDependentTask(int task, int denpendenttask) {
        addDependentTask(tasks.get(task), tasks.get(denpendenttask));
    }

    /**
     * Adds a dependency between two tasks by their titles.
     *
     * @param taskTitle      The title of the task.
     * @param dependentTitle The title of the dependent task.
     */
    public void addDependentTask(String task1, String task2) {
        addDependentTask(search(task1), search(task2));
    }

    /**
     * Adds a dependency between two tasks.
     *
     * @param task1 The first task.
     * @param task2 The second task.
     */
    public void addDependentTask(Task task1, Task task2) {
        if (!tasks.contain(task1) && !tasks.contain(task2)) {
            throw new IllegalArgumentException(
                    task1.getTitle().getTitle() + ", " + task2.getTitle() + "are not in project!");
        } else if (!tasks.contain(task1)) {
            addTask(task1);
        } else if (!tasks.contain(task2)) {
            addTask(task2);
        }
        graph.get(task1).put(task2, task1.getTime());

        task1.addDependentTask(task2);
    }

    /**
     * Updates the progress of all tasks in the project.
     */
    public void update() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).updateProgress();
        }
    }

    /**
     * Calculates the maximum duration of the project.
     *
     * @return The maximum duration of the project in days.
     */
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

    /**
     * Finds the longest path of tasks in the project.
     *
     * @return An ArrayList representing the longest path of tasks.
     */
    public ArrayList<Task> findLongestPath() {
        update();

        // Using map for storegare best time for task.
        HashMap<Task, Integer> maxTimeMap = new HashMap<>();

        // finding path for each task
        for (Task task : tasks) {
            findLongestPathForTask(task, maxTimeMap);
        }

        //find task have largest time
        Task maxTimeTask = null;
        int maxTime = 0;

        for (Task task : tasks) {
            if (maxTimeMap.containsKey(task) && maxTimeMap.get(task) > maxTime) {
                maxTime = maxTimeMap.get(task);
                maxTimeTask = task;
            }
        }

        // Build list of path from task have largest time
        ArrayList<Task> longestPath = new ArrayList<>();
        buildLongestPath(maxTimeTask, longestPath);

        return longestPath;
    }
    /**
     * Recursive method to find the longest path for a given task in the project.
     *
     * @param task       The task for which the longest path is to be found.
     * @param maxTimeMap A HashMap storing the maximum time for each task to avoid redundant calculations.
     * @return The maximum time for the given task and its dependencies.
     */
    private int findLongestPathForTask(Task task, HashMap<Task, Integer> maxTimeMap) {
        // If the total time for this task has already been calculated, return the precomputed value
        if (maxTimeMap.containsKey(task)) {
            return maxTimeMap.get(task);
        }

        int maxPathTime = 0;

        // Calculate the total time for all dependent tasks
        for (Task dependentTask : task.getDependentTasks()) {
            int pathTime = findLongestPathForTask(dependentTask, maxTimeMap);
            maxPathTime = Math.max(maxPathTime, pathTime);
        }

        // The total time for the current task is the sum of the total time for all dependent tasks
        // plus the time required for the current task
        maxTimeMap.put(task, maxPathTime + task.getTime());

        return maxTimeMap.get(task);
    }

    /**
     * Builds the longest path for a given task in the project.
     *
     * @param task        The task for which the longest path is to be built.
     * @param longestPath An ArrayList to store the tasks in the longest path.
     */
    private void buildLongestPath(Task task, ArrayList<Task> longestPath) {
        if (task != null) {
            // Recursively build the longest path for dependent tasks
            for (Task dependentTask : task.getDependentTasks()) {
                buildLongestPath(dependentTask, longestPath);
            }
            // Add the current task to the longest path
            longestPath.add(task);
        }
    }


    /**
     * Deletes the dependency between two tasks based on their indices.
     *
     * @param id        The index of the task.
     * @param dependent The index of the dependent task.
     */
    public void deleteDependentTask(int id, int dependent) {
        tasks.get(id).deleteDependent(tasks.get(dependent));
    }

    /**
     * Removes a task from the project and its dependencies.
     *
     * @param task The task to be removed from the project.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).getDependentTasks().remove(task);
        }
    }

    /**
     * Gets the list of tasks in the project.
     *
     * @return An ArrayList containing the tasks in the project.
     */
    public ArrayList<Task> tasks() {
        return tasks;
    }

    /**
     * Gets a task from the project based on its index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the title of the project.
     *
     * @return The title of the project.
     */
    @JsonProperty("tittle")
    public Title getTittle() {
        return title;
    }

    /**
     * Sets the title of the project.
     *
     * @param title The new title of the project.
     */
    public void setTittle(Title title) {
        this.title = title;
    }

    /**
     * Gets the list of tasks in the project.
     *
     * @return An ArrayList containing the tasks in the project.
     */
    @JsonProperty("tasks")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the list of tasks in the project.
     *
     * @param tasks The new list of tasks for the project.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the label associated with the project.
     *
     * @return The label associated with the project.
     */
    @JsonProperty("label")
    public Label getLabel() {
        return label;
    }

    /**
     * Sets the label for the project.
     *
     * @param label The new label for the project.
     */
    public void setLabel(Label label) {
        this.label = label;
    }

    /**
     * Gets the repository associated with the project.
     *
     * @return The repository associated with the project.
     */
    @JsonProperty("repository")
    public Repository getRepository() {
        return repository;
    }

    /**
     * Sets the repository for the project.
     *
     * @param repository The new repository for the project.
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    /**
     * Sorts the tasks in the project by title.
     */
    public void sortByTittle() {
        tasks = Sort.sort(tasks);
    }

    /**
     * Sorts the tasks in the project by day.
     */
    public void sortByDay() {
        tasks = Sort.sortByDay(tasks);
    }

    /**
     * Sorts the tasks in the project by time.
     */
    public void sortByTime() {
        tasks = Sort.sortByTime(tasks);
    }

    /**
     * Sorts the tasks in the project by degree.
     */
    public void sortByDegree() {
        tasks = Sort.sortByDegree(tasks);
    }

    /**
     * Sorts the tasks in the project by major.
     */
    public void sortByMajor() {
        tasks = Sort.sortByMajor(tasks);
    }


    /**
     * Displays the tasks in a roadmap style, sorted by day, up to the current date.
     */
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

    /**
     * Displays the tasks sorted by degree.
     */
    public void degreeDisplayStyle() {
        sortByDegree();
        System.out.println(tasks);
    }

    /**
     * Displays all tasks in the project along with their details.
     */
    public void display() {
        for (int i = 0; i < tasks().size(); i++) {
            System.out.println("Task: " + (i + 1));
            tasks.get(i).display();
        }
        System.out.println("************************");
    }

    /**
     * Searches for a task in the project based on its title.
     *
     * @param title The title of the task to be searched.
     * @return The task with the specified title.
     * @throws NullPointerException If the task with the specified title is not found.
     */
    public Task search(String title) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().getTitle().equals(title)) {
                return tasks.get(i);
            }
        }
        throw new NullPointerException("Task " + title + " didn't created in project");
    }

    /**
     * Searches for a task in the project based on its title.
     *
     * @param title The title of the task to be searched.
     * @return The task with the specified title.
     * @throws NullPointerException If the task with the specified title is not found.
     */
    public Task search(Title title) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().equals(title)) {
                return tasks.get(i);
            }
        }
        throw new NullPointerException("Task " + title.getTitle() + " didn't created in project");
    }

    /**
     * Sorts a list of people by their majors.
     *
     * @param peopleList The list of people to be sorted.
     * @return An ArrayList of people sorted by their majors.
     */
    public static ArrayList<People> sortPeopleByMajor(ArrayList<People> peopleList) {
        ArrayList<People> result = new ArrayList<>();
        ArrayList<ArrayList<People>> container = new ArrayList<>();
        for (int i = 0; i < Major.MAX; i++) {
            container.add(new ArrayList<>());
        }
        for (People people : peopleList) {
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

    /**
     * Assigns people from a given list to tasks based on their majors.
     *
     * @param peopleList The list of people available for task assignment.
     */
    public void assignTasks(ArrayList<People> peopleList) {
        for (Task task : tasks) {
            assignPeopleToTask(peopleList, task);
        }
    }

    /**
     * Assigns people from a given list to a specific task based on their majors.
     *
     * @param peopleList The list of people available for task assignment.
     * @param task       The task to which people are being assigned.
     */
    private void assignPeopleToTask(ArrayList<People> peopleList, Task task) {
        for (People person : peopleList) {
            if (person.getMajors().contain(task.getMajorLabel())) {
                task.addAssignment(person, false);
                return; // Return once a suitable person is found for the task
            }
        }

        // If no suitable person is found, try to assign a person with major "All"
        for (People person : peopleList) {
            if (person.getMajors().contain(new Major(0)) && person.hasAssignedTask()) {
                task.addAssignment(person, false);
                return; // Return once a person with major "All" is found
            }
        }
    }

    public void setTaskTitle(Task task,Title title){
        tree.delete(task.getTitle(),task);
        task.setTitle(title);
        tree.insert(title, task);
    }
}