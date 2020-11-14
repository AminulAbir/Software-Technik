package datatypes;

/**
 * Contains the necessary informations about a Project Starter.
 * 
 * @author swe.uni-due.de
 *
 */
public class PSData {
	private static String PaymentInformation;
	private static String email;

	public PSData(String email, String PaymentInformation) {
		PSData.email = email;
		PSData.PaymentInformation = PaymentInformation;
	}


	public static String getEmail() {
		return email;
	}
	
	public static String getPaymentInformation() {
		return PaymentInformation;
	}
}
