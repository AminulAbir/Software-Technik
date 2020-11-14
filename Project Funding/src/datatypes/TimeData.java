package datatypes;

/**
 * Contains the Date informations for the project.
 * 
 * @author swe.uni-due.de
 *
 */
public class TimeData {

	private int day;
	private int month;
	private int year; 

	public TimeData(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}

}
