package timer;

import application.PFApplication;

/**
 * Timer class to call the method checkPayment in the application. Main method
 * can be executed in a scheduled way.
 * 
 * @author swe.uni-due.de
 *
 */
public class Timer {

	public static void main(String[] args) {
		PFApplication pfApp = new PFApplication();
		pfApp.checkEndDate();
		System.out
				.println("End Date is reached!");
	}
}
