package testing;

import org.junit.Before;
import org.junit.Test;

import net.sourceforge.jwebunit.junit.WebTester;

/**
 * This class performs a system test on the supportergui using JWebUnit.
 * 
 * @author swe.uni-due.de
 *
 */
public class SupporterGUIWebTestCase {

	private WebTester tester;

	/**
	 * Create a new WebTester object that performs the test.
	 */
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost:8080/PF/");
	}

	@Test
	public void testSearchProject() {
		// Start testing for supportergui
		tester.beginAt("supportergui");

		// Check all components of the search form
		tester.assertTitleEquals("Project Funding - Search Projects");
		tester.assertFormPresent();
		
		tester.assertTextPresent("Project Name");
		tester.assertFormElementPresent("PName");
		
		tester.setTextField("PName", "test1");
		
		tester.clickButton("submit");
		
	}

}