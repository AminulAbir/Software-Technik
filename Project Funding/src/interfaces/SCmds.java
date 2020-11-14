package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import datatypes.SData;
import dbadapter.Donate;
import dbadapter.Project;

/**
 * Interface that provides all method to interact with a Supporter.
 * 
 * @author swe.uni-due.de
 *
 */
public interface SCmds {

	public ArrayList<Project> getProject(String PName);
	public Donate createDonate(String Pid, SData Sdata, String amount);
	public Donate addSupporter(int Pid, SData Sdata, double amount) throws SQLException;
}
