import java.util.Scanner;

class BankAccount {
    private double balance;

    
    public BankAccount(double initialBalance) {
        if (initialBalance > 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0;
            System.out.println("Initial balance must be positive. Defaulting to 0.");
        }
    }

   
    public double getBalance() {
        return balance;
    }

    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + ". Current balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew " + amount + ". Current balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance for the withdrawal.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }
}

class ATM {
    private BankAccount account;

   
    public ATM(BankAccount account) {
        this.account = account;
    }

    
    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Please choose an option: ");
    }

    
    public void processTransaction(int option) {
        Scanner scanner = new Scanner(System.in);

        switch (option) {
            case 1:
                System.out.print("Enter amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                account.withdraw(withdrawAmount);
                break;
            case 2:
                System.out.print("Enter amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                break;
            case 3:
                System.out.println("Current balance: " + account.getBalance());
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        
        BankAccount userAccount = new BankAccount(1000);

        
        ATM atm = new ATM(userAccount);

        Scanner scanner = new Scanner(System.in);

        
        int option;
        do {
            atm.displayMenu();
            option = scanner.nextInt();
            atm.processTransaction(option);
        } while (option != 4); 
    }
}
