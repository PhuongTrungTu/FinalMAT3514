package Service;

import java.util.Objects;

/**
 * Represents a Major with a specific level.
 */
public class Major implements Comparable<Major> {
	private static final String[] majors = { "All" , "Back end" , "Front end" , "Test" , "Design" , "Manager" };
	public static final int MAX = majors.length;
	private final String MAJOR;
	private final int LEVEL;

	/**
	 * Default constructor for Major. Initializes level as 0 and major as "All".
	 */
	public Major() {
		this.LEVEL = 0;
		this.MAJOR = "All";
	}

	/**
	 * Constructs a Major with the specified level.
	 *
	 * @param level The level of the major.
	 */
	public Major(int level) {
		if (level < 0 || level >= MAX){
			level = 0;
		}
		this.LEVEL = level;
		MAJOR = majors[level];
	}

	/**
	 * Gets the name of the major.
	 *
	 * @return The name of the major.
	 */
	public String getMAJOR() {
		return MAJOR;
	}

	/**
	 * Gets the level of the major.
	 *
	 * @return The level of the major.
	 */
	public int getLEVEL() {
		return LEVEL;
	}

	/**
	 * Compares this Major to another Major based on their levels.
	 *
	 * @param o The Major to compare to.
	 * @return A negative integer, zero, or a positive integer as this Major is less than, equal to, or greater than the specified Major.
	 */
	@Override
	public int compareTo(Major o) {
		return LEVEL - o.LEVEL;
	}

	/**
	 * Checks if this Major is equal to another object.
	 *
	 * @param o The object to compare to.
	 * @return {@code true} if the objects are equal, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (! (o instanceof Major major)){
			return false;
		}
		return getLEVEL() == major.getLEVEL();
	}

	/**
	 * Generates a hash code for this Major.
	 *
	 * @return The hash code for this Major.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getMAJOR() , getLEVEL());
	}
}
