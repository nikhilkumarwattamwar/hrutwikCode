public class CustomeEx {
    static void checkamount(int amount){
        if(amount<=0){
            throw new withdrawamount("cannot withdraw the amount");
        }
        else {
            System.out.println("Withdrawl amount is :"+amount);
        }
    }

    public static void main(String[] args) {
//        checkamount(0);  //exception
        checkamount(100);
    }
    }

    class withdrawamount extends RuntimeException{
    withdrawamount(String msg){
        super(msg);   //to make our description available to the throwable class which then  default handler
        // can get through printStackTrace and show it on console
    }
}

