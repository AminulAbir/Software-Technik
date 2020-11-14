package interfaces;

import datatypes.PSData;

/**
 * Interface that provides all methods for the interaction with the Project Starter.
 * 
 * @author swe.uni-due.de
 *
 */
public interface PSCmds {

	public void createProject(PSData PSdata, String EndDate, String PName, String Pdescription, String ListOfRewards,
			String FundingLimit);

}
