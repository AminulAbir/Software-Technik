package testing;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import datatypes.PSData;
import datatypes.SData;
import datatypes.TimeData;
import dbadapter.Configuration;
import dbadapter.DBFacade;
import dbadapter.Donate;
import dbadapter.Project;

/**
 * Testing our DBFacade.
 * 
 * @author swe.uni-due.de
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DBFacade.class)
public class DBFacadeTest {
	private Connection stubCon;
	private String sqlSelect;
	private String sqlCreate;
	private String sqlSelectB;
	private PreparedStatement ps;
	private PreparedStatement psSelectB;
	private PreparedStatement psCreate;
	private ResultSet rs;
	private ResultSet brs;
	private ResultSet crs;

	/**
	 * Preparing classes with static methods
	 */
	@Before
	public void setUp() {
		PowerMockito.mockStatic(DriverManager.class);

		// Declare necessary SQL queries
		sqlSelect = "SELECT * FROM Project WHERE PName = ?";
		sqlCreate = "INSERT INTO Project (Email, PaymentInformation, EndDate, PName, Pdescription, ListOfRewards, FundingLimit) VALUES (?,?,?,?,?,?,?)";
		sqlSelectB = "SELECT * FROM Donate WHERE Pid = ?";


		// Mock return values
		ps = mock(PreparedStatement.class);
		psSelectB = mock(PreparedStatement.class);
		psCreate = mock(PreparedStatement.class);
		rs = mock(ResultSet.class);
		brs = mock(ResultSet.class);
		crs = mock(ResultSet.class);

		try {
			// Setting up return values for connection and statements
			stubCon = mock(Connection.class);
			PowerMockito.when(DriverManager.getConnection(
					"jdbc:" + Configuration.getType() + "://" + Configuration.getServer() + ":"
							+ Configuration.getPort() + "/" + Configuration.getDatabase(),
					Configuration.getUser(), Configuration.getPassword())).thenReturn(stubCon);

			when(stubCon.prepareStatement(sqlSelect)).thenReturn(ps);
			when(stubCon.prepareStatement(sqlCreate)).thenReturn(psCreate);
			when(stubCon.prepareStatement(sqlSelectB)).thenReturn(psSelectB);
			when(ps.executeQuery()).thenReturn(rs);
			when(psSelectB.executeQuery()).thenReturn(brs);
			when(psCreate.executeQuery()).thenReturn(crs);

			// Setting up return values for methods
			when(rs.next()).thenReturn(true).thenReturn(false);
			when(rs.getInt(1)).thenReturn(0);
			when(rs.getTimestamp(2)).thenReturn(Timestamp.valueOf("2020-11-04 00:00:00"));
			when(rs.getString(3)).thenReturn("pemail@amail.com");
			when(rs.getString(4)).thenReturn("DE23XXXXX");
			when(brs.getString(5)).thenReturn("Hello");
			when(brs.getString(6)).thenReturn("Hello World");
			when(brs.getString(7)).thenReturn("Nothing");
			when(rs.getDouble(8)).thenReturn(4.5);			
			when(brs.getTimestamp(9)).thenReturn(Timestamp.valueOf("2020-12-04 00:00:00"));
			
			// Setting up return values for methods
						when(crs.next()).thenReturn(true).thenReturn(false);
						when(crs.getString(1)).thenReturn("pemail@amail.com");
						when(crs.getString(2)).thenReturn("DE23XXXXX");
						when(crs.getTimestamp(3)).thenReturn(Timestamp.valueOf("2020-11-04 00:00:00"));
						when(crs.getString(4)).thenReturn("Hello");
						when(crs.getString(5)).thenReturn("Hello World");
						when(crs.getString(6)).thenReturn("Nothing");
						when(crs.getDouble(7)).thenReturn(4.5);			
						
						// Setting up return values for methods
						when(brs.next()).thenReturn(true).thenReturn(false);
						when(rs.getInt(1)).thenReturn(0);
						when(brs.getString(2)).thenReturn("semail@amail.com");
						when(brs.getString(3)).thenReturn("DE32XXXXX");
						when(brs.getDouble(4)).thenReturn(5.5);			
						

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Testing getProject with non-empty results.
	 * @throws SQLException 
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testGetProject() throws SQLException {

		
		ArrayList<Project> p = DBFacade.getInstance().getProject("Test1");

		// Verify how often a method has been called
		try {
			verify(stubCon, times(1)).prepareStatement(sqlSelect);
			
			verify(ps, times(1)).executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Verify return values
		assertTrue(p.size() == 1);
		p.get(0);
		assertTrue(Project.getPName().equals("Test1"));
		
		assertTrue(p.get(0).getId() == 0);
		assertTrue(p.get(0).getDonate().size() == 1);
		
		assertTrue(p.get(0).getPdescription().equals("Test1"));
		assertTrue(p.get(0).getEndDate().equals("Test1"));
		assertTrue(p.get(0).getListOfRewards().equals("Test1"));
		// ...
		
		// ...

	}
	@Test
	public void testCreateProject()
	{
		DBFacade.getInstance().createProject(new PSData("test1@test.com","test1"), new TimeData(0,0,0), "Test1", "Something", "New", "4.5");
		try {
			verify(stubCon, times(1)).prepareStatement(sqlCreate);
			verify(psCreate, times(1)).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void testDoanateForProject() throws SQLException {


		Donate don = DBFacade.getInstance().addSupporter(0, new SData("test","DE89XXX"), 5.5);

		// Verify how often a method has been called
		try {
			verify(stubCon, times(1)).prepareStatement(sqlSelectB);
			verify(psSelectB, times(1)).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
}
	
	@Test
	public void testMarkProject()
	{
		String PName =  "testMarkProject";
		DBFacade.getInstance().markProject();
		
	}
}
