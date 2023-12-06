import Components.Project;
import Components.ProjectManager;
import Components.Task;
import Service.components.Tittle;

public class Main {
    public static void main(String[] args) {
        ProjectManager manager = ProjectManager.getInstance();
        Tittle tittle = new Tittle();
        manager.createNewProject(tittle);

        Project project1 = manager.getProject(tittle);

        project1.createNewTask(1);
        project1.createNewTask("Design");

        project1.search("Untitled").setTittle(new Tittle("Making User interface"));

//        manager.display();

//        project1.search("Making User interface").display();

        project1.addDependentTask("Design", "Making User interface");
        ((Task) project1.get(0).getDependentTasks().toArray()[0]).display();
    }
}