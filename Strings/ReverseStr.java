import java.util.Scanner;

public class ReverseStr {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.print("Enter the string :");
        String str= input.nextLine().toLowerCase();
        String rev= "";
        for (int i= str.length()-1; i>=0;i--){

                rev = rev + str.charAt(i);
        }
        System.out.println(rev);

    }
}
