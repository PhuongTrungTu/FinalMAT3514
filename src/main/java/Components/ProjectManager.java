package Components;

import Data.WriteData;
import Model.ArrayList;
import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectManager {
    private static ProjectManager instance;
    protected ArrayList<Project> projects = new ArrayList<>();
    private ProjectManager(){

    }

    public static ProjectManager getInstance(){
        if (instance == null){
            instance = new ProjectManager();
        }return instance;
    }

    public void createNewProject(Project project){
        projects.add(project);
    }
    public void createNewProject(){
        Project project = new Project();
        projects.add(project);
    }

    public Object getProject(int index){
        return projects.get(index);
    }

    @JsonProperty("Projects")
    protected ArrayList<Project> projectArrayList(){
        return projects;
    }

    @Override
    public String toString() {
        WriteData.writeDown("Data/StoreData/", projects);
        return new HashMap<>(projects).toString();
    }
}
