package testing;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.sourceforge.jwebunit.junit.WebTester;

public class PSGUIWebTestCase {

	private WebTester tester;
	
	@Mock PSGUIWebTestCase gui;
	
	@Before
	public void prepare() {
		MockitoAnnotations.initMocks(this);
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/PF/");
	}
	
	@Test
	public void testCreateProject() {
		// Start testing for projectStarterGUI
		tester.beginAt("psgui");

		// Check all components of the search form
		tester.assertTitleEquals("Project Funding - Create Project");
		tester.assertFormPresent();
		tester.assertTextPresent("Project Name");
		tester.assertFormElementPresent("PName");
		
		tester.assertTextPresent("Project Description");
		tester.assertFormElementPresent("Pdescription");
		
		tester.assertTextPresent("Email");
		tester.assertFormElementPresent("Email");
		
		tester.assertTextPresent("Payment Information");
		tester.assertFormElementPresent("PaymentInformation");
		
		tester.assertTextPresent("Funding Limit");
		tester.assertFormElementPresent("FundingLimit");
		
		tester.assertTextPresent("End Date");
		tester.assertFormElementPresent("EndaDate");
		
		tester.assertTextPresent("List Of Rewards");
		tester.assertFormElementPresent("ListOfRewards");
		
		//Fill form
		tester.setTextField("PName", new Date().toString()+" test1");
		tester.setTextField("Pdescription", "test2");
		tester.setTextField("Email", "email@email.com");
		tester.setTextField("PaymentInformation", "Payment service");
		tester.setTextField("FundingLimit", "5554.48");
		tester.setTextField("EndDate", "22/01/2020");
		tester.setTextField("ListOfRewards", "lists");
		
		tester.clickButton("submit");
		tester.assertTextPresent("New Project successful stored in the database.");
	
	}
}