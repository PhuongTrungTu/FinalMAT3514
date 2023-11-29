<<<<<<< HEAD
package Data;

import Components.*;
import Service.components.*;
import Model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class ReadData {

	public static void read(String filePath, ProjectManager manager) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(new File(filePath));

			JsonNode projectList = rootNode.get("List");

			for (JsonNode projectNode : projectList) {
				Project project = createProjectFromJson(projectNode);
				manager.createNewProject(project);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Project createProjectFromJson(JsonNode projectNode) {
		Tittle tittle = new Tittle(projectNode.path("tittle").path("tittle").asText(),
		                           projectNode.path("tittle").path("description").asText());

		Repository repository = new Repository(projectNode.path("repository").path("link").asText(),
		                                       projectNode.path("repository").path("tittle").asText());

		Project project = new Project();
		project.setTittle(tittle);
		project.setRepository(repository);

		JsonNode taskList = projectNode.path("tasks").path("List");
		for (JsonNode taskNode : taskList) {
			Task task = createTaskFromJson(taskNode);
			project.addTask(task);
		}

		return project;
	}

	private static Task createTaskFromJson(JsonNode taskNode) {
		String title = taskNode.path("tittle").asText();
		String description = taskNode.path("description").asText();

		Date endDay = new Date(taskNode.path("endDay").path("day").asInt(),
		                       taskNode.path("endDay").path("month").asInt(),
		                       taskNode.path("endDay").path("year").asInt());

		ArrayList<People> assignments = new ArrayList<>();
		JsonNode assignmentList = taskNode.path("assignments").path("List");
		for (JsonNode assignmentNode : assignmentList) {
			People people = new People(assignmentNode.path("name").asText(), assignmentNode.path("id").asText());
			assignments.add(people);
		}

		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setEndDay(endDay);
		task.setAssignments(assignments);

		return task;
	}
}
=======
package Data;

import Components.*;
import Service.components.*;
import Model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class ReadData {

	public static void read(String filePath, ProjectManager manager) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(new File(filePath));

			JsonNode projectList = rootNode.get("List");

			for (JsonNode projectNode : projectList) {
				Project project = createProjectFromJson(projectNode);
				manager.createNewProject(project);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Project createProjectFromJson(JsonNode projectNode) {
		Tittle tittle = new Tittle(projectNode.path("tittle").path("tittle").asText(),
		                           projectNode.path("tittle").path("description").asText());

		Repository repository = new Repository(projectNode.path("repository").path("link").asText(),
		                                       projectNode.path("repository").path("tittle").asText());

		Project project = new Project();
		project.setTittle(tittle);
		project.setRepository(repository);

		JsonNode taskList = projectNode.path("tasks").path("List");
		for (JsonNode taskNode : taskList) {
			Task task = createTaskFromJson(taskNode);
			project.addTask(task);
		}

		return project;
	}

	private static Task createTaskFromJson(JsonNode taskNode) {
		String title = taskNode.path("tittle").asText();
		String description = taskNode.path("description").asText();

		Date endDay = new Date(taskNode.path("endDay").path("day").asInt(),
		                       taskNode.path("endDay").path("month").asInt(),
		                       taskNode.path("endDay").path("year").asInt());

		ArrayList<People> assignments = new ArrayList<>();
		JsonNode assignmentList = taskNode.path("assignments").path("List");
		for (JsonNode assignmentNode : assignmentList) {
			People people = new People(assignmentNode.path("name").asText(), assignmentNode.path("id").asText());
			assignments.add(people);
		}

		Task task = new Task();
		task.setTitle(title);
		task.setDescription(description);
		task.setEndDay(endDay);
		task.setAssignments(assignments);

		return task;
	}
}
>>>>>>> c7a28f21b4fb34dd50e8da65932680bb6d347f26
