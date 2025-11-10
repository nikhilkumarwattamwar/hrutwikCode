package lambda.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class Shopping {
    String name;
    String product;
    Shopping(String name,String product){
     this.name=name;
     this.product=product;
    }
}
class Cart{
    public static void main(String[] args) {
        HashMap<String,Double> map = new HashMap<>();
        map.put("Laptop",50000.0);
        map.put("Headphone",1500.0);
        map.put("Mobile",60000.0);
        map.put("Charger",1000.0);

        System.out.println("===Available Products===");
        map.forEach((k,v)-> System.out.println(k+", "+v));

        Scanner sc= new Scanner(System.in);
        System.out.print("Select the Product: ");
        String select= sc.nextLine();

        if(map.containsKey(select)){

            System.out.print("Enter the quantity: ");
            int quantity= sc.nextInt();

            double price=map.get(select);

            BiFunction<Integer,Double,Double> total=(a,b)->a*b;

            System.out.println("Selected Product"+select+", Price:"+ map.get(select));
        }
    }


}
