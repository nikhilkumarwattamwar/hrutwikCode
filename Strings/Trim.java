import java.util.Scanner;

public class Trim {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your country name :");
//        String name = sc.nextLine(); here if we write city name in capital letter or with including blank spaces
//        it will print else msg to overcome this .tolowercase method and trim method used to remove case sensitivity and blanck space
        String name = sc.nextLine().toLowerCase().trim();
        //trim method is used to remove blank spaces from start and end of the string and not middle of the string
        if (name.equals("india")) {
            System.out.println("Hello India...");
        }
        if (name.equals("japan")) {
                System.out.println("Hello Japan...");
            }

        if (name.equals("china")) {
                    System.out.println("Hello China...");
                }
        else {
            System.out.println("Enter valid country name");
        }

        System.out.println("---X---");
        String p= " Pratiksha Bhukan";
        System.out.println(p.trim().length());
            }
        }


