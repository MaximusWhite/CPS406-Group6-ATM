package BackEnd;

/*
* Account class that holds all the
*/
public class Account implements Transaction {
    private double balance;
    private String IDNum;
    private String pin;
    private String userName;

    /*
     * Account constructor to initialize the Account ID, Pin, Name, and Balance
     */
    public Account(String ID, String pin, String uName, double balance) {
        this.balance = balance;
        this.IDNum = ID;
        this.userName = uName;
        this.pin = pin;
    }

    /*
     * Returns Balance of account
     */
    public double getBalance() {
        return balance;
    }

    /*
     * Deposits amount into account balance
     */
    public void deposit(double amount) { balance += amount; }

    /*
     * Withdraws Amount from account balance
     */
    public boolean withdraw(double amount) {
        if(amount < balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
    /*
     * returns the PIN
     */
    public String getPIN() {
        return pin;
    }

    /*
     * returns the properties of the account
     */
    public String toString() {
        return IDNum + "/" + pin + "/" + userName + "/" + String.format("%.2f", balance);
    }

    /*
     * returns the user name specifically
     */
    public String getUserName() {
        return userName;
    }
}
