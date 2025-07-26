//package CustomException;
//
//public class ATM_Withdrawal {
//
//    static int balace=1000;
//    public static void  withdraw(int amount) throws InSufficientMoney{
//        if(amount>balace){
//            throw new InSufficientMoney("Incufficient balance");
//        }
//        if (amount<0){
//            throw new NegativAmountException();
//        }
//        balace-=amount;
//        System.out.println("After withdrawing the Amount "+balace);
//
//    }
//    public static void main(String[] args) {
//        try {
//            withdraw(1101);
//        }catch (Exception e){
//            System.out.println( e.getMessage());
//        }
//        try{
//            withdraw(-1);
//        }catch (Exception e){
//            System.out.println( e.getMessage());
//        }
//        try{
//            withdraw(124);
//        }catch (Exception e){
//            System.out.println( e.getMessage());
//        }
//
//    }
//}
//class InSufficientMoney extends Exception{
//    public InSufficientMoney(String s){
//        super(s);
//    }
//}
//class NegativAmountException extends RuntimeException{
//    @Override
//    public String getMessage() {
//        return "Insufficient Amount ";
//    }
//}