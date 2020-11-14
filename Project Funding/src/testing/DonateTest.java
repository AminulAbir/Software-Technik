package testing;

import org.junit.Test;
import datatypes.SData;
import dbadapter.Donate;

public class DonateTest  {
	private Donate don;
	
	@Test(expected = IllegalArgumentException.class)
    public void constructorIllegalArgumentTest(){
		String test = "test";
        don = new Donate(0, new SData("test", "test"),4.5);
    }
	
	//Test if the deadline is before today
    public void constructorExceptionTest(){
		String test = "test";
		don = new Donate(0, new SData("test", "test"),4.5);
    }
}