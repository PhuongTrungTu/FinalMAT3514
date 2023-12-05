package Components;

import Data.WriteData;
import Model.ArrayList;
import Model.BinarySearchingTree;
import Model.HashMap;
import Service.components.Components;
import Service.components.Repository;
import Service.components.Tittle;
import Service.components.Label;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.*;

public class ProjectManager {
    private static ProjectManager instance;
    protected ArrayList<Project> projects = new ArrayList<>();
    private final BinarySearchingTree<Tittle, Project> tree = new BinarySearchingTree<>();
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
    public void createNewProject(Tittle tittle){
        Project project = new Project(tittle);
        projects.add(project);
        tree.insert(project.getTittle(), project);
    }

    public void deleteProject(String tittle){
        for (int i = 0; i < projects.size(); i++){
            if (projects.get(i).getTittle().getTittle().equalsIgnoreCase(tittle)){
                projects.remove(i);
            }
        }
    }


    public void deleteProject(int index){
        projects.remove(index);
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
        return new HashMap<>(projects).toString();
    }

    public ArrayList<Project> search(Components components){
        ArrayList<Project> result = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++){
            if (components instanceof Tittle){
                if (projects.get(i).getTittle().getTittle().equalsIgnoreCase((((Tittle) components).getTittle()))){
                    result.add(projects.get(i));
                }
            } else if (components instanceof Label){
                if (projects.get(i).getLabel().getType().equalsIgnoreCase((((Label) components).getType()))){
                    result.add(projects.get(i));
                }
            } else if (components instanceof Repository){
                if (projects.get(i).getRepository().getTittle().equalsIgnoreCase((((Repository) components).getTittle()))){
                    result.add(projects.get(i));
                }
            }
        }
        return result;
    }

    public ArrayList<Project> search(String components){
        ArrayList<Project> result = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++){
            if (projects.get(i).getTittle().getTittle().equalsIgnoreCase(components)){
                    result.add(projects.get(i));
            }
        }
        return result;
    }
    public void writeData(String path){
        WriteData.writeDown(path, projects);
    }

    public void display(){
        for (int i = 0 ; i < projects.size(); i ++){
            System.out.println("Project " + (i + 1) + ": " + projects.get(i).getTittle());
            System.out.println("Number task: " + projects.get(i).tasks().size());
            projects.get(i).display();
            System.out.println();
        }
    }
}