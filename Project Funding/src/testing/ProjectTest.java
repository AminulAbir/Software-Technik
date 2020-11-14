package testing;

import java.sql.Timestamp;

import org.junit.Test;
import datatypes.PSData;
import datatypes.PType;
import datatypes.TimeData;
import dbadapter.Project;

public class ProjectTest {
	private Project pi;
	
	//Tests if the fundinglimit is zero or less
	@Test(expected = IllegalArgumentException.class)
    public void constructorIllegalArgumentTest(){
		String test = "test";
        pi = new Project(0, new Timestamp(0), new PType(), new PSData("test", "test"),test,test,test,4.5,new TimeData(0, 0, 0));
    }
	
	//Test if the deadline is before today
    public void constructorExceptionTest(){
		String test = "test";
		pi = new Project(0, new Timestamp(0), new PType(), new PSData("test", "test"),test,test,test,4.5,new TimeData(0, 0, 0));
    }
}