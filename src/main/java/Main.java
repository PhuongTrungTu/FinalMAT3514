<<<<<<< HEAD
import Components.Project;
import Components.ProjectManager;
import Components.Task;
import Data.ReadData;
import Model.ArrayList;
import Service.components.Date;
import Service.components.People;
import Service.components.Repository;
import Service.components.Tittle;

public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        long maxMemory = 4L * 1024 * 1024 * 1024; // 4 gigabytes in bytes
        runtime.gc();

        // Init manager
        ProjectManager manager = ProjectManager.getInstance();

//        String         filePath = "Data/data.json";
//        ReadData.read(filePath, manager);
//
//        System.out.println(manager);
//
//        manager.writeData("Data/");


        // Repository of project
        Repository repo = new Repository("https://github.com/PhuongTrungTu/FinalMAT3514", "FinalMAT3514");

        // Init Project name: First Project
        Project first = new Project(new Tittle("First Project", "Nothing"));

        // Set repository
        first.setRepository(repo);

        // Assignments for task
        ArrayList<People> peopleArrayList = new ArrayList<>();
        peopleArrayList.add(new People("htmlexe", "21000245"));
        peopleArrayList.add(new People("Mlisfi", "21000708"));
        peopleArrayList.add(new People("Grizmo", "23000709"));

        // init task 1
        Task task = new Task("Task1", "Nothing for task1", new Date(26,10,2023), peopleArrayList);

        Task task1 = new Task("Task2", "This is task2", new Date(31,12,2023), new ArrayList<>(new People[]{new People("Grizmo", "21000709")}));
        // Add task for project
        first.addTask(task);
        first.addTask(task1);

        // Create project
        manager.createNewProject(first);

        // Init Project name: First Project
        Project second = new Project(new Tittle("Second Project", "Seconds"));

        // Repository of project
        Repository repository = new Repository("https://github.com/PhuongTrungTu/FinalMAT3515", "Project Manager");

        // Set repository
        second.setRepository(repository);

        // init task 1
        Task task2 = new Task("Task21", "Task 1 for second project", new Date(26,10,2023), new ArrayList<>(new People[]{new People("htmlexe", "21000245")}));

        Task task3 = new Task("Task22", "This is task 2 of second project", new Date(31,2,2025), new ArrayList<>(new People[]{new People("Mlisfi", "21000708")}));
        // Add task for project
        second.addTask(task2);
        second.addTask(task3);

        manager.createNewProject(second);

//        manager.deleteProject(1);

        manager.deleteProject(1);

        manager.writeData("Data/");


        manager.display();

    }
=======
import Components.Project;
import Components.ProjectManager;
import Components.Task;
import Data.ReadData;
import Model.ArrayList;
import Service.components.Date;
import Service.components.People;
import Service.components.Repository;
import Service.components.Tittle;

public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        long maxMemory = 4L * 1024 * 1024 * 1024; // 4 gigabytes in bytes
        runtime.gc();

        // Init manager
        ProjectManager manager = ProjectManager.getInstance();

//        String         filePath = "Data/data.json";
//        ReadData.read(filePath, manager);
//
//        System.out.println(manager);
//
//        manager.writeData("Data/");


        // Repository of project
        Repository repo = new Repository("https://github.com/PhuongTrungTu/FinalMAT3514", "FinalMAT3514");

        // Init Project name: First Project
        Project first = new Project(new Tittle("First Project", "Nothing"));

        // Set repository
        first.setRepository(repo);

        // Assignments for task
        ArrayList<People> peopleArrayList = new ArrayList<>();
        peopleArrayList.add(new People("htmlexe", "21000245"));
        peopleArrayList.add(new People("Mlisfi", "21000708"));
        peopleArrayList.add(new People("Grizmo", "23000709"));

        // init task 1
        Task task = new Task("Task1", "Nothing for task1", new Date(26,10,2023), peopleArrayList);

        Task task1 = new Task("Task2", "This is task2", new Date(31,12,2023), new ArrayList<>(new People[]{new People("Grizmo", "21000709")}));
        // Add task for project
        first.addTask(task);
        first.addTask(task1);

        // Create project
        manager.createNewProject(first);

        // Init Project name: First Project
        Project second = new Project(new Tittle("Second Project", "Seconds"));

        // Repository of project
        Repository repository = new Repository("https://github.com/PhuongTrungTu/FinalMAT3515", "Project Manager");

        // Set repository
        second.setRepository(repository);

        // init task 1
        Task task2 = new Task("Task21", "Task 1 for second project", new Date(26,10,2023), new ArrayList<>(new People[]{new People("htmlexe", "21000245")}));

        Task task3 = new Task("Task22", "This is task 2 of second project", new Date(31,2,2025), new ArrayList<>(new People[]{new People("Mlisfi", "21000708")}));
        // Add task for project
        second.addTask(task2);
        second.addTask(task3);

        manager.createNewProject(second);

//        manager.deleteProject(1);

        manager.deleteProject(1);

        manager.writeData("Data/");


        manager.display();

    }
>>>>>>> c7a28f21b4fb34dd50e8da65932680bb6d347f26
}