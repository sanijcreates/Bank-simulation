
import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
public class driver {

    public static void main(String[] args) throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        String ans="";
        long amount=0;
        boolean b=false;
        ATM atm = new ATM();

        do {
            System.out.print(
                    "\nWelcome valued Customer,\n" +
                    "-------------------------\n" +
                    "1. Open account\n" +
                    "2. Deposit\n" +
                    "3. Withdraw\n" +
                    "4. Print balance\n" +
                     "-------------------------\n" +
                    "Enter your option-->1, or 2, or 3, or 4:>"
            );
            ans = input.nextLine();

            switch (ans) {
                //Opening new Account
                case "1":
                    System.out.print("\n==========================\nOpening Account Operation \n==========================\n");

                    System.out.print("Enter your first name:");
                    String fname= input.nextLine();
                    System.out.print("Enter your last name:");
                    String lname= input.nextLine();
                    String dob="";
                    do {
                        System.out.print("Enter your birthdate (mm/dd/YYYY):");
                        dob= input.nextLine();
                        String dobSplit[]=dob.split("/");
                        if(dob.length()<10 && dobSplit[0].length()==2 && Integer.parseInt(dobSplit[0])<=12
                                  && dobSplit[1].length()==2 && dobSplit[2].length()==4 )
                            System.out.println("Invalid birthdate entry, please retry!");
                    } while (dob.length()<10);
                    System.out.print("Enter your home address:");
                    String address= input.nextLine();
                    int pin_1 =0;
                    int pin_2 =1;
                    do {
                        System.out.print("Enter your 4-digit PIN :");
                        pin_1 = Integer.parseInt(PasswordField.readPin(input));
                        System.out.print("Confirm your 4-digit PIN :");
                        pin_2 = Integer.parseInt(PasswordField.readPin(input));
                        if(pin_1!=pin_2 || pin_1+"".length()<4 || pin_2+"".length()<4){
                            System.out.println("PIN Confirmation failed, please retry");
                        }else
                            System.out.println("PIN confirmation passed");
                    }while(pin_1!=pin_2);
                    String wantCreditCard;
                    System.out.println("Would you like a Credit Card(y/n)?");
                    wantCreditCard = input.nextLine();
                    boolean creditCard = false;
                    if (wantCreditCard.equalsIgnoreCase("y")) {
                        creditCard = true;
                    }
                    String []bb =atm.transaction(fname, lname, dob, address, pin_1, creditCard);
                   System.out.println(bb.length);
                    if (Boolean.parseBoolean(bb[0])) {
                        System.out.println("Account successfully opened!");
                        System.out.println("Your account number:"+bb[1]);
                        System.out.println("Your card number:"+bb[2]);
                        if (creditCard) {

                            System.out.println("Your Credit Card Number:" + bb[3]);
                        }
                    }else
                        System.out.println("Opening account transaction failed!");
                    break;

                case "2": //Deposit
                    System.out.print("\n===================\nDeposit Operation \n===================\n");

                    int option = 0;

                    String acc_type="";
                    do {
                        System.out.print(
                                "\nSelect account type,\n" +
                                        "-------------------------\n" +
                                        "1. Checking\n" +
                                        "2. Saving\n" +
                                        "3. Cancel\n" +
                                        "Enter your option-->1, or 2 or 3:>"
                        );
                        option = Integer.parseInt(input.nextLine());
                        if (option == 1) {
                            acc_type = "checking";
                            break;
                        } else if (option == 2) {
                            acc_type = "savings";
                            break;
                        }else if (option == 3){
                            b=false;
                            break;
                        } else
                            System.out.println("Invalid option entry, retry");
                    } while (true);


                    System.out.print("Please insert your card number>");

                    transaction( b, atm, input, amount, "deposit",acc_type, option);
                    break;

                case "3":

                    System.out.print("\n===================\nWithdraw Operation \n===================\n" +
                            "please insert your card number>");
                    transaction( b, atm, input, amount, "withdraw","checking",0);
                    break;
                default:

                    System.out.print("\n===================\nPrint Operation \n===================\n" +
                            "please insert your card number>");
                    transaction( b, atm, input, amount, "print","print",0);
            }

        } while (!ans.strip().equalsIgnoreCase( "q"));



    }

    static void transaction(boolean b, ATM atm, Scanner input, long amount, String type, String acc_type, int option) throws IOException, ParseException {

        String pinCardNumber[] = PasswordField.readPinCardNumber("Enter your card pin:", input);
        b =atm.identifies(Integer.parseInt(pinCardNumber[0]),Long.parseLong(pinCardNumber[1]), acc_type);

        if(b && type=="print") {
            atm.print(Integer.parseInt(pinCardNumber[0]),Long.parseLong(pinCardNumber[1]));
            System.out.println("\nPrint succeessfully");
        }
        else if (b==true){
            System.out.print("Enter amount:");
            amount=Long.parseLong(input.nextLine());
            b =atm.transaction(amount,type,acc_type); //for atm withdraw operation
            if(b && type=="withdraw") {
                System.out.println("\nTransaction succeessfully completed, take your cash!");
            }
            else if(b && type=="deposit") {
                System.out.println("\nDeposit transaction succeessfully completed!");
            }else
                System.out.println("\nTransaction failed, retry!");

        } else{
            if(option==3)
                System.out.println("Operation cancelled");
            else
                System.out.println("Invalid PIN or CardNumber, please retry");
        }
    }
}

class PasswordField {

    public static String[] readPinCardNumber (String prompt, Scanner sc)  {
        StringBuilder sb = new StringBuilder();
        Console console = System.console();
        if (console==null){
            System.out.println("\nprogram need to be ran on command line");
            System.exit(0);
        }
         long cardNumber = Long.parseLong(sc.nextLine());


        char [] pass= console.readPassword(prompt);

        return   (new String[] {sb.append(pass).toString(), Long.toString (cardNumber)});

    }
// this function returns a String.

    public static String readPin (Scanner sc)  {
        StringBuilder sb = new StringBuilder();
//            int pin = sc.nextInt();
            Console console = System.console();

        if (console==null){
            System.out.println("\nprogram need to be ran on command line");
            System.exit(0);
        }



        char [] pass= console.readPassword();

        return   sb.append(pass).toString();

    }


}

