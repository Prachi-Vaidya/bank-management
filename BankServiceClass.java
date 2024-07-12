import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class BankServiceClass {
    public static void main(String[] args)  {
        BankControllerClass conn =new BankControllerClass();
        String str = "y";
        String dbUrl = "jdbc:postgresql://localhost:5432/tadb";
        String dbUser = "postgres";
        String dbPassword = "postgres";
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Connection connection = conn.connect_To_DB(dbUrl, dbUser, dbPassword);
        String cName;
        String branch;
        String acType;
        double balance;
        int acNumber;
        int choice;
        conn.createTable(connection);

        try {
            do {
                System.out.println("Choose operation: ");
                System.out.println("1. Create Customer");
                System.out.println("2. Check Balance");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Find customer by its account type");
                System.out.println("5. Deposit Money");
                System.out.println("6. Change Branch");
                System.out.println("7. Print All Customers");
                System.out.println("8. check if account already exsist:");
                System.out.println("9. transfer money");
                choice = sc1.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter name of customer : ");
                        cName = sc1.next();
                        System.out.println("Enter branch of customer : ");
                        branch = sc2.next();
                        System.out.println("Enter type of account : ");
                        acType = sc3.next();
                        System.out.println("Enter the balance: ");
                        balance = sc4.nextDouble();
                        conn.createCustomer(connection, cName, branch, acType, balance);
                        break;

                    }

                    case 2 -> {
                        System.out.println("Enter the account number to check balance : ");
                        acNumber = sc4.nextInt();
                        conn.checkBalance(connection, acNumber);
                        break;
                    }
                    case 3 -> {
                        System.out.println("Enter the account number : ");
                        acNumber = sc4.nextInt();
                        System.out.println("Enter withdrawal Amount : ");
                        double withdrawAmount = sc4.nextDouble();
                        conn.WithdrawMoney(connection, acNumber, withdrawAmount);
                        break;
                    }
                    case 4 -> {
                        System.out.println("Enter type of account : ");
                        acType = sc3.next();
                        conn.accountType(connection, acType);
                        break;
                    }
                    case 5 -> {
                        System.out.println("Enter the account number : ");
                        acNumber = sc4.nextInt();
                        System.out.println("Enter Deposit Amount : ");
                        double depositAmount = sc4.nextDouble();
                        conn.depositMoney(connection, acNumber, depositAmount);
                        break;
                    }
                    case 6 -> {
                        System.out.println("Enter name of customer : ");
                        cName = sc1.next();
                        System.out.println("Enter New Branch : ");
                        String newBranch = sc2.next();
                        conn.changeBranch(connection, cName, newBranch);
                        break;
                    }
                    case 7 -> {
                        conn.printAllCustomer(connection);
                        break;
                    }
                    case 8 -> {
                        System.out.println("Enter name of customer : ");
                        cName = sc1.next();
                        System.out.println("Enter type : ");
                        acType = sc2.next();
                        conn.checkUser(connection, cName, acType);
                    }
                    case 9 -> {
                        System.out.println("Enter your account number : ");
                        acNumber = sc4.nextInt();
                        System.out.println("Enter transfer Amount : ");
                        double transfermoney = sc4.nextDouble();
                        System.out.println("Enter name of customer to transfer: ");
                        cName = sc1.next();
                        conn.transfer(connection, acNumber, transfermoney, cName);
                    }
                    default -> {
                        System.out.println("Wrong choice has been made...");
                    }
                }
                System.out.println("Do you want to continue ???");
                str = sc.next();

            } while (Objects.equals(str, "y") || Objects.equals(str, "Y"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
