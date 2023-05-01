import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Checking extends Account {

	private long account_no;
	boolean verify;
	private double balance;

	public void withdraw() {

	}
	public Checking(){

	}
	public void setCredential(int pin, long cardNumber, Customer customer, String type) throws IOException, ParseException {

		customer = new Customer(Checking.this, null);

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
				setBalance(Double.parseDouble(fields[5]));
				this.account_no=Long.parseLong(fields[7]);
				setAccount(this.account_no, getBalance(),Double.parseDouble(fields[6]));
				if (type=="checking")
					System.out.println("Checking Account Balance:" + super.c_balance);
				setVerify(true);
				customer.setCredential(pin,cardNumber,DOB, address);
				break;
			}else
				customer.setCredential(0,0,null, "");
			record = reader.readLine();
		}
	}


	public void print(int pin, long cardNumber) throws IOException, ParseException {

		//customer = new Customer(Checking.this, null);

		BufferedReader reader = new BufferedReader(new FileReader("customers_accounts.txt"));
		String record = reader.readLine();
		String fields[] = record.split("\t");
		while(record!=null) {
			fields = record.split("\t");
			int _pin = Integer.parseInt(fields[4]);
			long _cardNumber = Long.parseLong(fields[3]);
			if (pin == _pin && cardNumber == _cardNumber) {
				//Date DOB = new SimpleDateFormat("dd/mm/yyyy").parse(fields[1]);
				this.account_no=Long.parseLong(fields[7]);

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");
					LocalDateTime now = LocalDateTime.now();
					String dateTime= dtf.format(now);
					System.out.println("----------------------------");
					System.out.println("Accounts Balance & Details" );
					System.out.println("Date:" + dateTime);
					System.out.println("-----------------------------");
					System.out.println("Name:" + fields[0]);
					System.out.println("Adddress:"+fields[2]);
					System.out.println("Account number:"+ fields[7]);
					System.out.println("Checking account balance:" + fields[5]);
					System.out.println("Savings account balance:" + fields[6]);
					System.out.println("------------------------------");
					System.out.println("Total account balance:" + (Double.parseDouble(fields[5])+ Double.parseDouble(fields[6])));
					System.out.println("-------------------------------");

				}
				break;
			}
			record = reader.readLine();
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