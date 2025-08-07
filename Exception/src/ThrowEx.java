import java.io.IOException;
import java.util.Scanner;

public class ThrowEx {
    static void checkEligibilityProcess(int age){
        if(age<18){
            throw new ArithmeticException("Not eligible for voting");
        }else {
            System.out.println("Eligible for voting");
        }
    }

    static void checkData(int data) throws IOException, Exception{
        if(data<100){
            throw new IOException("issue with the data");
        }
        if(data<100){
            throw new Exception("issue with data");
        }
        else {
            System.out.println("data is correct");
        }
    }

    public static void main(String[] args) {
        Scanner in= new Scanner(System.in);
        System.out.println("Enter your age:");
        int age=in.nextInt();
        ThrowEx.checkEligibilityProcess(age);

        }

}