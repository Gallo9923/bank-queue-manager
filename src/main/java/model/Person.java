package model;

public class Person implements Comparable<Person> {

	// Priorities
	public static final int HIGH = 1;
	public static final int MEDIUM = 2;
	public static final int LOW = 3;
	public static final int NONE = 4;

	private String name;
	private int identification;
	private int priority;
	
	private boolean inLine;
	

	public Person(int identification, String name, int priority) {
		this.name = name;
		this.identification = identification;
		this.priority = priority;
		this.inLine = false;
		
	}

	public String getName() {
		return name;
	}

	public int getIdentification() {
		return identification;
	}
	
	public int getPriority() {
		return priority;
	}

	public boolean isInLine() {
		return inLine;
	}

	public void setIsInLine(boolean isInLine) {
		this.inLine = isInLine;
	}

	@Override
	public int compareTo(Person p2) {

		int priorityC2 = p2.getPriority();
		int result = 0;

		if (priority - priorityC2 < 0) {
			result = -1;
		} else if (priority - priorityC2 > 0) {
			result = 1;
		}

		return result;
	}

	@Override
	public int hashCode() {
		return this.identification;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj instanceof Person) {
			Person c2 = (Person) obj;

			if (this.identification - c2.identification == 0) {
				result = true;
			}
		}

		return result;
	}


}
