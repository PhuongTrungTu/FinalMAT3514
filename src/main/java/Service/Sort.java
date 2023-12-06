package Service;

import Components.Task;
import Model.ArrayList;

import java.util.Arrays;
import java.util.Comparator;

public class Sort{

	public static ArrayList<Task> sort(ArrayList<Task> tasks){
		Task[] data = tasks.toArray();
		Arrays.sort(data);
		return new ArrayList<>(data);
	}

	public static ArrayList<Task> sortByDay(ArrayList<Task> tasks) {
		Task[] data = tasks.toArray();
		Arrays.sort(data, Comparator.comparing(Task :: getEndDay));
		return new ArrayList<>(data);
	}

	public static ArrayList<Task> sortByTime(ArrayList<Task> tasks) {
		Task[] data = tasks.toArray();
		Arrays.sort(data, Comparator.comparingInt(Task::getTime));
		return new ArrayList<>(data);
	}

	public static ArrayList<Task> sortByDegree(ArrayList<Task> tasks){
		Task[] data = tasks.toArray();
		Arrays.sort(data, Comparator.comparing(Task:: getDegree));
		return new ArrayList<>(data);
	}
}
