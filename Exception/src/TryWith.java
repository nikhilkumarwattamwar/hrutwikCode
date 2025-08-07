import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class TryWith {
    public static void main(String[] args)  {
        try(Scanner scanner = new Scanner(new File("example.txt"))){
                while (scanner.hasNext()){
                    System.out.println(scanner.nextLine());
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
