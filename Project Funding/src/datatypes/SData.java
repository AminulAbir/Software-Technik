package datatypes;

/**
 * Contains the necessary informations about a Supporter.
 * 
 * @author swe.uni-due.de
 *
 */
public class SData {
	private String PaymentInformation;
	private String email;

	public SData(String email, String PaymentInformation) {
		this.email = email;
		this.PaymentInformation = PaymentInformation;
	}


	public String getEmail() {
		return email;
	}
	
	public String getPaymentInformation() {
		return PaymentInformation;
	}
}
