package Service;

import Components.Task;
import Model.ArrayList;

import java.util.Arrays;
import java.util.Comparator;

public class Sort{
	Task[] data;
	public Sort(ArrayList<Task> tasks){
		data = tasks.toArray();

	}

	public ArrayList<Task> sort(){
		Arrays.sort(data);
		return new ArrayList<>(data);
	}

	public ArrayList<Task> sortByDay() {
		Arrays.sort(data, Comparator.comparing(Task :: getEndDay));
		return new ArrayList<>(data);
	}

	public ArrayList<Task> sortByTime() {
		Arrays.sort(data, Comparator.comparingInt(Task::getTime));
		return new ArrayList<>(data);
	}

	public ArrayList<Task> sortByDegree(){
		Arrays.sort(data, Comparator.comparing(Task:: getDegree));
		return new ArrayList<>(data);
	}
}
