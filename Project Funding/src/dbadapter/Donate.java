package dbadapter;

import datatypes.SData;

/**
 * Class representing a booking
 * 
 * @author swe.uni-due.de
 *
 */
public class Donate {

	int Pid;
	SData Sdata;
	double amount;

	public Donate(int Pid, SData Sdata, double amount) {
		super();
		this.Pid = Pid;
		this.Sdata = Sdata;
		this.amount = amount;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int Pid) {
		this.Pid = Pid;
	}

	public SData getSData() {
		return Sdata;
	}

	public void setSData(SData Sdata) {
		this.Sdata = Sdata;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
