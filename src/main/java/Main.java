import Components.Project;
import Components.ProjectManager;
import Components.Task;
import Model.ArrayList;
import Service.components.Tittle;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ProjectManager manager = ProjectManager.getInstance();
        Tittle tittle = new Tittle();
        manager.createNewProject(tittle);

        Project project1 = manager.getProject(tittle);

        project1.createNewTask(1);
        project1.createNewTask("Design");
        project1.createNewTask(2);
        project1.get(1).setTime(5);


        project1.search("Untitled").setTittle(new Tittle("Making User interface"));
        project1.search("Untitled").setTittle(new Tittle("Test"));
        project1.addDependentTask("Design", "Making User interface");


        ArrayList<Task> longestDistances = project1.findLongestPath();

        for (Task entry : longestDistances) {
            System.out.println("Task: ");
            entry.display();
            System.out.println("Distance: " + entry.getTime());
            System.out.println("__________");
        }
    }
}