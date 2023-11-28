import Components.ProjectManager;

public class App {
    private final ProjectManager app;
    private static App instance;
    private App (){
        app = ProjectManager.getInstance();
    }

    public static App getInstance(){
        if (instance == null){
            instance = new App();
        }
        return instance;
    }

    public void display(){
        System.out.println(app.toString());
    }
}
