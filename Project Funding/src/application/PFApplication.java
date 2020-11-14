package application;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import datatypes.PSData;
import datatypes.SData;
import dbadapter.DBFacade;
import dbadapter.Donate;
import dbadapter.Project;
import interfaces.PSCmds;
import interfaces.SCmds;


/**
 * This class contains the VRApplication which acts as the interface between all
 * components.
 * 
 * @author swe.uni-due.de
 *
 */
public class PFApplication implements PSCmds, SCmds {

	private static PFApplication instance;

	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static PFApplication getInstance() {
		if (instance == null) {
			instance = new PFApplication();
		}

		return instance;
	}

	/**
	 * Calls DBFacace method to retrieve all projects fitting to the given
	 * parameters.
	 * 
	 * @param PName
	 * @param Pstatus
	 * @return
	 */
	public ArrayList<Project> getProject(String PName){
		ArrayList<Project> result = null;

		// Parse string attributes to correct datatype
		try {
			String PNameSQL = (PName);
			result = DBFacade.getInstance().getProject(PNameSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Forwards a new project to the database.
	 * 
	 * @param PSdata
	 * @param EndDate
	 * @param PName
	 * @param Pdescription
	 * @param ListOfRewards
	 * @param FundingLimit
	 */
	public void createProject(PSData PSdata, String EndDate, String PName, String Pdescription, String ListOfRewards,
			String FundingLimit) {

		// Parse inputs to correct datatypes
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = dateFormat.parse(EndDate);
			long time = date.getTime();
			Timestamp EndDateSQL = new Timestamp(time);
			String PNameSQL = (PName);
			String PdescriptionSQL = (Pdescription);
			String ListOfRewardsSQL = (ListOfRewards);
			String FundingLimitSQL = (FundingLimit);
			DBFacade.getInstance().createProject(PSdata, EndDateSQL, PNameSQL, PdescriptionSQL, ListOfRewardsSQL, FundingLimitSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkEndDate() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Forwards a Donate request to the database and waits for the new Donate
	 * This will be returned to the GUI after.
	 * 
	 * @param Pid
	 * @param Sdata
	 * @param Amount
	 * @return
	 */
	public Donate createDonate(String Pid, SData Sdata, String amount) {

		// pre: ho−>one(h:HolidayOffer|h.id = hid)
		assert preAvailable(Integer.parseInt(Pid)) : "Precondition not satisfied";

		// Create result object
		Donate okfail = null;

		// Parse inputs to correct datatypes
		try {
			int PidSQL = Integer.parseInt(Pid);
			int amountSQL = Integer.parseInt(amount);
			okfail = DBFacade.getInstance().addSupporter(PidSQL, Sdata, amountSQL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return okfail;
	}

	/**
	 * Initiates for marking Projects.
	 */
	public void checkPayment() {
		DBFacade.getInstance().markProject();
	}

	/**
	 * Checks precondition holidayoffer exists
	 * 
	 * @param hid
	 * @return
	 */
	private boolean preAvailable(int Pid) {
		return DBFacade.getInstance().checkProjectById(Pid);
	}

}


