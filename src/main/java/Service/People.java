package Service;

import Components.Task;
import Model.ArrayList;
import Model.HashMap;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a person with a name, ID, majors, and assigned tasks.
 */
public class People {
    private String name;
    private String id;
    private ArrayList<Major> majors = new ArrayList<>();
    private ArrayList<Task> assignedTask = new ArrayList<>();

    /**
     * Constructs a People object with the specified name and ID.
     *
     * @param name The name of the person.
     * @param id   The ID of the person.
     */
    public People(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Constructs a People object with the specified name, ID, and list of majors.
     *
     * @param name   The name of the person.
     * @param id     The ID of the person.
     * @param majors The list of majors associated with the person.
     */
    public People(String name, String id, ArrayList<Major> majors) {
        this.name = name;
        this.id = id;
        this.majors = majors;
    }

    /**
     * Constructs a People object with the specified name, ID, and major.
     *
     * @param name  The name of the person.
     * @param id    The ID of the person.
     * @param major The major associated with the person.
     */
    public People(String name, String id, Major major) {
        this.name = name;
        this.id = id;
        this.majors.add(major);
    }

    /**
     * Gets the list of majors associated with the person.
     *
     * @return The list of majors.
     */
    public ArrayList<Major> getMajors() {
        return majors;
    }

    /**
     * Sets the list of majors associated with the person.
     *
     * @param majors The new list of majors.
     */
    public void setMajors(ArrayList<Major> majors) {
        this.majors = majors;
    }

    /**
     * Adds a major to the list of majors associated with the person.
     *
     * @param major The major to add.
     */
    public void addMajor(Major major) {
        if (major.getMAJOR().equals("All")) {
            majors = new ArrayList<>();
        }
        majors.add(major);
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The new name for the person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the person.
     *
     * @return The ID of the person.
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the person.
     *
     * @param id The new ID for the person.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the person using the mapping.
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return mapping().toString();
    }

    /**
     * Maps the name and ID of the person to a HashMap.
     *
     * @return A HashMap containing the name and ID as keys with their respective values.
     */
    public HashMap<String, String> mapping() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Name", name);
        map.put("Id", id);
        return map;
    }

    /**
     * Assigns a task to the person.
     *
     * @param task The task to be assigned.
     */
    public void assign(Task task) {
        assignedTask.add(task);
    }

    /**
     * Removes a task from the assigned tasks of the person.
     *
     * @param task The task to be removed.
     */
    public void removeTask(Task task) {
        assignedTask.remove(task);
    }

    /**
     * Checks if the person has any assigned tasks.
     *
     * @return {@code true} if the person has no assigned tasks, {@code false} otherwise.
     */
    public boolean hasAssignedTask() {
        return assignedTask.isEmpty();
    }
}
