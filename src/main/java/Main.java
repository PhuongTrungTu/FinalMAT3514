import Components.Project;
import Components.ProjectManager;
import Model.ArrayList;
import Service.Date;
import Service.Major;
import Service.People;
import Service.Tittle;

public class Main {
    public static void main(String[] args) {
        ProjectManager manager = ProjectManager.getInstance();
        Tittle tittle = new Tittle();
        manager.createNewProject(tittle);

        Project project1 = manager.getProject(tittle);

        project1.createNewTask(2);
        project1.createNewTask("Design");
        project1.createNewTask(3);
        project1.get(1).setTime(5);


        project1.search("Untitled").setTittle(new Tittle("Making User interface"));
        project1.search("Untitled").setTittle(new Tittle("Test"));

        project1.addDependentTask("Design", "Making User interface");
        project1.search("Design").setStartDay(new Date(1,1,2023));
        project1.search("Design").setEndDay(new Date(3,1,2023));
        project1.search("Design").setTime(10);
        project1.search("Design").setMajorLabel(new Major(4));

        project1.createNewTask("Des");
        project1.search("Des").setMajorLabel(new Major(4));

        project1.sortByDay();

        ArrayList<People> peoples = new ArrayList<>();
        peoples.add(new People("Tester", "3", new Major(3)));
        peoples.add(new People("Manager", "5", new Major(5)));
        peoples.add(new People("BE Dev", "1", new Major(1)));
        peoples.add(new People("Design", "4", new Major(4)));
        peoples.add(new People("FE Dev", "2", new Major(2)));
        peoples.add(new People("Full stack", "6", new Major(0)));

        project1.assignTasks(peoples);

        project1.update();
        manager.display();


    }
}