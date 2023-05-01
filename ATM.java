import java.io.IOException;
import java.text.ParseException;

public class ATM {

	private String location;
	private String managedBy;
	private int TID;
	private Server server;

	public ATM() throws IOException, ParseException {

		server = new Server();

	}

	public boolean identifies(int pin, long cardNumber, String acc_type) throws IOException, ParseException {

		return server.getApproval(pin, cardNumber, acc_type);
	}

	public boolean transaction(double amount, String type, String acc_type) throws IOException, ParseException {
		if (type.equalsIgnoreCase("withdraw")){
			return server.getApproval(amount,type,acc_type);

		} else if (type.equalsIgnoreCase("open account")) {
			return server.getApproval(0.0,type,acc_type);

		} else if (type.equalsIgnoreCase("deposit")) {
			return server.getApproval(amount, type,acc_type);
		}

		return false;
	}

	public String [] transaction(String fname, String lname, String dob, String address, int pin_1, boolean creditCard) throws IOException, ParseException {

		server.setApproval(fname,lname,dob,address,pin_1, creditCard);

		return  server.getApproval();
	}
	public void print(int pin, long CardNumber) throws IOException, ParseException {
		server.print(pin, CardNumber);
	}

}