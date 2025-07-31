public class Balance {
    private double balance;

    public void setBalance(double amount){
        if(amount>0){
            balance=amount;
        }
        else {
            System.out.println("invalid amount");
        }
    }

    public double getBalance(){
        return balance;
    }

    public static void main(String[] args) {
        Balance b = new Balance();
        b.setBalance(100.53);
        System.out.println(b.getBalance());

    }
}
