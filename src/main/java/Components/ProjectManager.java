package Components;


import Model.ArrayList;
import Model.HashMap;
import Service.Title;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectManager {
    private static ProjectManager instance;
    protected ArrayList<Project> projects = new ArrayList<>();

    private ProjectManager() {

    }

    public static ProjectManager getInstance() {
        if (instance == null) {
            instance = new ProjectManager();
        }
        return instance;
    }

    public void createNewProject(Project project) {
        projects.add(project);
    }

    public void createNewProject(Title title) {
        Project project = new Project(title);
        projects.add(project);
    }

    public void deleteProject(String tittle) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getTittle().getTittle().equalsIgnoreCase(tittle)) {
                projects.remove(i);
            }
        }
    }

    public void deleteProject(int index) {
        projects.remove(index);
    }

    public Project getProject(int index) {
        return projects.get(index);
    }

    public Project getProject(Title title) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getTittle().equals(title)) {
                return projects.get(i);
            }
        }
        return null;
    }

    public Project getProject(String tittle) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getTittle().getTittle().equalsIgnoreCase(tittle)) {
                return projects.get(i);
            }
        }
        return null;
    }

    @JsonProperty("Projects")
    protected ArrayList<Project> projectArrayList() {
        return projects;
    }

    @Override
    public String toString() {
        return new HashMap<>().toString();
    }

    public void writeData(String path) {
        /*TODO*/
    }

    public void display() {
        for (int i = 0; i < projects.size(); i++) {
            System.out.println("Project " + (i + 1) + ": " + projects.get(i).getTittle().getTittle());
            System.out.println("Number task: " + projects.get(i).tasks().size());
            projects.get(i).display();
            System.out.println();
        }
    }
}