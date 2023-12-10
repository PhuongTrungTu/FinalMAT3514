package Service;

import Components.Task;
import Model.ArrayList;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Utility class for sorting tasks based on different criteria.
 */
public class Sort {

	/**
	 * Creates a copy of the ArrayList of tasks as an array.
	 *
	 * @param tasks The ArrayList of tasks to copy.
	 * @return An array containing the tasks from the ArrayList.
	 */
	private static Task[] copy(ArrayList<Task> tasks) {
		Task[] result = new Task[tasks.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = tasks.get(i);
		}

		return result;
	}

	/**
	 * Sorts tasks based on their natural order (by end date).
	 *
	 * @param tasks The ArrayList of tasks to be sorted.
	 * @return A new ArrayList containing the sorted tasks.
	 */
	public static ArrayList<Task> sort(ArrayList<Task> tasks) {
		Task[] data = copy(tasks);
		Arrays.sort(data);
		return new ArrayList<>(data);
	}

	/**
	 * Sorts tasks by end date.
	 *
	 * @param tasks The ArrayList of tasks to be sorted.
	 * @return A new ArrayList containing the sorted tasks.
	 */
	public static ArrayList<Task> sortByDay(ArrayList<Task> tasks) {
		Task[] data = copy(tasks);
		Arrays.sort(data, Comparator.comparing(Task::getEndDay));
		return new ArrayList<>(data);
	}

	/**
	 * Sorts tasks by time.
	 *
	 * @param tasks The ArrayList of tasks to be sorted.
	 * @return A new ArrayList containing the sorted tasks.
	 */
	public static ArrayList<Task> sortByTime(ArrayList<Task> tasks) {
		Task[] data = copy(tasks);
		Arrays.sort(data, Comparator.comparingInt(Task::getTime));
		return new ArrayList<>(data);
	}

	/**
	 * Sorts tasks by degree of importance.
	 *
	 * @param tasks The ArrayList of tasks to be sorted.
	 * @return A new ArrayList containing the sorted tasks.
	 */
	public static ArrayList<Task> sortByDegree(ArrayList<Task> tasks) {
		Task[] data = copy(tasks);
		Arrays.sort(data, Comparator.comparing(Task::getDegree));
		return new ArrayList<>(data);
	}

	/**
	 * Sorts tasks by major label.
	 *
	 * @param tasks The ArrayList of tasks to be sorted.
	 * @return A new ArrayList containing the sorted tasks.
	 */
	public static ArrayList<Task> sortByMajor(ArrayList<Task> tasks) {
		Task[] data = copy(tasks);
		Arrays.sort(data, Comparator.comparing(Task::getMajorLabel));
		return new ArrayList<>(data);
	}
}
