import Components.Project;
import Components.ProjectManager;
import Components.Task;
import Model.ArrayList;
import Service.components.Date;
import Service.components.People;
import Service.components.Repository;
import Service.components.Tittle;

public class Main {
    public static void main(String[] args) {
        // Init manager
        ProjectManager manager = ProjectManager.getInstance();

        // Repository of project
        Repository repo = new Repository("https://github.com/PhuongTrungTu/FinalMAT3514", "FinalMAT3514");

        // Init Project name: First Project
        Project first = new Project(new Tittle("First Project", "Nothing"));

        // Set repository
        first.setRepository(repo);

        // Assignments for task
        ArrayList<People> peopleArrayList = new ArrayList<>();
        peopleArrayList.add(new People("Grizmo", new Date(26,10,2003), "21000709"));
        peopleArrayList.add(new People("Hoang Tu", new Date(26,10,2005), "23000709"));

        // init task 1
        Task task = new Task("Task1", "Nothing for task1", new Date(26,10,2023), peopleArrayList);

        // Add task for project
        first.addTask(task);

        // Create project
        manager.createNewProject(first);

        // display
        System.out.println(manager);

    }
}