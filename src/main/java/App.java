import Components.ProjectManager;

import java.util.ArrayList;
class Node{
    String data;
    ArrayList<Node> neighbors = new ArrayList<>();

    public Node(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data + ":" + neighbors;
    }
}

public class App {
    public static void main(String[] args) {
        ArrayList<Node> test = new ArrayList<>();
        test.add(new Node("A"));
        test.add(new Node("B"));
        test.add(new Node("C"));
        test.add(new Node("D"));
        test.add(new Node("E"));
        test.get(0).neighbors.add(test.get(1));
        System.out.println(test);
        Node node = test.get(1);
        test.remove(node);
        for (Node value : test){
            value.neighbors.remove(node);
        }
        System.out.println(test);
    }
}
