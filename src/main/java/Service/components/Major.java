package Service.components;

public class Major implements Comparable<Major>{
	private final String MAJOR;
	private final int LEVEL;
	public Major(){
		this.LEVEL = -1;
		this.MAJOR = "All";
	}
	public Major(int level){
		this.LEVEL = level;
		if (level == 1){
			MAJOR = "Back end";
		} else if (level == 2){
			MAJOR = "Front end";
		} else if (level == 3){
			MAJOR = "Tester";
		} else if (level == 4){
			MAJOR = "Designer";
		}else if (level == 5){
			MAJOR = "Manager";
		}else{
			MAJOR = "All";
		}
	}

	public String getMAJOR() {
		return MAJOR;
	}

	public int getLEVEL() {
		return LEVEL;
	}

	public Major copy() {
		return new Major(getLEVEL());
	}

	@Override
	public int compareTo(Major o) {
		return LEVEL - o.LEVEL;
	}
}
