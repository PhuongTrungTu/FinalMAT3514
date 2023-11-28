package Components;

import Data.Write.WriteData;
import Model.ArrayList;
import Model.HashMap;

public class ProjectManager {
    private static ProjectManager instance;
    protected ArrayList<Object> projects = new ArrayList<>();
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

    @Override
    public String toString() {
        WriteData.writeDown("Data/StoreData/", new HashMap<>(projects));
        return new HashMap<>(projects).toString();
    }
}
