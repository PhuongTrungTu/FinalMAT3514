/**
 * The ProjectManager class manages projects in a project management system.
 * It includes methods for creating, deleting, and retrieving projects.
 *
 * @author Grizmo
 * @version 1.0
 */
package com.raven.Components;

import com.raven.Service.Title;
import com.raven.frame.ArrayList;
import com.raven.frame.HashMap;

public class ProjectManager {
	private static ProjectManager instance;
	protected ArrayList<Project> projects = new ArrayList<>();

	/**
	 * Private constructor to ensure a singleton pattern.
	 */
	private ProjectManager() {

	}

	/**
	 * Gets the instance of the ProjectManager class.
	 *
	 * @return The instance of the ProjectManager class.
	 */
	public static ProjectManager getInstance() {
		if (instance == null){
			instance = new ProjectManager();
		}
		return instance;
	}

	/**
	 * Creates a new project and adds it to the list of projects.
	 *
	 * @param project The project to be added.
	 */
	public void createNewProject(Project project) {
		projects.add(project);
	}

	/**
	 * Creates a new project with the specified title and adds it to the list of projects.
	 *
	 * @param title The title of the new project.
	 */
	public void createNewProject(Title title) {
		Project project = new Project(title);
		projects.add(project);
	}

	/**
	 * Deletes a project with the specified title.
	 *
	 * @param title The title of the project to be deleted.
	 */
	public void deleteProject(String title) {
		for (int i = 0; i < projects.size(); i++){
			if (projects.get(i).getTittle().getTitle().equalsIgnoreCase(title)){
				projects.remove(i);
			}
		}
	}

	/**
	 * Deletes a project at the specified index.
	 *
	 * @param index The index of the project to be deleted.
	 */
	public void deleteProject(int index) {
		projects.remove(index);
	}

	/**
	 * Gets a project at the specified index.
	 *
	 * @param index The index of the project to be retrieved.
	 * @return The project at the specified index.
	 */
	public Project getProject(int index) {
		return projects.get(index);
	}

	/**
	 * Gets a project with the specified title.
	 *
	 * @param title The title of the project to be retrieved.
	 * @return The project with the specified title.
	 */
	public Project getProject(Title title) {
		for (int i = 0; i < projects.size(); i++){
			if (projects.get(i).getTittle().equals(title)){
				return projects.get(i);
			}
		}
		return null;
	}

	/**
	 * Gets a project with the specified title (case-insensitive).
	 *
	 * @param title The title of the project to be retrieved.
	 * @return The project with the specified title.
	 */
	public Project getProject(String title) {
		for (int i = 0; i < projects.size(); i++){
			if (projects.get(i).getTittle().getTitle().equalsIgnoreCase(title)){
				return projects.get(i);
			}
		}
		return null;
	}

	/**
	 * Gets the list of projects.
	 *
	 * @return The list of projects.
	 */

	protected ArrayList<Project> projectArrayList() {
		return projects;
	}

	/**
	 * Returns a string representation of the ProjectManager class.
	 *
	 * @return A string representation of the ProjectManager class.
	 */
	@Override
	public String toString() {
		return new HashMap<>().toString();
	}

	/**
	 * Writes data to a specified path. (TODO: Implement this method)
	 *
	 * @param path The path to write data to.
	 */
	public void writeData(String path) {
		/*TODO*/
	}

	/**
	 * Displays information about each project in the ProjectManager.
	 */
	public void display() {
		for (int i = 0; i < projects.size(); i++){
			System.out.println("Project " + (i + 1) + ": " + projects.get(i).getTittle().getTitle());
			System.out.println("Number task: " + projects.get(i).tasks().size());
			projects.get(i).display();
			System.out.println();
		}
	}
}
