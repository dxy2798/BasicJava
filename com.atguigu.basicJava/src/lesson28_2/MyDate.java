package lesson28_2;

public class MyDate {

	private int month;
	private int day;
	private int year;
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public MyDate() {

	}
	public MyDate(int day, int month, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}

	public String toString() {
		return year + "年" + month + "月" + day + "日";
	}
}
