package GitFolder.hrutwikCode.JAVA_8;

import java.util.*;
import java.util.stream.Collectors;

public class StreamExamples {
    public static void main(String[] args) {
        List<String> list=Arrays.asList("Alice","john","Vipul","Vipul","Pune","m", "Mumbai", "Nashik", "Nagpur");
        List<Integer> nums = List.of(5, 4,6,6,12, 3, 18, 7);

       List<String> res= list.stream().map((a)->a.toUpperCase()).collect(Collectors.toList());
        System.out.println(res);
        res.stream().map(a->a.substring(0,1).toLowerCase()+a.substring(1)).forEach(System.out::println);
        System.out.println("===============================================================================000");

        nums.stream().filter(a->a>10).forEach(System.out::println);
        System.out.println("Even Numbers :");
        nums.stream().filter(n->n%2==0).forEach(System.out::println);
        System.out.println("Removing duplicate from List :");
        List<String>a=list.stream().distinct().collect(Collectors.toList());
        System.out.println(a);

        System.out.println("===============================================================================000");
        System.out.println("Sorting Interger in ascending Order");
//        nums.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        nums.stream().sorted().forEach(System.out::println);

        System.out.println("Convert list of strings to their lengths");
//        list.stream().map(b->b.length()).forEach(System.out::println);
        list.stream().map(String::length).forEach(System.out::println);

        System.out.println("===============================================================================000");
        System.out.println("Skip first 2 elements from a list");
        list.stream().skip(2).forEach(System.out::println);

        System.out.println(" Limit list to first 3 elements");
        nums.stream().limit(3).forEach(System.out::println);

        System.out.println("===============================================================================000");
        System.out.println("Find first element starting with \"A\"");

       list.stream().map(name-> name.substring(0,1).toUpperCase()+name.substring(1)).filter(name->name.startsWith("M")).forEach(System.out::println);


    }
}
