import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
public class Customer {

	private String name;
	private Date DOB;
	private String address;
	private long cardNumber;
	private int pin;

	private Checking checking;
	private Savings savings;
	private ATM atm;

	public Customer() {
	}

	public Customer(Checking checking, Savings savings) throws IOException, ParseException {

		this.checking = checking;
		this.savings=savings;

	}

	public boolean verifyPassword(int pin, long cardNumber, String acc_type) throws IOException, ParseException {
		this.pin = pin;
		this.cardNumber = cardNumber;
		if (acc_type == "checking")
			checking.setCredential(this.pin, this.cardNumber, Customer.this, acc_type);
		else
			savings.setCredential(this.pin, this.cardNumber, Customer.this, acc_type);

		if (acc_type == "checking") {
			if (checking.getVerify()) {
				return true;
			} else {
				return false;
			}
		} else {
			if (savings.getVerify()) {
				return true;
			} else {
				return false;
			}
		}
	}
	public void ATM_transaction(int pin, long cardNumber, String acc_type) throws IOException, ParseException {
		verifyPassword(pin,cardNumber, acc_type);
	}

	public  void setCredential(int p, long c, Date d, String addr ){
		this.pin=p;
		this.cardNumber=c;
		this.DOB=d;
		this.address=addr;

	}

}