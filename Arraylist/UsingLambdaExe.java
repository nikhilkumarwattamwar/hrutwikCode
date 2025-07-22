package Collection.Arraylist;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UsingLambdaExe {
   static ArrayList<Integer> listWithDup = new ArrayList<>(Arrays.asList(1, 2, 5,3, 2, 3, 4, 5, 1, 6));

    public static void main(String[] args) {
//        usingLambda();
        getOddSquare();
        sorted();
    }

    static void usingLambda(){
        ArrayList<Integer> a1=new ArrayList<>();
        IntStream.rangeClosed(1,30).forEach(a1::add);
        a1.stream().filter(n-> n%2==0).map(a->a*10).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("List"+a1);
    }
    static void getOddSquare(){
        ArrayList<Integer> a1=new ArrayList<>();
        IntStream.rangeClosed(10,20).forEach(a1::add);
        a1.stream().filter(n->n%2==1).map(n->n*n).collect(Collectors.toList()).forEach(System.out::println);
      int value=  a1.stream().reduce(0,(a,b)->(a+b));
        System.out.println(value);
        int res=a1.stream().mapToInt(Integer::intValue).sum();
        System.out.println(res);
    }
    static  void sorted(){

    List<Integer> sor=listWithDup.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println(sor);

    }
}
