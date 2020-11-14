package dbadapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import datatypes.PSData;
import datatypes.PType;
import datatypes.SData;
import datatypes.TimeData;
import interfaces.IProject;
import dbadapter.Donate;

/**
 * Class which acts as the connector between application and database. Creates
 * Java objects from SQL returns. Exceptions thrown in this class will be
 * Cached with a 500 error page.
 * 
 * @author swe.uni-due.de
 *
 */
public class DBFacade implements IProject {
	private static DBFacade instance;

	/**
	 * Contructor which loads the corresponding driver for the chosen database
	 * type
	 */
	@SuppressWarnings("deprecation")
	private DBFacade() {
		try {
			Class.forName("com." + Configuration.getType() + ".jdbc.Driver")
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Implementation of the Singleton pattern.
	 * 
	 * @return
	 */
	public static DBFacade getInstance() {
		if (instance == null) {
			instance = new DBFacade();
		}

		return instance;
	}

	/**
	 * Function that returns all appropriate projects from the database.
	 * 
	 * @param arrivalTime
	 *            compared with existing bookings and start time.
	 * @param departureTime
	 *            compared with existing bookings and start time.
	 * @param persons
	 *            compared with capacity.
	 * @return Arraylist of all offer objects.
	 * @throws SQLException 
	 */
	public ArrayList<Project> getProject(
			String PName 
			) throws SQLException {
		ArrayList<Project> result = new ArrayList<Project>();

		// Declare the necessary SQL queries.
		String sqlSelect = "SELECT * FROM Project WHERE PName = ?";
		String sqlSelectB = "SELECT * FROM Donate WHERE Pid = ?";

		// Query all projects that fits to the given criteria.
		try (Connection connection = DriverManager.getConnection(
				"jdbc:" + Configuration.getType() + "://"
						+ Configuration.getServer() + ":"
						+ Configuration.getPort() + "/"
						+ Configuration.getDatabase(), Configuration.getUser(),
				Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlSelect);
					PreparedStatement psSelectB = connection.prepareStatement(sqlSelectB)) {
				ps.setString(1, PName);
				
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Project temp = new Project(rs.getInt(1),
								rs.getTimestamp(2), new PType(),
								new PSData(rs.getString(3), rs.getString(4)), rs.getInt(5),
								rs.getDouble(6));
						psSelectB.setInt(1, temp.getId());
						// Query all Donates for the project to check if its
						// available.
						try (ResultSet brs = psSelectB.executeQuery()) {
							ArrayList<Donate> donate = new ArrayList<Donate>();
							while (brs.next()) {
								donate.add(new Donate(brs.getInt(1),
										new SData(brs.getString(2), brs
												.getString(3)), brs
												.getDouble(4)));
								temp.setDonate(donate);
							}
							
						
						if (temp.available(PName))
							result.add(temp);
						}
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;}
	}

	/**
	 * Inserts a new offer in the database.
	 * 
	 * @param startTime
	 * @param endTime
	 * @param address
	 * @param capacity
	 * @param FundingLimit
	 */
	
	public void createProject(PSData pSdata, Time timeData, String pNameSQL, String pdescriptionSQL,
			String listOfRewardsSQL, String fundingLimitSQL) {

		// Declare SQL query to insert offer.
		String sqlInsert = "INSERT INTO Project (Email, PaymentInformation, EndDate, PName, Pdescription, ListOfRewards, FundingLimit) VALUES (?,?,?,?,?,?,?)";
				

		// Insert offer into database.
		try (Connection connection = DriverManager.getConnection(
				"jdbc:" + Configuration.getType() + "://"
						+ Configuration.getServer() + ":"
						+ Configuration.getPort() + "/"
						+ Configuration.getDatabase(), Configuration.getUser(),
				Configuration.getPassword())) {

			try (PreparedStatement ps = connection.prepareStatement(sqlInsert)) {
				ps.setString(1, PSData.getEmail());
				ps.setString(2, PSData.getPaymentInformation());
				ps.setTime(3, timeData);
				ps.setString(4, Project.getPName());
				ps.setString(5, Project.getPdescription());
				ps.setString(6, Project.getListOfRewards());
				ps.setDouble(6, Project.getFundingLimit());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
}
	/**
	 * Inserts a Donate into the database 
	 * 
	 * 
	 * @return new donation object if available or null if not available
	 * @throws SQLException 
	 */
	public Donate addSupporter(int Pid, SData Sdata, double amount) throws SQLException {
		Project p = null;
		ArrayList<Donate> donate = new ArrayList<Donate>();
		Donate donates = null;

		// Declare necessary SQL queries.
		String sqlSelectP = "SELECT * FROM Project WHERE Pid=?";
		String sqlInsertDonate = "INSERT INTO Donate (Pid, SEmail, SPaymentInformation, amount) VALUES (?,?,?,?)";
		String sqlSelectB = "SELECT * FROM Donate WHERE Pid=?";

		// Get donated project
		try (Connection connection = DriverManager.getConnection(
				"jdbc:" + Configuration.getType() + "://"
						+ Configuration.getServer() + ":"
						+ Configuration.getPort() + "/"
						+ Configuration.getDatabase(), Configuration.getUser(),
				Configuration.getPassword())) {
			try (PreparedStatement psSelect = connection
					.prepareStatement(sqlSelectP);
					PreparedStatement psSelectB = connection
							.prepareStatement(sqlSelectB);
					PreparedStatement psInsert = connection.prepareStatement(
							sqlInsertDonate,
							PreparedStatement.RETURN_GENERATED_KEYS)) {
				psSelect.setInt(1, Pid);
				try (ResultSet hors = psSelect.executeQuery()) {
					if (hors.next()) {
						p = new Project(hors.getInt(1),
								hors.getTimestamp(2), new PType(),
								new PSData(hors.getString(3), hors.getString(4)), hors.getInt(5),
								hors.getDouble(6));
					}
				}
				

				// Check if donation is still available
				if (p != null) {
					psSelectB.setInt(1, Pid);
					try (ResultSet brs = psSelectB.executeQuery()) {
						while (brs.next()) {
							donate.add(new Donate(brs.getInt(1),
									new SData(brs.getString(2), brs
											.getString(3)), brs
											.getDouble(4)));
						}
						p.setDonate(donate);
					}
				}
			}
		}
		return donates;
	}
	
	/**
	 * Checks if Project with given id exists.
	 * 
	 * @param Pid
	 * @return
	 */
	public boolean checkProjectById(int Pid) {

		// Declare necessary SQL query.
		String queryP = "SELECT * FROM Project WHERE Pid=?";

		// query data.
		try (Connection connection = DriverManager.getConnection(
				"jdbc:" + Configuration.getType() + "://"
						+ Configuration.getServer() + ":"
						+ Configuration.getPort() + "/"
						+ Configuration.getDatabase(), Configuration.getUser(),
				Configuration.getPassword())) {
			try (PreparedStatement psSelect = connection
					.prepareStatement(queryP)) {
				psSelect.setInt(1, Pid);
				try (ResultSet rs = psSelect.executeQuery()) {
					return rs.next();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void markProject() {
		// Declare necessary SQL statement.
				String markSSQL= "UPDATE project p " + 
						"SET p.Pstatus = 'successful' " + 
						"WHERE p.PName IN "
						+ "(SELECT PName FROM (SELECT p.PName, p.FundingLimit, SUM(d.Amount) AS tot FROM Project p " +
						"JOIN Donate d ON p.PName = Donate.PName GROUP BY p.PName) as res "
						+ "WHERE res.FundingLimit <= res.tot) and p.EndDate > CURDATE()";
				String markFSQL= "UPDATE project p " + 
						"SET p.Pstatus = 'failed' " + 
						"WHERE p.PName IN "
						+ "(SELECT PName FROM (SELECT p.PName, p.FundingLimit, SUM(d.Amount) AS tot FROM Project p " +
						"JOIN Donate d ON p.PName = d.PName GROUP BY p.PName) as res "
						+ "WHERE res.FundingLimit > res.tot) and p.EndDate <= CURDATE()";
				// Update Database.
						try (Connection connection = DriverManager.getConnection(
								"jdbc:" + Configuration.getType() + "://"
										+ Configuration.getServer() + ":"
										+ Configuration.getPort() + "/"
										+ Configuration.getDatabase(), Configuration.getUser(),
								Configuration.getPassword())) {
							try (PreparedStatement psS = connection.prepareStatement(markSSQL);
									PreparedStatement psF = connection.prepareStatement(markFSQL)) {
								psS.executeUpdate();
								psF.executeUpdate();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
		
	}
	@Override
	public Donate createDonate(String Pid, SData Sdata, String amount) {
		// TODO Auto-generated method stub
		return null;
	}
	public void createProject(String string, String string2, String string3, String string4, String string5,
			String string6, String string7) {
		// TODO Auto-generated method stub
		
	}
	public ArrayList<Donate> addSupporter(int i, String string, String string2, double d) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void createProject(PSData pSdata, Timestamp endDateSQL, String pNameSQL, String pdescriptionSQL,
			String listOfRewardsSQL, String fundingLimitSQL) {
		// TODO Auto-generated method stub
		
	}
	public void createProject(PSData pSdata, TimeData timeData, String pNameSQL, String pdescriptionSQL,
			String listOfRewardsSQL, String fundingLimitSQL) {
		// TODO Auto-generated method stub
		
	}
}

