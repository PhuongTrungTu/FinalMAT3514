package Service;

import java.util.Objects;

public class Major implements Comparable<Major> {
	private final String MAJOR;
	private final int LEVEL;
	private static final String[] majors = { "All", "Back end", "Front end", "Test", "Design", "Manager" };
	public static final int MAX = majors.length;

	public Major() {
		this.LEVEL = 0;
		this.MAJOR = "All";
	}

	public Major(int level) {
		if (level < 0 || level >= MAX) {
			level = 0;
		}
		this.LEVEL = level;
		MAJOR = majors[level];
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Major major)) {
			return false;
		}
		return getLEVEL() == major.getLEVEL();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMAJOR(), getLEVEL());
	}
}
