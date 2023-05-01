import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Savings extends Account {

	private long account_no;
	private double balance;
	boolean verify;
	public Savings() {

	}

	public void setCredential(int pin, long cardNumber, Customer customer, String type) throws IOException, ParseException {
		customer = new Customer(null, Savings.this);
		BufferedReader reader = new BufferedReader(new FileReader("customers_accounts.txt"));
		String record = reader.readLine();
		String fields[] = record.split("\t");
		while(record!=null) {
			fields = record.split("\t");
			int _pin = Integer.parseInt(fields[4]);
			long _cardNumber = Long.parseLong(fields[3]);
			if (pin == _pin && cardNumber == _cardNumber) {
				Date DOB = new SimpleDateFormat("dd/mm/yyyy").parse(fields[1]);
				String address = fields[2];

				setBalance(Double.parseDouble(fields[6]));
				this.account_no=Long.parseLong(fields[7]);
				setAccount(this.account_no, Double.parseDouble(fields[5]),getBalance());
				if (type=="savings")
					System.out.println("Savings Account Balance:" + super.s_balance);
				setVerify(true);
				customer.setCredential(pin,cardNumber,DOB, address);
				break;
			}else
				customer.setCredential(0,0,null, "");
			record = reader.readLine();
		}

	}

	public void setBalance(double balance){

		this.balance=balance;
	}

	public double  getBalance(){

		return this.balance;
	}
	public void setVerify(boolean verify){

		this.verify=verify;
	}

	public boolean getVerify(){

		return this.verify;
	}
}