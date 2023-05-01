import java.io.IOException;
import java.text.ParseException;

public class Server {

	private Bank bank;
	private boolean approvalCode;
	private String approvalCode_CardNumber_AccNumber[];

	public Server() throws IOException, ParseException {
		bank =new Bank();
	}

	public boolean getApproval(int pin, long CardNumber, String acc_type) throws IOException, ParseException {
		setApproval(pin, CardNumber,acc_type);
		return approvalCode;
	}

	public String[] getApproval()  {

		return (new String[]{approvalCode+"",approvalCode_CardNumber_AccNumber[0],approvalCode_CardNumber_AccNumber[1],approvalCode_CardNumber_AccNumber[2]});
	}
	public boolean getApproval(double amount, String type, String acc_type) throws IOException, ParseException {
		setApproval(amount, type, acc_type);
		return approvalCode;
	}
	public void setApproval(int pin, long CardNumber, String acc_type) throws IOException, ParseException {

		approvalCode= bank.manages(pin, CardNumber, acc_type);

	}

	public void setApproval(String fname, String lname, String dob, String address, int pin_1, boolean creditCard) throws IOException, ParseException {

 		approvalCode_CardNumber_AccNumber= bank.manages(fname, lname, dob, address, pin_1, creditCard);

		approvalCode=true;
	}
	public void setApproval(double amount, String type, String acc_type) throws IOException, ParseException {

		approvalCode= bank.manages(amount, type,acc_type);

	}

	public void print(int pin, long CardNumber) throws IOException, ParseException {
		bank.print(pin, CardNumber);
	}


}