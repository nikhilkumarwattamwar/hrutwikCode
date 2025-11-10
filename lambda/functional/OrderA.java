package lambda.functional;

import java.util.Scanner;
import java.util.UUID;
import java.util.function.Supplier;

public class OrderA {
    String name;
    String item;

    OrderA(String name,String item){
        this.name=name;
        this.item=item;
    }
}

class GenerateOrder{
    public static void main(String[] args) {
        Supplier<String> orderSupplier=()-> UUID.randomUUID().toString();

        Scanner sc= new Scanner(System.in);

        for (int i=0;i<5;i++){
            System.out.print("Enter Customer Name: ");
            String name= sc.nextLine();
            System.out.print("Enter Product Name: ");
            String item= sc.nextLine();

            OrderA order = new OrderA(name,item);

            String id=orderSupplier.get();

            System.out.println("Customer Name: "+order.name+"   "+"Product Name: "+order.item);
            System.out.println("New Order ID: ORDER-"+id);
        }
    }
}
