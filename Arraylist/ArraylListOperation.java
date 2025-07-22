package Collection.Arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class ArraylListOperation {
    public static void main(String[] args) {
//        ArrayList<Integer> a=new ArrayList<>();
//        for (int i=0;i<23;i++){
//            a.add(i);
//        }
//        for (int i=0;i<a.size();i++){
//            System.out.print("\t"+a.get(i));
//        }
//        stringArrayList();
        usingListIterator();
    }

    static void usingListIterator(){
        ArrayList<Integer> a=new ArrayList<>();
        for (int i=1;i<10;i++){
            a.add(i*10);
        }

        ListIterator<Integer> a1=a.listIterator();
        while (a1.hasNext()){

            System.out.print("\t"+a1.next());
        }
    }
   static void stringArrayList(){
        ArrayList<String> a2=new ArrayList<>();
        a2.add("hk");
        a2.add("JK");
        a2.add("pk");
        a2.add("Apple");
        a2.add("Banana");
        a2.add("Mango");
        a2.add("Orange");

//        a2.set(2,"JackFruit");
//       for (int i=0;i< a2.size();i++){
//           System.out.print("\t"+a2.get(i));
//       }
       System.out.println();
       ArrayList<String> a3= (ArrayList<String>) a2.clone();
//       System.out.println(a3);
//       System.out.println(a2);
        for (int i=0;i<a2.size();i++){
            System.out.print(i+" \t");
            System.out.print(a2.get(i));
            System.out.println();

        }
       System.out.println(a2.size()+" size of an Arraylist");

       ArrayList<String> removeEle=new ArrayList<>();
//       for (int i = a2.size() - 2; i > 0; i--) {
//           if (i % 2 == 0) {
//               removeEle.add(a2.remove(i));
//           }
//       }
       for (int i=a2.size()-1;i>0;i--){
           if(i%2==1){
               removeEle.add(a2.remove(i));
           }
       }

           System.out.println(a2);
       System.out.println(removeEle);

//       a2.clear();
   }
}
