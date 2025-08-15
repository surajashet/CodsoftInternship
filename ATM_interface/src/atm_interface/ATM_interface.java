import java.util.Scanner;

class BankAcc {
    private double bal;

    public BankAcc(double startBal) {
        bal = startBal;
    }

    public double getBal() {
        return bal;
    }

    public void depositAmt(double amt) {
        bal += amt;
    }

    public boolean withdrawAmt(double amt) {
        if (amt > 0 && amt <= bal) {
            bal -= amt;
            return true;
        }
        return false;
    }
}

class ATMApp {
    private BankAcc acc;
    private Scanner sc;

    public ATMApp(BankAcc acc) {
        this.acc = acc;
        sc = new Scanner(System.in);
    }

    public void menu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter option: ");
    }

    public void run() {
        boolean active = true;
        while (active) {
            menu();
            int opt = sc.nextInt();

            if (opt == 1) {
                System.out.println("Balance: Rs. " + acc.getBal());
            } else if (opt == 2) {
                System.out.print("Enter deposit amount: ");
                double dep = sc.nextDouble();
                if (dep > 0) {
                    acc.depositAmt(dep);
                    System.out.println("Deposit done.");
                } else {
                    System.out.println("Invalid amount.");
                }
            } else if (opt == 3) {
                System.out.print("Enter withdraw amount: ");
                double wd = sc.nextDouble();
                if (acc.withdrawAmt(wd)) {
                    System.out.println("Withdrawal done.");
                } else {
                    System.out.println("Not enough balance or invalid amount.");
                }
            } else if (opt == 4) {
                System.out.println("Thanks for using ATM.");
                active = false;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}

public class ATM_interface {
    public static void main(String[] args) {
        BankAcc myAcc = new BankAcc(25000);
        ATMApp atm = new ATMApp(myAcc);
        atm.run();
    }
}

