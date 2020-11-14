package dbadapter;

import java.sql.Timestamp;
import java.util.ArrayList;

import datatypes.PSData;
import datatypes.PType;
import datatypes.TimeData;

/**
 * Class representing an offer
 * 
 * @author swe.uni-due.de
 *
 */
public class Project {

	private int Pid;
	private Timestamp creationDate;
	private PType Pstatus;
	private PSData PSdata;
	private static String PName;
	private static String Pdescription;
	private static String ListOfRewards;
	private static double FundingLimit;
	private TimeData EndDate;
	private ArrayList<Donate> donate;

	public Project(int Pid, Timestamp creationDate, PType Pstatus, PSData PSdata, String PName, String Pdescription,
			String ListOfRewards, double FundingLimit, TimeData EndDate) {
		this.Pid = Pid;
		this.creationDate = creationDate;
		this.Pstatus = Pstatus;
		this.PSdata = PSdata;
		Project.PName = PName;
		Project.Pdescription = Pdescription;
		Project.ListOfRewards = ListOfRewards;
		Project.FundingLimit = FundingLimit;
		this.EndDate = EndDate;
		this.donate = new ArrayList<Donate>();
	}


	public Project(int int1, Timestamp timestamp, PType pType, PSData psData2, int int2, double double1) {
		// TODO Auto-generated constructor stub
	}


	public String toString() {
		return "Projects " + Pid + "Name: " + PName + " End Date: " + "Description: " + Pdescription + EndDate + " Funding Limit: " + FundingLimit; 
	
	}

	public int getId() {
		return Pid;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public PType getPstatus() {
		return Pstatus;
	}

	public PSData getPSdata() {
		return PSdata;
	}

	public static String getPName() {
		return PName;
	}

	public static String getPdescription() {
		return Pdescription;
	}

	public static String getListOfRewards() {
		return ListOfRewards;
	}

	public static double getFundingLimit() {
		return FundingLimit;
	}
	
	public TimeData getEndDate() {
		return EndDate;
	}


	public boolean available(String pName2) {
		// TODO Auto-generated method stub
		return false;
	}
	public ArrayList<Donate> getDonate() {
		return donate;
	}

	public void setDonate(ArrayList<Donate> donate) {
		this.donate = donate;
	}

}
