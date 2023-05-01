import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ATM_TransactionLog {

	private String TID;
	private String date;
	private String transaction_type;
	private double amount;
	private double balance;
	private double savings_augment;

	public void setTransaction(double amount, double openingBalance, double closeBalance, long acc_no, double savings_augment, String t_type) throws IOException {
		FileWriter myWriter = new FileWriter("transaction_log.txt",true);
		this.TID =(char)('A' + ((int)(Math.random()* (26)))) + ((int)(Math.random()* (5000)))*1000 + "";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		date= dtf.format(now);
		this.balance=closeBalance;
		this.amount=amount;
		this.savings_augment=savings_augment;
		this.transaction_type= t_type;
		myWriter.write(this.TID +"\t" + date + "\t" + acc_no + "\t" + openingBalance + "\t" + this.balance
				+ "\t" + this.amount + "\t" + savings_augment + "\t" + this.transaction_type +System.lineSeparator());
		myWriter.close();

	}

	public void updateAccountBalance(long acc_no, String acc_type ,String type) throws IOException {

		Scanner sc = new Scanner(new File("customers_accounts.txt"));
		//String record = reader.readLine();
		StringBuffer buffer = new StringBuffer();
		//Reading lines of the file and appending them to StringBuffer
		while (sc.hasNextLine()) {

			String record = sc.nextLine();
			String fields[] = record.split("\t");
			long _acc_no =Long.parseLong (fields[7]);
			if (acc_no == _acc_no) {
				if (acc_type=="checking" && type=="withdraw")
						buffer.append(fields[0]+ "\t" + fields[1]+ "\t" + fields[2] + "\t" + fields[3]+
								   "\t" +  fields[4]+ "\t" +  this.balance+ "\t" + fields[6]+ "\t" + _acc_no +
									System.lineSeparator());
				else if (acc_type=="savings" && type=="withdraw")
					buffer.append(fields[0]+ "\t" + fields[1]+ "\t" + fields[2] + "\t" + fields[3]+
							"\t" +  fields[4]+ "\t" + 0.0 + "\t" + (Double.parseDouble(fields[6])+savings_augment) + "\t" +  _acc_no +
							System.lineSeparator());

				else if (acc_type=="checking" && type=="deposit")
					buffer.append(fields[0]+ "\t" + fields[1]+ "\t" + fields[2] + "\t" + fields[3]+
							"\t" +  fields[4]+ "\t" + this.balance  + "\t" + Double.parseDouble(fields[6])+ "\t" +  _acc_no +
							System.lineSeparator());
				else if (acc_type=="savings" && type=="deposit")
					buffer.append(fields[0]+ "\t" + fields[1]+ "\t" + fields[2] + "\t" + fields[3]+
							"\t" +  fields[4]+ "\t" + Double.parseDouble(fields[5]) + "\t" + this.balance + "\t" +  _acc_no +
							System.lineSeparator());

			}else
				buffer.append(record+System.lineSeparator());
		}
		String fileContents = buffer.toString();
		FileWriter writer = new FileWriter("customers_accounts.txt");
		writer.append(fileContents);
		writer.flush();

	}
	public void getTransaction() {


	}

}