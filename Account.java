import java.io.FileWriter;
import java.io.IOException;


public class Account {

	private long number;
	String creditNo;
	public double c_balance;
	public double s_balance;
	String cardNumber;
	private ATM_TransactionLog transactionLog;

	public Account() {

		transactionLog = new ATM_TransactionLog();

	}


	public void setAccount(long n, double c_b, double s_b) {
		this.number=n;
		this.c_balance=c_b;
		this.s_balance=s_b;


	}

	public void setAccount(String fname, String lname, String dob, String address, int pin_1, boolean creditCard) throws IOException {

		FileWriter myWriter = new FileWriter("customers_accounts.txt",true);
		this. cardNumber = ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) + ""+ ((int)(Math.random()* (10))) +
				     "" + ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) +
				     "" + ((int)(Math.random()* (10))) + "" +  ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) +
				     "" + ((int)(Math.random()* (10))) + "" +  ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) +
				     "" + ((int)(Math.random()* (10))) + "" +  ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) ;

		this.number= Long.parseLong(((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) + ""+ ((int)(Math.random()* (10))) +
				"" + ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) +
				"" + ((int)(Math.random()* (10))) + "" +  ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) +
				"" + ((int)(Math.random()* (10))));

		this.c_balance=0;
		this.s_balance=0;
		if(creditCard) {
			System.out.println("making credit card");
			this. creditNo = ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) + ""+ ((int)(Math.random()* (10))) +
					"" + ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) +
					"" + ((int)(Math.random()* (10))) + "" +  ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) +
					"" + ((int)(Math.random()* (10))) + "" +  ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) +
					"" + ((int)(Math.random()* (10))) + "" +  ((int)(Math.random()* (10))) + "" + ((int)(Math.random()* (10))) ;
		} else {
			this.creditNo = this.cardNumber;
		}

		myWriter.write(fname+" " +lname +"\t" + dob + "\t" + address  + "\t" + cardNumber +
				         "\t" + pin_1 + this.c_balance + "\t" + this.s_balance + "\t" +
				          this.number + "\t" + creditNo + System.lineSeparator());
		myWriter.close();

	}


	public String [] getAccount(){
		System.out.println("credit" + this.creditNo);
	   return (new String[]{this.number+"",this.cardNumber, creditNo});
	}

	public boolean deposit(double amount, String acc_type) throws IOException {
		boolean b =createTransaction("deposit", amount, acc_type);
		if(b)
			return b;
		return  false;
	}

	public boolean withdraw(double amount) throws IOException {
		if (this.c_balance-amount>=0 ){
			boolean b =createTransaction("withdraw", amount, "checking");
			if(b)
				return  true;
			else
				this.c_balance=this.c_balance+amount;

		}else if(this.c_balance+s_balance-amount>=0)
			return createTransaction("withdraw", amount, "savings");

		return false;
	}

	public boolean createTransaction(String t_type, double amount, String acc_type) throws IOException {

			if(t_type=="withdraw" && acc_type=="checking") {
				transactionLog.setTransaction(amount, this.c_balance, this.c_balance - amount, this.number,0, t_type);
				this.c_balance = this.c_balance - amount;
			}
			else if (t_type=="withdraw" && acc_type == "savings"){
				transactionLog.setTransaction(amount, this.c_balance,0,this.number, this.c_balance - amount, t_type);
				this.c_balance = 0;
			}
			else if (t_type == "deposit" && acc_type == "savings")
			{
			transactionLog.setTransaction(amount, this.s_balance,this.s_balance+amount,this.number,0, t_type);
			this.s_balance = this.s_balance + amount;
		    }
			else
		    {
				transactionLog.setTransaction(amount, this.c_balance,this.c_balance+amount,this.number,0, t_type);
				this.c_balance = this.c_balance + amount;
			}

			transactionLog.updateAccountBalance(this.number, acc_type,t_type);
		return true;

	}

}