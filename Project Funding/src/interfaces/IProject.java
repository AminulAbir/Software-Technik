package interfaces;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import datatypes.PSData;
import datatypes.SData;
import dbadapter.Donate;
import dbadapter.Project;

/**
 * Interface for DBFacade to provide all necessary database function.
 * 
 * @author swe.uni-due.de
 *
 */
public interface IProject {
	public void createProject(PSData pSdata, Timestamp endDateSQL, String pNameSQL, String pdescriptionSQL,
			String listOfRewardsSQL, String fundingLimitSQL);

	public ArrayList<Project> getProject(String PName) throws SQLException;
	public Donate createDonate(String Pid, SData Sdata, String amount);
	public Donate addSupporter(int Pid, SData Sdata, double amount) throws SQLException;
	public void markProject();
}
